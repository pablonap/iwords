package com.binary_winters.projects.iverbs;

/**
 * Created by chipo on 28/04/18.
 */

public class ContenidoEnEspGenericoHelper {
    private String palabraEnEsp;
    private Boolean markedWithStar;
    private int id;

    public ContenidoEnEspGenericoHelper(String palabraEnEsp, Boolean markedWithStar, int id) {
        this.palabraEnEsp = palabraEnEsp;
        this.markedWithStar = markedWithStar;
        this.id = id;
    }

    public String getPalabraEnEsp() {
        return palabraEnEsp;
    }

    public void setPalabraEnEsp(String palabraEnEsp) {
        this.palabraEnEsp = palabraEnEsp;
    }

    public Boolean getMarkedWithStar() {
        return markedWithStar;
    }

    public void setMarkedWithStar(Boolean markedWithStar) {
        this.markedWithStar = markedWithStar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
