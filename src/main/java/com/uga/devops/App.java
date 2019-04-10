package com.uga.devops;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<Column> columns = new ArrayList<>();

        ArrayList<Object> list = new ArrayList<Object>();
        list.add(1);
        list.add(2);
        list.add(null);
        list.add(4);
        list.add(5);

        columns.add(new Column("A", list));


        ArrayList list2 = new ArrayList();
        list2.add("yo 1");
        list2.add("yo 2");
        list2.add("yo 3");
        list2.add("yo 4");
        list2.add("yo 16");

        columns.add(new Column("B", list2));


        DataFrame frame = new DataFrame(columns);
        frame.affichage();
        System.out.println();
        System.out.println();
        frame.affichageDebut(3);
        System.out.println();
        System.out.println();
        frame.affichageFin(3);

    }
}
