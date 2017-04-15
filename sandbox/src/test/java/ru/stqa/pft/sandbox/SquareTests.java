package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Альфия on 18.02.2017.
 */
public class SquareTests {

    @Test
    public void testArea() {
        Square s = new Square(5.0);
        Assert.assertEquals(s.area(), 25.0);

    }
}
