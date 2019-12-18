package study.qi.com.algorithm.sort;

import java.util.Arrays;

public class QuickSort3WaysApp {
    public static void sort(char[] arr) {
        int lt = 0, i = 0, gt = arr.length - 1;

        while (i <= gt) {
            if (Character.isLowerCase(arr[i]))
                swap(arr, i++, lt++);
            else if (Character.isUpperCase(arr[i]))
                swap(arr, i, gt--);
            else
                i++;
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        char[] arr = {'X','a', '3', 'T', 't', 'b', 'C', '4', '2'};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
