package com.uga.devops;

import junit.framework.TestCase;

import java.util.ArrayList;

public class AppTest extends TestCase {

    public AppTest(String testName) {
        super(testName);
    }

    /************************************************************************
     ****************** Column testing ********************************
     ***********************************************************************/

    public void testSumFloatCol() {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<Object>();

        list.add(1.4f);
        list.add(2.7f);
        list.add(null);
        list.add(4.2f);
        list.add(5.1f);

        columns.add(new Column("A", list));

        DataFrame df = new DataFrame(columns);

        assertEquals(13.4f, df.loc("A").sum());
    }

    public void testSumIntCol() {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<Object>();

        list.add(1);
        list.add(2);
        list.add(null);
        list.add(4);
        list.add(5);

        columns.add(new Column("A", list));

        DataFrame df = new DataFrame(columns);

        assertEquals(12, df.loc("A").sum());
    }

    public void testMaxFloatCol() {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<Object>();

        list.add(1.4f);
        list.add(2.7f);
        list.add(null);
        list.add(4.2f);
        list.add(5.1f);

        columns.add(new Column("A", list));

        DataFrame df = new DataFrame(columns);

        assertEquals(5.1f, df.loc("A").max());
    }

    public void testMaxIntCol() {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<Object>();

        list.add(1);
        list.add(2);
        list.add(null);
        list.add(4);
        list.add(5);

        columns.add(new Column("A", list));

        DataFrame df = new DataFrame(columns);

        assertEquals(5, df.loc("A").max());
    }

    public void testMaxStringCol() {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<Object>();

        list.add("tata");
        list.add(null);
        list.add("toto");
        list.add("popo");
        list.add("coco");

        columns.add(new Column("A", list));

        DataFrame df = new DataFrame(columns);

        assertEquals("toto", df.loc("A").max());
    }

    public void testAverageCol() {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<Object>();

        list.add(1.4f);
        list.add(2.7f);
        list.add(null);
        list.add(5.1f);
        list.add(4.2f);

        columns.add(new Column("A", list));

        DataFrame df = new DataFrame(columns);

        float f = (Float) df.loc("A").average();
        double roundedFloat = Math.round(f * 100.0) / 100.0;

        assertEquals(2.68, roundedFloat);
    }

    public void testAverageNullCol() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        list.add(null);
        Column column = new Column("NIL", list);
        assertNull(column.min());
        assertNull(column.max());
    }

    public void testMinFloatCol() {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<Object>();

        list.add(2.7f);
        list.add(null);
        list.add(1.4f);
        list.add(5.1f);
        list.add(4.2f);

        columns.add(new Column("A", list));

        DataFrame df = new DataFrame(columns);

        assertEquals(0.0f, df.loc("A").min());
    }


    public void testMinIntCol() {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<Object>();

        list.add(1);
        list.add(2);
        list.add(null);
        list.add(4);
        list.add(5);

        columns.add(new Column("A", list));

        DataFrame df = new DataFrame(columns);

        assertEquals(0, df.loc("A").min());
    }

    public void testMinStringCol() {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<Object>();

        list.add("tata");
        list.add(null);
        list.add("toto");
        list.add("popo");
        list.add("coco");

        columns.add(new Column("A", list));

        DataFrame df = new DataFrame(columns);

        assertEquals("", df.loc("A").min());
    }

    public void testColumnIntegerGetValueAt() { // we do not need to test other types to get an index
        ArrayList<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(null);
        list.add(4);
        list.add(5);
        Column column = new Column("A", list);
        assertEquals(2, column.getValueAt(1));
    }

    public void testGetColumnSize() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        Column columnWithTenIntegers = new Column("AA", list);
        assertEquals(10, columnWithTenIntegers.getColumnSize());
    }

    public void testColumnReplaceAt() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Column column = new Column("AA", list);
        column.replaceAt(0, 0);
        assertEquals(0, column.getValueAt(0));
    }

    public void testColumnAdd() {
        ArrayList<Object> list = new ArrayList<>();
        Column column = new Column("AA", list);
        column.add("a random value");
        assertEquals(1, column.getColumnSize());
    }

    public void testColumnRemove() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        Column column = new Column("AA", list);
        column.remove(1);
        assertEquals(2, column.getColumnSize());
    }

    public void testAverageColIntegers() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(5); // 5
        list.add(3); // 8
        list.add(null); // 8
        list.add(9); // 17
        list.add(8); // 25
        // 25 / 5 = 5.0
        assertEquals(5.0f, new Column("A", list).average());
    }

    /************************************************************************
     ****************** DataFrame testing ********************************
     ***********************************************************************/

//    public void testDataFramePrintOutput(){
//        ArrayList<Column> columns = new ArrayList<>();
//        ArrayList<Object> list = new ArrayList<>();
//        list.add(1.4f);
//        list.add(2.7f);
//        list.add(null);
//        list.add(4.2f);
//        list.add(5.1f);
//        columns.add(new Column("A", list));
//        DataFrame df = new DataFrame(columns);
//        String printDataFrameOutput = df.affichage();
//        System.out.println(printDataFrameOutput);
//        String expectedOutput =
//                "\tA\tB\tC\t\n" +
//                "0\t1.4\ta\t1\t\n" +
//                "1\t2.7\tb\t2\t\n" +
//                "2\t \tnull\t \t\n" +
//                "3\t4.2\tdd\t4\t\n" +
//                "4\t5.1\te\t5";
//        assertEquals(expectedOutput, printDataFrameOutput);
//    }
    public void testDataFrameConstructorFromArrayListOfColumns() {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        columns.add(new Column("A", list));
        DataFrame df = new DataFrame(columns);
        assertEquals(columns, df.getColumns());
    }
//
//    public void constructorTest(){
//        c = new Calculator(4, 5);
//        assertThat(9, is(c.sum()));
//    }
}