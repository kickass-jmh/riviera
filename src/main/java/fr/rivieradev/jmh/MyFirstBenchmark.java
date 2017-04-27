package fr.rivieradev.jmh;

import java.math.BigInteger;
import java.util.Random;

public class MyFirstBenchmark {
  String a = new BigInteger(8000, new Random()).toString(32);
  
  private int search() {
    return a.indexOf("nb");
  }
  
  private void repeat(int repeats) {
    for (int i = 0; i < repeats; i++) {
      search();
    }
  }
  
  public static void main(String[] args) {
    MyFirstBenchmark mfb = new MyFirstBenchmark();
    int iterations = 1;
    long start = System.currentTimeMillis();
    mfb.repeat(iterations);
    System.out.println((System.currentTimeMillis() - start)/(double)iterations);
  }
}
