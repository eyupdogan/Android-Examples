package org.csystem.app;

import java.util.Scanner;

class App {
    public static void main(String [] args)
    {
        Scanner kb = new Scanner(System.in);
        System.out.print("Birinci sayıyı giriniz: ");
        int a = kb.nextInt();
        System.out.print("İkinci sayıyı giriniz: ");
        int b = kb.nextInt();

        NumberUtil.printTotal(a, b);
        int result = NumberUtil.multiply(a,b);
        System.out.println("Result = " + result);

        int resultSquare = NumberUtil.square(a);
        System.out.println("Result Square = " + resultSquare);
    }
}


class NumberUtil {
    public static void printTotal(int a, int b)
    {
        int total;
        total = a + b;
        System.out.println(total);
    }

    public static int multiply(int a, int b)
    {
        return a * b;
    }

    public static int square(int a)
    {
        return a * a;
    }
}