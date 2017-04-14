package fr.rivieradev.jmh;

import java.math.BigInteger;
import java.util.Random;

public class IDontKnowHowToBenchmark {
  String x = new BigInteger(8000, new Random()).toString(32);
  
  private void method() {
    x.indexOf("nb");
  }
  

  public static void main(String[] args) {
    IDontKnowHowToBenchmark b = new IDontKnowHowToBenchmark();
    
    long instantMilo = System.currentTimeMillis();
    long instant = System.nanoTime();
    for (int i = 0; i<1000000; i++) {
      b.method();
    }
    System.out.println(System.currentTimeMillis() - instantMilo);
    System.out.println(System.nanoTime() - instant);
  }
}
