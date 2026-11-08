package Problems.Array;

import java.util.Arrays;

public class Matrix {

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        System.out.println(Arrays.deepToString(matrix));
        System.out.println(Arrays.deepToString(setZeroes(matrix)));

    }
        public static int[][] setZeroes(int[][] matrix) {

            int m = matrix.length;
            int n = matrix[0].length;

            boolean[] row = new boolean[m];
            boolean[] col = new boolean[n];

            // Identify rows and columns containing zero
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (matrix[i][j] == 0) {
                        row[i] = true;
                        col[j] = true;
                    }
                }
            }

            // Set corresponding elements to zero
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (row[i] || col[j]) {
                        matrix[i][j] = 0;
                    }
                }
            }
            return matrix;
        }
}

