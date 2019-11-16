package com.magicworldz.leetcode.longestpalindrome;

import com.magicworldz.leetcode.common.duration.Duration;
import com.magicworldz.leetcode.common.duration.LeetCode;

import java.util.ArrayList;

public class LongestPalindromeV2 {
    public static void main(String[] args) {
        var app = LeetCode.newInstance(LongestPalindromeV2.class);
        System.out.println(app.longestPalindrome("character"));
        System.out.println(app.longestPalindrome("civic"));
        System.out.println(app.longestPalindrome("racecar"));
        System.out.println(app.longestPalindrome("aibohphobia"));

        System.out.println(app.longestPalindrome("abccba"));
    }

    @Duration
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        int[][] dp = new int[len][len];
        char[][] b = new char[len][len];

        for (int i = 0; i < len; ++i) {
            dp[i][i] = 1;
        }

        int j;
        int delta;
        char[] chars = s.toCharArray();
        for (int l = 1; l < len; ++l) {
            for (int i = 0; i < len -l; ++i) {
                j = i + l;
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);

                delta = 0;
                if (chars[i] == chars[j]) {
                    if (i == j - 1) {
                        delta += 1;
                    }
                    else {
                        delta += 2;
                    }
                }
                if (dp[i+1][j] > dp[i][j-1]) {
                    dp[i][j] = dp[i+1][j] + delta;
                    b[i][j] = '^';
                } else {
                    dp[i][j] = dp[i][j-1] + delta;
                    b[i][j] = '>';
                }

            }
        }
        return findLongestPalindrome(len, dp, b, chars);
    }

    private String findLongestPalindrome(int len, int[][] dp, char[][] b, char[] chars) {
        int maxLen =  dp[0][len - 1];
        int m = 0;
        int n = 0;
        for (; n < len; ++n) {
            if (dp[m][n] == maxLen) {
                break;
            }
        }
        boolean[] index = new boolean[len];

        while (dp[m][n] > 1) {
            if (chars[m] == chars[n]) {
                index[m] = index[n] = true;
                if (m + 2 == n) {
                    index[m+1] = true;
                }
            }
            if (b[m][n] == '>') {
                --n;
            } else if (b[m][n] == '^') {
                ++m;
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            if (index[i] == true) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
}
