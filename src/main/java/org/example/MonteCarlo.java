package org.example;

/**
 * Реализация метода Монте-Карло для вычисления определенного интеграла
 * @author Baioret
 * @version 1.0
 */
public class MonteCarlo {

    /**
     * Список значений случайных величин xi
     */
    private static double[] variables;

    /**
     * Оценка определенного интеграла
     */
    private double omega;

    public static void solve(int a, int b, int n) {

        System.out.println(calculateDefiniteIntegral(a, b));
        System.out.println(calculateEstimationOfDefiniteIntegral(a, b, n));
    }

    /**
     * Вычисление оценки определенного интеграла a∫(x - 1)dx, b
     * @param a значение нижнего предела интегрирования
     * @param b значение верхнего предела интегрирования
     * @param n число испытаний (разбиений отрезка)
     * @return оценка определенного интеграла
     */
    private static double calculateEstimationOfDefiniteIntegral(int a, int b, int n) {

        variables = new double[n];

        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            variables[i] = RandomVariable.generate(a, b) - 1;
            sum += variables[i];
        }

        return (b - a) * sum / n;
    }

    /**
     * Вычисление точного значения определенного интеграла a∫(x - 1)dx, b
     * @param a значение нижнего предела интегрирования
     * @param b значение верхнего предела интегрирования
     * @return значение определенного интеграла
     */
    private static double calculateDefiniteIntegral(int a, int b) {

        return (Math.pow(b - 1, 2) / 2) - (Math.pow(a - 1, 2) / 2);
    }
}
