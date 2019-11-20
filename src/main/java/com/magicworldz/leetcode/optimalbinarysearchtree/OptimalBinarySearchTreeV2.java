package com.magicworldz.leetcode.optimalbinarysearchtree;

import com.magicworldz.leetcode.common.duration.Duration;
import com.magicworldz.leetcode.common.duration.LeetCode;

public class OptimalBinarySearchTreeV2 {
    public static void main(String[] args) {
        var app = LeetCode.newInstance(OptimalBinarySearchTreeV2.class);
        double[] p = new double[]{0,    0.15, 0.1,  0.05, 0.10, 0.2};
        double[] q = new double[]{0.05, 0.1,  0.05, 0.05, 0.05, 0.1};
        int[][] root = new int[p.length+1][p.length+1];
        System.out.println(app.optimalBST(p, q, root));
    }

    @Duration
    public double optimalBST(double[] p, double[] q, int[][] root) {
        // e[i][j] = q(i-1) j=i-1
        // e[i][j] = min(i<=k<=j){w[i][k-1] + p[k] + w[k+1][j]}
        //           w[i][j] = w[i][k-1] + p[k] + w[k+1][j]

        // n = q.length - 1
        // p.length = q.length
        // p [1, n] q [0, n]
        int n = q.length - 1;
        int len = q.length + 1;
        double[][] e = new double[len][len];
        double[][] w = new double[len][len];

        for (int i = 1; i <= n + 1; ++i) {
            e[i][i-1] = w[i][i-1] = q[i-1];
        }

        int j;
        double ee;
        for (int l = 1; l <= n; ++l) {
            for (int i = 1; i <= n - l + 1; ++i) {
                j = i + l - 1;
                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j-1] + p[j] + q[j];
                if (i == j) {
                    root[i][j] = j;
                    e[i][j] = e[i][j-1] + e[j+1][j] + w[i][j];
                } else {
                    for (int k = root[i][j-1]; k <= root[i+1][j]; ++k) {
                        ee = e[i][k-1] + e[k+1][j] + w[i][j];
                        if (e[i][j] > ee) {
                            e[i][j] = ee;
                            root[i][j] = k;
                        }
                    }
                }
            }
        }
        return e[1][n];
    }
}
