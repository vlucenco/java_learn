package ru.vitali.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point a = new Point(1, 4);
    Point b = new Point(3, 5);
    System.out.println("Расстояние между точками " +
            "А(" + a.x + ";" + a.y + ") и B(" + b.x + ";" + b.y + ")" + " = " + Point.distance(a, b));

    Point c = new Point(3, 5);
    Point d = new Point(1, 4);
    System.out.println("Расстояние между точками " +
            "C(" + c.x + ";" + c.y + ") и D(" + d.x + ";" + d.y + ")" + " = " + Point.distance(c, d));

    Point e = new Point(-2.5, 5);
    Point f = new Point(1, -45.7);
    System.out.println("Расстояние между точками " +
            "E(" + e.x + ";" + e.y + ") и F(" + f.x + ";" + f.y + ")" + " = " + Point.distance(e, f));

    Point g = new Point(-0.0, 2.36);
    Point h = new Point(-1, -12.745);
    System.out.println("Расстояние между точками " +
            "G(" + g.x + ";" + g.y + ") и H(" + h.x + ";" + h.y + ")" + " = " + Point.distance(g, h));

    Point i = new Point(-0.0, 2.36);
    Point j = new Point(-1, -12.745);
    System.out.println("Расстояние между точками " +
            "I(" + i.x + ";" + i.y + ") и G(" + j.x + ";" + j.y + ")" + " = " + i.distanceMethodWithOneParameter(j));
  }
}