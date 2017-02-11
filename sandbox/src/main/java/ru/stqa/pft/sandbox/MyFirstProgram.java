package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		hello("world");
		hello("Lana");

		Square s = new Square(5);
		System.out.println("площадь квадрата со стороной " + s.a + " = " + area(s));

		Rectangle r = new Rectangle(4,6);
		System.out.println("площадь прямоугольника со сторонами "+ r.c + " и " + r.b + " = " + area(r));
	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

	public static double area(Square s) {
		return s.a * s.a;
	}

	public static double area(Rectangle r) {
		return r.c * r.b;
	}

}