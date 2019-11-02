package com.magicworldz.leetcode.sumofknumber;

import java.util.ArrayList;

public class SumOfKNumber {
    public static void main(String[] args) {
        var app = new SumOfKNumber();
        app.sumOfKNumber(10, 10);
    }

    public void sumOfKNumber(int sum, int n) {
        sumOfKNumber(sum, n, new ArrayList<Integer>());
    }

    private void sumOfKNumber(int sum, int n, ArrayList<Integer> integers) {
        if (n <= 0 || sum <= 0) {
            return;
        }

        if (sum == n) {
            for (int i = integers.size() - 1; i >= 0; i--) {
                System.out.print(String.format("%d + ", integers.get(i)));
            }
            System.out.println(n);
        }
        integers.add(n);

        sumOfKNumber(sum - n, n - 1, integers);
        integers.remove(integers.size() - 1);
        sumOfKNumber(sum, n - 1, integers);
    }
}
