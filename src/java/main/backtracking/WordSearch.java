package src.java.main.backtracking;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 * <p>
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 * <p>
 * <p>
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existHelper(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Time Complexity: O(row*col* 4^L) where L is length of the word. for each cell we will do dfs call at max till length of the word in 4 directions.
     * Space Complexity: O(n*m) + O(L) recursive call stack
     *
     * @param board
     * @param word
     * @param row
     * @param col
     * @param index
     * @return
     */
    private boolean existHelper(char[][] board, String word, int row, int col, int index) {
        if (index == word.length())
            return true;

        if (row < 0 || col < 0 || row > board.length - 1 || col > board[0].length - 1 || index > word.length())
            return false;
        if (board[row][col] == word.charAt(index)) {
            board[row][col] = '*';
            if (existHelper(board, word, row + 1, col, index + 1)
                    || existHelper(board, word, row, col - 1, index + 1)
                    || existHelper(board, word, row, col + 1, index + 1)
                    || existHelper(board, word, row - 1, col, index + 1))
                return true;
            board[row][col] = word.charAt(index);
        }
        return false;
    }
}
