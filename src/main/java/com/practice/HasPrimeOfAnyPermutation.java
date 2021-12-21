package com.practice;

import java.util.ArrayList;
import java.util.List;

public class HasPrimeOfAnyPermutation {

    public static void main(String[] args) {
        String s = "716";
        System.out.print(primeChecker(s));
    }

    public static int primeChecker(String tmp) {
        int[] numbers = new int[tmp.length()];
        for (int i = 0; i < tmp.length(); i++) {
            numbers[i] = tmp.charAt(i) - '0';
        }
        List<List<Integer>> matrix = new ArrayList<>();
        getPermutationArray(numbers, matrix, 0);
        for (List<Integer> list : matrix) {
            StringBuilder numString = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                numString.append(list.get(i).toString());
            }
            if (isPrime(Integer.parseInt(numString.toString()))) {
                return 1;
            }
        }
        return 0;
    }

    private static void getPermutationArray(int[] array, List<List<Integer>> matrix, int pos) {
        List<Integer> tempList = new ArrayList<>();
        if (pos >= array.length - 1) {
            System.out.print("[");
            for (int i = 0; i < array.length - 1; i++) {
                System.out.print(array[i] + ", ");
                tempList.add(array[i]);
            }
            if (array.length > 0)
                System.out.print(array[array.length - 1]);
            tempList.add(array[array.length - 1]);
            System.out.println("]");
            matrix.add(tempList);
            return;
        }

        for (int i = pos; i < array.length; i++) {

            int t = array[pos];
            array[pos] = array[i];
            array[i] = t;

            getPermutationArray(array, matrix, pos + 1);

            t = array[pos];
            array[pos] = array[i];
            array[i] = t;
        }


    }

    private static boolean isPrime(int num) {
        boolean flag = false;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                flag = true;
                break;
            }
        }
        boolean result = false;
        if (!flag) {
            result = true;
        }
        return result;
    }
}
