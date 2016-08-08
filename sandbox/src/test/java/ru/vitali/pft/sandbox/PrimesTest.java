package ru.vitali.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.vitali.pft.sandbox.Primes.isPrime;
import static ru.vitali.pft.sandbox.Primes.isPrimeFast;

public class PrimesTest {

  @Test
  public void testPrime(){
    Assert.assertTrue(isPrimeFast(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrime(){
    Assert.assertFalse(isPrime(Integer.MAX_VALUE - 2));
  }

  @Test(enabled = false)
  public void testPrimeLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(isPrime(n));
  }
}
