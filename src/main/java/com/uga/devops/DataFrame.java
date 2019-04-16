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

    /**
     * Create a DataFrame object from a parsed csv file.
     *
     * @param csv the given csv
     */
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
                        columns.get(i).add(separated[i], true);
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
            int columnSize = column.calculateMaxLength();
            StringBuilder columnLabel = new StringBuilder(column.getLabel());
            int toAdd = columnSize - columnLabel.toString().length();
            for (int i = 0; i < toAdd; i++) {
                columnLabel.append(" ");
            }
            columnLabel.append("\t");
            builder.append(columnLabel.toString());
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
            int columnSizeLength = column.calculateMaxLength();
            StringBuilder columnValue = new StringBuilder(column.getLabel());
            int toAdd = columnSizeLength - columnValue.toString().length();
            for (int i = 0; i < toAdd; i++) {
                columnValue.append(" ");
            }
            columnValue.append("\t");
            builder.append(columnValue.toString());
        }
        builder.append("\n");
        for (int i = startIndex; i < maxLines; i++) {
            printLine(builder, i);
        }
        return builder.toString();
    }

    /**
     * Print a line
     *
     * @param builder     the string builder to append to
     * @param lineToPrint the index of the line
     */
    private void printLine(StringBuilder builder, int lineToPrint) {
        builder.append(lineToPrint).append("\t");
        for (Column column : columns) {
            int columnSizeLength = column.calculateMaxLength();
            if (lineToPrint < column.getColumnSize()) {
                if (column.getValueAt(lineToPrint) == null) {
                    for (int i = 0; i < columnSizeLength; i++) {
                        builder.append(" ");
                    }
                    builder.append("\t");
                } else {
                    StringBuilder columnValue = new StringBuilder(column.getValueAt(lineToPrint).toString());
                    int toAdd = columnSizeLength - columnValue.toString().length();
                    for (int i = 0; i < toAdd; i++) {
                        columnValue.append(" ");
                    }
                    columnValue.append("\t");
                    builder.append(columnValue.toString());
                }
            } else {
                builder.append(" \t");
            }
        }
        builder.append("\n");
    }

    /**
     * Returns a sub DataFrame (a line).
     */
    DataFrame iloc(int pos) {
        ArrayList<Column> result = new ArrayList<>();
        for (Column column : columns) {
            ArrayList<Object> values = new ArrayList<>();
            values.add(column.getValueAt(pos));
            result.add(new Column(column.getLabel(), values));
        }
        return new DataFrame(result);
    }

    /**
     * Returns a sub DataFrame (from multiple lines).
     */
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
     * Return a column from a given label.
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
     * Return columns from given labels.
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
     * Returns the max column lines (heigth of the sheet).
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