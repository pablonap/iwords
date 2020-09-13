package com.binary_winters.projects.iverbs.modelo;

import com.binary_winters.projects.iverbs.MyApp;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by chipo on 28/02/18.
 */

public class HistorialDePartidaBean extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private Date fechaDePartida;
    private int cantidadDeVerbos;
    private int porcentajeAciertos;
    private int porcentajeErrores;

    public HistorialDePartidaBean() {}

    public HistorialDePartidaBean(int cantidadDeVerbos, int porcentajeAciertos, int porcentajeErrores) {
        this.id = MyApp.HistorialDePartidaID.incrementAndGet();
        this.fechaDePartida = new Date();
        this.cantidadDeVerbos = cantidadDeVerbos;
        this.porcentajeAciertos = porcentajeAciertos;
        this.porcentajeErrores = porcentajeErrores;
    }

    public Date getFechaDePartida() {
        return fechaDePartida;
    }

    public int getId() {
        return id;
    }

    public int getCantidadDeVerbos() {
        return cantidadDeVerbos;
    }

    public void setCantidadDeVerbos(int cantidadDeVerbos) {
        this.cantidadDeVerbos = cantidadDeVerbos;
    }

    public int getPorcentajeAciertos() {
        return porcentajeAciertos;
    }

    public void setPorcentajeAciertos(int porcentajeAciertos) {
        this.porcentajeAciertos = porcentajeAciertos;
    }

    public int getPorcentajeErrores() {
        return porcentajeErrores;
    }

    public void setPorcentajeErrores(int porcentajeErrores) {
        this.porcentajeErrores = porcentajeErrores;
    }
}
