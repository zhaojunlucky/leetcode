package com.magicworldz.leetcode.bitoniceuclideantraveling;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class Point {
    @Setter
    private int x;
    @Setter
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public double distance(Point point) {
        return Math.sqrt(square(x - point.x) + square(y - point.y));
    }

    private int square(int n) {
        return n * n;
    }

    public int getX() {
        return x;
    }
}
