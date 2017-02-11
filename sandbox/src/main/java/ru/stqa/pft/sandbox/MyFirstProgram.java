package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		hello("world");
		hello("Lana");

		double a = 5;
		System.out.println("площаль квадрата со стороной " + a + " = " + area(a));

		double c = 4;
		double b = 6;
		System.out.println("площадь прямоугольника со сторонами "+ c + " и " + b + " = " + area(b, c));
	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

	public static double area(double b) {
		return b * b;
	}

	public static double area(double c, double b) {
		return c * b;
	}

}