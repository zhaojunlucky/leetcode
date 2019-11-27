package com.magicworldz.leetcode.bitoniceuclideantraveling;

import com.magicworldz.leetcode.common.duration.LeetCode;

import java.util.Arrays;
import java.util.Comparator;

public class BitonicEuclideanTravelingSalesmanProblem {
    public static void main(String[] args) {
        var app = LeetCode.newInstance(BitonicEuclideanTravelingSalesmanProblem.class);
        Point[] points = new Point[] {Point.of(0, 0), Point.of(1, 6),
                Point.of(2, 3), Point.of(5, 2), Point.of(6, 5),
                Point.of(7, 1), Point.of(8, 4)};

        System.out.println(app.bitonicTours(points));
    }

    private double bitonicTours(Point[] points) {
        Arrays.sort(points, Comparator.comparingInt(Point::getX));

        double[][] b = new double[points.length][points.length];
        int[][] root = new int[points.length][points.length];
        b[0][1] = points[0].distance(points[1]);

        for (int j = 2; j < points.length; ++j) {
            for (int i = 0; i < j - 1; ++i) {
                b[i][j] = b[i][j-1] + points[j-1].distance(points[j]);
                root[i][j] = j - 1;
            }
            b[j-1][j] = Double.MAX_VALUE;

            for (int k = 0; k < j - 1; ++k) {
                double q = b[k][j-1] + points[k].distance(points[j]);
                if (q < b[j-1][j]) {
                    b[j-1][j] = q;
                    root[j - 1][j] = k;
                }
            }
        }
        b[points.length - 1][points.length - 1] = b[points.length - 2][points.length - 1]
                + points[points.length - 2].distance(points[points.length - 1]);
        return b[points.length - 1][points.length - 1];
    }
}
