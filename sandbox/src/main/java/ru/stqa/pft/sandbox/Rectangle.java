package ru.stqa.pft.sandbox;

/**
 * Created by Альфия on 11.02.2017.
 */
public class Rectangle {
    public double c;
    public double b;

    public Rectangle (double c, double b) {
        this.c = c;
        this.b = b;
    }


    public double area() {
        return this.c * this.b;
    }
}
