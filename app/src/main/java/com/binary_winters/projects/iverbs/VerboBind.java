package com.binary_winters.projects.iverbs;

import android.content.Context;
import android.media.MediaPlayer;

import com.binary_winters.projects.iverbs.activities.MainActivity;
import com.binary_winters.projects.iverbs.modelo.VerboGenericoBean;
import com.binary_winters.projects.iverbs.util.AudioRecord;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class VerboBind {
	private static Map<List<String>, VerboEnEsp> mapa;
	private static Map<List<String>, VerboEnEsp> mapaSoloEscritura;
	private VerboStructure verboStructure;
	private Integer cantidadDeRespuestasCorrectas = 0;
	private List<VerboEnEsp> listaDeVerbosEnEspMalRespondidos;
    private List<VerboEnEsp> listaDeVerbosFavoritos = new ArrayList<>();
    private static RealmResults<VerboGenericoBean> listaDeVerbosAgregadosPorUsuario;
    private Realm realm;

	public VerboBind(){
		verboStructure = new VerboStructure();
		this.armarEstructuraConPalabraEspYVerbosIrregulares();
		this.armarEstructuraConPalabraEspYVerbosIrregularesParaSoloEscrituraOn();

		listaDeVerbosEnEspMalRespondidos = new ArrayList<VerboEnEsp>();

		realm = Realm.getDefaultInstance();
		listaDeVerbosAgregadosPorUsuario = realm.where(VerboGenericoBean.class).equalTo("verboEnEsp.VERBO_DESDE_APP", Boolean.FALSE).findAll();

		if(listaDeVerbosAgregadosPorUsuario != null){
            agregarVerbosDeUsuarioEnMapa();
		}

        for(VerboEnEsp verboEnEsp : verboStructure.getListaDeVerbosEnEsp()){
            if(verboEnEsp.getMarkedWithStar()){
                listaDeVerbosFavoritos.add(verboEnEsp);
            }
        }
	}

    public VerboEnEsp generarVerboRandom(Boolean isRadioButtonConTodos){
		Random generarRandom = new Random(System.currentTimeMillis());
        int indiceDeListaAleatorio;
        VerboEnEsp verboElegido;
        if(isRadioButtonConTodos){
            indiceDeListaAleatorio = generarRandom.nextInt(verboStructure.getListaDeVerbosEnEsp().size());
            verboElegido = verboStructure.getListaDeVerbosEnEsp().get(indiceDeListaAleatorio);
            verboStructure.getListaDeVerbosEnEsp().remove(indiceDeListaAleatorio);
        } else{
            indiceDeListaAleatorio = generarRandom.nextInt(this.getListaDeVerbosEnEspFavoritos().size());
            verboElegido = this.getListaDeVerbosEnEspFavoritos().get(indiceDeListaAleatorio);
            this.getListaDeVerbosEnEspFavoritos().remove(indiceDeListaAleatorio);
        }

		return verboElegido;
	}

	public void armarEstructuraConPalabraEspYVerbosIrregulares(){
		mapa = new LinkedHashMap<List<String>, VerboEnEsp>();
		
		mapa.put(verboStructure.getListaArise(), verboStructure.getListaDeVerbosEnEsp().get( 0 ));
		mapa.put(verboStructure.getListaBe1(), verboStructure.getListaDeVerbosEnEsp().get( 1 ));
		mapa.put(verboStructure.getListaBe2(), verboStructure.getListaDeVerbosEnEsp().get( 1 ));
		mapa.put(verboStructure.getListaBeat(), verboStructure.getListaDeVerbosEnEsp().get( 2 ));
		mapa.put(verboStructure.getListaBecome1(), verboStructure.getListaDeVerbosEnEsp().get( 3 ));
		mapa.put(verboStructure.getListaBecome2(), verboStructure.getListaDeVerbosEnEsp().get( 3 ));
		mapa.put(verboStructure.getListaBegin1(), verboStructure.getListaDeVerbosEnEsp().get( 4 ));
		mapa.put(verboStructure.getListaBegin2(), verboStructure.getListaDeVerbosEnEsp().get( 4 ));
		mapa.put(verboStructure.getListaBend(), verboStructure.getListaDeVerbosEnEsp().get( 5 ));
		mapa.put(verboStructure.getListaBound(), verboStructure.getListaDeVerbosEnEsp().get( 6 ));
		mapa.put(verboStructure.getListaBite(), verboStructure.getListaDeVerbosEnEsp().get( 7 ));
		mapa.put(verboStructure.getListaBleed(), verboStructure.getListaDeVerbosEnEsp().get( 8 ));
		mapa.put(verboStructure.getListaBlow1(), verboStructure.getListaDeVerbosEnEsp().get( 9 ));
		mapa.put(verboStructure.getListaBlow2(), verboStructure.getListaDeVerbosEnEsp().get( 9 ));
		mapa.put(verboStructure.getListaBreak1(), verboStructure.getListaDeVerbosEnEsp().get( 10 ));
		mapa.put(verboStructure.getListaBreak2(), verboStructure.getListaDeVerbosEnEsp().get( 10 ));
		mapa.put(verboStructure.getListaBreed(), verboStructure.getListaDeVerbosEnEsp().get( 11 ));
		mapa.put(verboStructure.getListaBring(), verboStructure.getListaDeVerbosEnEsp().get( 12 ));
		mapa.put(verboStructure.getListaBuild(), verboStructure.getListaDeVerbosEnEsp().get( 13 ));
		mapa.put(verboStructure.getListaBurn(), verboStructure.getListaDeVerbosEnEsp().get( 14 ));
		mapa.put(verboStructure.getListaBuy(), verboStructure.getListaDeVerbosEnEsp().get( 15 ));
		mapa.put(verboStructure.getListaCast(), verboStructure.getListaDeVerbosEnEsp().get( 16 ));
		mapa.put(verboStructure.getListaCatch(), verboStructure.getListaDeVerbosEnEsp().get( 17 ));
		mapa.put(verboStructure.getListaCome1(), verboStructure.getListaDeVerbosEnEsp().get( 18 ));
		mapa.put(verboStructure.getListaCome2(), verboStructure.getListaDeVerbosEnEsp().get( 18 ));
		mapa.put(verboStructure.getListaCost(), verboStructure.getListaDeVerbosEnEsp().get( 19 ));
		mapa.put(verboStructure.getListaCut(), verboStructure.getListaDeVerbosEnEsp().get( 20 ));
		mapa.put(verboStructure.getListaChoose(), verboStructure.getListaDeVerbosEnEsp().get( 21 ));
		mapa.put(verboStructure.getListaDeal(), verboStructure.getListaDeVerbosEnEsp().get( 22 ));
		mapa.put(verboStructure.getListaDig(), verboStructure.getListaDeVerbosEnEsp().get( 23 ));
		mapa.put(verboStructure.getListaDo(), verboStructure.getListaDeVerbosEnEsp().get( 24 ));
		mapa.put(verboStructure.getListaDraw(), verboStructure.getListaDeVerbosEnEsp().get( 25 ));
		mapa.put(verboStructure.getListaDream(), verboStructure.getListaDeVerbosEnEsp().get( 26 ));
		mapa.put(verboStructure.getListaDrink1(), verboStructure.getListaDeVerbosEnEsp().get( 27 ));
		mapa.put(verboStructure.getListaDrink2(), verboStructure.getListaDeVerbosEnEsp().get( 27 ));
		mapa.put(verboStructure.getListaDrive(), verboStructure.getListaDeVerbosEnEsp().get( 28 ));
		mapa.put(verboStructure.getListaEat(), verboStructure.getListaDeVerbosEnEsp().get( 29 ));
		mapa.put(verboStructure.getListaFall(), verboStructure.getListaDeVerbosEnEsp().get( 30 ));
		mapa.put(verboStructure.getListaFeed(), verboStructure.getListaDeVerbosEnEsp().get( 31 ));
		mapa.put(verboStructure.getListaFeel(), verboStructure.getListaDeVerbosEnEsp().get( 32 ));
		mapa.put(verboStructure.getListaFind(), verboStructure.getListaDeVerbosEnEsp().get( 33));
		mapa.put(verboStructure.getListaFlee(), verboStructure.getListaDeVerbosEnEsp().get( 34 ));
		mapa.put(verboStructure.getListaFly1(), verboStructure.getListaDeVerbosEnEsp().get( 35 ));
		mapa.put(verboStructure.getListaFly2(), verboStructure.getListaDeVerbosEnEsp().get( 35 ));
		mapa.put(verboStructure.getListaFly3(), verboStructure.getListaDeVerbosEnEsp().get( 35 ));
		mapa.put(verboStructure.getListaForbid(), verboStructure.getListaDeVerbosEnEsp().get( 36 ));
		mapa.put(verboStructure.getListaForget(), verboStructure.getListaDeVerbosEnEsp().get( 37 ));
		mapa.put(verboStructure.getListaForgive(), verboStructure.getListaDeVerbosEnEsp().get( 38 ));
		mapa.put(verboStructure.getListaGet(), verboStructure.getListaDeVerbosEnEsp().get( 39 ));
		mapa.put(verboStructure.getListaGive(), verboStructure.getListaDeVerbosEnEsp().get( 40 ));
		mapa.put(verboStructure.getListaGo1(), verboStructure.getListaDeVerbosEnEsp().get( 41 ));
		mapa.put(verboStructure.getListaGo2(), verboStructure.getListaDeVerbosEnEsp().get( 41 ));
		mapa.put(verboStructure.getListaGrow1(), verboStructure.getListaDeVerbosEnEsp().get( 42 ));
		mapa.put(verboStructure.getListaGrow2(), verboStructure.getListaDeVerbosEnEsp().get( 42 ));
		mapa.put(verboStructure.getListaHang1(), verboStructure.getListaDeVerbosEnEsp().get( 43 ));
		mapa.put(verboStructure.getListaHang2(), verboStructure.getListaDeVerbosEnEsp().get( 43 ));
		mapa.put(verboStructure.getListaHave1(), verboStructure.getListaDeVerbosEnEsp().get( 44 ));
		mapa.put(verboStructure.getListaHave2(), verboStructure.getListaDeVerbosEnEsp().get( 44 ));
		mapa.put(verboStructure.getListaHear1(), verboStructure.getListaDeVerbosEnEsp().get( 45 ));
		mapa.put(verboStructure.getListaHear2(), verboStructure.getListaDeVerbosEnEsp().get( 45 ));
		mapa.put(verboStructure.getListaHide1(), verboStructure.getListaDeVerbosEnEsp().get( 46 ));
		mapa.put(verboStructure.getListaHide2(), verboStructure.getListaDeVerbosEnEsp().get( 46 ));
		mapa.put(verboStructure.getListaHit1(), verboStructure.getListaDeVerbosEnEsp().get( 47 ));
		mapa.put(verboStructure.getListaHit2(), verboStructure.getListaDeVerbosEnEsp().get( 47 ));
		mapa.put(verboStructure.getListaHold1(), verboStructure.getListaDeVerbosEnEsp().get( 48 ));
		mapa.put(verboStructure.getListaHold2(), verboStructure.getListaDeVerbosEnEsp().get( 48 ));
		mapa.put(verboStructure.getListaHurt1(), verboStructure.getListaDeVerbosEnEsp().get( 49 ));
		mapa.put(verboStructure.getListaHurt2(), verboStructure.getListaDeVerbosEnEsp().get( 49 ));
		mapa.put(verboStructure.getListaKeep1(), verboStructure.getListaDeVerbosEnEsp().get( 50 ));
		mapa.put(verboStructure.getListaKeep2(), verboStructure.getListaDeVerbosEnEsp().get( 50 ));
		mapa.put(verboStructure.getListaKnow1(), verboStructure.getListaDeVerbosEnEsp().get( 51 ));
		mapa.put(verboStructure.getListaKnow2(), verboStructure.getListaDeVerbosEnEsp().get( 51 ));
		mapa.put(verboStructure.getListaKnow3(), verboStructure.getListaDeVerbosEnEsp().get( 51 ));
		mapa.put(verboStructure.getListaKneel(), verboStructure.getListaDeVerbosEnEsp().get( 52 ));
		mapa.put(verboStructure.getListaLay(), verboStructure.getListaDeVerbosEnEsp().get( 53 ));
		mapa.put(verboStructure.getListaLead(), verboStructure.getListaDeVerbosEnEsp().get( 54 ));
		mapa.put(verboStructure.getListaLean(), verboStructure.getListaDeVerbosEnEsp().get( 55 ));
		mapa.put(verboStructure.getListaLeave(), verboStructure.getListaDeVerbosEnEsp().get( 56 ));
		mapa.put(verboStructure.getListaLend(), verboStructure.getListaDeVerbosEnEsp().get( 57 ));
		mapa.put(verboStructure.getListaLet(), verboStructure.getListaDeVerbosEnEsp().get( 58 ));
		mapa.put(verboStructure.getListaLie(), verboStructure.getListaDeVerbosEnEsp().get( 59 ));
		mapa.put(verboStructure.getListaLight(), verboStructure.getListaDeVerbosEnEsp().get( 60 ));
		mapa.put(verboStructure.getListaLose(), verboStructure.getListaDeVerbosEnEsp().get( 61 ));
		mapa.put(verboStructure.getListaMake1(), verboStructure.getListaDeVerbosEnEsp().get( 62 ));
		mapa.put(verboStructure.getListaMake2(), verboStructure.getListaDeVerbosEnEsp().get( 62 ));
		mapa.put(verboStructure.getListaMean(), verboStructure.getListaDeVerbosEnEsp().get( 63 ));
		mapa.put(verboStructure.getListaMeet(), verboStructure.getListaDeVerbosEnEsp().get( 64 ));
		mapa.put(verboStructure.getListaMistake1(), verboStructure.getListaDeVerbosEnEsp().get( 65 ));
		mapa.put(verboStructure.getListaMistake2(), verboStructure.getListaDeVerbosEnEsp().get( 65 ));
		mapa.put(verboStructure.getListaOvercome1(), verboStructure.getListaDeVerbosEnEsp().get( 66 ));
		mapa.put(verboStructure.getListaOvercome2(), verboStructure.getListaDeVerbosEnEsp().get( 66 ));
		mapa.put(verboStructure.getListaPay(), verboStructure.getListaDeVerbosEnEsp().get( 67 ));
		mapa.put(verboStructure.getListaPut(), verboStructure.getListaDeVerbosEnEsp().get( 68 ));
		mapa.put(verboStructure.getListaRead(), verboStructure.getListaDeVerbosEnEsp().get( 69 ));
		mapa.put(verboStructure.getListaRide(), verboStructure.getListaDeVerbosEnEsp().get( 70 ));
		mapa.put(verboStructure.getListaRing(), verboStructure.getListaDeVerbosEnEsp().get( 71 ));
		mapa.put(verboStructure.getListaRise(), verboStructure.getListaDeVerbosEnEsp().get( 72 ));
		mapa.put(verboStructure.getListaRun(), verboStructure.getListaDeVerbosEnEsp().get( 73 ));
		mapa.put(verboStructure.getListaSay1(), verboStructure.getListaDeVerbosEnEsp().get( 74 ));
		mapa.put(verboStructure.getListaSay2(), verboStructure.getListaDeVerbosEnEsp().get( 74 ));
		mapa.put(verboStructure.getListaSee(), verboStructure.getListaDeVerbosEnEsp().get( 75 ));
		mapa.put(verboStructure.getListaSell(), verboStructure.getListaDeVerbosEnEsp().get( 76 ));
		mapa.put(verboStructure.getListaSend(), verboStructure.getListaDeVerbosEnEsp().get( 77 ));
		mapa.put(verboStructure.getListaShake1(), verboStructure.getListaDeVerbosEnEsp().get( 78 ));
		mapa.put(verboStructure.getListaShake2(), verboStructure.getListaDeVerbosEnEsp().get( 78 ));
		mapa.put(verboStructure.getListaShine(), verboStructure.getListaDeVerbosEnEsp().get( 79 ));
		mapa.put(verboStructure.getListaShoot(), verboStructure.getListaDeVerbosEnEsp().get( 80 ));
		mapa.put(verboStructure.getListaShow1(), verboStructure.getListaDeVerbosEnEsp().get( 81 ));
		mapa.put(verboStructure.getListaShow2(), verboStructure.getListaDeVerbosEnEsp().get( 81 ));
		mapa.put(verboStructure.getListaShut(), verboStructure.getListaDeVerbosEnEsp().get( 82 ));
		mapa.put(verboStructure.getListaSing(), verboStructure.getListaDeVerbosEnEsp().get( 83 ));
		mapa.put(verboStructure.getListaSit(), verboStructure.getListaDeVerbosEnEsp().get( 84 ));
		mapa.put(verboStructure.getListaSleep(), verboStructure.getListaDeVerbosEnEsp().get( 85 ));
		mapa.put(verboStructure.getListaSlide(), verboStructure.getListaDeVerbosEnEsp().get( 86 ));
		mapa.put(verboStructure.getListaSmell(), verboStructure.getListaDeVerbosEnEsp().get( 87 ));
		mapa.put(verboStructure.getListaSow1(), verboStructure.getListaDeVerbosEnEsp().get( 88 ));
		mapa.put(verboStructure.getListaSow2(), verboStructure.getListaDeVerbosEnEsp().get( 88 ));
		mapa.put(verboStructure.getListaSpeak1(), verboStructure.getListaDeVerbosEnEsp().get( 89 ));
		mapa.put(verboStructure.getListaSpeak2(), verboStructure.getListaDeVerbosEnEsp().get( 89 ));
		mapa.put(verboStructure.getListaSpeed(), verboStructure.getListaDeVerbosEnEsp().get( 90 ));
		mapa.put(verboStructure.getListaSpell(), verboStructure.getListaDeVerbosEnEsp().get( 91 ));
		mapa.put(verboStructure.getListaSpend(), verboStructure.getListaDeVerbosEnEsp().get( 92 ));
		mapa.put(verboStructure.getListaSpill(), verboStructure.getListaDeVerbosEnEsp().get( 93 ));
		mapa.put(verboStructure.getListaSpit(), verboStructure.getListaDeVerbosEnEsp().get( 94 ));
		mapa.put(verboStructure.getListaSpoil(), verboStructure.getListaDeVerbosEnEsp().get( 95 ));
		mapa.put(verboStructure.getListaSpread(), verboStructure.getListaDeVerbosEnEsp().get( 96 ));
		mapa.put(verboStructure.getListaSteal(), verboStructure.getListaDeVerbosEnEsp().get( 97 ));
		mapa.put(verboStructure.getListaSting(), verboStructure.getListaDeVerbosEnEsp().get( 98 ));
		mapa.put(verboStructure.getListaStink1(), verboStructure.getListaDeVerbosEnEsp().get( 99 ));
		mapa.put(verboStructure.getListaStink2(), verboStructure.getListaDeVerbosEnEsp().get( 99 ));
		mapa.put(verboStructure.getListaSwear1(), verboStructure.getListaDeVerbosEnEsp().get( 100 ));
		mapa.put(verboStructure.getListaSwear2(), verboStructure.getListaDeVerbosEnEsp().get( 100 ));
		mapa.put(verboStructure.getListaSweat1(), verboStructure.getListaDeVerbosEnEsp().get( 101 ));
		mapa.put(verboStructure.getListaSweat2(), verboStructure.getListaDeVerbosEnEsp().get( 101 ));
		mapa.put(verboStructure.getListaSweep1(), verboStructure.getListaDeVerbosEnEsp().get( 102 ));
		mapa.put(verboStructure.getListaSweep2(), verboStructure.getListaDeVerbosEnEsp().get( 102 ));
		mapa.put(verboStructure.getListaSwim1(), verboStructure.getListaDeVerbosEnEsp().get( 103 ));
		mapa.put(verboStructure.getListaSwim2(), verboStructure.getListaDeVerbosEnEsp().get( 103 ));
		mapa.put(verboStructure.getListaTake1(), verboStructure.getListaDeVerbosEnEsp().get( 104 ));
		mapa.put(verboStructure.getListaTake2(), verboStructure.getListaDeVerbosEnEsp().get( 104 ));
		mapa.put(verboStructure.getListaTeach(), verboStructure.getListaDeVerbosEnEsp().get( 105 ));
		mapa.put(verboStructure.getListaTell(), verboStructure.getListaDeVerbosEnEsp().get( 106 ));
		mapa.put(verboStructure.getListaThink1(), verboStructure.getListaDeVerbosEnEsp().get( 107 ));
		mapa.put(verboStructure.getListaThink2(), verboStructure.getListaDeVerbosEnEsp().get( 107 ));
		mapa.put(verboStructure.getListaThrow1(), verboStructure.getListaDeVerbosEnEsp().get( 108 ));
		mapa.put(verboStructure.getListaThrow2(), verboStructure.getListaDeVerbosEnEsp().get( 108 ));
		mapa.put(verboStructure.getListaUnderstand(), verboStructure.getListaDeVerbosEnEsp().get( 109 ));
		mapa.put(verboStructure.getListaWake1(), verboStructure.getListaDeVerbosEnEsp().get( 110 ));
		mapa.put(verboStructure.getListaWake2(), verboStructure.getListaDeVerbosEnEsp().get( 110 ));
		mapa.put(verboStructure.getListaWake3(), verboStructure.getListaDeVerbosEnEsp().get( 110 ));
		mapa.put(verboStructure.getListaWake4(), verboStructure.getListaDeVerbosEnEsp().get( 110 ));
		mapa.put(verboStructure.getListaWake5(), verboStructure.getListaDeVerbosEnEsp().get( 110 ));
		mapa.put(verboStructure.getListaWear1(), verboStructure.getListaDeVerbosEnEsp().get( 111 ));
		mapa.put(verboStructure.getListaWear2(), verboStructure.getListaDeVerbosEnEsp().get( 111 ));
		mapa.put(verboStructure.getListaWeep1(), verboStructure.getListaDeVerbosEnEsp().get( 112 ));
		mapa.put(verboStructure.getListaWeep2(), verboStructure.getListaDeVerbosEnEsp().get( 112 ));
		mapa.put(verboStructure.getListaWet1(), verboStructure.getListaDeVerbosEnEsp().get( 113 ));
		mapa.put(verboStructure.getListaWet2(), verboStructure.getListaDeVerbosEnEsp().get( 113 ));
		mapa.put(verboStructure.getListaWin1(), verboStructure.getListaDeVerbosEnEsp().get( 114 ));
		mapa.put(verboStructure.getListaWin2(), verboStructure.getListaDeVerbosEnEsp().get( 114 ));
		mapa.put(verboStructure.getListaWrite(), verboStructure.getListaDeVerbosEnEsp().get( 115 ));
	}

	public void armarEstructuraConPalabraEspYVerbosIrregularesParaSoloEscrituraOn(){
		mapaSoloEscritura = new LinkedHashMap<List<String>, VerboEnEsp>();

		for (Map.Entry<List<String>, VerboEnEsp> elementoDeMapa : mapa.entrySet()){
			List<String> cadenaEnInglesAntigua = elementoDeMapa.getKey();
			List<String> cadenaEnInglesNueva = new ArrayList<>();

			for(int i=0; i<cadenaEnInglesAntigua.size(); i++){
				String[] divisionesConGuion = cadenaEnInglesAntigua.get(i).split("-");
				cadenaEnInglesNueva.add(divisionesConGuion[0]);
			}

			mapaSoloEscritura.put(cadenaEnInglesNueva, elementoDeMapa.getValue());

		}
	}

	public String buscarCadenaDeUsuarioEnMapa(List<String> cadenaEscritaPorUsuario, VerboEnEsp verboEnEspRandom,
											  Boolean isSoloEscrituraOn){
		VerboEnEsp verboEnEspHalladoEnMapa;

		if(isSoloEscrituraOn == false){
			verboEnEspHalladoEnMapa = mapa.get(cadenaEscritaPorUsuario);
		} else{
			verboEnEspHalladoEnMapa = mapaSoloEscritura.get(cadenaEscritaPorUsuario);
		}

		String cadenaDeVerboEnEspHalladoEnMapa;
		if(verboEnEspHalladoEnMapa != null){
			cadenaDeVerboEnEspHalladoEnMapa = verboEnEspHalladoEnMapa.getNombreDeVerbo();
		} else{
			cadenaDeVerboEnEspHalladoEnMapa = "";
		}

		String respuesta = "";

		// El usuario ingresó una cadena que existe en el mapa pero quizá no sea la que se corresponda con el verbo en esp
		if(verboEnEspRandom != null){
			// Si la cadena ingresada por el usuario coincide con la requerida.
			if(verboEnEspRandom.getNombreDeVerbo().equals(cadenaDeVerboEnEspHalladoEnMapa)){
				cantidadDeRespuestasCorrectas++;

				if( listaDeVerbosEnEspMalRespondidos != null
						&& listaDeVerbosEnEspMalRespondidos.contains(verboEnEspRandom) ){
					listaDeVerbosEnEspMalRespondidos.remove(verboEnEspRandom);
				}

				respuesta = "RIGHT \uD83D\uDE42";
			} else{
                Boolean respuestaCorrectaHallada = Boolean.FALSE;

                // Busca en el mapa la traducción correspondiente al verbo en español traducido mal por el usuario
                for (Map.Entry<List<String>, VerboEnEsp> elementoDeMapa : mapa.entrySet()){
                    if( elementoDeMapa.getValue().getNombreDeVerbo().equals( verboEnEspRandom.getNombreDeVerbo() )
                            && respuestaCorrectaHallada.equals(Boolean.FALSE) ){
                        respuestaCorrectaHallada = Boolean.TRUE;

						if(listaDeVerbosEnEspMalRespondidos.contains(verboEnEspRandom) == false){
							listaDeVerbosEnEspMalRespondidos.add( elementoDeMapa.getValue());
						}

                        respuesta = "WRONG \uD83D\uDE41 -  RIGHT ANSWER : " + elementoDeMapa.getKey();
                    }
                }
            }
		}
		return respuesta;
	}

	public void agregarVerbosDeUsuarioEnMapa(){
		for(int i=0; i<listaDeVerbosAgregadosPorUsuario.size(); i++){
			VerboGenericoBean verboGenericoBean = listaDeVerbosAgregadosPorUsuario.get(i);

			List<String> verbosEnIngles = new ArrayList<>();

            String[] divisiones = verboGenericoBean.getCadenaDeVerbos().split(" ");

            String verboEnIngCol1 = divisiones[0];
            String verboEnIngCol2 = divisiones[1];
            String verboEnIngCol3 = divisiones[2];

			verbosEnIngles.add(verboEnIngCol1);
            verbosEnIngles.add(verboEnIngCol2);
            verbosEnIngles.add(verboEnIngCol3);

			VerboEnEsp verboEnEsp = new VerboEnEsp(verboGenericoBean.getVerboEnEsp().getNombreDeVerbo(),
					verboGenericoBean.getVerboEnEsp().getVERBO_DESDE_APP(), verboGenericoBean.getVerboEnEsp().getMarkedWithStar());
            verboEnEsp.setId(verboGenericoBean.getVerboEnEsp().getId());

            mapa.put(verbosEnIngles, verboEnEsp);
		}
	}

	public Integer getCantidadDeRespuestasCorrectas() {
		return cantidadDeRespuestasCorrectas;
	}

	public void setCantidadDeRespuestasCorrectas(
			Integer cantidadDeRespuestasCorrectas) {
		this.cantidadDeRespuestasCorrectas = cantidadDeRespuestasCorrectas;
	}

	public List<VerboEnEsp> getListaDeVerbosEnEspMalRespondidos() {
		return listaDeVerbosEnEspMalRespondidos;
	}

	public void setListaDeVerbosEnEspMalRespondidos(
			List<VerboEnEsp> listaDeVerbosEnEspMalRespondidos) {
		this.listaDeVerbosEnEspMalRespondidos = listaDeVerbosEnEspMalRespondidos;
	}

	public Map<List<String>, VerboEnEsp> getMapa() {
		return mapa;
	}

	public Integer darSizeDeListaTotalDeVerbosEnEsp(Boolean isRadioButtonConTodos){
		Integer cantidadDeVerbos = null;
		if(isRadioButtonConTodos){
			cantidadDeVerbos = verboStructure.getListaDeVerbosEnEsp().size();
		} else{
            cantidadDeVerbos = this.getListaDeVerbosEnEspFavoritos().size();
		}

		return cantidadDeVerbos;
	}

	public List<VerboEnEsp> getListaDeVerbosEnEsp(){
		return verboStructure.getListaDeVerbosEnEsp();
	}

	public List<VerboEnEsp> getListaDeVerbosEnEspFavoritos(){
        return this.listaDeVerbosFavoritos;
    }

    public Integer getCantidadDeVerbosEnEsp(){
        return verboStructure.getListaDeVerbosEnEsp().size();
    }

    public Integer getCantidadDeVerbosEnEspFavoritos(){
        return getListaDeVerbosEnEspFavoritos().size();
    }

	public static VerboGenericoBean buscarEnVerbosAgregosPorUsuarioSegunNombreVerboEnEsp(String nombreVerboEnEsp){
		Boolean resultado = Boolean.FALSE;

		int index = 0;
		VerboGenericoBean verboGenericoBeanHallado = null;

		while(index < listaDeVerbosAgregadosPorUsuario.size() && resultado.equals(Boolean.FALSE)){
			if(listaDeVerbosAgregadosPorUsuario.get(index).getVerboEnEsp().getNombreDeVerbo().equals(nombreVerboEnEsp)){
				verboGenericoBeanHallado = listaDeVerbosAgregadosPorUsuario.get(index);
				resultado = Boolean.TRUE;
			}
			index++;
		}

		return verboGenericoBeanHallado;
	}

	public static MediaPlayer cargarAudioSegunVerbo(Context context, MediaPlayer mp, VerboEnEsp verboEnEsp){
		VerboGenericoBean verboGenericoBeanHallado = null;

		if(verboEnEsp.getVERBO_DESDE_APP() == false){
			verboGenericoBeanHallado = buscarEnVerbosAgregosPorUsuarioSegunNombreVerboEnEsp(verboEnEsp.getNombreDeVerbo());

			if(verboGenericoBeanHallado != null){
				AudioRecord.startPlaying(verboGenericoBeanHallado.getNombreArchivoAudio());
			}
		} else{
			switch (verboEnEsp.getNombreDeVerbo()) {
				case ValorDeVerbosEsp.SURGIR_LEVANTARSE:
					mp = MediaPlayer.create(context, R.raw.surgir_levantarse);
					break;
				case ValorDeVerbosEsp.SER:
					mp = MediaPlayer.create(context, R.raw.ser);
					break;
				case ValorDeVerbosEsp.GOLPEAR_REPETIDAMENTE:
					mp = MediaPlayer.create(context, R.raw.golpear_repetidamente);
					break;
				case ValorDeVerbosEsp.CONVERTIRSE:
					mp = MediaPlayer.create(context, R.raw.convertirse);
					break;
				case ValorDeVerbosEsp.COMENZAR:
					mp = MediaPlayer.create(context, R.raw.comenzar);
					break;
				case ValorDeVerbosEsp.DOBLAR:
					mp = MediaPlayer.create(context, R.raw.doblar);
					break;
				case ValorDeVerbosEsp.ATAR:
					mp = MediaPlayer.create(context, R.raw.atar);
					break;
				case ValorDeVerbosEsp.MORDER:
					mp = MediaPlayer.create(context, R.raw.morder);
					break;
				case ValorDeVerbosEsp.SANGRAR:
					mp = MediaPlayer.create(context, R.raw.sangrar);
					break;
				case ValorDeVerbosEsp.SOPLAR:
					mp = MediaPlayer.create(context, R.raw.soplar);
					break;
				case ValorDeVerbosEsp.ROMPER:
					mp = MediaPlayer.create(context, R.raw.romper);
					break;
				case ValorDeVerbosEsp.CRIAR:
					mp = MediaPlayer.create(context, R.raw.criar);
					break;
				case ValorDeVerbosEsp.TRAER:
					mp = MediaPlayer.create(context, R.raw.traer);
					break;
				case ValorDeVerbosEsp.CONSTRUIR:
					mp = MediaPlayer.create(context, R.raw.construir);
					break;
				case ValorDeVerbosEsp.QUEMAR:
					mp = MediaPlayer.create(context, R.raw.quemar);
					break;
				case ValorDeVerbosEsp.COMPRAR:
					mp = MediaPlayer.create(context, R.raw.comprar);
					break;
				case ValorDeVerbosEsp.ARROJAR:
					mp = MediaPlayer.create(context, R.raw.arrojar);
					break;
				case ValorDeVerbosEsp.ATRAPAR:
					mp = MediaPlayer.create(context, R.raw.atrapar);
					break;
				case ValorDeVerbosEsp.VENIR:
					mp = MediaPlayer.create(context, R.raw.venir);
					break;
				case ValorDeVerbosEsp.COSTAR:
					mp = MediaPlayer.create(context, R.raw.costar);
					break;
				case ValorDeVerbosEsp.CORTAR:
					mp = MediaPlayer.create(context, R.raw.cortar);
					break;
				case ValorDeVerbosEsp.ELEGIR:
					mp = MediaPlayer.create(context, R.raw.elegir);
					break;
				case ValorDeVerbosEsp.NEGOCIAR:
					mp = MediaPlayer.create(context, R.raw.negociar);
					break;
				case ValorDeVerbosEsp.CAVAR:
					mp = MediaPlayer.create(context, R.raw.cavar);
					break;
				case ValorDeVerbosEsp.HACER_TAREAS:
					mp = MediaPlayer.create(context, R.raw.hacer_tareas);
					break;
				case ValorDeVerbosEsp.DIBUJAR:
					mp = MediaPlayer.create(context, R.raw.dibujar);
					break;
				case ValorDeVerbosEsp.SONIAR:
					mp = MediaPlayer.create(context, R.raw.soniar);
					break;
				case ValorDeVerbosEsp.BEBER:
					mp = MediaPlayer.create(context, R.raw.beber);
					break;
				case ValorDeVerbosEsp.CONDUCIR:
					mp = MediaPlayer.create(context, R.raw.conducir);
					break;
				case ValorDeVerbosEsp.COMER:
					mp = MediaPlayer.create(context, R.raw.comer);
					break;
				case ValorDeVerbosEsp.CAER:
					mp = MediaPlayer.create(context, R.raw.caer);
					break;
				case ValorDeVerbosEsp.ALIMENTAR:
					mp = MediaPlayer.create(context, R.raw.alimentar);
					break;
				case ValorDeVerbosEsp.SENTIR:
					mp = MediaPlayer.create(context, R.raw.sentir);
					break;
				case ValorDeVerbosEsp.ENCONTRAR:
					mp = MediaPlayer.create(context, R.raw.encontrar);
					break;
				case ValorDeVerbosEsp.HUIR:
					mp = MediaPlayer.create(context, R.raw.huir);
					break;
				case ValorDeVerbosEsp.VOLAR:
					mp = MediaPlayer.create(context, R.raw.volar);
					break;
				case ValorDeVerbosEsp.PROHIBIR:
					mp = MediaPlayer.create(context, R.raw.prohibir);
					break;
				case ValorDeVerbosEsp.OLVIDAR:
					mp = MediaPlayer.create(context, R.raw.olvidar);
					break;
				case ValorDeVerbosEsp.PERDONAR:
					mp = MediaPlayer.create(context, R.raw.perdonar);
					break;
				case ValorDeVerbosEsp.OBTENER:
					mp = MediaPlayer.create(context, R.raw.obtener);
					break;
				case ValorDeVerbosEsp.DAR:
					mp = MediaPlayer.create(context, R.raw.dar);
					break;
				case ValorDeVerbosEsp.IR:
					mp = MediaPlayer.create(context, R.raw.ir);
					break;
				case ValorDeVerbosEsp.CRECER:
					mp = MediaPlayer.create(context, R.raw.crecer);
					break;
				case ValorDeVerbosEsp.COLGAR:
					mp = MediaPlayer.create(context, R.raw.colgar);
					break;
				case ValorDeVerbosEsp.TENER:
					mp = MediaPlayer.create(context, R.raw.tener);
					break;
				case ValorDeVerbosEsp.OIR:
					mp = MediaPlayer.create(context, R.raw.oir);
					break;
				case ValorDeVerbosEsp.OCULTAR:
					mp = MediaPlayer.create(context, R.raw.ocultar);
					break;
				case ValorDeVerbosEsp.GOLPEAR:
					mp = MediaPlayer.create(context, R.raw.golpear);
					break;
				case ValorDeVerbosEsp.SOSTENER:
					mp = MediaPlayer.create(context, R.raw.sostener);
					break;
				case ValorDeVerbosEsp.HERIR:
					mp = MediaPlayer.create(context, R.raw.herir);
					break;
				case ValorDeVerbosEsp.CONSERVAR:
					mp = MediaPlayer.create(context, R.raw.conservar);
					break;
				case ValorDeVerbosEsp.SABER:
					mp = MediaPlayer.create(context, R.raw.saber);
					break;
				case ValorDeVerbosEsp.ARRODILLARSE:
					mp = MediaPlayer.create(context, R.raw.arrodillarse);
					break;
				case ValorDeVerbosEsp.PONER_CUIDADOSAMENTE:
					mp = MediaPlayer.create(context, R.raw.poner_cuidadosamente);
					break;
				case ValorDeVerbosEsp.LIDERAR:
					mp = MediaPlayer.create(context, R.raw.liderar);
					break;
				case ValorDeVerbosEsp.APOYARSE:
					mp = MediaPlayer.create(context, R.raw.apoyarse);
					break;
				case ValorDeVerbosEsp.SALIR:
					mp = MediaPlayer.create(context, R.raw.salir);
					break;
				case ValorDeVerbosEsp.PRESTAR:
					mp = MediaPlayer.create(context, R.raw.prestar);
					break;
				case ValorDeVerbosEsp.PERMITIR:
					mp = MediaPlayer.create(context, R.raw.permitir);
					break;
				case ValorDeVerbosEsp.MENTIR:
					mp = MediaPlayer.create(context, R.raw.mentir);
					break;
				case ValorDeVerbosEsp.ENCENDER:
					mp = MediaPlayer.create(context, R.raw.encender);
					break;
				case ValorDeVerbosEsp.PERDER:
					mp = MediaPlayer.create(context, R.raw.perder);
					break;
				case ValorDeVerbosEsp.HACER_PRODUCIR:
					mp = MediaPlayer.create(context, R.raw.hacer_producir);
					break;
				case ValorDeVerbosEsp.SIGNIFICAR:
					mp = MediaPlayer.create(context, R.raw.significar);
					break;
				case ValorDeVerbosEsp.REUNIRSE:
					mp = MediaPlayer.create(context, R.raw.reunirse);
					break;
				case ValorDeVerbosEsp.EQUIVOCARSE:
					mp = MediaPlayer.create(context, R.raw.equivocarse);
					break;
				case ValorDeVerbosEsp.VENCER:
					mp = MediaPlayer.create(context, R.raw.vencer);
					break;
				case ValorDeVerbosEsp.PAGAR:
					mp = MediaPlayer.create(context, R.raw.pagar);
					break;
				case ValorDeVerbosEsp.PONER:
					mp = MediaPlayer.create(context, R.raw.poner);
					break;
				case ValorDeVerbosEsp.LEER:
					mp = MediaPlayer.create(context, R.raw.leer);
					break;
				case ValorDeVerbosEsp.MONTAR:
					mp = MediaPlayer.create(context, R.raw.montar);
					break;
				case ValorDeVerbosEsp.LLAMAR:
					mp = MediaPlayer.create(context, R.raw.llamar);
					break;
				case ValorDeVerbosEsp.SUBIR:
					mp = MediaPlayer.create(context, R.raw.subir);
					break;
				case ValorDeVerbosEsp.CORRER:
					mp = MediaPlayer.create(context, R.raw.correr);
					break;
				case ValorDeVerbosEsp.DECIR:
					mp = MediaPlayer.create(context, R.raw.decir);
					break;
				case ValorDeVerbosEsp.VER:
					mp = MediaPlayer.create(context, R.raw.ver);
					break;
				case ValorDeVerbosEsp.VENDER:
					mp = MediaPlayer.create(context, R.raw.vender);
					break;
				case ValorDeVerbosEsp.ENVIAR:
					mp = MediaPlayer.create(context, R.raw.enviar);
					break;
				case ValorDeVerbosEsp.SACUDIR:
					mp = MediaPlayer.create(context, R.raw.sacudir);
					break;
				case ValorDeVerbosEsp.BRILLAR:
					mp = MediaPlayer.create(context, R.raw.brillar);
					break;
				case ValorDeVerbosEsp.DISPARAR:
					mp = MediaPlayer.create(context, R.raw.disparar);
					break;
				case ValorDeVerbosEsp.MOSTRAR:
					mp = MediaPlayer.create(context, R.raw.mostrar);
					break;
				case ValorDeVerbosEsp.CERRAR:
					mp = MediaPlayer.create(context, R.raw.cerrar);
					break;
				case ValorDeVerbosEsp.CANTAR:
					mp = MediaPlayer.create(context, R.raw.cantar);
					break;
				case ValorDeVerbosEsp.SENTARSE:
					mp = MediaPlayer.create(context, R.raw.sentarse);
					break;
				case ValorDeVerbosEsp.DORMIR:
					mp = MediaPlayer.create(context, R.raw.dormir);
					break;
				case ValorDeVerbosEsp.RESBALAR:
					mp = MediaPlayer.create(context, R.raw.resbalar);
					break;
				case ValorDeVerbosEsp.OLER:
					mp = MediaPlayer.create(context, R.raw.oler);
					break;
				case ValorDeVerbosEsp.SEMBRAR:
					mp = MediaPlayer.create(context, R.raw.sembrar);
					break;
				case ValorDeVerbosEsp.HABLAR:
					mp = MediaPlayer.create(context, R.raw.hablar);
					break;
				case ValorDeVerbosEsp.ACELERAR:
					mp = MediaPlayer.create(context, R.raw.acelerar);
					break;
				case ValorDeVerbosEsp.DELETREAR:
					mp = MediaPlayer.create(context, R.raw.deletrear);
					break;
				case ValorDeVerbosEsp.GASTAR:
					mp = MediaPlayer.create(context, R.raw.gastar);
					break;
				case ValorDeVerbosEsp.DERRAMAR:
					mp = MediaPlayer.create(context, R.raw.derramar);
					break;
				case ValorDeVerbosEsp.ESCUPIR:
					mp = MediaPlayer.create(context, R.raw.escupir);
					break;
				case ValorDeVerbosEsp.ESTROPEAR:
					mp = MediaPlayer.create(context, R.raw.estropear);
					break;
				case ValorDeVerbosEsp.EXTENDER:
					mp = MediaPlayer.create(context, R.raw.extender);
					break;
				case ValorDeVerbosEsp.ROBAR:
					mp = MediaPlayer.create(context, R.raw.robar);
					break;
				case ValorDeVerbosEsp.PICAR:
					mp = MediaPlayer.create(context, R.raw.picar);
					break;
				case ValorDeVerbosEsp.APESTAR:
					mp = MediaPlayer.create(context, R.raw.apestar);
					break;
				case ValorDeVerbosEsp.SUDAR:
					mp = MediaPlayer.create(context, R.raw.sudar);
					break;
				case ValorDeVerbosEsp.JURAR:
					mp = MediaPlayer.create(context, R.raw.jurar);
					break;
				case ValorDeVerbosEsp.BARRER:
					mp = MediaPlayer.create(context, R.raw.barrer);
					break;
				case ValorDeVerbosEsp.NADAR:
					mp = MediaPlayer.create(context, R.raw.nadar);
					break;
				case ValorDeVerbosEsp.TOMAR:
					mp = MediaPlayer.create(context, R.raw.tomar);
					break;
				case ValorDeVerbosEsp.ENSENIAR:
					mp = MediaPlayer.create(context, R.raw.enseniar);
					break;
				case ValorDeVerbosEsp.COMENTAR:
					mp = MediaPlayer.create(context, R.raw.comentar);
					break;
				case ValorDeVerbosEsp.PENSAR:
					mp = MediaPlayer.create(context, R.raw.pensar);
					break;
				case ValorDeVerbosEsp.LANZAR:
					mp = MediaPlayer.create(context, R.raw.lanzar);
					break;
				case ValorDeVerbosEsp.ENTENDER:
					mp = MediaPlayer.create(context, R.raw.entender);
					break;
				case ValorDeVerbosEsp.DESPERTARSE:
					mp = MediaPlayer.create(context, R.raw.despertarse);
					break;
				case ValorDeVerbosEsp.LLEVAR_PUESTO:
					mp = MediaPlayer.create(context, R.raw.llevar_puesto);
					break;
				case ValorDeVerbosEsp.LLORAR:
					mp = MediaPlayer.create(context, R.raw.llorar);
					break;
				case ValorDeVerbosEsp.MOJAR:
					mp = MediaPlayer.create(context, R.raw.mojar);
					break;
				case ValorDeVerbosEsp.GANAR:
					mp = MediaPlayer.create(context, R.raw.ganar);
					break;
				case ValorDeVerbosEsp.ESCRIBIR:
					mp = MediaPlayer.create(context, R.raw.escribir);
					break;
			}
			if(mp != null){
				mp.start();

			}
		}
        return mp;
	}

}