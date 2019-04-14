package com.uga.devops;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataFrame {

    ArrayList<Column> columns = new ArrayList<>();

    public DataFrame(ArrayList<Column> data) {
        columns = data;
    }

    public DataFrame(String csv) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(csv));
            int index = 0;
            for (String line : allLines) {
                String[] separated = line.split(";");
                if (index == 0) {
                    for (String s : separated) {
                        Column column = new Column(s, new ArrayList<Object>());
                        columns.add(column);
                    }
                } else {
                    for (int i = 0; i < separated.length; i++) {
                        columns.get(i).add(separated[i]);
                    }
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                if (i < columns.get(j).getColumnSize()) {
                    if (columns.get(j).getValueAt(i) == null) {
                        builder.append(" \t");
                    } else {
                        builder.append(columns.get(j).getValueAt(i)).append("\t");
                    }
                } else {
                    builder.append(" \t");
                }
            }
            System.out.println(builder.toString());
        }
    }

    public void affichageDebut(int nb_lines) {
        int maxLines = maxColumnLines();

        StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (Column column : columns) {
            builder.append(column.getLabel()).append("\t");
        }
        System.out.println(builder.toString());

        for (int i = 0; i < nb_lines; i++) {
            builder = new StringBuilder();
            builder.append(i).append("\t");
            for (int j = 0; j < columns.size(); j++) {
                if (i < columns.get(j).getColumnSize()) {
                    if (columns.get(j).getValueAt(i) == null) {
                        builder.append(" \t");
                    } else {
                        builder.append(columns.get(j).getValueAt(i)).append("\t");
                    }
                } else {
                    builder.append(" \t");
                }
            }
            System.out.println(builder.toString());
        }
    }

    public void affichageFin(int nb_lines) {
        int maxLines = maxColumnLines();
        int startIndex = maxLines - nb_lines;

        StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (Column column : columns) {
            builder.append(column.getLabel()).append("\t");
        }
        System.out.println(builder.toString());

        for (int i = startIndex; i < maxLines; i++) {
            builder = new StringBuilder();
            builder.append(i).append("\t");
            for (int j = 0; j < columns.size(); j++) {
                if (i < columns.get(j).getColumnSize()) {
                    if (columns.get(j).getValueAt(i) == null) {
                        builder.append(" \t");
                    } else {
                        builder.append(columns.get(j).getValueAt(i)).append("\t");
                    }
                } else {
                    builder.append(" \t");
                }
            }
            System.out.println(builder.toString());
        }
    }

    public DataFrame iloc(int pos) {
        ArrayList<Column> result = new ArrayList<>();
        for (Column column : columns) {
            ArrayList<Object> values = new ArrayList<>();
            values.add(column.getValueAt(pos));
            result.add(new Column(column.getLabel(), values));
        }

        return new DataFrame(result);
    }

    public DataFrame loc(String label) {
        ArrayList<Column> result = new ArrayList<>();
        for (Column column : columns) {
            if (column.getLabel().equals(label)) {
                result.add(column);
            }
        }

        return new DataFrame(result);
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
}
