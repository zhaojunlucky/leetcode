package com.magicworldz.leetcode.matrixmultiplication;

import com.magicworldz.leetcode.common.CollectionUtil;
import com.magicworldz.leetcode.common.duration.Duration;
import com.magicworldz.leetcode.common.duration.LeetCode;

import java.util.List;

public class MatrixMultiplication {
    public static void main(String[] args) {
        List<Matrix> matrices = CollectionUtil.newArrList();
        matrices.add(new Matrix(10, 20));
        matrices.add(new Matrix(20, 50));
        matrices.add(new Matrix(50, 1));
        matrices.add(new Matrix(1, 100));

        List<String> answer = CollectionUtil.newArrList();
        var app = LeetCode.newInstance(MatrixMultiplication.class);
        System.out.println("min multiplication: " + app.matrixMultiplication(matrices, answer));
        System.out.println("how to calculate: " + String.join(" ", answer));
    }

    @Duration
    public int matrixMultiplication(List<Matrix> matrices, List<String> answer) {
        int len = matrices.size();
        int[][] dp = new int[len][len];
        int[][] b = new int[len][len];
        for (int i = 0; i < len; ++i) {
            dp[i][i] = 0;
        }

        int j;
        int q;
        for (int n = 1; n < len; ++n) {
            for (int i = 0; i < len - n; ++i) {
                j = i + n;
                // m(i, j)(i <= k < j) = min{m(i,k)+m(k+1,j) + r(i)*r(k)*r(j)}
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; ++k) {
                    q = dp[i][k] + dp[k + 1][j]
                            + matrices.get(i).getRows() * matrices.get(k).getColumns() * matrices.get(j).getColumns();
                   if (q < dp[i][j]) {
                       dp[i][j] = q;
                       b[i][j] = k;
                   }
                }
            }
        }
        calcAnswer(b, 0, len - 1, answer);
        return dp[0][len - 1];
    }

    private void calcAnswer(int[][] b, int i, int j, List<String> answer) {
        if (i == j) {
            answer.add("M" + String.valueOf(i));
        } else {
            answer.add("(");
            calcAnswer(b, i, b[i][j], answer);
            calcAnswer(b, b[i][j] + 1, j, answer);
            answer.add(")");
        }
    }


}
