package com.magicworldz.leetcode.longestvalidparentheses;

import java.util.*;

public class LongestValidParenthesesStack {

    public static void main(String[] args) {
        var app = new LongestValidParenthesesStack();
        var tests = Arrays.asList(
                ")(((((()())()()))()(()))(",
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

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int maxLen = 0;
        for (int i = 1; i <= s.length(); ++i) {
            if (s.charAt(i - 1) == '(') {
                stack.push(i);
            } else if (stack.size() > 1) {
                stack.pop();
                maxLen = Math.max(maxLen, i - stack.peek());
            } else if (stack.size() == 1) {
                stack.pop();
                stack.push(i);
            }
        }
        return maxLen;
    }

}

