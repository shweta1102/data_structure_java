package src.java.main.hashmap;

import java.util.HashMap;

/**
 * Given a pattern and a string s, find if s follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. Specifically:
 * <p>
 * Each letter in pattern maps to exactly one unique word in s.
 * Each unique word in s maps to exactly one letter in pattern.
 * No two letters map to the same word, and no two words map to the same letter.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: pattern = "abba", s = "dog cat cat dog"
 * <p>
 * Output: true
 * <p>
 * Explanation:
 * <p>
 * The bijection can be established as:
 * <p>
 * 'a' maps to "dog".
 * 'b' maps to "cat".
 * Example 2:
 * <p>
 * Input: pattern = "abba", s = "dog cat cat fish"
 * <p>
 * Output: false
 * <p>
 * Example 3:
 * <p>
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * <p>
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= pattern.length <= 300
 * pattern contains only lower-case English letters.
 * 1 <= s.length <= 3000
 * s contains only lowercase English letters and spaces ' '.
 * s does not contain any leading or trailing spaces.
 * All the words in s are separated by a single space.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> patternToS = new HashMap<Character, String>();
        String[] sWords = s.split(" ");
        if (pattern.length() != sWords.length)
            return false;
        for (int i = 0; i < pattern.length(); i++) {
            Character pCh = pattern.charAt(i);
            if (patternToS.containsKey(pCh) && !patternToS.get(pCh).equals(sWords[i]))
                return false;
            if (!patternToS.containsKey(pCh) && patternToS.containsValue(sWords[i]))
                return false;
            patternToS.put(pCh, sWords[i]);
        }
        return true;
    }
}
