package src.java.main.hashmap;

import java.util.HashMap;

/**
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 * <p>
 * Each letter in magazine can only be used once in ransomNote.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 * <p>
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 * <p>
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote and magazine consist of lowercase English letters.
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : magazine.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (char ch : ransomNote.toCharArray()) {
            Integer count = map.get(ch);
            if (count == null)
                return false;
            if (count == 1)
                map.remove(ch);
            else
                map.put(ch, count - 1);
        }
        return true;
    }
}
