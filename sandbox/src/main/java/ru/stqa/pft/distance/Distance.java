package ru.stqa.pft.distance;

/**
 * Created by Альфия on 11.02.2017.
 */
public class Distance {
    public static void main (String[] args) {

        Point p1 = new Point(1,10);
        Point p2 = new Point(10,10);

        System.out.println("Расстояние между точками " + "(" + p1.x + "," + p1.y + ")" + " и " + "(" + p2.x + "," + p2.y + ")" + " = " + p1.distance(p2));

    }
}
