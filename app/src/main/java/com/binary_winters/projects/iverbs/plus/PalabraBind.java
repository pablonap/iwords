package com.binary_winters.projects.iverbs.plus;

import com.binary_winters.projects.iverbs.ContenidoEnEspGenericoHelper;
import com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class PalabraBind {
	private Map<String, ContenidoEnEspGenericoHelper> mapa;
    private Map<String, ContenidoEnEspGenericoHelper> mapaSoloEscritura;
	private Integer cantidadDeRespuestasCorrectas = 0;
	private List<ContenidoEnEspGenericoHelper> listaDePalabrasEnEspMalRespondidas;
	private Realm realm;
    private RealmResults<PalabraAgregadaBean> listaDePalabrasAgregadasFromBD;
    private List<ContenidoEnEspGenericoHelper> palabrasEnEspHelper = new ArrayList<>();
	private List<ContenidoEnEspGenericoHelper> palabrasAgregadasFavoritas = new ArrayList<>();

	public PalabraBind(){
		realm = Realm.getDefaultInstance();
        mapa = new LinkedHashMap<>();

        listaDePalabrasAgregadasFromBD = realm.where(PalabraAgregadaBean.class).findAll();
        if(listaDePalabrasAgregadasFromBD != null && listaDePalabrasAgregadasFromBD.isEmpty() == false){
            for(PalabraAgregadaBean palabraActual : listaDePalabrasAgregadasFromBD){
                ContenidoEnEspGenericoHelper contenidoEnEspGenericoHelper =
                        new ContenidoEnEspGenericoHelper(palabraActual.getPalabraEnEsp(), palabraActual.getMarkedWithStar(), palabraActual.getId());
                String textoIngles = palabraActual.getCadenaEnIngles();
                palabrasEnEspHelper.add(contenidoEnEspGenericoHelper);
                mapa.put(textoIngles, contenidoEnEspGenericoHelper);
            }
        }

        this.armarMapaConPalabrasSoloEscrituraOn();

		listaDePalabrasEnEspMalRespondidas = new ArrayList<ContenidoEnEspGenericoHelper>();

		for(ContenidoEnEspGenericoHelper palabra : palabrasEnEspHelper){
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
            indiceDeListaAleatorio = generarRandom.nextInt(palabrasEnEspHelper.size());
            ContenidoEnEspGenericoHelperElegida = palabrasEnEspHelper.get(indiceDeListaAleatorio);
            palabrasEnEspHelper.remove(indiceDeListaAleatorio);
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
            cantidadDePalabras = palabrasEnEspHelper.size();
        } else{
            cantidadDePalabras = palabrasAgregadasFavoritas.size();
        }

        return cantidadDePalabras;
    }

    public Integer getCantidadPalabras(){
        return palabrasEnEspHelper.size();
    }

    public Integer getCantidadPalabrasFavoritas(){
        return palabrasAgregadasFavoritas.size();
    }

    public RealmResults<PalabraAgregadaBean> getListaDePalabrasAgregadasFromBD() {
        return listaDePalabrasAgregadasFromBD;
    }

    public PalabraAgregadaBean buscarPalabraSegunNombrePalabraEnEsp(String nombrePalabraEnEsp){
        Boolean resultado = Boolean.FALSE;

        int index = 0;
        PalabraAgregadaBean palabraAgregadaBeanHallada = null;

        while(index < listaDePalabrasAgregadasFromBD.size() && resultado.equals(Boolean.FALSE)){
            if(listaDePalabrasAgregadasFromBD.get(index).getPalabraEnEsp().equals(nombrePalabraEnEsp)){
                palabraAgregadaBeanHallada = listaDePalabrasAgregadasFromBD.get(index);
                resultado = Boolean.TRUE;
            }
            index++;
        }

        return palabraAgregadaBeanHallada;
    }
}