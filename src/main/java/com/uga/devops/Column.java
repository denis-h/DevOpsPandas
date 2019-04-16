package com.uga.devops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Column {

    enum Type {NULL, INT, FLOAT, STRING, EMPTY}

    private String label;
    private ArrayList<Object> values;
    private int columnSize;
    private Type type;

    public Column(String label, ArrayList<Object> values) {
        this.label = label;
        this.values = values;
        columnSize = this.values.size();
        this.type = extractType(values);
    }

    /**
     * Returns the max length (size in characters) of the column.
     */
    int calculateMaxLength() {
        int max = 0;
        for (Object object : values) {
            switch (type) {
                case NULL:
                case EMPTY:
                    break;
                case INT:
                    Integer i = (Integer) object;
                    int ci = 0;
                    if (i != null) {
                        ci = i.toString().length();
                    }
                    if (ci > max) {
                        max = ci;
                    }
                    break;
                case FLOAT:
                    Float f = (Float) object;
                    int cf = 0;
                    if (f != null) {
                        cf = f.toString().length();
                    }
                    if (cf > max) {
                        max = cf;
                    }
                    break;
                case STRING:
                    String s = (String) object;
                    int cs = 0;
                    if (s != null) {
                        cs = s.length();
                    }
                    if (cs > max) {
                        max = cs;
                    }
                    break;
            }
        }
        return max;
    }

    String getLabel() {
        return label;
    }

    ArrayList<Object> getValues() {
        return values;
    }

    Object getValueAt(int index) {
        return values.get(index);
    }

    private Type getType() {
        return type;
    }

    /**
     * Basic type extractor (not used by CSV)
     */
    private Type extractType(ArrayList<Object> values) {
        if (values != null) {
            if (values.size() >= 1) {
                if (values.get(0) instanceof Integer) {
                    return Type.INT;
                } else if (values.get(0) instanceof String) {
                    return Type.STRING;
                } else if (values.get(0) instanceof Float) {
                    return Type.FLOAT;
                }
            } else {
                return Type.EMPTY;
            }
        }
        return Type.NULL;
    }

    /**
     * Replace a value at a given index.
     */
    void replaceAt(int index, Object value) {
        values.remove(index);
        values.add(index, value);
    }

    /**
     * Adds a value to the column. If from CSV then the first time we add a value the column values will be empty so
     * we can extract the type from that String value. Then all the column value will be filled with the right type.
     *
     * @param value   the given value
     * @param fromCSV true if from CSV
     */
    public void add(Object value, boolean fromCSV) {
        if (values.isEmpty()) { // we insert into a column where values are empty still, so we have to change
            this.type = extractType(value, fromCSV);
        }
        if (fromCSV) {
            String sValue = (String) value;
            switch (type) {
                case NULL:
                case EMPTY:
                    values.add(value);
                    break;
                case INT:
                    values.add(Integer.parseInt(sValue));
                    break;
                case FLOAT:
                    values.add(Float.parseFloat(sValue));
                    break;
                case STRING:
                    values.add(sValue);
                    break;
            }
        } else
            values.add(value);
        columnSize++;
    }

    /**
     * Extracts the type of a column. If the column was created from CSV we have to extract the type
     * of the String first (because csv is parsed in String)
     *
     * @param value   the given value
     * @param fromCSV true if from CSV
     * @return the type
     */
    private Type extractType(Object value, boolean fromCSV) {
        if (value instanceof Integer) {
            return Type.INT;
        } else if (value instanceof String) {
            if (fromCSV) {
                String sValue = (String) value;

                if (Pattern.matches("^\\d+$", sValue)) {
                    return Type.INT;
                }
                if (Pattern.matches("^[-+]?[0-9]*\\.?[0-9]+$", sValue)) {
                    return Type.FLOAT;
                }
            }
            return Type.STRING;
        } else if (value instanceof Float) {
            return Type.FLOAT;
        } else if (value == null) {
            return Type.NULL;
        }
        return Type.NULL;
    }

    /**
     * Removes a given Object.
     */
    void remove(Object value) {
        values.remove(value);
        columnSize--;
    }

    /**
     * Column size (height)
     */
    int getColumnSize() {
        return columnSize;
    }

    /**
     * Returns the max value of the column.
     * Return value depends on the type.
     */
    Object max() {
        Object result;
        switch (this.getType()) {
            case INT:
                List<Integer> integers = new ArrayList<>(this.columnSize);
                for (Object object : this.getValues()) {
                    integers.add((object == null ? 0 : (Integer) object));
                }
                result = Collections.max(integers);
                break;
            case FLOAT:
                List<Float> floats = new ArrayList<>(this.columnSize);
                for (Object object : this.getValues()) {
                    floats.add((object == null ? 0.0f : (Float) object));
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
            default:
                result = null;
        }
        return result;
    }

    /**
     * Returns the min value of the column.
     * Return value depends on the type.
     */
    Object min() {
        Object result;
        switch (this.getType()) {
            case INT:
                List<Integer> integers = new ArrayList<>(this.columnSize);
                for (Object object : this.getValues()) {
                    integers.add((object == null ? 0 : (Integer) object));
                }
                result = Collections.min(integers);
                break;
            case FLOAT:
                List<Float> floats = new ArrayList<>(this.columnSize);
                for (Object object : this.getValues()) {
                    floats.add((object == null ? 0.0f : (Float) object));
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
            default:
                result = null;
        }
        return result;
    }


    /**
     * Returns the sum value of the column.
     * Return value depends on the type.
     */
    Object sum() {
        Object result = null;
        switch (this.getType()) {
            case INT:
                List<Integer> integers = new ArrayList<>(this.columnSize);
                for (Object object : this.getValues()) {
                    integers.add((object == null ? 0 : (Integer) object));
                }
                int sumInt = 0;
                for (Integer integer : integers) {
                    sumInt += integer;
                }
                result = sumInt;
                break;
            case FLOAT:
                List<Float> floats = new ArrayList<>(this.columnSize);
                for (Object object : this.getValues()) {
                    floats.add((object == null ? 0.0f : (Float) object));
                }

                float sumFloat = 0.0f;
                for (Float f : floats) {
                    sumFloat += f;
                }
                result = sumFloat;
                break;
        }
        return result;
    }

    /**
     * Returns the average value of the column.
     * Return value depends on the type.
     */
    Object average() {
        float result = 0.0f;
        switch (this.getType()) {
            case INT:
                List<Integer> integers = new ArrayList<>(this.columnSize);
                for (Object object : this.getValues()) {
                    integers.add((object == null ? 0 : (Integer) object));
                }

                for (Integer integer : integers) {
                    result += integer;
                }

                result = result / this.columnSize;
                break;
            case FLOAT:
                List<Float> floats = new ArrayList<>(this.columnSize);
                for (Object object : this.getValues()) {
                    floats.add((object == null ? 0.0f : (Float) object));
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