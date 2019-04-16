package com.uga.devops;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<>();
        list.add(1.4f);
        list.add(2.7f);
        list.add(null);
        list.add(4.2f);
        list.add(5.1f);
        columns.add(new Column("A", list));
        ArrayList<Object> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("null");
        list2.add("dd");
        list2.add("e");
        columns.add(new Column("B", list2));
        ArrayList<Object> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(2);
        list3.add(null);
        list3.add(4);
        list3.add(5);
        columns.add(new Column("C", list3));
        DataFrame frame = new DataFrame(columns);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(frame.print());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(frame.printEnd(3));
        System.out.println();
        System.out.println();
        System.out.println();
        DataFrame frame2 = new DataFrame("test.csv");
        System.out.println(frame2.print());
        System.out.println();
        System.out.println();
        System.out.println();
        DataFrame dataFrameCSV = new DataFrame("random.csv");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(dataFrameCSV.print());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(dataFrameCSV.printBegin(5));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(dataFrameCSV.printEnd(5));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Min value of first column");
        System.out.println(dataFrameCSV.getColumns().get(0).min());
        System.out.println("Max value of first column");
        System.out.println(dataFrameCSV.getColumns().get(0).max());
        System.out.println("Average value of first column");
        System.out.println(dataFrameCSV.getColumns().get(0).average());
    }
}