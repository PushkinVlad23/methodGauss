package org.example;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        double[][] matrix = readMatrixFromFile("src\\main\\resources\\matrix.txt");
        int rows = matrix.length;
        int cols = matrix[0].length;

        System.out.println("Исходная матрица:");
        printMatrix(matrix);

        // Применим метод Гаусса для решения СЛАУ
        for (int i = 0; i < rows - 1; i++) {
            for (int j = i + 1; j < rows; j++) {
                double ratio = matrix[j][i] / matrix[i][i];
                for (int k = 0; k < cols; k++) {
                    matrix[j][k] -= ratio * matrix[i][k];
                }
            }
        }

        double[] solutions = new double[rows];
        for (int i = rows - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < rows; j++) {
                sum += matrix[i][j] * solutions[j];
            }
            solutions[i] = (matrix[i][cols - 1] - sum) / matrix[i][i];
        }

        System.out.println("\nРешение СЛАУ:");
        for (int i = 0; i < rows; i++) {
            System.out.println("X" + i + " = " + solutions[i]);
        }

    }

    public static double[][] readMatrixFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            double[][] matrix = new double[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = scanner.nextDouble();
                }
            }

            scanner.close();
            return matrix;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
