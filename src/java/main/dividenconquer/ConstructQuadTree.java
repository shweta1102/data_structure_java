package src.java.main.dividenconquer;

/**
 * Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.
 * <p>
 * Return the root of the Quad-Tree representing grid.
 * <p>
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
 * <p>
 * val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can assign the val to True or False when isLeaf is False, and both are accepted in the answer.
 * isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
 * class Node {
 * public boolean val;
 * public boolean isLeaf;
 * public Node topLeft;
 * public Node topRight;
 * public Node bottomLeft;
 * public Node bottomRight;
 * }
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 * <p>
 * If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
 * If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
 * Recurse for each of the children with the proper sub-grid.
 * <p>
 * If you want to know more about the Quad-Tree, you can refer to the wiki.
 * <p>
 * Quad-Tree format:
 * <p>
 * You don't need to read this section for solving the problem. This is only if you want to understand the output format here. The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.
 * <p>
 * It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].
 * <p>
 * If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[0,1],[1,0]]
 * Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
 * Explanation: The explanation of this example is shown below:
 * Notice that 0 represents False and 1 represents True in the photo representing the Quad-Tree.
 * <p>
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
 * Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
 * Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
 * The topLeft, bottomLeft and bottomRight each has the same value.
 * The topRight have different values so we divide it into 4 sub-grids where each has the same value.
 * Explanation is shown in the photo below:
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == grid.length == grid[i].length
 * n == 2x where 0 <= x <= 6
 */
public class ConstructQuadTree {
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    public Node construct(int[][] grid) {
        return construct(0, 0, grid.length - 1, grid[0].length - 1, grid);
    }

    private Node construct(int row1, int col1, int row2, int col2, int[][] grid) {
        if (row2 - row1 + 1 <= 2) {
            return constructNode(row1, col1, grid);
        }

        Node topLeft = construct(row1, col1, row1 + (row2 - row1) / 2, col1 + (col2 - col1) / 2, grid);
        Node topRight = construct(row1, (col1 + (col2 - col1) / 2) + 1, row1 + (row2 - row1) / 2, col2, grid);
        Node bottomLeft = construct((row1 + (row2 - row1) / 2) + 1, col1, row2, col1 + (col2 - col1) / 2, grid);
        Node bottomRight = construct((row1 + (row2 - row1) / 2) + 1, (col1 + (col2 - col1) / 2) + 1, row2, col2, grid);
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && (topLeft.val == topRight.val && topRight.val == bottomLeft.val
                && bottomLeft.val == bottomRight.val)) {
            return new Node(grid[row1][col1] == 1, true);
        }
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    private Node constructNode(int row, int col, int[][] grid) {
        if (row + 1 > grid.length - 1 || col + 1 > grid[0].length - 1)
            return new Node(grid[row][col] == 1, true);
        if (grid[row][col] == grid[row][col + 1] &&
                grid[row][col + 1] == grid[row + 1][col + 1] &&
                grid[row + 1][col + 1] == grid[row + 1][col]) {
            return new Node(grid[row][col] == 1, true);
        } else {
            return new Node(false, false, new Node(grid[row][col] == 1, true), new Node(grid[row][col + 1] == 1, true),
                    new Node(grid[row + 1][col] == 1, true), new Node(grid[row + 1][col + 1] == 1, true));
        }
    }
}
