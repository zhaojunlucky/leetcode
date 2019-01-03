package com.magicworldz.leetcode.longestvalidparentheses;

import java.util.Arrays;
import java.util.Stack;

public class LongestValidParenthesesDP {

    public static void main(String[] args) {
        var app = new LongestValidParenthesesDP();
        var tests = Arrays.asList(
                ")(((((()())()()))()(()))(",
                "()(()()",
                ")()())()()(",
                "((()))())",
                "()(())",
                "()(()",
                "(()()())(",
                "(()()()");
        tests.forEach(s-> {
            System.out.println(String.format("%s=%d", s, app.longestValidParentheses(s)));
        });
    }

    // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
    // ) ( ( ( ( ( ( ) ( )  ) (  )  (  )   )  )  (  )  (  (  )  )  )  (
    // 0 0 0 0 0 0 0 2 0 4  6 0  8  0  10  12 14 0 16  0  0  18 20 22 0

    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int maxLen = 0;

        int[] dp = new int[s.length()];
        int openCnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                ++openCnt;
            } else if (openCnt > 0) {
                dp[i] = 2;
                // for ( ( ) )
                //     0 1 2 3
                // dp[3] = 2
                // dp[3] = dp[3] + dp[2]
                dp[i] += dp[i - 1];
                // for ( ) ( )
                //     0 1 2 3
                // dp[3] = 2
                // dp[3] = 2 + dp[3-dp[3]]
                if (i - dp[i] > 0) {
                    dp[i] += dp[i-dp[i]];
                }
                maxLen = Math.max(maxLen, dp[i]);
                --openCnt;
            }
        }
        return maxLen;
    }

}

