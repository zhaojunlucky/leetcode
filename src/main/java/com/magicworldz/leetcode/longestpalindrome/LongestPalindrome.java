package com.magicworldz.leetcode.longestpalindrome;

import com.magicworldz.leetcode.common.duration.Duration;
import com.magicworldz.leetcode.common.duration.LeetCode;

public class LongestPalindrome {
    public static void main(String[] args) {
        var app = LeetCode.newInstance(LongestPalindrome.class);
        System.out.println(app.longestPalindrome("babad"));
    }

    @Duration
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        char[] chars = this.padStr(s);
        int mx = 0;
        int id = 0;
        int i;
        int[] p = new int[chars.length];

        for (i = 0; i < chars.length; ++i) {
            if (mx > i && 2*id - i < chars.length) {
                p[i] = Math.min(p[2*id - i], mx - i);
            } else {
                p[i] = 1;
            }
            while (i >= p[i] && i + p[i] < chars.length && chars[i + p[i]] == chars[i - p[i]]) {
                ++p[i];
            }

            if (p[i] > p[id]) {
                mx = p[i] + i;
                id = i;
            }
        }

        StringBuilder sb = new StringBuilder(p[id] - 1);
        int len = p[id] - 1;
        for (int j = id - len; j <= id + len; ++j) {
            if (chars[j] != '#') {
                sb.append(chars[j]);
            }
        }

        return sb.toString();
    }

    private char[] padStr(String s) {
        char pad = '#';
        StringBuilder sb = new StringBuilder(s.length() * 2 + 1);
        sb.append(pad);
        for (int i = 0; i < s.length(); ++i) {
            sb.append(s.charAt(i)).append(pad);
        }
        return sb.toString().toCharArray();
    }
}
