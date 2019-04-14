package com.uga.devops;

import java.util.ArrayList;
import java.io.File;

public class App {
    public static void main(String[] args) {
        ArrayList<Column> columns = new ArrayList<>();

        ArrayList<Object> list = new ArrayList<Object>();
        list.add(1.4f);
        list.add(2.7f);
        list.add(null);
        list.add(4.2f);
        list.add(5.1f);

        columns.add(new Column("A", list));


        ArrayList list2 = new ArrayList();
        list2.add("a");
        list2.add("b");
        list2.add("null");
        list2.add("dd");
        list2.add("e");

        columns.add(new Column("B", list2));


        ArrayList<Object> list3 = new ArrayList<Object>();
        list3.add(1);
        list3.add(2);
        list3.add(null);
        list3.add(4);
        list3.add(5);

        columns.add(new Column("C", list3));

        DataFrame frame = new DataFrame(columns);
        System.out.println(frame.affichage());
//        System.out.println();
//        System.out.println();
//        frame.affichageDebut(3);
//        System.out.println();
//        System.out.println();
//        frame.affichageFin(3);
//
//        DataFrame frame2 = new DataFrame("/home/dadmin/Documents/M1/S8/DevOps/DevOpsPandas/test.csv");
//        System.out.println();
//        System.out.println();
//        frame2.affichage();
//        System.out.println(columns.get(1).getType());
//
//        DataFrame frame3 = frame2.iloc(1);
//
//        System.out.println();
//        System.out.println();
//        frame3.affichage();
//
//        ArrayList<String> subFrame = new ArrayList<>();
//        subFrame.add("Year");
//        subFrame.add("Model");
//
//        frame3 = frame2.loc(subFrame);
//
//        ArrayList<Integer> subFrame2 = new ArrayList<>();
//        subFrame2.add(0);
//        subFrame2.add(3);
//
//        frame3 = frame.iloc(subFrame2);
//
//        System.out.println();
//        System.out.println();
//        frame3.affichage();
    }
}
