package org.example;

/**
 * Равномерное распределение
 */
public class Uniform {

    /**
     * @param a левая граница
     * @param b правая граница
     * @return Uniform[a,b]
     */
    public static double generate(int a, int b) {
        return Math.random() * (b - a) + a;
    }
}
