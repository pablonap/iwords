package com.binary_winters.projects.iverbs;

/**
 * Created by chipo on 29/05/18.
 */

public enum TipoSituacion {
    MESERO(1, "MESERO"),
    EN_RESTORAN(2,"EN RESTORÁN"),
    EN_AEROPUERTO(3, "EN AEROPUERTO"),
    RECEPCION_HOTEL(4, "RECEPCIÓN DE HOTEL"),
    EN_DOCTOR(5, "EN EL DOCTOR"),
    ENTREVISTA_TRABAJO(6, "ENTREVISTA DE TRABAJO"),
    SUPERMERCADO(7, "SUPERMERCADO"),
    UBICARSE(8, "UBICARSE");

    private Integer id;
    private String descripcion;

    private TipoSituacion(Integer id, final String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
