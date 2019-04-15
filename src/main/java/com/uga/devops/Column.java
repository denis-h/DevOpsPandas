package com.uga.devops;

import java.text.ParseException;
import java.util.ArrayList;
import java.lang.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Column {

    enum Type {NULL, INT, FLOAT, STRING, EMPTY}

    private String label;
    private ArrayList<Object> values;
    private int columnSize;
    private Type type;

    public Column() {

    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValues(ArrayList<Object> values) {
        this.values = values;
    }

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

    public void replaceAt(int index, Object value) {
        values.remove(index);
        values.add(index, value);
    }

    public void add(Object value) {
        if (values.isEmpty()) { // we insert into a column where values are empty still, so we have to change
            this.type = extractType(value);
        }
        values.add(value);
        columnSize++;
    }

    private Type extractType(Object value) {
        if (value instanceof Integer) {
            return Type.INT;
        } else if (value instanceof String) {
//            Integer.decode()
            // the string may be an int or float if csv
//            final String sValue = (String) value;
//            boolean isAnInt = false;
//            boolean isAFloat = true;
//            try {
//                int i = Integer.parseInt(sValue);
//                return Type.INT;
////                if (possibleInt instanceof Integer) {
////                    return Type.INT;
////                } else if (possibleInt instanceof NumberFormatException) {
////                    return Type.STRING;
////                }
//                if (isAnInt) return Type.INT;
//                else {
//                    Float.parseFloat(sValue);
//                    isAFloat = true;
//                }
////                if (possibleFloat instanceof Float) {
////                    return Type.FLOAT;
////                } else if (possibleFloat instanceof NumberFormatException) {
////                    return Type.STRING;
////                }
//                if (isAFloat) return Type.FLOAT;
//            } catch (NumberFormatException e) {
//                // not an int
//            }
            return Type.STRING;
        } else if (value instanceof Float) {
            return Type.FLOAT;
        } else if (value == null) {
            return Type.NULL;
        }
        return Type.NULL;
    }

//    public static Object stringToDataType(String valueAsString) throws ParseException {
//        // detections ordered by probability of occurrence in Buffer_Bank.
//        String decimalPattern = detectDecimal(valueAsString);
//        if (decimalPattern != null) {
//            return stringToBigDecimal(valueAsString, decimalPattern);
//        }
//        String integerPattern = detectInteger(valueAsString);
//        if (integerPattern != null) {
//            return stringToBigInteger(valueAsString);
//        }
//        String datePattern = detectDate(valueAsString);
//        if (datePattern != null) {
//            return stringToDate(valueAsString, datePattern);
//        }
//        return valueAsString;
//    }

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

    public Object min() {
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

    public Object average() {
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

    public Object sum() {
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

}
