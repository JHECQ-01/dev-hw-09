package org.example;

import java.io.File;
import java.util.Scanner;

public class Demo {
    File file = new File("c:\\file.txt");

    public static void main(String[] args) {
      String numbers = "10, 20, 30, 40, 50";
      Scanner sc = new Scanner(System.in);
      while (sc.hasNextInt()) {
          System.out.println(sc.nextInt() + " ");
      }
    }
}
