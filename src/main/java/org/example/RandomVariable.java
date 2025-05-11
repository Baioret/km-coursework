package org.example;

/**
 * Генерация случайных чисел
 */
public class RandomVariable {

    /**
     * Генерация равномерно распределенных случайных чисел на отрезке [a, b]
     * @param a значение левой точки
     * @param b значение правой точки
     * @return значение случайной величины x
     */
    public static double generate(int a, int b) {
        return a + (b - a) * Uniform.generate(0, 1);
    }
}
