package com.binary_winters.projects.iverbs;

/**
 * Created by chipo on 27/05/18.
 */

public class Situacion {
    private String nombreSituacion;
    private Integer imagen;

    public Situacion(String nombreSituacion, Integer imagen) {
        this.nombreSituacion = nombreSituacion;
        this.imagen = imagen;
    }

    public String getNombreSituacion() {
        return nombreSituacion;
    }

    public void setNombreSituacion(String nombreSituacion) {
        this.nombreSituacion = nombreSituacion;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }
}
