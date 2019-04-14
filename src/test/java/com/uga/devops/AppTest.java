package com.uga.devops;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testSumFloatCol()
    {
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

    public void testSumIntCol()
    {
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

    public void testMaxFloatCol()
    {
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

    public void testMaxIntCol()
    {
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

    public void testMaxStringCol()
    {
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

    public void testAverageCol()
    {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Object> list = new ArrayList<Object>();

        list.add(1.4f);
        list.add(2.7f);
        list.add(null);
        list.add(5.1f);
        list.add(4.2f);

        columns.add(new Column("A", list));

        DataFrame df = new DataFrame(columns);

        float f = (Float)df.loc("A").average();
        double roundedFloat = Math.round(f * 100.0) / 100.0;

        assertEquals(2.68, roundedFloat);
    }

    public void testMinCol()
    {
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
}
