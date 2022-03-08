package ru.job4j.tracker.oop;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void when000and5105to12dot24() {
        Point a1 = new Point(0, 0, 0);
        Point a2 = new Point(5, 10, 5);
        double expected = 12.24;
        double result = a1.distance3D(a2);
        Assert.assertEquals(expected, result, 0.01);
    }

    @Test
    public void when000and5105to10() {
        Point a1 = new Point(1, 2, 3);
        Point a2 = new Point(3, 4, 5);
        double expected = 3.46;
        double result = a1.distance3D(a2);
        Assert.assertEquals(expected, result, 0.01);
    }
}