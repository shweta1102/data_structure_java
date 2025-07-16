package src.java.main.binarysearch;

/**
 * You are given an m x n integer matrix matrix with the following two properties:
 * <p>
 * Each row is sorted in non-decreasing order.
 * The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 * <p>
 * You must write a solution in O(log(m * n)) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        //find the row where element can be present based on first column and then search in that particular row
        int rowNumber = searchColumn(matrix, target, 0);
        return rowNumber >= 0 && rowNumber < matrix.length && searchRow(matrix, target, rowNumber);
    }

    private int searchColumn(int[][] matrix, int target, int col) {
        int leftRow = 0;
        int rightRow = matrix.length - 1;
        while (leftRow <= rightRow) {
            int mid = leftRow + (rightRow - leftRow) / 2;
            if (matrix[mid][col] == target)
                return mid;
            //if left is equal to right and we didn't find the element then it is not present. so we can return it's expected row
            if (leftRow == rightRow)
                return target < matrix[mid][col] ? mid - 1 : mid;
            //check right side. shift left pointer
            if (matrix[mid][col] < target)
                leftRow = mid + 1;
            //check in left side. shift right pointer
            if (matrix[mid][col] > target)
                rightRow = mid - 1;
        }
        //case when element is not repsent and left is not equal to right
        return target < matrix[leftRow][col] ? rightRow : leftRow;
    }

    private boolean searchRow(int[][] matrix, int target, int row) {
        int leftCol = 0;
        int rightCol = matrix[0].length - 1;
        while (leftCol <= rightCol) {
            int mid = leftCol + (rightCol - leftCol) / 2;
            if (matrix[row][mid] == target)
                return true;
            //check right side. shift left pointer
            if (matrix[row][mid] < target)
                leftCol = mid + 1;
            //check in left side. shift right pointer
            if (matrix[row][mid] > target)
                rightCol = mid - 1;
        }
        //case when element is not repsent and left is not equal to right
        return false;
    }

    public boolean searchMatrixOnePass(int[][] matrix, int target) {
        //as the matrix elements are sorted for each row, we can visualise the whole matrix as one sorted row
        int rows = matrix.length;
        int cols = matrix[0].length;
        //top left corner cell
        int left = 0;
        //bottom right corner cell
        int right = rows * cols - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / cols;
            int col = mid % cols;
            if (matrix[row][col] == target)
                return true;
            if (matrix[row][col] > target)
                right = mid - 1;
            if (matrix[row][col] < target)
                left = mid + 1;
        }
        return false;
    }
}
