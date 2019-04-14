package com.uga.devops;

import java.util.ArrayList;
import java.lang.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Column {

    enum Type {NULL, INT, FLOAT, STRING}

    private String label;
    private ArrayList<Object> values;
    private int columnSize = 0;
    private Type type;

    public Column(String label, ArrayList<Object> values) {
        this.label = label;
        this.values = values;
        columnSize = this.values.size();
        this.type = extractType(values);
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

    public Type getType() {
        return type;
    }

    public Type extractType(ArrayList<Object> values) {
        if ((values != null) && (values.size() > 1)) {
            if (values.get(0) instanceof Integer) {
                return Type.INT;
            } else if (values.get(0) instanceof String) {
                return Type.STRING;
            } else if (values.get(0) instanceof Float) {
                return Type.FLOAT;
            }
        }

        return Type.NULL;
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

    public Object max() {
        Object result = null;
        switch (this.getType()) {
            case INT:
            List<Integer> integers = new ArrayList<>(this.columnSize);
            for (Object object : this.getValues()) {
                integers.add((object == null ? 0 : (Integer)object));
            }
            result = Collections.max(integers);
            break;
            case FLOAT:
            List<Float> floats = new ArrayList<>(this.columnSize);
            for (Object object : this.getValues()) {
                floats.add((object == null ? 0.0f : (Float)object));
            }
            result = Collections.max(floats);
            break;
            case STRING:
            List<String> strings = new ArrayList<>(this.columnSize);
            for (Object object : this.getValues()) {
                strings.add((object == null ? "" : Objects.toString(object, null)));
            }
            result = Collections.max(strings);
            break;
            default :
            result = null;
        }

        return result;
    }

    public Object min() {
        Object result = null;
        switch (this.getType()) {
            case INT:
            List<Integer> integers = new ArrayList<>(this.columnSize);
            for (Object object : this.getValues()) {
                integers.add((object == null ? 0 : (Integer)object));
            }
            result = Collections.min(integers);
            break;
            case FLOAT:
            List<Float> floats = new ArrayList<>(this.columnSize);
            for (Object object : this.getValues()) {
                floats.add((object == null ? 0.0f : (Float)object));
            }
            result = Collections.min(floats);
            break;
            case STRING:
            List<String> strings = new ArrayList<>(this.columnSize);
            for (Object object : this.getValues()) {
                strings.add((object == null ? "" : Objects.toString(object, null)));
            }
            result = Collections.min(strings);
            break;
            default :
            result = null;
        }

        return result;
    }

    public Object average() {
        float result = 0.0f;
        switch (this.getType()) {
            case INT:
            List<Integer> integers = new ArrayList<>(this.columnSize);
            for (Object object : this.getValues()) {
                integers.add((object == null ? 0 : (Integer)object));
            }

            for (Integer integer : integers) {
                result += integer;
            }

            result = result / this.columnSize;
            break;
            case FLOAT:
            List<Float> floats = new ArrayList<>(this.columnSize);
            for (Object object : this.getValues()) {
                floats.add((object == null ? 0.0f : (Float)object));
            }

            for (Float f : floats) {
                result += f;
            }

            result = result / this.columnSize;
            break;
        }

        return result;
    }

}
