package ru.stqa.pft.distance;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Альфия on 18.02.2017.
 */
public class PointTests {

    @Test
    public void testDistance(){
        Point p1 = new Point(1.0,10.0);
        Point p2 = new Point(10.0,10.0);
        Assert.assertEquals(p1.distance(p2),9.0);
    }

    @Test
    public void testDistance1(){
        Point p1 = new Point(1.0,1.0);
        Point p2 = new Point(1.0,1.0);
        Assert.assertEquals(p1.distance(p2),0.0);
    }

    @Test
    public void testDistance2(){
        Point p1 = new Point(2.5,6.0);
        Point p2 = new Point(7.0,10.9);
        Assert.assertEquals(p1.distance(p2),6.652818951391959);
    }

}
