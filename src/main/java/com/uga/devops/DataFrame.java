package com.uga.devops;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class DataFrame {

    private ArrayList<Column> columns = new ArrayList<>();

    DataFrame(ArrayList<Column> data) {
        columns = data;
    }

    DataFrame(String csv) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(csv));
            int index = 0;
            for (String line : allLines) {
                String[] separated = line.split(";");
                if (index == 0) {
                    for (String s : separated) {
                        Column column = new Column(s, new ArrayList<>());
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

    ArrayList<Column> getColumns() {
        return columns;
    }

    /**
     * Returns all the DataFrame as a String (ready to be printed).
     */
    String print() {
        int maxLines = maxColumnLines();
        return getColumns(maxLines);
    }

    /**
     * Returns the beginning of a DataFrame as a String (ready to be printed).
     *
     * @param nb_lines to be printed.
     */
    String printBegin(int nb_lines) {
        return getColumns(nb_lines);
    }

    private String getColumns(int nb_lines) {
        StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (Column column : columns) {
            builder.append(column.getLabel()).append("\t");
        }
        builder.append("\n");
        for (int i = 0; i < nb_lines; i++) {
            printLine(builder, i);
        }
        return builder.toString();
    }

    /**
     * Returns the end of a DataFrame as a String (ready to be printed).
     *
     * @param nb_lines to be printed.
     */
    String printEnd(int nb_lines) {
        int maxLines = maxColumnLines();
        int startIndex = maxLines - nb_lines;

        StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (Column column : columns) {
            builder.append(column.getLabel()).append("\t");
        }
        builder.append("\n");
        for (int i = startIndex; i < maxLines; i++) {
            printLine(builder, i);
        }
        return builder.toString();
    }

    private void printLine(StringBuilder builder, int lineToPrint) {
        builder.append(lineToPrint).append("\t");
        for (Column column : columns) {
            if (lineToPrint < column.getColumnSize()) {
                if (column.getValueAt(lineToPrint) == null) {
                    builder.append(" \t");
                } else {
                    builder.append(column.getValueAt(lineToPrint)).append("\t");
                }
            } else {
                builder.append(" \t");
            }
        }
        builder.append("\n");
    }

    DataFrame iloc(int pos) {
        ArrayList<Column> result = new ArrayList<>();
        for (Column column : columns) {
            ArrayList<Object> values = new ArrayList<>();
            values.add(column.getValueAt(pos));
            result.add(new Column(column.getLabel(), values));
        }

        return new DataFrame(result);
    }

    DataFrame iloc(ArrayList<Integer> pos) {
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

    /**
     * @param label
     * @return
     */
    Column loc(String label) {
        for (Column column : columns) {
            if (column.getLabel().equals(label)) {
                return column;
            }
        }

        return null;
    }

    /**
     * @param label
     * @return
     */
    DataFrame loc(ArrayList<String> label) {
        ArrayList<Column> result = new ArrayList<>();
        for (Column column : columns) {
            if (label.contains(column.getLabel())) {
                result.add(column);
            }
        }

        return new DataFrame(result);
    }

    /**
     * @return
     */
    int maxColumnLines() {
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