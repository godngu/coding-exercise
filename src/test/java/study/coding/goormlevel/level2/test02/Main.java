package study.coding.goormlevel.level2.test02;

import org.junit.jupiter.api.Test;

public class Main {


    public static final int O = 1;
    public static final int X = 0;

    @Test
    void name() {
        solution(5);
    }

    private void solution(int n) {
        int[][] arr = new int[n][n*2];

        int xMin = 0, yMin = 0;
        int xMax = n, yMax = n * 2;
        int directionX = 1, directionY = 1;
        int reverse = -1;

        yLoop(arr, yMin, yMax, xMin, false);
        xLoop(arr, xMin, xMax, yMax-2, false);

        yLoop(arr, yMax, yMin, xMax-1, true);
        xLoop(arr, xMax, xMin+2, yMin, true);

        yLoop(arr, yMin+2, yMax-4, xMin+2, false);
//        xLoop(arr, xMin, xMax, yMax-2, false);

        print(arr);
    }

    private void xLoop(int[][] arr, int start, int end, int y, boolean reverse) {
        System.out.println(String.format("xLoop: start=%d, end=%d, y=%d, reverse=%b", start, end, y, reverse));
        if (reverse) {
            for (int x = start-1; x >= end; x--) {
                arr[x][y] = O;
            }
        } else {
            for (int x = start; x < end; x++) {
                arr[x][y] = O;
            }
        }
    }

    private void yLoop(int[][] arr, int start, int end, int x, boolean reverse) {
        System.out.println(String.format("yLoop: start=%d, end=%d, x=%d, reverse=%b", start, end, x, reverse));

        if (reverse) {
            for (int y = start-1; y >= end; y--) {
                arr[x][y] = y % 2 == 0 ? O : X;
            }

        } else {
            for (int y = start; y < end; y++) {
                arr[x][y] = y % 2 == 0 ? O : X;
            }
        }
    }

    private void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println("");
        }
        System.out.println("------------------------");
        print2(arr);
    }

    private void print2(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] == 1 ? '#' : '|');
            }
            System.out.println("");

        }
    }
}
