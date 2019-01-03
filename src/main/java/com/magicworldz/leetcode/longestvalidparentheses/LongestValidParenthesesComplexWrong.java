package com.magicworldz.leetcode.longestvalidparentheses;

import java.util.*;

public class LongestValidParenthesesComplexWrong {

    public static void main(String[] args) {
        var app = new LongestValidParenthesesComplexWrong();
        var tests = Arrays.asList(")(((((()())()()))()(()))(",
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
        if (s == null || s.isEmpty()) {
            return 0;
        }
        // (()()())(
        // (()()()
        int i = 0;
        Map<Integer, ParenthesesMatch> cache = new HashMap<>();
        do {
            ParenthesesMatch m = buildCache(cache, s, i, s.length() - 1);
            if (m != null) {
                i = cache.keySet().stream().max(Integer::compareTo).get();
                i = cache.get(i).getEndIndex() + 1;
            } else {
                break;
            }
        } while (i < s.length() - 1);


        int size = 0;

        do {
            size = cache.size();

            for (int k : cache.keySet()) {
                ParenthesesMatch m = cache.get(k);
                int nextK = m.getEndIndex() + 1;
                ParenthesesMatch n = cache.get(nextK);
                if (m.isMatched() && n != null && n.isMatched()) {
                    // merge m, n
                    m.setEndIndex(n.getEndIndex());
                    cache.remove(nextK);
                    break;
                }
            }
        } while (size > cache.size());



        return cache.isEmpty()? 0 : cache.values().stream()
                .max(Comparator.comparingInt(ParenthesesMatch::getMatchedLen)).get().getMatchedLen();
    }

    private ParenthesesMatch buildCache(Map<Integer, ParenthesesMatch> cache, final String s, final int f, final int e) {
        if (cache.containsKey(f)) {
            return cache.get(f);
        } else if (f > e) {
            return null;
        }


        int i = f;
        while (i <= e && s.charAt(i) == ')') {
            i++;
        }
        if (i > e) {
            return null;
        }

        // 1. should be a '('
        ParenthesesMatch m = ParenthesesMatch.of(i);
        // 2. so, match all like '()()()'
        while (i < e && s.charAt(i) != s.charAt(i + 1)) {
            i++;
        }

        // 3. if char(i) == '(', match next child
        // for example, like (()), for the first '(', need match the second '(' first
        if (i != f && s.charAt(i) == '(') {
            ParenthesesMatch child = buildCache(cache, s, i, e);
            if (child.isMatched()) {
                // extend current
                m.setEndIndex(child.getEndIndex());
            } else {
                m.setEndIndex(i - 1);
            }
        } else if (i == f && i < e && s.charAt(i + 1) == '(') {
            ParenthesesMatch child = buildCache(cache, s, i + 1, e);
            if (child.isMatched() && child.getEndIndex() < e && s.charAt(child.getEndIndex() + 1) == ')') {
                m.setEndIndex(child.getEndIndex() + 1);
                cache.remove(child.getStartIndex());
            }
        } else {
            m.setEndIndex(i);
        }

        cache.put(m.getStartIndex(), m);
        return m;
    }
}

class ParenthesesMatch {
    private int startIndex;
    private int endIndex;

    private ParenthesesMatch(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public static ParenthesesMatch of(int startIndex) {
        return new ParenthesesMatch(startIndex, startIndex);
    }

    public int getEndIndex() {
        return endIndex;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public boolean isMatched() {
        return this.startIndex >= 0 && this.endIndex > this.startIndex;
    }

    public int getMatchedLen() {
        if (this.isMatched()) {
            return this.endIndex - this.startIndex + 1;
        } else {
            return 0;
        }
    }
}
