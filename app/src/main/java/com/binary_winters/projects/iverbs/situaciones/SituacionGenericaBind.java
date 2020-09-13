package com.binary_winters.projects.iverbs.situaciones;


import android.content.Context;
import android.media.MediaPlayer;

import com.binary_winters.projects.iverbs.ContenidoEnEspGenericoHelper;
import com.binary_winters.projects.iverbs.NombresAudioParaSituaciones;
import com.binary_winters.projects.iverbs.R;
import com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class SituacionGenericaBind {
	private Map<String, ContenidoEnEspGenericoHelper> mapa;
    private Map<String, ContenidoEnEspGenericoHelper> mapaSoloEscritura;
	private Integer cantidadDeRespuestasCorrectas = 0;
	private List<ContenidoEnEspGenericoHelper> listaDePalabrasEnEspMalRespondidas;
	private Realm realm;
    private RealmResults<SituacionGenerica> listaDeSituacionGenericaFromBD;
    private List<ContenidoEnEspGenericoHelper> listaDeContenidoEnEspGenericoHelper = new ArrayList<>();
	private List<ContenidoEnEspGenericoHelper> palabrasAgregadasFavoritas = new ArrayList<>();

	public SituacionGenericaBind(String tipoDeSituacionGenerica){
		realm = Realm.getDefaultInstance();
        mapa = new LinkedHashMap<>();

//        situacionGenericaFromRealm = realm.where(SituacionGenerica.class).equalTo("tipoSituacion", descripcionTipo).findAll();
        listaDeSituacionGenericaFromBD = realm.where(SituacionGenerica.class).equalTo("tipoSituacion", tipoDeSituacionGenerica).findAll();
        if(listaDeSituacionGenericaFromBD != null && listaDeSituacionGenericaFromBD.isEmpty() == false){
            for(SituacionGenerica situacionGenerica : listaDeSituacionGenericaFromBD){
                ContenidoEnEspGenericoHelper contenidoEnEspGenericoHelper =
                        new ContenidoEnEspGenericoHelper(situacionGenerica.getPalabraEnEsp(), situacionGenerica.getMarkedWithStar(), situacionGenerica.getId());
                String textoIngles = situacionGenerica.getCadenaEnIngles();
                listaDeContenidoEnEspGenericoHelper.add(contenidoEnEspGenericoHelper);
                mapa.put(textoIngles, contenidoEnEspGenericoHelper);
            }
        }

        this.armarMapaConPalabrasSoloEscrituraOn();

		listaDePalabrasEnEspMalRespondidas = new ArrayList<ContenidoEnEspGenericoHelper>();

		for(ContenidoEnEspGenericoHelper palabra : listaDeContenidoEnEspGenericoHelper){
			if(palabra.getMarkedWithStar()){
				palabrasAgregadasFavoritas.add(palabra);
			}
		}
	}

    public void armarMapaConPalabrasSoloEscrituraOn(){
        mapaSoloEscritura = new LinkedHashMap<>();

        for (Map.Entry<String, ContenidoEnEspGenericoHelper> elementoDeMapa : mapa.entrySet()){
            String cadenaEnInglesAntigua = elementoDeMapa.getKey();
            String cadenaEnInglesNueva;

            String[] divisionesConGuion = cadenaEnInglesAntigua.split("-");
            cadenaEnInglesNueva = divisionesConGuion[0];

            mapaSoloEscritura.put(cadenaEnInglesNueva, elementoDeMapa.getValue());
        }
    }

    public ContenidoEnEspGenericoHelper generarPalabraRandom(Boolean isRadioButtonConTodos){
        Random generarRandom = new Random(System.currentTimeMillis());
        int indiceDeListaAleatorio;
        ContenidoEnEspGenericoHelper ContenidoEnEspGenericoHelperElegida;

        if(isRadioButtonConTodos){
            indiceDeListaAleatorio = generarRandom.nextInt(listaDeContenidoEnEspGenericoHelper.size());
            ContenidoEnEspGenericoHelperElegida = listaDeContenidoEnEspGenericoHelper.get(indiceDeListaAleatorio);
            listaDeContenidoEnEspGenericoHelper.remove(indiceDeListaAleatorio);
        } else{
            indiceDeListaAleatorio = generarRandom.nextInt(palabrasAgregadasFavoritas.size());
            ContenidoEnEspGenericoHelperElegida = this.palabrasAgregadasFavoritas.get(indiceDeListaAleatorio);
            palabrasAgregadasFavoritas.remove(indiceDeListaAleatorio);
        }

        return ContenidoEnEspGenericoHelperElegida;
	}

	public String buscarCadenaDeUsuarioEnMapa(String cadenaEscritaPorUsuario, ContenidoEnEspGenericoHelper palabraEnEspRandom,
											  Boolean isSoloEscrituraOn){
		ContenidoEnEspGenericoHelper palabraEnEspHalladaEnMapa;
        String cadenaDePalabraEnEspHalladoEnMapa;

        if(isSoloEscrituraOn == false){
            palabraEnEspHalladaEnMapa = mapa.get(cadenaEscritaPorUsuario);
        } else{
            palabraEnEspHalladaEnMapa = mapaSoloEscritura.get(cadenaEscritaPorUsuario);
        }

        if(palabraEnEspHalladaEnMapa != null){
			cadenaDePalabraEnEspHalladoEnMapa = palabraEnEspHalladaEnMapa.getPalabraEnEsp();
        } else{
			cadenaDePalabraEnEspHalladoEnMapa = "";
        }

		String respuesta = "";

		// El usuario ingresó una cadena que existe en el mapa pero quizá no sea la que se corresponda con la palabra en esp
		if(palabraEnEspRandom != null){
			// Si la cadena ingresada por el usuario coincide con la requerida.
			if(palabraEnEspRandom.getPalabraEnEsp().equals(cadenaDePalabraEnEspHalladoEnMapa)){
				cantidadDeRespuestasCorrectas++;

				if( listaDePalabrasEnEspMalRespondidas != null
						&& listaDePalabrasEnEspMalRespondidas.contains(palabraEnEspRandom) ){
					listaDePalabrasEnEspMalRespondidas.remove(palabraEnEspRandom);
				}

				respuesta = "RIGHT \uD83D\uDE42";
			} else{
                Boolean respuestaCorrectaHallada = Boolean.FALSE;

                // Busca en el mapa la traducción correspondiente al verbo en español traducido mal por el usuario
                for (Map.Entry<String, ContenidoEnEspGenericoHelper> elementoDeMapa : mapa.entrySet()){
                    if( elementoDeMapa.getValue().getPalabraEnEsp().equals( palabraEnEspRandom.getPalabraEnEsp() )
                            && respuestaCorrectaHallada.equals(Boolean.FALSE) ){
                        respuestaCorrectaHallada = Boolean.TRUE;

						if(listaDePalabrasEnEspMalRespondidas.contains(palabraEnEspRandom) == false){
							listaDePalabrasEnEspMalRespondidas.add(elementoDeMapa.getValue());
						}

                        respuesta = "WRONG \uD83D\uDE41 -  RIGHT ANSWER : " + elementoDeMapa.getKey();
                    }
                }
            }
		}
		return respuesta;
	}

	public Integer getCantidadDeRespuestasCorrectas() {
		return cantidadDeRespuestasCorrectas;
	}

	public void setCantidadDeRespuestasCorrectas(
			Integer cantidadDeRespuestasCorrectas) {
		this.cantidadDeRespuestasCorrectas = cantidadDeRespuestasCorrectas;
	}

	public List<ContenidoEnEspGenericoHelper> getListaDePalabrasEnEspMalRespondidas() {
		return listaDePalabrasEnEspMalRespondidas;
	}

	public void setListaDePalabrasEnEspMalRespondidas(
			List<ContenidoEnEspGenericoHelper> listaDePalabrasEnEspMalRespondidas) {
		this.listaDePalabrasEnEspMalRespondidas = listaDePalabrasEnEspMalRespondidas;
	}

	public Map<String, ContenidoEnEspGenericoHelper> getMapa() {
		return mapa;
	}

    public Integer darSizeDeListaTotalDePalabrasEnEsp(Boolean isRadioButtonConTodos){
        Integer cantidadDePalabras = null;
        if(isRadioButtonConTodos){
            cantidadDePalabras = listaDeContenidoEnEspGenericoHelper.size();
        } else{
            cantidadDePalabras = palabrasAgregadasFavoritas.size();
        }

        return cantidadDePalabras;
    }

    public Integer getCantidadPalabras(){
        return listaDeContenidoEnEspGenericoHelper.size();
    }

    public Integer getCantidadPalabrasFavoritas(){
        return palabrasAgregadasFavoritas.size();
    }

    public RealmResults<SituacionGenerica> getListaDeSituacionGenericaFromBD() {
        return listaDeSituacionGenericaFromBD;
    }

    public SituacionGenerica buscarSituacionGenericaSegunNombrePalabraEnEsp(String nombrePalabraEnEsp){
        Boolean resultado = Boolean.FALSE;

        int index = 0;
        SituacionGenerica situacionGenericaHallado = null;

        while(index < listaDeSituacionGenericaFromBD.size() && resultado.equals(Boolean.FALSE)){
            if(listaDeSituacionGenericaFromBD.get(index).getPalabraEnEsp().equals(nombrePalabraEnEsp)){
                situacionGenericaHallado = listaDeSituacionGenericaFromBD.get(index);
                resultado = Boolean.TRUE;
            }
            index++;
        }

        return situacionGenericaHallado;
    }

    public static MediaPlayer cargarAudioSegunVerbo(Context context, MediaPlayer mp, String nombreAudioDeSituacionGenerica){
        switch (nombreAudioDeSituacionGenerica) {
            case NombresAudioParaSituaciones.FOOD :
                mp = MediaPlayer.create(context, R.raw.food);
                break;
            case NombresAudioParaSituaciones.DRINK :
                mp = MediaPlayer.create(context, R.raw.drink);
                break;
            case NombresAudioParaSituaciones.ALCOHOLIC_DRINK :
                mp = MediaPlayer.create(context, R.raw.alcoholic_drink);
                break;
            case NombresAudioParaSituaciones.NON_ALCOHOLIC_DRINK :
                mp = MediaPlayer.create(context, R.raw.non_alcoholic_drink);
                break;
            case NombresAudioParaSituaciones.WATER :
                mp = MediaPlayer.create(context, R.raw.water);
                break;
            case NombresAudioParaSituaciones.MINERAL_WATER :
                mp = MediaPlayer.create(context, R.raw.mineral_water);
                break;
            case NombresAudioParaSituaciones.SOFT_DRINK :
                mp = MediaPlayer.create(context, R.raw.soft_drink);
                break;
            case NombresAudioParaSituaciones.WAITER :
                mp = MediaPlayer.create(context, R.raw.waiter);
                break;
            case NombresAudioParaSituaciones.WAITRESS :
                mp = MediaPlayer.create(context, R.raw.waitress);
                break;
            case NombresAudioParaSituaciones.CHEF :
                mp = MediaPlayer.create(context, R.raw.chef);
                break;
            case NombresAudioParaSituaciones.THE_MENU :
                mp = MediaPlayer.create(context, R.raw.the_menu);
                break;
            case NombresAudioParaSituaciones.THE_WINE_LIST :
                mp = MediaPlayer.create(context, R.raw.the_wine_list);
                break;
            case NombresAudioParaSituaciones.THE_BILL :
                mp = MediaPlayer.create(context, R.raw.the_bill);
                break;
            case NombresAudioParaSituaciones.THE_TIP :
                mp = MediaPlayer.create(context, R.raw.the_tip);
                break;
            case NombresAudioParaSituaciones.TABLE :
                mp = MediaPlayer.create(context, R.raw.table);
                break;
            case NombresAudioParaSituaciones.CHAIR :
                mp = MediaPlayer.create(context, R.raw.chair);
                break;
            case NombresAudioParaSituaciones.TABLE_CLOTH :
                mp = MediaPlayer.create(context, R.raw.table_cloth);
                break;
            case NombresAudioParaSituaciones.SPOON :
                mp = MediaPlayer.create(context, R.raw.spoon);
                break;
            case NombresAudioParaSituaciones.FORK :
                mp = MediaPlayer.create(context, R.raw.fork);
                break;
            case NombresAudioParaSituaciones.KNIFE :
                mp = MediaPlayer.create(context, R.raw.knife);
                break;
            case NombresAudioParaSituaciones.PEPPER_POT :
                mp = MediaPlayer.create(context, R.raw.pepper_pot);
                break;
            case NombresAudioParaSituaciones.SALT_CELLAR :
                mp = MediaPlayer.create(context, R.raw.salt_cellar);
                break;
            case NombresAudioParaSituaciones.NAPKIN :
                mp = MediaPlayer.create(context, R.raw.napkin);
                break;
            case NombresAudioParaSituaciones.TO_SERVE_THE_TABLE :
                mp = MediaPlayer.create(context, R.raw.to_serve_the_table);
                break;
            case NombresAudioParaSituaciones.DAILY_MENU :
                mp = MediaPlayer.create(context, R.raw.daily_menu);
                break;
            case NombresAudioParaSituaciones.MEAT :
                mp = MediaPlayer.create(context, R.raw.meat);
                break;
            case NombresAudioParaSituaciones.FISH :
                mp = MediaPlayer.create(context, R.raw.fish);
                break;
            case NombresAudioParaSituaciones.VEGETABLES :
                mp = MediaPlayer.create(context, R.raw.vegetables);
                break;
            case NombresAudioParaSituaciones.PASTA :
                mp = MediaPlayer.create(context, R.raw.pasta);
                break;
            case NombresAudioParaSituaciones.COURSE :
                mp = MediaPlayer.create(context, R.raw.course);
                break;
            case NombresAudioParaSituaciones.MAIN_COURSE :
                mp = MediaPlayer.create(context, R.raw.main_course);
                break;
            case NombresAudioParaSituaciones.SECOND_COURSE :
                mp = MediaPlayer.create(context, R.raw.second_course);
                break;
            case NombresAudioParaSituaciones.DESSERT :
                mp = MediaPlayer.create(context, R.raw.dessert);
                break;
            case NombresAudioParaSituaciones.ICE_CREAM :
                mp = MediaPlayer.create(context, R.raw.ice_cream);
                break;
            case NombresAudioParaSituaciones.CAN_I_HELP_YOU :
                mp = MediaPlayer.create(context, R.raw.can_i_help_you);
                break;
            case NombresAudioParaSituaciones.HOW_CAN_I_HELP_YOU :
                mp = MediaPlayer.create(context, R.raw.how_can_i_help_you);
                break;
            case NombresAudioParaSituaciones.HAVE_YOU_DECIDED_YET :
                mp = MediaPlayer.create(context, R.raw.have_you_decided_yet);
                break;
            case NombresAudioParaSituaciones.DO_YOU_NEED_ANYTHING_ELSE :
                mp = MediaPlayer.create(context, R.raw.do_you_need_anything_else);
                break;
            case NombresAudioParaSituaciones.WHAT_WOULD_YOU_LIKE_TO_DRINK :
                mp = MediaPlayer.create(context, R.raw.what_would_you_like_to_drink);
                break;
            case NombresAudioParaSituaciones.WHAT_WOULD_YOU_LIKE_TO_EAT :
                mp = MediaPlayer.create(context, R.raw.what_would_you_like_to_eat);
                break;
            case NombresAudioParaSituaciones.IS_EVERYTHING_FINE :
                mp = MediaPlayer.create(context, R.raw.is_everything_fine);
                break;
            case NombresAudioParaSituaciones.ANYTHING_ELSE :
                mp = MediaPlayer.create(context, R.raw.anything_else);
                break;
            case NombresAudioParaSituaciones.WHAT_ELSE :
                mp = MediaPlayer.create(context, R.raw.what_else);
                break;
            case NombresAudioParaSituaciones.DO_YOU_WANT_THE_BILL :
                mp = MediaPlayer.create(context, R.raw.do_you_want_the_bill);
                break;
            case NombresAudioParaSituaciones.DO_YOU_WANT_IT_WELL_COOCKED_OR_RARE :
                mp = MediaPlayer.create(context, R.raw.do_you_want_it_well_coocked_or_rare);
                break;
            case NombresAudioParaSituaciones.WOULD_YOU_LIKE_TO_HAVE_DESSERTS :
                mp = MediaPlayer.create(context, R.raw.would_you_like_to_have_desserts);
                break;
            case NombresAudioParaSituaciones.DO_YOU_WANT_TO_SEE_THE_MENU :
                mp = MediaPlayer.create(context, R.raw.do_you_want_to_see_the_menu);
                break;
            case NombresAudioParaSituaciones.TAKEAWAY :
                mp = MediaPlayer.create(context, R.raw.takeaway);
                break;
            case NombresAudioParaSituaciones.TAKEOUT :
                mp = MediaPlayer.create(context, R.raw.takeout);
                break;
            case NombresAudioParaSituaciones.COFFEE_TO_TAKE_AWAY :
                mp = MediaPlayer.create(context, R.raw.coffee_to_take_away);
                break;
            case NombresAudioParaSituaciones.CUP_OF_COFFEE :
                mp = MediaPlayer.create(context, R.raw.cup_of_coffee);
                break;
            case NombresAudioParaSituaciones.ICED_TEA :
                mp = MediaPlayer.create(context, R.raw.iced_tea);
                break;
            case NombresAudioParaSituaciones.SNACK :
                mp = MediaPlayer.create(context, R.raw.snack);
                break;
            case NombresAudioParaSituaciones.GLASS :
                mp = MediaPlayer.create(context, R.raw.glass);
                break;
            case NombresAudioParaSituaciones.LUNCH :
                mp = MediaPlayer.create(context, R.raw.lunch);
                break;
            case NombresAudioParaSituaciones.RARE :
                mp = MediaPlayer.create(context, R.raw.rare);
                break;
            case NombresAudioParaSituaciones.MEDIUM :
                mp = MediaPlayer.create(context, R.raw.medium);
                break;
            case NombresAudioParaSituaciones.WELL_DONE :
                mp = MediaPlayer.create(context, R.raw.well_done);
                break;
            case NombresAudioParaSituaciones.TO_ORDER :
                mp = MediaPlayer.create(context, R.raw.to_order);
                break;
            case NombresAudioParaSituaciones.ENJOY_YOUR_MEAL :
                mp = MediaPlayer.create(context, R.raw.enjoy_your_meal);
                break;
            case NombresAudioParaSituaciones.BOTTLE_OPENER :
                mp = MediaPlayer.create(context, R.raw.bottle_opener);
                break;
            case NombresAudioParaSituaciones.CUP :
                mp = MediaPlayer.create(context, R.raw.cup);
                break;
            case NombresAudioParaSituaciones.DINNER :
                mp = MediaPlayer.create(context, R.raw.dinner);
                break;
            case NombresAudioParaSituaciones.DRESSING :
                mp = MediaPlayer.create(context, R.raw.dressing);
                break;
            case NombresAudioParaSituaciones.MAY_I_HAVE_THE_MENU_PLEASE :
                mp = MediaPlayer.create(context, R.raw.may_i_have_the_menu_please);
                break;
            case NombresAudioParaSituaciones.I_WOULD_LIKE_TO_HAVE_A_SANDWICH :
                mp = MediaPlayer.create(context, R.raw.i_would_like_to_have_a_sandwich);
                break;
            case NombresAudioParaSituaciones.WHATS_THIS_DISH :
                mp = MediaPlayer.create(context, R.raw.whats_this_dish);
                break;
            case NombresAudioParaSituaciones.WHAT_IS_TODAYS_SPECIAL :
                mp = MediaPlayer.create(context, R.raw.what_is_todays_special);
                break;
            case NombresAudioParaSituaciones.WHAT_DO_YOU_RECOMMEND_ME :
                mp = MediaPlayer.create(context, R.raw.what_do_you_recommend_me);
                break;
            case NombresAudioParaSituaciones.IM_NOT_READY_YET :
                mp = MediaPlayer.create(context, R.raw.im_not_ready_yet);
                break;
            case NombresAudioParaSituaciones.I_WOULD_LIKE_TO_ORDER_NOW :
                mp = MediaPlayer.create(context, R.raw.i_would_like_to_order_now);
                break;
            case NombresAudioParaSituaciones.ILL_HAVE_THE :
                mp = MediaPlayer.create(context, R.raw.ill_have_the);
                break;
            case NombresAudioParaSituaciones.ID_LIKE_THE :
                mp = MediaPlayer.create(context, R.raw.id_like_the);
                break;
            case NombresAudioParaSituaciones.PLEASE_CAN_I_HAVE_THE :
                mp = MediaPlayer.create(context, R.raw.please_can_i_have_the);
                break;
            case NombresAudioParaSituaciones.WHAT_ARE_THE_INGREDIENTS :
                mp = MediaPlayer.create(context, R.raw.what_are_the_ingredients);
                break;
            case NombresAudioParaSituaciones.ILL_HAVE_THE_SAME :
                mp = MediaPlayer.create(context, R.raw.ill_have_the_same);
                break;
            case NombresAudioParaSituaciones.WHAT_KIND_OF_DESSERTS_DO_YOU_HAVE :
                mp = MediaPlayer.create(context, R.raw.what_kind_of_desserts_do_you_have);
                break;
            case NombresAudioParaSituaciones.MAY_I_HAVE_A_BEER :
                mp = MediaPlayer.create(context, R.raw.may_i_have_a_beer);
                break;
            case NombresAudioParaSituaciones.THATS_ALL_THANK_YOU :
                mp = MediaPlayer.create(context, R.raw.thats_all_thank_you);
                break;
            case NombresAudioParaSituaciones.EVERYTHING_WAS_DELICIOUS :
                mp = MediaPlayer.create(context, R.raw.everything_was_delicious);
                break;
            case NombresAudioParaSituaciones.THIS_IS_VERY_TASTY :
                mp = MediaPlayer.create(context, R.raw.this_is_very_tasty);
                break;
            case NombresAudioParaSituaciones.I_WANT_TO_TALK_TO_THE_MANAGER :
                mp = MediaPlayer.create(context, R.raw.i_want_to_talk_to_the_manager);
                break;
            case NombresAudioParaSituaciones.KEEP_THE_CHANGE :
                mp = MediaPlayer.create(context, R.raw.keep_the_change);
                break;
            case NombresAudioParaSituaciones.COULD_I_HAVE_THE_BILL_PLEASE :
                mp = MediaPlayer.create(context, R.raw.could_i_have_the_bill_please);
                break;
            case NombresAudioParaSituaciones.COULD_I_PAY_BY_CARD :
                mp = MediaPlayer.create(context, R.raw.could_i_pay_by_card);
                break;
            case NombresAudioParaSituaciones.THIS_IS_NOT_WHAT_I_ORDERED :
                mp = MediaPlayer.create(context, R.raw.this_is_not_what_i_ordered);
                break;
            case NombresAudioParaSituaciones.WHERE_ARE_THE_RESTROOMS :
                mp = MediaPlayer.create(context, R.raw.where_are_the_restrooms);
                break;
            case NombresAudioParaSituaciones.AIRPORT :
                mp = MediaPlayer.create(context, R.raw.airport);
                break;
            case NombresAudioParaSituaciones.AIRPLANE :
                mp = MediaPlayer.create(context, R.raw.airplane);
                break;
            case NombresAudioParaSituaciones.FLIGHT :
                mp = MediaPlayer.create(context, R.raw.flight);
                break;
            case NombresAudioParaSituaciones.BOARDING_PASS :
                mp = MediaPlayer.create(context, R.raw.boarding_pass);
                break;
            case NombresAudioParaSituaciones.AIR_HOSTESS :
                mp = MediaPlayer.create(context, R.raw.air_hostess);
                break;
            case NombresAudioParaSituaciones.DEPARTURE_LOUNGE :
                mp = MediaPlayer.create(context, R.raw.departure_lounge);
                break;
            case NombresAudioParaSituaciones.CHECK_IN :
                mp = MediaPlayer.create(context, R.raw.check_in);
                break;
            case NombresAudioParaSituaciones.ONE_WAY :
                mp = MediaPlayer.create(context, R.raw.one_way);
                break;
            case NombresAudioParaSituaciones.ROUND_TRIP :
                mp = MediaPlayer.create(context, R.raw.round_trip);
                break;
            case NombresAudioParaSituaciones.DEPARTURES :
                mp = MediaPlayer.create(context, R.raw.departures);
                break;
            case NombresAudioParaSituaciones.ARRIVALS :
                mp = MediaPlayer.create(context, R.raw.arrivals);
                break;
            case NombresAudioParaSituaciones.LAYOVER :
                mp = MediaPlayer.create(context, R.raw.layover);
                break;
            case NombresAudioParaSituaciones.COACH_CLASS :
                mp = MediaPlayer.create(context, R.raw.coach_class);
                break;
            case NombresAudioParaSituaciones.LUGGAGE :
                mp = MediaPlayer.create(context, R.raw.luggage);
                break;
            case NombresAudioParaSituaciones.SUITCASE :
                mp = MediaPlayer.create(context, R.raw.suitcase);
                break;
            case NombresAudioParaSituaciones.CARRY_ON_LUGGAGE :
                mp = MediaPlayer.create(context, R.raw.carry_on_luggage);
                break;
            case NombresAudioParaSituaciones.CUSTOMS :
                mp = MediaPlayer.create(context, R.raw.customs);
                break;
            case NombresAudioParaSituaciones.BOARD :
                mp = MediaPlayer.create(context, R.raw.board);
                break;
            case NombresAudioParaSituaciones.DEPART :
                mp = MediaPlayer.create(context, R.raw.depart);
                break;
            case NombresAudioParaSituaciones.ARRIVE :
                mp = MediaPlayer.create(context, R.raw.arrive);
                break;
            case NombresAudioParaSituaciones.TAKE_OFF :
                mp = MediaPlayer.create(context, R.raw.take_off);
                break;
            case NombresAudioParaSituaciones.LAND :
                mp = MediaPlayer.create(context, R.raw.land);
                break;
            case NombresAudioParaSituaciones.AISLE_SEAT :
                mp = MediaPlayer.create(context, R.raw.aisle_seat);
                break;
            case NombresAudioParaSituaciones.WHERE_IS_THE_BAGGAGE_CLAIM :
                mp = MediaPlayer.create(context, R.raw.where_is_the_baggage_claim);
                break;
            case NombresAudioParaSituaciones.STOPOVER :
                mp = MediaPlayer.create(context, R.raw.stopover);
                break;
            case NombresAudioParaSituaciones.SEAT_BELT :
                mp = MediaPlayer.create(context, R.raw.seat_belt);
                break;
            case NombresAudioParaSituaciones.RESTROOMS :
                mp = MediaPlayer.create(context, R.raw.restrooms);
                break;
            case NombresAudioParaSituaciones.HAVE_YOU_GOT_ANY_ROOMS_AVAILABLE :
                mp = MediaPlayer.create(context, R.raw.have_you_got_any_rooms_available);
                break;
            case NombresAudioParaSituaciones.DO_YOU_HAVE_A_RESERVATION :
                mp = MediaPlayer.create(context, R.raw.do_you_have_a_reservation);
                break;
            case NombresAudioParaSituaciones.ACCOMMODATION :
                mp = MediaPlayer.create(context, R.raw.accommodation);
                break;
            case NombresAudioParaSituaciones.SHEETS :
                mp = MediaPlayer.create(context, R.raw.sheets);
                break;
            case NombresAudioParaSituaciones.DOUBLE_BED :
                mp = MediaPlayer.create(context, R.raw.double_bed);
                break;
            case NombresAudioParaSituaciones.DOUBLE_ROOM :
                mp = MediaPlayer.create(context, R.raw.double_room);
                break;
            case NombresAudioParaSituaciones.BOOKING :
                mp = MediaPlayer.create(context, R.raw.booking);
                break;
            case NombresAudioParaSituaciones.SINGLE_BED :
                mp = MediaPlayer.create(context, R.raw.single_bed);
                break;
            case NombresAudioParaSituaciones.SINGLE_ROOM :
                mp = MediaPlayer.create(context, R.raw.single_room);
                break;
            case NombresAudioParaSituaciones.AIR_CONDITIONING :
                mp = MediaPlayer.create(context, R.raw.air_conditioning);
                break;
            case NombresAudioParaSituaciones.BLANKETS :
                mp = MediaPlayer.create(context, R.raw.blankets);
                break;
            case NombresAudioParaSituaciones.DINING_ROOM :
                mp = MediaPlayer.create(context, R.raw.dining_room);
                break;
            case NombresAudioParaSituaciones.HAIR_DRYER :
                mp = MediaPlayer.create(context, R.raw.hair_dryer);
                break;
            case NombresAudioParaSituaciones.KEY :
                mp = MediaPlayer.create(context, R.raw.key);
                break;
            case NombresAudioParaSituaciones.LAUNDRY_SERVICE :
                mp = MediaPlayer.create(context, R.raw.laundry_service);
                break;
            case NombresAudioParaSituaciones.MATTRESS :
                mp = MediaPlayer.create(context, R.raw.mattress);
                break;
            case NombresAudioParaSituaciones.PILLOW :
                mp = MediaPlayer.create(context, R.raw.pillow);
                break;
            case NombresAudioParaSituaciones.RECEIPT :
                mp = MediaPlayer.create(context, R.raw.receipt);
                break;
            case NombresAudioParaSituaciones.SOAP :
                mp = MediaPlayer.create(context, R.raw.soap);
                break;
            case NombresAudioParaSituaciones.STAIRS :
                mp = MediaPlayer.create(context, R.raw.stairs);
                break;
            case NombresAudioParaSituaciones.TAP :
                mp = MediaPlayer.create(context, R.raw.tap);
                break;
            case NombresAudioParaSituaciones.TOILET_PAPER :
                mp = MediaPlayer.create(context, R.raw.toilet_paper);
                break;
            case NombresAudioParaSituaciones.TOWEL :
                mp = MediaPlayer.create(context, R.raw.towel);
                break;
            case NombresAudioParaSituaciones.TWIN_BEDS :
                mp = MediaPlayer.create(context, R.raw.twin_beds);
                break;
            case NombresAudioParaSituaciones.ID_LIKE_A_DOUBLE_ROOM_FOR_TWO_NIGHTS :
                mp = MediaPlayer.create(context, R.raw.id_like_a_double_room_for_two_nights);
                break;
            case NombresAudioParaSituaciones.HOW_MUCH_DOES_IT_COST_PER_DAY :
                mp = MediaPlayer.create(context, R.raw.how_much_does_it_cost_per_day);
                break;
            case NombresAudioParaSituaciones.IS_BREAKFAST_INCLUDED :
                mp = MediaPlayer.create(context, R.raw.is_breakfast_included);
                break;
            case NombresAudioParaSituaciones.AMENITIES :
                mp = MediaPlayer.create(context, R.raw.amenities);
                break;
            case NombresAudioParaSituaciones.BALCONY :
                mp = MediaPlayer.create(context, R.raw.balcony);
                break;
            case NombresAudioParaSituaciones.CAN_I_HAVE_YOUR_PASSPORT :
                mp = MediaPlayer.create(context, R.raw.can_i_have_your_passport);
                break;
            case NombresAudioParaSituaciones.FROM_WHERE_ARE_YOU_COMING_FROM :
                mp = MediaPlayer.create(context, R.raw.from_where_are_you_coming_from);
                break;
            case NombresAudioParaSituaciones.BREAKFAST_TIME :
                mp = MediaPlayer.create(context, R.raw.breakfast_time);
                break;
            case NombresAudioParaSituaciones.CHECK_OUT :
                mp = MediaPlayer.create(context, R.raw.check_out);
                break;
            case NombresAudioParaSituaciones.WE_HAVE_AMENITIES_SUCH_AS :
                mp = MediaPlayer.create(context, R.raw.we_have_amenities_such_as);
                break;
            case NombresAudioParaSituaciones.COULD_YOU_PLEASE_GIVE_ME_DIRECTIONS :
                mp = MediaPlayer.create(context, R.raw.could_you_please_give_me_directions);
                break;
            case NombresAudioParaSituaciones.AT_WHAT_TIME_IS_THE_BREAKFAST_SERVED :
                mp = MediaPlayer.create(context, R.raw.at_what_time_is_the_breakfast_served);
                break;
            case NombresAudioParaSituaciones.COULD_YOU_PLEASE_SUGGEST_ME_A_GOOD_RESTAURANT :
                mp = MediaPlayer.create(context, R.raw.could_you_please_suggest_me_a_good_restaurant);
                break;
            case NombresAudioParaSituaciones.I_HAVE_A_SORE_THROAT :
                mp = MediaPlayer.create(context, R.raw.i_have_a_sore_throat);
                break;
            case NombresAudioParaSituaciones.IT_HURTS_WHEN_I_SWALLOW :
                mp = MediaPlayer.create(context, R.raw.it_hurts_when_i_swallow);
                break;
            case NombresAudioParaSituaciones.TONSILLITIS :
                mp = MediaPlayer.create(context, R.raw.tonsillitis);
                break;
            case NombresAudioParaSituaciones.I_HAVE_A_HEADACHE :
                mp = MediaPlayer.create(context, R.raw.i_have_a_headache);
                break;
            case NombresAudioParaSituaciones.I_HAVE_A_BACKACHE :
                mp = MediaPlayer.create(context, R.raw.i_have_a_backache);
                break;
            case NombresAudioParaSituaciones.I_HAVE_A_STOMACHACE :
                mp = MediaPlayer.create(context, R.raw.i_have_a_stomachace);
                break;
            case NombresAudioParaSituaciones.I_HAVE_A_TOOTHACHE :
                mp = MediaPlayer.create(context, R.raw.i_have_a_toothache);
                break;
            case NombresAudioParaSituaciones.I_HAVE_AN_EARACHE :
                mp = MediaPlayer.create(context, R.raw.i_have_an_earache);
                break;
            case NombresAudioParaSituaciones.I_HAVE_A_COLD :
                mp = MediaPlayer.create(context, R.raw.i_have_a_cold);
                break;
            case NombresAudioParaSituaciones.I_HAVE_A_BLOCKED_NOSE :
                mp = MediaPlayer.create(context, R.raw.i_have_a_blocked_nose);
                break;
            case NombresAudioParaSituaciones.I_HAVE_A_COUGH :
                mp = MediaPlayer.create(context, R.raw.i_have_a_cough);
                break;
            case NombresAudioParaSituaciones.I_CANT_STOP_SNEEZING :
                mp = MediaPlayer.create(context, R.raw.i_cant_stop_sneezing);
                break;
            case NombresAudioParaSituaciones.I_HAVE_THE_FLU :
                mp = MediaPlayer.create(context, R.raw.i_have_the_flu);
                break;
            case NombresAudioParaSituaciones.I_HAVE_A_FEVER :
                mp = MediaPlayer.create(context, R.raw.i_have_a_fever);
                break;
            case NombresAudioParaSituaciones.I_HAVE_TEMPERATURE :
                mp = MediaPlayer.create(context, R.raw.i_have_temperature);
                break;
            case NombresAudioParaSituaciones.I_FELL_DOWN_THE_STAIRS_AND_I_TWISTED_MY_ANKLE :
                mp = MediaPlayer.create(context, R.raw.i_fell_down_the_stairs_and_i_twisted_my_ankle);
                break;
            case NombresAudioParaSituaciones.I_HAVE_A_BRUISE :
                mp = MediaPlayer.create(context, R.raw.i_have_a_bruise);
                break;
            case NombresAudioParaSituaciones.IVE_FRACTURED_A_RIB :
                mp = MediaPlayer.create(context, R.raw.ive_fractured_a_rib);
                break;
            case NombresAudioParaSituaciones.I_HAVE_A_RASH :
                mp = MediaPlayer.create(context, R.raw.i_have_a_rash);
                break;
            case NombresAudioParaSituaciones.IM_ALLERGIC_TO_NUTS :
                mp = MediaPlayer.create(context, R.raw.im_allergic_to_nuts);
                break;
            case NombresAudioParaSituaciones.IM_INJURED :
                mp = MediaPlayer.create(context, R.raw.im_injured);
                break;
            case NombresAudioParaSituaciones.IVE_INJURED_MY_KNEE_PLAYING_FOOTBALL :
                mp = MediaPlayer.create(context, R.raw.ive_injured_my_knee_playing_football);
                break;
            case NombresAudioParaSituaciones.I_HAVE_AN_INJURY :
                mp = MediaPlayer.create(context, R.raw.i_have_an_injury);
                break;
            case NombresAudioParaSituaciones.THEY_SHOT_HIM_AND_HE_HAD_A_WOUND_ON_HIS_CHEST :
                mp = MediaPlayer.create(context, R.raw.they_shot_him_and_he_had_a_wound_on_his_chest);
                break;
            case NombresAudioParaSituaciones.IM_GOING_TO_HAVE_A_HEART_ATTACK :
                mp = MediaPlayer.create(context, R.raw.im_going_to_have_a_heart_attack);
                break;
            case NombresAudioParaSituaciones.I_NEED_A_BANDAGE :
                mp = MediaPlayer.create(context, R.raw.i_need_a_bandage);
                break;
            case NombresAudioParaSituaciones.STITCHES :
                mp = MediaPlayer.create(context, R.raw.stitches);
                break;
            case NombresAudioParaSituaciones.I_GOT_FIVE_STITCHES_IN_MY_HAND :
                mp = MediaPlayer.create(context, R.raw.i_got_five_stitches_in_my_hand);
                break;
            case NombresAudioParaSituaciones.YOU_NEED_TO_REST :
                mp = MediaPlayer.create(context, R.raw.you_need_to_rest);
                break;
            case NombresAudioParaSituaciones.PILLS :
                mp = MediaPlayer.create(context, R.raw.pills);
                break;
            case NombresAudioParaSituaciones.INJECTION :
                mp = MediaPlayer.create(context, R.raw.injection);
                break;
            case NombresAudioParaSituaciones.THE_DOCTOR_GAVE_ME_SOME_PAINKILLERS :
                mp = MediaPlayer.create(context, R.raw.the_doctor_gave_me_some_painkillers);
                break;
            case NombresAudioParaSituaciones.PLASTER :
                mp = MediaPlayer.create(context, R.raw.plaster);
                break;
            case NombresAudioParaSituaciones.PLASTER_CAST :
                mp = MediaPlayer.create(context, R.raw.plaster_cast);
                break;
            case NombresAudioParaSituaciones.IM_GOING_TO_HAVE_AN_OPERATION_ON_MY_KNEE :
                mp = MediaPlayer.create(context, R.raw.im_going_to_have_an_operation_on_my_knee);
                break;
            case NombresAudioParaSituaciones.I_FEEL_NAUSEOUS :
                mp = MediaPlayer.create(context, R.raw.i_feel_nauseous);
                break;
            case NombresAudioParaSituaciones.ACCOMPLISHMENT :
                mp = MediaPlayer.create(context, R.raw.accomplishment);
                break;
            case NombresAudioParaSituaciones.APPLY :
                mp = MediaPlayer.create(context, R.raw.apply);
                break;
            case NombresAudioParaSituaciones.ARE_YOU_A_GOOD_TEAM_WORKER_OR_DO_YOU_PREFER_TO_WORK_ON_YOUR_OWN :
                mp = MediaPlayer.create(context, R.raw.are_you_a_good_team_worker_or_do_you_prefer_to_work_on_your_own);
                break;
            case NombresAudioParaSituaciones.CANDIDATE :
                mp = MediaPlayer.create(context, R.raw.candidate);
                break;
            case NombresAudioParaSituaciones.EFFICIENT :
                mp = MediaPlayer.create(context, R.raw.efficient);
                break;
            case NombresAudioParaSituaciones.GOAL :
                mp = MediaPlayer.create(context, R.raw.goal);
                break;
            case NombresAudioParaSituaciones.GOOD_COMMUNICATION_SKILLS :
                mp = MediaPlayer.create(context, R.raw.good_communication_skills);
                break;
            case NombresAudioParaSituaciones.HAVE_A_SEAT :
                mp = MediaPlayer.create(context, R.raw.have_a_seat);
                break;
            case NombresAudioParaSituaciones.HIRE :
                mp = MediaPlayer.create(context, R.raw.hire);
                break;
            case NombresAudioParaSituaciones.HOW_WOULD_YOUR_FAMILY_AND_FRIENDS_DESCRIBE_YOU :
                mp = MediaPlayer.create(context, R.raw.how_would_your_family_and_friends_describe_you);
                break;
            case NombresAudioParaSituaciones.I_HAVE_AN_APPOINTMENT_AT_THREE_THIRTY :
                mp = MediaPlayer.create(context, R.raw.i_have_an_appointment_at_three_thirty);
                break;
            case NombresAudioParaSituaciones.INTERVIEW :
                mp = MediaPlayer.create(context, R.raw.interview);
                break;
            case NombresAudioParaSituaciones.LEADERSHIP :
                mp = MediaPlayer.create(context, R.raw.leadership);
                break;
            case NombresAudioParaSituaciones.RELIABLE :
                mp = MediaPlayer.create(context, R.raw.reliable);
                break;
            case NombresAudioParaSituaciones.RESPONSIBILITY :
                mp = MediaPlayer.create(context, R.raw.responsibility);
                break;
            case NombresAudioParaSituaciones.RESPONSIBLE :
                mp = MediaPlayer.create(context, R.raw.responsible);
                break;
            case NombresAudioParaSituaciones.RESUME :
                mp = MediaPlayer.create(context, R.raw.resume);
                break;
            case NombresAudioParaSituaciones.SALARY_EXPECTATIONS :
                mp = MediaPlayer.create(context, R.raw.salary_expectations);
                break;
            case NombresAudioParaSituaciones.SKILLS :
                mp = MediaPlayer.create(context, R.raw.skills);
                break;
            case NombresAudioParaSituaciones.STRENGTH :
                mp = MediaPlayer.create(context, R.raw.strength);
                break;
            case NombresAudioParaSituaciones.TASK :
                mp = MediaPlayer.create(context, R.raw.task);
                break;
            case NombresAudioParaSituaciones.TELL_ME_A_LITTLE_BIT_ABOUT_YOURSELF :
                mp = MediaPlayer.create(context, R.raw.tell_me_a_little_bit_about_yourself);
                break;
            case NombresAudioParaSituaciones.WEAKNESS :
                mp = MediaPlayer.create(context, R.raw.weakness);
                break;
            case NombresAudioParaSituaciones.WHAT_ARE_YOUR_GOALS_IN_LIFE :
                mp = MediaPlayer.create(context, R.raw.what_are_your_goals_in_life);
                break;
            case NombresAudioParaSituaciones.WHAT_ARE_YOUR_STRENGTHS :
                mp = MediaPlayer.create(context, R.raw.what_are_your_strengths);
                break;
            case NombresAudioParaSituaciones.WHAT_ARE_YOUR_WEAKNESSES :
                mp = MediaPlayer.create(context, R.raw.what_are_your_weaknesses);
                break;
            case NombresAudioParaSituaciones.WHAT_CAN_YOU_TELL_ME_ABOUT_YOURSELF :
                mp = MediaPlayer.create(context, R.raw.what_can_you_tell_me_about_yourself);
                break;
            case NombresAudioParaSituaciones.WHAT_DO_YOU_KNOW_ABOUT_THIS_COMPANY :
                mp = MediaPlayer.create(context, R.raw.what_do_you_know_about_this_company);
                break;
            case NombresAudioParaSituaciones.WHAT_HAS_BEEN_YOUR_GREATEST_ACCOMPLISHMENT :
                mp = MediaPlayer.create(context, R.raw.what_has_been_your_greatest_accomplishment);
                break;
            case NombresAudioParaSituaciones.WHERE_DO_YOU_SEE_YOURSELF_IN_FIVE_TEN_YEARS :
                mp = MediaPlayer.create(context, R.raw.where_do_you_see_yourself_in_five_ten_years);
                break;
            case NombresAudioParaSituaciones.WHY_SHOULD_WE_HIRE_YOU :
                mp = MediaPlayer.create(context, R.raw.why_should_we_hire_you);
                break;
            case NombresAudioParaSituaciones.PERCENT_OFF :
                mp = MediaPlayer.create(context, R.raw.percent_off);
                break;
            case NombresAudioParaSituaciones.AISLE :
                mp = MediaPlayer.create(context, R.raw.aisle);
                break;
            case NombresAudioParaSituaciones.BAKERY :
                mp = MediaPlayer.create(context, R.raw.bakery);
                break;
            case NombresAudioParaSituaciones.BARCODE_READER :
                mp = MediaPlayer.create(context, R.raw.barcode_reader);
                break;
            case NombresAudioParaSituaciones.BASKET :
                mp = MediaPlayer.create(context, R.raw.basket);
                break;
            case NombresAudioParaSituaciones.BEVERAGES :
                mp = MediaPlayer.create(context, R.raw.beverages);
                break;
            case NombresAudioParaSituaciones.BREAD :
                mp = MediaPlayer.create(context, R.raw.bread);
                break;
            case NombresAudioParaSituaciones.BUTTER :
                mp = MediaPlayer.create(context, R.raw.butter);
                break;
            case NombresAudioParaSituaciones.CANNED_GOODS :
                mp = MediaPlayer.create(context, R.raw.canned_goods);
                break;
            case NombresAudioParaSituaciones.CARROT :
                mp = MediaPlayer.create(context, R.raw.carrot);
                break;
            case NombresAudioParaSituaciones.CASHIER :
                mp = MediaPlayer.create(context, R.raw.cashier);
                break;
            case NombresAudioParaSituaciones.CASH :
                mp = MediaPlayer.create(context, R.raw.cash);
                break;
            case NombresAudioParaSituaciones.CHANGE :
                mp = MediaPlayer.create(context, R.raw.change);
                break;
            case NombresAudioParaSituaciones.CHEESE :
                mp = MediaPlayer.create(context, R.raw.cheese);
                break;
            case NombresAudioParaSituaciones.CORN_FLOUR :
                mp = MediaPlayer.create(context, R.raw.corn_flour);
                break;
            case NombresAudioParaSituaciones.CUSTOMER :
                mp = MediaPlayer.create(context, R.raw.customer);
                break;
            case NombresAudioParaSituaciones.DAIRY_PRODUCTS :
                mp = MediaPlayer.create(context, R.raw.dairy_products);
                break;
            case NombresAudioParaSituaciones.DELICATESSEN :
                mp = MediaPlayer.create(context, R.raw.delicatessen);
                break;
            case NombresAudioParaSituaciones.DEPARTMENT :
                mp = MediaPlayer.create(context, R.raw.department);
                break;
            case NombresAudioParaSituaciones.DRUGSTORE :
                mp = MediaPlayer.create(context, R.raw.drugstore);
                break;
            case NombresAudioParaSituaciones.EGGS :
                mp = MediaPlayer.create(context, R.raw.eggs);
                break;
            case NombresAudioParaSituaciones.ELECTRONICS :
                mp = MediaPlayer.create(context, R.raw.electronics);
                break;
            case NombresAudioParaSituaciones.EXPRESS_LANE :
                mp = MediaPlayer.create(context, R.raw.express_lane);
                break;
            case NombresAudioParaSituaciones.FLOUR :
                mp = MediaPlayer.create(context, R.raw.flour);
                break;
            case NombresAudioParaSituaciones.FROZEN_FOOD :
                mp = MediaPlayer.create(context, R.raw.frozen_food);
                break;
            case NombresAudioParaSituaciones.GARLIC :
                mp = MediaPlayer.create(context, R.raw.garlic);
                break;
            case NombresAudioParaSituaciones.HALF_PRICE :
                mp = MediaPlayer.create(context, R.raw.half_price);
                break;
            case NombresAudioParaSituaciones.HOW_MUCH_DOES_IT_COST :
                mp = MediaPlayer.create(context, R.raw.how_much_does_it_cost);
                break;
            case NombresAudioParaSituaciones.LENTILS :
                mp = MediaPlayer.create(context, R.raw.lentils);
                break;
            case NombresAudioParaSituaciones.LETTUCE :
                mp = MediaPlayer.create(context, R.raw.lettuce);
                break;
            case NombresAudioParaSituaciones.NOODLES :
                mp = MediaPlayer.create(context, R.raw.noodles);
                break;
            case NombresAudioParaSituaciones.ONION :
                mp = MediaPlayer.create(context, R.raw.onion);
                break;
            case NombresAudioParaSituaciones.PLASTIC_BAG :
                mp = MediaPlayer.create(context, R.raw.plastic_bag);
                break;
            case NombresAudioParaSituaciones.PRICE :
                mp = MediaPlayer.create(context, R.raw.price);
                break;
            case NombresAudioParaSituaciones.PURCHASE :
                mp = MediaPlayer.create(context, R.raw.purchase);
                break;
            case NombresAudioParaSituaciones.RICE :
                mp = MediaPlayer.create(context, R.raw.rice);
                break;
            case NombresAudioParaSituaciones.SALT :
                mp = MediaPlayer.create(context, R.raw.salt);
                break;
            case NombresAudioParaSituaciones.SCALES :
                mp = MediaPlayer.create(context, R.raw.scales);
                break;
            case NombresAudioParaSituaciones.SKIM_MILK :
                mp = MediaPlayer.create(context, R.raw.skim_milk);
                break;
            case NombresAudioParaSituaciones.STORE_HOURS :
                mp = MediaPlayer.create(context, R.raw.store_hours);
                break;
            case NombresAudioParaSituaciones.SUGAR :
                mp = MediaPlayer.create(context, R.raw.sugar);
                break;
            case NombresAudioParaSituaciones.SUPERMARKET :
                mp = MediaPlayer.create(context, R.raw.supermarket);
                break;
            case NombresAudioParaSituaciones.TOMATOES :
                mp = MediaPlayer.create(context, R.raw.tomatoes);
                break;
            case NombresAudioParaSituaciones.TOMATO :
                mp = MediaPlayer.create(context, R.raw.tomato);
                break;
            case NombresAudioParaSituaciones.YEAST :
                mp = MediaPlayer.create(context, R.raw.yeast);
                break;
            case NombresAudioParaSituaciones.ABOUT_TEN_MIN_ON_FOOT :
                mp = MediaPlayer.create(context, R.raw.about_min_on_foot);
                break;
            case NombresAudioParaSituaciones.AHEAD :
                mp = MediaPlayer.create(context, R.raw.ahead);
                break;
            case NombresAudioParaSituaciones.AT_THE_CORNER :
                mp = MediaPlayer.create(context, R.raw.at_the_corner);
                break;
            case NombresAudioParaSituaciones.AVENUE :
                mp = MediaPlayer.create(context, R.raw.avenue);
                break;
            case NombresAudioParaSituaciones.BLOCK :
                mp = MediaPlayer.create(context, R.raw.block);
                break;
            case NombresAudioParaSituaciones.CAN_YOU_GET_THERE_BY_TRAIN :
                mp = MediaPlayer.create(context, R.raw.can_you_get_there_by_train);
                break;
            case NombresAudioParaSituaciones.CAN_YOU_SHOW_ME_ON_THE_MAP :
                mp = MediaPlayer.create(context, R.raw.can_you_show_me_on_the_map);
                break;
            case NombresAudioParaSituaciones.CROSS_THE_STREET :
                mp = MediaPlayer.create(context, R.raw.cross_the_street);
                break;
            case NombresAudioParaSituaciones.GO_AHEAD :
                mp = MediaPlayer.create(context, R.raw.go_ahead);
                break;
            case NombresAudioParaSituaciones.GO_PAST :
                mp = MediaPlayer.create(context, R.raw.go_past);
                break;
            case NombresAudioParaSituaciones.GO_STRAIGHT :
                mp = MediaPlayer.create(context, R.raw.go_straight);
                break;
            case NombresAudioParaSituaciones.GO_UP_DOWN :
                mp = MediaPlayer.create(context, R.raw.go_up_down);
                break;
            case NombresAudioParaSituaciones.HOW_FAR_IS_IT :
                mp = MediaPlayer.create(context, R.raw.how_far_is_it);
                break;
            case NombresAudioParaSituaciones.IM_LOOKING_FOR :
                mp = MediaPlayer.create(context, R.raw.im_looking_for);
                break;
            case NombresAudioParaSituaciones.IM_LOST :
                mp = MediaPlayer.create(context, R.raw.im_lost);
                break;
            case NombresAudioParaSituaciones.IM_NOT_FROM_AROUND_HERE :
                mp = MediaPlayer.create(context, R.raw.im_not_from_around_here);
                break;
            case NombresAudioParaSituaciones.INTERSECTION :
                mp = MediaPlayer.create(context, R.raw.intersection);
                break;
            case NombresAudioParaSituaciones.IS_IT_FAR :
                mp = MediaPlayer.create(context, R.raw.is_it_far);
                break;
            case NombresAudioParaSituaciones.IS_THIS_THE_RIGHT_WAY_TO_THE_CENTER :
                mp = MediaPlayer.create(context, R.raw.is_this_the_right_way_to_the_center);
                break;
            case NombresAudioParaSituaciones.ITS_METTERS_AWAY :
                mp = MediaPlayer.create(context, R.raw.its_metters_away);
                break;
            case NombresAudioParaSituaciones.ITS_ON_THE_OTHER_SIDE_OF_THE_STREET :
                mp = MediaPlayer.create(context, R.raw.its_on_the_other_side_of_the_street);
                break;
            case NombresAudioParaSituaciones.ITS_OPPOSITE_TO_THE_X :
                mp = MediaPlayer.create(context, R.raw.its_opposite_to_the_x);
                break;
            case NombresAudioParaSituaciones.NEXT_TO :
                mp = MediaPlayer.create(context, R.raw.next_to);
                break;
            case NombresAudioParaSituaciones.ON_THE_LEFT :
                mp = MediaPlayer.create(context, R.raw.on_the_left);
                break;
            case NombresAudioParaSituaciones.ON_THE_RIGHT :
                mp = MediaPlayer.create(context, R.raw.on_the_right);
                break;
            case NombresAudioParaSituaciones.OPPOSITE :
                mp = MediaPlayer.create(context, R.raw.opposite);
                break;
            case NombresAudioParaSituaciones.QUITE_CLOSE :
                mp = MediaPlayer.create(context, R.raw.quite_close);
                break;
            case NombresAudioParaSituaciones.ROAD :
                mp = MediaPlayer.create(context, R.raw.road);
                break;
            case NombresAudioParaSituaciones.ROUNDABOUT :
                mp = MediaPlayer.create(context, R.raw.roundabout);
                break;
            case NombresAudioParaSituaciones.SIDE_STREET :
                mp = MediaPlayer.create(context, R.raw.side_street);
                break;
            case NombresAudioParaSituaciones.SIDEWALK :
                mp = MediaPlayer.create(context, R.raw.sidewalk);
                break;
            case NombresAudioParaSituaciones.STOPLIGHT :
                mp = MediaPlayer.create(context, R.raw.stoplight);
                break;
            case NombresAudioParaSituaciones.STRAIGHT :
                mp = MediaPlayer.create(context, R.raw.straight);
                break;
            case NombresAudioParaSituaciones.TURN_AT_THE_CORNER :
                mp = MediaPlayer.create(context, R.raw.turn_at_the_corner);
                break;
            case NombresAudioParaSituaciones.WHERE_DO_I_CATCH_THE_BUS_FOR :
                mp = MediaPlayer.create(context, R.raw.where_do_i_catch_the_bus_for);
                break;
        }

        if(mp != null){
            mp.start();

        }
        return mp;
    }
}