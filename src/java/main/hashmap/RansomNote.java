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
        HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
        for (int i = 0; i < magazine.length(); i++) {
            Character ch = magazine.charAt(i);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            Character ch = ransomNote.charAt(i);
            if (!freq.containsKey(ch) || freq.get(ch) == 0)
                return false;
            freq.put(ch, freq.get(ch) - 1);
        }
        return true;
    }
}
