package fr.rivieradev.jmh;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IDontKnowHowToBenchmark {
  String a = new BigInteger(8000, new Random()).toString(32);
  int x = 1;
  int y = 2;
  ObjectMapper om = new ObjectMapper();
  
  private void search() {
    a.indexOf("nb");
  }
  
  private void json() {
    try {
      om.readTree("{\"firstName\":\"John\",\"lastName\":\"Smith\",\"age\":25,\"address\":{\"streetAddress\":\"21 2nd Street\",\"city\":\"New York\",\"state\":\"NY\",\"postalCode\":\"10021\"},\"phoneNumber\":[{\"type\":\"home\",\"number\":\"212 555-1234\"},{\"type\":\"fax\",\"number\":\"646 555-4567\"}],\"gender\":{\"type\":\"male\"}}");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private void repeat(int repeats) {
    for (int i = 0; i < repeats; i++) {
      json();
    }
  }

  public static void main(String[] args) {
    IDontKnowHowToBenchmark b = new IDontKnowHowToBenchmark();
    int iterations = 100000000;
    long instantMilo = System.currentTimeMillis();
    long instant = System.nanoTime();
    b.repeat(iterations);
    System.out.println((System.currentTimeMillis() - instantMilo)/iterations);
    System.out.println((System.nanoTime() - instant)/iterations);
  }
}
