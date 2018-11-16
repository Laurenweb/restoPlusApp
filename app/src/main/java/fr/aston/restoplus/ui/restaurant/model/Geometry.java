package fr.aston.restoplus.ui.restaurant.model;

import java.io.Serializable;

public class Geometry implements Serializable {
    private String type;
    private double[] coordinates;

    public String getType() {
        return type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }
    public double getLat(){
        return coordinates[0];
    }
    public double getLong(){
        return coordinates[1];
    }
}
