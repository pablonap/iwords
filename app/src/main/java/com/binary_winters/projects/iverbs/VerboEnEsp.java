package com.binary_winters.projects.iverbs;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by chipo on 23/02/18.
 */

public class VerboEnEsp extends RealmObject implements Serializable {
    @PrimaryKey
    private int id;
    private String nombreDeVerbo;
    private Boolean VERBO_DESDE_APP;
    private Boolean markedWithStar;

    public VerboEnEsp(String nombreDeVerbo, Boolean VERBO_DESDE_APP, Boolean markedWithStar) {
        this.nombreDeVerbo = nombreDeVerbo;
        this.VERBO_DESDE_APP = VERBO_DESDE_APP;
        this.markedWithStar = markedWithStar;
    }

    public VerboEnEsp() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdFromBd(){
        this.id = MyApp.VerboEnEspID.incrementAndGet();
    }

    public String getNombreDeVerbo() {
        return nombreDeVerbo;
    }

    public void setNombreDeVerbo(String nombreDeVerbo) {
        this.nombreDeVerbo = nombreDeVerbo;
    }

    public Boolean getVERBO_DESDE_APP() {
        return VERBO_DESDE_APP;
    }

    public void setVERBO_DESDE_APP(Boolean VERBO_DESDE_APP) {
        this.VERBO_DESDE_APP = VERBO_DESDE_APP;
    }

    public Boolean getMarkedWithStar() {
        return markedWithStar;
    }

    public void setMarkedWithStar(Boolean markedWithStar) {
        this.markedWithStar = markedWithStar;
    }
}
