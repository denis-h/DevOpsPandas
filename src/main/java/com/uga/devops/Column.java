package com.uga.devops;

import java.util.ArrayList;

public class Column {

    private String label;
    private ArrayList<Object> values;
    private int columnSize = 0;

    public Column(String label, ArrayList<Object> values) {
        this.label = label;
        this.values = values;
        columnSize = this.values.size();
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<Object> getValues() {
        return values;
    }

    public Object getValueAt(int index) {
        return values.get(index);
    }

    public void replaceAt(int index, Object value) {
        values.remove(index);
        values.add(index, value);
    }

    public void add(Object value) {
        values.add(value);
        columnSize++;
    }

    public void remove(Object value) {
        values.remove(value);
        columnSize--;
    }

    public int getColumnSize() {
        return columnSize;
    }


}