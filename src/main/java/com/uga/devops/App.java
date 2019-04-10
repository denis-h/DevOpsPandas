package com.uga.devops;

import java.util.ArrayList;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
//        HashMap<Integer, Column> integerColumnHashMap = new HashMap<Integer, Column>();
        ArrayList<Column> columns = new ArrayList<>();

//        ArrayList<Object> list = new ArrayList<Object>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
////        integerColumnHashMap.put(0, new Column("A", list));


        ArrayList list2 = new ArrayList();
        list2.add("yo 1");
        list2.add("yo 2");
        list2.add("yo 3");
        list2.add("yo 4");
        list2.add("yo 16");
//        integerColumnHashMap.put(1, );

        columns.add(new Column("B", list2));


        for (int lineIndex = 0; lineIndex < columns.size(); lineIndex++) {

        }

        DataFrame frame = new DataFrame(columns);
        frame.affichage();


//        for (int i = 0; i < integerColumnHashMap.size(); i++) {
//
//        }
    }
}