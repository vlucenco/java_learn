package ru.vitali.pft.sandbox;

public class Rectangle {

  public double a;
  public double b;

  public Rectangle(double a, double b) {
    this.a = a;
    this.b = b;
  }

  public double area() {
    return this.a * this.b;
  }
}


//  Rectangle r = new Rectangle(4, 6);
//System.out.println("Площадь прямоугольника со сторонами "+r.a+" и "+r.b+" = "+r.area());