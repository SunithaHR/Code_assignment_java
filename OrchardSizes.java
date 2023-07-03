package ition_Solution;

import java.util.ArrayList;
import java.util.List;

public class OrchardSizes {

    // Function to compute the sizes of all orchards in the matrix
    public static List<Integer> computeOrchardSizes(char[][] matrix) {
        List<Integer> orchardSizes = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        // Traverse each cell in the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 'T' && !visited[i][j]) {
                    // Found a tree, perform DFS to find the size of the orchard
                    int orchardSize = performDFS(matrix, visited, i, j);
                    orchardSizes.add(orchardSize);
                }
            }
        }

        return orchardSizes;
    }

    // Perform Depth-First Search (DFS) to find the size of the orchard
    private static int performDFS(char[][] matrix, boolean[][] visited, int row, int col) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Base cases for out of bounds or already visited cells
        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col] || matrix[row][col] == 'O') {
            return 0;
        }

        // Mark the current cell as visited
        visited[row][col] = true;

        int orchardSize = 1;

        // Recursively explore the neighbors (up, down, left, right, diagonals)
        int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
        for (int i = 0; i < 8; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];
            orchardSize += performDFS(matrix, visited, newRow, newCol);
        }

        return orchardSize;
    }

    // Main method to test the code
    public static void main(String[] args) {
        char[][] matrix = {
                {'O', 'T', 'O', 'O'},
                {'O', 'T', 'O', 'T'},
                {'T', 'T', 'O', 'T'},
                {'O', 'T', 'O', 'T'}
        };

        List<Integer> orchardSizes = computeOrchardSizes(matrix);
        System.out.println(orchardSizes);
    }
}

