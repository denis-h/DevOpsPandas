package com.uga.devops;

import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataFrame {

    private ArrayList<Column> columns = new ArrayList<>();

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

    public String affichage() {
        int maxLines = maxColumnLines();
        StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (Column column : columns) {
            builder.append(column.getLabel()).append("\t");
        }
        builder.append("\n");
        for (int i = 0; i < maxLines; i++) {
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
            builder.append("\n");
        }
        return builder.toString();
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

    public DataFrame iloc(ArrayList<Integer> pos) {
        ArrayList<Column> result = new ArrayList<>();
            for (Column column : columns) {
                ArrayList<Object> values = new ArrayList<>();
                for (Integer p : pos) {
                    values.add(column.getValueAt(p));
                }
                result.add(new Column(column.getLabel(), values));
            }

        return new DataFrame(result);
    }

    public Column loc(String label) {
        for (Column column : columns) {
            if (column.getLabel().equals(label)) {
                return column;
            }
        }

        return null;
    }

    public DataFrame loc(ArrayList<String> label) {
        ArrayList<Column> result = new ArrayList<>();
        for (Column column : columns) {
            if (label.contains(column.getLabel())) {
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

    public ArrayList<Column> getColumns() {
        return columns;
    }
}
