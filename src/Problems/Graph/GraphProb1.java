package Problems.Graph;

public class GraphProb1 {


    public static void main(String[] args){
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '0', '0', '0', '1'},
            {'0', '0', '0', '1', '1'},
            {'1', '0', '0', '1', '1'}
        };
        System.out.println("Number of Islands: " + numIslands(grid));
    }

        public static int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int rows = grid.length;
            int cols = grid[0].length;
            int islands = 0;

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {

                    if (grid[r][c] == '1') {
                        islands++;
                        dfs(grid, r, c);
                    }
                }
            }

            return islands;
        }

        private static void dfs(char[][] grid, int r, int c) {
            int rows = grid.length;
            int cols = grid[0].length;

            // Out of bounds
            if (r < 0 || c < 0 || r >= rows || c >= cols) {
                return;
            }

            // Water or already visited
            if (grid[r][c] != '1') {
                return;
            }

            // Mark as visited
            grid[r][c] = '0';

            // Explore 4 directions
            dfs(grid, r + 1, c); // Down
            dfs(grid, r - 1, c); // Up
            dfs(grid, r, c + 1); // Right
            dfs(grid, r, c - 1); // Left
        }

}
