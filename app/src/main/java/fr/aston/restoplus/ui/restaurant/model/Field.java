package fr.aston.restoplus.ui.restaurant.model;


import java.io.Serializable;

public class Field implements Serializable {
    private String ville;
    private double lat_ok;
    private String adresse_concatenee;
    private String categorie;
    private double long_ok;
    private String enseigne;
    private String cartier;
    private double[] column_16;
    private String nom_voie;
    private String cp;
    private String ndeg;
    private String type_voie;
    private Geometry geometry;
    private String record_timestamp;

    public String getVille() {
        return ville;
    }

    public double getLat_ok() {
        return lat_ok;
    }

    public String getAdresse_concatenee() {
        return adresse_concatenee;
    }

    public String getCategorie() {
        return categorie;
    }

    public double getLong_ok() {
        return long_ok;
    }

    public String getEnseigne() {
        return enseigne;
    }

    public String getCartier() {
        return cartier;
    }

    public double[] getColumn_16() {
        return column_16;
    }

    public String getNom_voie() {
        return nom_voie;
    }

    public String getCp() {
        return cp;
    }

    public String getNdeg() {
        return ndeg;
    }

    public String getType_voie() {
        return type_voie;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getRecord_timestamp() {
        return record_timestamp;
    }
}
