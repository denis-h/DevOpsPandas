package com.uga.devops;

import java.util.ArrayList;

public class Column {

    private String label;
    private ArrayList<Object> values;

    public Column(String label, ArrayList<Object> values) {
        this.label = label;
        this.values = values;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<Object> getValues() {
        return values;
    }

}