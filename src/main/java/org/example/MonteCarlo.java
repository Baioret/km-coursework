package org.example;

/**
 * Реализация метода Монте-Карло для вычисления определенного интеграла
 * @author Baioret
 * @version 1.0
 */
public class MonteCarlo {

    /** Список значений случайных величин xi */
    private static double[] randomVariables;

    /** z(alpha/2) = 1.96 при уровне значимости alpha = 0.05 */
    private static final double z_alpha_2 = 1.96;

    /** Основной метод класса для вычисления определенного интервала с помощью метода Монте-Карло */
    public static void solve(int a, int b, int n) {

        double omega = calculateEstimationOfDefiniteIntegral(a, b, n);
        double I = calculateDefiniteIntegral(a, b);
        double absoluteError = calculateAbsoluteError(I, omega);
        double sampleVariance = calculateSampleVariance(omega);
        double[] confidenceInterval = getConfidenceInterval(omega, Math.sqrt(sampleVariance));

        String output = """
                Определенный интеграл от функции f(x) = x - 1 на отрезке [a,b]
                
                Начало отрезка: %d
                Конец отрезка: %d
                Число разбиений отрезка: %d
                
                Точное значение интеграла: %.4f
                Вычисленное значение интеграла: %.4f
                
                Абсолютная погрешность: %.4f
                Выборочная дисперсия: %.4f
                Среднеквадратичное отклонение: %.4f
                
                Доверительный интервал для оценки интеграла: (%.4f ; %.4f)
                
                """.formatted(a, b, n, I, omega, absoluteError, sampleVariance, Math.sqrt(sampleVariance), confidenceInterval[0], confidenceInterval[1]);

        System.out.println(output);
    }

    /**
     * Вычисление оценки определенного интеграла a∫(x - 1)dx, b
     * @param a значение нижнего предела интегрирования
     * @param b значение верхнего предела интегрирования
     * @param n число испытаний (разбиений отрезка)
     * @return оценка определенного интеграла
     */
    private static double calculateEstimationOfDefiniteIntegral(int a, int b, int n) {

        randomVariables = new double[n];

        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            randomVariables[i] = RandomVariable.generate(a, b);
            sum += randomVariables[i] - 1;
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

    /** Вычисление абсолютной погрешности */
    private static double calculateAbsoluteError(double a, double b) {
        return Math.abs(a - b);
    }

    /** Вычисление выборочной дисперсии */
    private static double calculateSampleVariance(double omega) {

        double sum = 0.0;
        for (double xi : randomVariables) {
            sum += Math.pow(xi - omega, 2);
        }
        return sum / (randomVariables.length - 1);
    }

    /** Получение крайних точек доверительного интервала */
    private static double[] getConfidenceInterval(double omega, double deviation) {
        double[] points = new double[2];

        points[0] = omega - z_alpha_2 * deviation / Math.sqrt(randomVariables.length);
        points[1] = omega + z_alpha_2 * deviation / Math.sqrt(randomVariables.length);

        return points;
    }
}
