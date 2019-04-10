package com.uga.devops;

import java.io.File;
import java.util.ArrayList;

public class DataFrame {

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
