package com.uga.devops;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        HashMap<Integer, String> map = new HashMap();
//        HashMap<Integer, HashMap<String, Integer>> map = new HashMap();

//        map.put(1, new HashMap<String, Integer>());



        HashMap<Integer, Column> integerColumnHashMap = new HashMap<Integer, Column>();

        ArrayList<Object> list = new ArrayList<Object>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        integerColumnHashMap.put(0, new Column("A", list));


        ArrayList list2 = new ArrayList();
        list2.add("yo 1");
        list2.add("yo 2");
        list2.add("yo 3");
        list2.add("yo 4");
        list2.add("yo 16");
        integerColumnHashMap.put(1, new Column("B", list2));


        for (int i = 0; i < integerColumnHashMap.size(); i++) {

        }
    }
}
