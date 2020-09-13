package com.binary_winters.projects.iverbs.modelo;

import com.binary_winters.projects.iverbs.MyApp;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by chipo on 23/02/18.
 */

public class PalabraAgregadaBean extends RealmObject implements Serializable {
    @PrimaryKey
    private int id;
    @Required
    private String palabraEnEsp;
    @Required
    private String cadenaEnIngles;
//    @Required
    private Boolean markedWithStar;
    @Required
    private String archivoDeAudioEnString;
    @Required
    private String nombreArchivoAudio;

    public PalabraAgregadaBean() {}

    public PalabraAgregadaBean(String palabraEnEsp, String cadenaEnIngles, Boolean markedWithStar) {
        this.palabraEnEsp = palabraEnEsp;
        this.cadenaEnIngles = cadenaEnIngles;
        this.markedWithStar = markedWithStar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdFromBd(){
        this.id = MyApp.MiPalabraID.incrementAndGet();
    }

    public String getPalabraEnEsp() {
        return palabraEnEsp;
    }

    public void setPalabraEnEsp(String palabraEnEsp) {
        this.palabraEnEsp = palabraEnEsp;
    }

    public String getCadenaEnIngles() {
        return cadenaEnIngles;
    }

    public void setCadenaEnIngles(String cadenaEnIngles) {
        this.cadenaEnIngles = cadenaEnIngles;
    }

    public Boolean getMarkedWithStar() {
        return markedWithStar;
    }

    public void setMarkedWithStar(Boolean markedWithStar) {
        this.markedWithStar = markedWithStar;
    }

    public String getArchivoDeAudioEnString() {
        return archivoDeAudioEnString;
    }

    public void setArchivoDeAudioEnString(String archivoDeAudioEnString) {
        this.archivoDeAudioEnString = archivoDeAudioEnString;
    }

    public String getNombreArchivoAudio() {
        return nombreArchivoAudio;
    }

    public void setNombreArchivoAudio(String nombreArchivoAudio) {
        this.nombreArchivoAudio = nombreArchivoAudio;
    }
}
