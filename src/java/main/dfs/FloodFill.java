package src.java.main.dfs;

/**
 * You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].
 * <p>
 * To perform a flood fill:
 * <p>
 * Begin with the starting pixel and change its color to color.
 * Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
 * Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
 * The process stops when there are no more adjacent pixels of the original color to update.
 * Return the modified image after performing the flood fill.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 * <p>
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
 * <p>
 * Note the bottom corner is not colored 2, because it is not horizontally or vertically connected to the starting pixel.
 * <p>
 * Example 2:
 * <p>
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 * <p>
 * Output: [[0,0,0],[0,0,0]]
 * <p>
 * Explanation:
 * <p>
 * The starting pixel is already colored with 0, which is the same as the target color. Therefore, no changes are made to the image.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 50
 * 0 <= image[i][j], color < 216
 * 0 <= sr < m
 * 0 <= sc < n
 */
public class FloodFill {
    int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color)
            return image;
        boolean[][] visited = new boolean[image.length][image[0].length];
        floodfill(visited, sr, sc, image[sr][sc], color, image);
        return image;
    }

    /**
     * Time Complexity: O(row * columns)
     *
     * @param visited
     * @param row
     * @param col
     * @param startColor
     * @param color
     * @param image
     */
    private void floodfill(boolean[][] visited, int row, int col, int startColor, int color, int[][] image) {
        if (visited[row][col])
            return;
        visited[row][col] = true;
        image[row][col] = color;
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow >= 0 && newCol >= 0 && newRow < image.length && newCol < image[0].length
                    && image[newRow][newCol] == startColor)
                floodfill(visited, row + direction[0], col + direction[1], startColor, color, image);
        }
    }
}
