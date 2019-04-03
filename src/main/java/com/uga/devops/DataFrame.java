package com.uga.devops;

import java.util.HashMap;

public class DataFrame {

    private HashMap<String, HashMap<Integer, Object>> map = new HashMap<String, HashMap<Integer, Object>>();

    public DataFrame(String column, HashMap<Integer, Object> data) {
        this.map.put(column, data);
    }

    public void affichage() {
        StringBuilder printer = new StringBuilder("\t");
        for (String column : map.keySet()) {
            printer.append(column).append(" ");
        }

        System.out.println(printer.toString());

        printer.append("\n");

        for (int i = 0; i < map.keySet().size(); i++) {
            printer.append(i).append("\t");
            for (Object o : map.get(i).values()) {
                printer.append(o.toString());
            }
            printer.append("\n");
        }
    }

    @Override
    public String toString() {
        return "DataFrame{" +
                "map=" + map.toString() +
                '}';
    }

    public <T> void put(String column, HashMap<Integer, T> columnData) {
        map.put(column, (HashMap<Integer, Object>) columnData);
    }
}