package org.example;

import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Введите значения нижнего и верхнего пределов интегрирования и число разбиений отрезка (через пробел): ");

        try {
            int a = input.nextInt();
            int b = input.nextInt();
            int n = input.nextInt();

            MonteCarlo.solve(a, b, n);

        } catch (Exception e) {
            System.out.println("Ошибка ввода. " + e.getMessage());
        }
    }
}