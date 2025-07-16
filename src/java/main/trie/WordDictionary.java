package src.java.main.trie;

import java.util.HashMap;

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * <p>
 * Implement the WordDictionary class:
 * <p>
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 * <p>
 * <p>
 * Example:
 * <p>
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 * <p>
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= word.length <= 25
 * word in addWord consists of lowercase English letters.
 * word in search consist of '.' or lowercase English letters.
 * There will be at most 2 dots in word for search queries.
 * At most 104 calls will be made to addWord and search.
 */
public class WordDictionary {
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

    public WordDictionary() {
        root = new TrieNode(' ');
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (Character c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode(c));
            current = current.children.get(c);
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        return search(word, 0, root);
    }

    public boolean search(String word, int index, TrieNode currentNode) {
        TrieNode current = currentNode;
        if (index == word.length())
            return current.isWord == true;
        char[] charArray = word.toCharArray();
        if (charArray[index] != '.') {
            current = current.children.get(charArray[index]);
            if (current == null)
                return false;
            return search(word, index + 1, current);
        } else {
            if (current.children.isEmpty())
                return false;
            for (HashMap.Entry<Character, TrieNode> entry : current.children.entrySet()) {
                boolean found = search(word, index + 1, entry.getValue());
                if (found)
                    return true;
            }
            return false;
        }
    }
}
