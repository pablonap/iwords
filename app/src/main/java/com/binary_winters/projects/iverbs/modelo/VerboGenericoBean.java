package com.binary_winters.projects.iverbs.modelo;

import android.support.annotation.Nullable;

import com.binary_winters.projects.iverbs.MyApp;
import com.binary_winters.projects.iverbs.VerboEnEsp;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by chipo on 23/02/18.
 */

public class VerboGenericoBean extends RealmObject implements Serializable {
    @PrimaryKey
    private int id;
    private VerboEnEsp verboEnEsp;
    @Required
    private String cadenaDeVerbos;
    @Required
    private String archivoDeAudioEnString;
    @Required
    private String nombreArchivoAudio;

    public VerboGenericoBean() {}

    public VerboGenericoBean(VerboEnEsp verboEnEsp, String cadenaDeVerbos) {
        this.verboEnEsp = verboEnEsp;
        this.cadenaDeVerbos = cadenaDeVerbos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdFromBd(){
        this.id = MyApp.VerboGenericoBeanID.incrementAndGet();
    }

    public VerboEnEsp getVerboEnEsp() {
        return verboEnEsp;
    }

    public void setVerboEnEsp(VerboEnEsp verboEnEsp) {
        this.verboEnEsp = verboEnEsp;
    }

    public String getCadenaDeVerbos() {
        return cadenaDeVerbos;
    }

    public void setCadenaDeVerbos(String cadenaDeVerbos) {
        this.cadenaDeVerbos = cadenaDeVerbos;
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
