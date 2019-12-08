package com.magicworldz.leetcode.bitoniceuclideantraveling;

import com.magicworldz.leetcode.common.duration.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BitonicEuclideanTravelingSalesmanProblem {
    public static void main(String[] args) {
        var app = LeetCode.newInstance(BitonicEuclideanTravelingSalesmanProblem.class);
        Point[] points = new Point[] {Point.of(0, 0), Point.of(1, 6),
                Point.of(2, 3), Point.of(5, 2), Point.of(6, 5),
                Point.of(7, 1), Point.of(8, 4)};
        List<Point> solution = new ArrayList<>();
        System.out.println(app.bitonicTours(points, solution));
        System.out.println("solution: " + solution.stream().map(Point::toString).collect(Collectors.joining("->")));
    }

    private double bitonicTours(Point[] points, List<Point> solution) {
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
        calcTour(root, points, solution);
        return b[points.length - 1][points.length - 1];
    }

    private void calcTour(int[][] root, Point[] points, List<Point> solution) {
        int i = points.length - 1;
        int j = points.length - 2;
        solution.add(points[i]);
        solution.add(points[j]);
        int k = root[j][i];
        calcTour(root, points, solution, k, j);
        solution.add(points[k]);
    }

    private void calcTour(int[][] root, Point[] points, List<Point> solution, int i, int j) {
        if (i < j) {
            int k = root[i][j];
            solution.add(points[k]);

            if (k > 0) {
                calcTour(root, points, solution, i, k);
            }
        } else {
            int k = root[j][i];
            if (k > 0) {
                calcTour(root, points, solution, k, j);
                solution.add(points[k]);
            }
        }
    }
}
