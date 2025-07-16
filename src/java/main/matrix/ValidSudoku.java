package src.java.main.matrix;

import java.util.HashSet;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 * <p>
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 * Example 2:
 * <p>
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 */
public class ValidSudoku {
    /**
     * Time Complexity: O(row*column) we parse it 3 times
     * @param board
     * @return
     */
    public boolean isValidSudokuBruteForce(char[][] board) {
        HashSet<Integer> uniqueSet = null;
        //verify each row
        for (int row = 0; row < 9; row++) {
            uniqueSet = new HashSet<Integer>();
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != '.') {
                    int digit = board[row][col] - '0';
                    if (digit < 0 || digit > 9 || uniqueSet.contains(digit)) {
                        return false;
                    }
                    uniqueSet.add(digit);
                }
            }
        }
        //verify each column
        for (int col = 0; col < 9; col++) {
            uniqueSet = new HashSet<Integer>();
            for (int row = 0; row < 9; row++) {
                if (board[row][col] != '.') {
                    int digit = board[row][col] - '0';
                    if (digit < 0 || digit > 9 || uniqueSet.contains(digit)) {
                        return false;
                    }
                    uniqueSet.add(digit);
                }
            }
        }
        //verify each sub matrix
        for (int row = 0; row < 9; row = row + 3) {
            for (int col = 0; col < 9; col = col + 3) {
                uniqueSet = new HashSet<Integer>();
                for (int i = row; i < row + 3; i++) {
                    for (int j = col; j < col + 3; j++) {
                        if (board[i][j] != '.') {
                            int digit = board[i][j] - '0';
                            if (digit < 0 || digit > 9 || uniqueSet.contains(digit)) {
                                return false;
                            }
                            uniqueSet.add(digit);
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        HashSet<String> uniqueSet = new HashSet<String>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != '.') {
                    char digit = board[row][col];
                    if (!uniqueSet.contains("r" + row + digit) && !uniqueSet.contains("c" + col + digit)
                            && !uniqueSet.contains("s" + row / 3 + "-" + col / 3 + digit)) {
                        //encode different string for row, col and sub matrix check.
                        uniqueSet.add("r" + row + digit);
                        uniqueSet.add("c" + col + digit);
                        uniqueSet.add("s" + row / 3 + "-" + col / 3 + digit);
                    } else {
                        return false;
                    }

                }
            }
        }
        return true;
    }
}
