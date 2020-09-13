package com.binary_winters.projects.iverbs.situaciones.modelo;

import com.binary_winters.projects.iverbs.MyApp;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by chipo on 28/05/18.
 */

public class SituacionGenerica extends RealmObject implements Serializable {
    @PrimaryKey
    private int id;
    @Required
    private String palabraEnEsp;
    @Required
    private String cadenaEnIngles;
    @Required
    private Boolean markedWithStar;
    @Required
    private String tipoSituacion;
    private String nombreAudio;

    public SituacionGenerica() {}

    public SituacionGenerica(String palabraEnEsp, String cadenaEnIngles, Boolean markedWithStar, String tipoSituacion, String nombreAudio) {
        this.palabraEnEsp = palabraEnEsp;
        this.cadenaEnIngles = cadenaEnIngles;
        this.markedWithStar = markedWithStar;
        this.tipoSituacion = tipoSituacion;
        this.nombreAudio = nombreAudio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdFromBd(){
        this.id = MyApp.SituacionGenericaID.incrementAndGet();
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

    public String getTipoSituacion() {
        return tipoSituacion;
    }

    public void setTipoSituacion(String tipoSituacion) {
        this.tipoSituacion = tipoSituacion;
    }

    public String getNombreAudio() {
        return nombreAudio;
    }

    public void setNombreAudio(String nombreAudio) {
        this.nombreAudio = nombreAudio;
    }
}
