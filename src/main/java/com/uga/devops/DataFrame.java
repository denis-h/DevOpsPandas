package com.uga.devops;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class DataFrame {

//    private HashMap<String, HashMap<Integer, Object>> map = new HashMap<String, HashMap<Integer, Object>>();


    ArrayList<Column> columns = new ArrayList<>();

    public DataFrame(ArrayList<Column> data) {
        columns = data;
    }

    public DataFrame(File csv) {
        // TODO
    }

    public void affichage() {
        int maxLines = maxColumnLines();

        StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (Column column : columns) {
            builder.append(column.getLabel()).append("\t");
        }
        System.out.println(builder.toString());

        for (int i = 0; i < maxLines; i++) {
            builder = new StringBuilder();
            builder.append(i).append("\t");
            for (int j = 0; j < columns.size(); j++) {
                builder.append(columns.get(i).getValueAt(j)).append("\t");
            }
            System.out.println(builder.toString());
        }
    }

    private int maxColumnLines() {
        int maxLines = 0;
        for (Column column : columns) {
            int temp = column.getColumnSize();
            if (temp > maxLines) {
                maxLines = temp;
            }
        }
        return maxLines;
    }

//    @Override
//    public String toString() {
//        return "DataFrame{" +
//                "map=" + map.toString() +
//                '}';
//    }

//    public <T> void put(String column, HashMap<Integer, T> columnData) {
//        map.put(column, (HashMap<Integer, Object>) columnData);
//    }
}