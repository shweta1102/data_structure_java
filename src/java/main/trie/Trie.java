package src.java.main.trie;

import java.util.HashMap;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 * <p>
 * Implement the Trie class:
 * <p>
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 * <p>
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 */
public class Trie {
    private TrieNode root;

    class TrieNode {
        HashMap<Character, TrieNode> children;
        Character val;
        boolean isWord;

        public TrieNode(Character val, boolean isWord, HashMap<Character, TrieNode> children) {
            this.val = val;
            this.isWord = isWord;
            this.children = children;
        }

        public TrieNode(Character val) {
            this.val = val;
            this.isWord = false;
            this.children = new HashMap();
        }
    }

    public Trie() {
        root = new TrieNode(' ');
    }

    public void insert(String word) {
        TrieNode current = root;
        for (Character c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode(c));
            current = current.children.get(c);
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (Character c : word.toCharArray()) {
            current = current.children.get(c);
            if (current == null)
                return false;
        }
        return current.isWord == true;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (Character c : prefix.toCharArray()) {
            current = current.children.get(c);
            if (current == null)
                return false;
        }
        return true;
    }
}
