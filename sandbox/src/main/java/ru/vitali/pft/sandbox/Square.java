package ru.vitali.pft.sandbox;

public class Square {

  public double l;

  public Square(double l){
    this.l = l;
  }

  public double area() {
    return this.l * this.l;
  }
}

//  Square s = new Square(5);
//System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());