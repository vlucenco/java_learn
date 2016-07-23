package ru.vitali.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistanceMethodPositiveCoordinates() {
    Point a = new Point(1, 4);
    Point b = new Point(3, 5);
    Assert.assertEquals(a.distanceMethod(b), 2.23606797749979);
  }

  @Test
  public void testDistanceMethodNegativeCoordinates() {
    Point a = new Point(-2.5, 5);
    Point b = new Point(1, -45.7);
    Assert.assertEquals(a.distanceMethod(b), 50.82066508813123);
  }

  @Test
  public void testDistanceFunctionPositiveCoordinates() {
    Point a = new Point(3, 5);
    Point b = new Point(1, 4);
    Assert.assertEquals(Point.distanceFunction(a, b), 2.23606797749979);
  }

  @Test
  public void testDistanceFunctionNegativeCoordinates() {
    Point a = new Point(-0.0, 2.36);
    Point b = new Point(-1, -12.745);
    Assert.assertEquals(Point.distanceFunction(a, b), 15.13806543122337);
  }
}
