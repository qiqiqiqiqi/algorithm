package study.qi.com.algorithm.leetcode;

/**
 * 如何编程实现“求一个数的平方根”？要求精确到小数点后 6 位。
 */
public class SqrtBsearch {

    public static void main(String[] args) {
        double sqrt = sqrt(20000000, 0, 20000000);
        double sqrt2 = sqrt2(20000000);
        System.out.print("sqrt=" + sqrt + ",sprt2=" + sqrt2);
    }

    private static double sqrt(double num, double minValue, double maxValue) {
        double middleValue = (minValue + maxValue) / 2;
        if (Math.pow(middleValue, 2) - num < 0 && Math.abs(Math.pow(middleValue, 2) - num) < 0.00001) {
            return middleValue;
        } else if (Math.pow(middleValue, 2) > num) {
            return sqrt(num, minValue, middleValue);
        } else /*if (Math.pow(middleValue, 2) < num)*/ {
            return sqrt(num, middleValue, maxValue);
        }
    }


    private static double sqrt2(double num) {
        sqrtGetInt(num, 0, num);
        return sqrtGetFloat(num, sqrtGetInt(num, 0, num), 6, 0);
    }

    private static int sqrtGetInt(double num, double minValue, double maxValue) {
        int middleValue = (int) ((minValue + maxValue) / 2);
        if (Math.pow(middleValue, 2) - num <= 0
                && Math.pow(middleValue + 1, 2) - num > 0) {
            return middleValue;
        } else if (Math.pow(middleValue, 2) > num) {
            return sqrtGetInt(num, minValue, middleValue);
        } else {
            return sqrtGetInt(num, middleValue, maxValue);
        }
    }

    private static double sqrtGetFloat(double num, double minValue, int floatLength, int floatIndex) {
        double maxValue = (minValue + 1.0 / Math.pow(10, floatIndex));
        if (floatIndex == floatLength - 1) {
            return sqrtGetIndexFloat(num, minValue, maxValue, floatIndex);
        } else {
            return sqrtGetFloat(num, sqrtGetIndexFloat(num, minValue, maxValue, floatIndex), floatLength, ++floatIndex);
        }
    }

    private static double sqrtGetIndexFloat(double num, double minValue, double maxValue, int floatIndex) {
        double middleValue = (minValue + maxValue) / 2;
        if ((Math.pow(middleValue, 2) - num) <= 0
                && (Math.pow(middleValue + (1.0 / Math.pow(10, floatIndex + 1)), 2) - num) > 0) {
            return middleValue;
        } else if (Math.pow(middleValue, 2) > num) {
            return sqrtGetIndexFloat(num, minValue, middleValue, floatIndex);
        } else {
            return sqrtGetIndexFloat(num, middleValue, maxValue, floatIndex);
        }
    }


}
