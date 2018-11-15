package fr.aston.restoplus.ui.restaurant.model;

import java.io.Serializable;
import java.util.List;
import fr.aston.restoplus.ui.restaurant.model.Field;

public class Restaurant implements Serializable {
    private String datasetid;
    private String recordid;
    private Field fields;


    public String getDatasetid() {
        return datasetid;
    }

    public String getRecordid() {
        return recordid;
    }

    public Field getFields() {
        return fields;
    }
}
