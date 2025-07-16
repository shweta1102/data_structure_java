package src.java.main.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * Example 2:
 * <p>
 * <p>
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 */
public class WordSearch {
    TrieNode root;

    public List<String> findWords(char[][] board, String[] words) {
        //construct trie from the list of words
        buildTree(words);
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfsBoard(i, j, board, result, root);
            }
        }
        return result;
    }

    private void buildTree(String[] words) {
        root = new TrieNode();
        for (String word : words) {
            addWord(word);
        }
    }

    private void addWord(String word) {
        char[] chars = word.toCharArray();
        TrieNode current = root;
        for (char c : chars) {
            if (current.children[c - 'a'] == null) {
                current.children[c - 'a'] = new TrieNode();
            }
            current = current.children[c - 'a'];
        }
        current.word = word;
    }

    private void dfsBoard(int row, int col, char[][] board, List<String> result, TrieNode current) {
        if (current != null && current.word != null) {
            result.add(new String(current.word));
            current.word = null;
        }
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] == '#')
            return;
        char c = board[row][col];
        if (current.children[c - 'a'] == null)
            return;
        board[row][col] = '#';
        dfsBoard(row + 1, col, board, result, current.children[c - 'a']);
        dfsBoard(row - 1, col, board, result, current.children[c - 'a']);
        dfsBoard(row, col + 1, board, result, current.children[c - 'a']);
        dfsBoard(row, col - 1, board, result, current.children[c - 'a']);
        board[row][col] = c;
    }

    class TrieNode {
        TrieNode[] children;
        String word;

        public TrieNode() {
            children = new TrieNode[26];
        }

        public void setWord(String word) {
            this.word = word;
        }
    }
}
