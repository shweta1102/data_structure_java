package src.java.main.hashmap;

import java.util.HashMap;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "anagram", t = "nagaram"
 * <p>
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: s = "rat", t = "car"
 * <p>
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, t.length <= 5 * 104
 * s and t consist of lowercase English letters.
 * <p>
 * <p>
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            Character ch = t.charAt(i);
            if (!freq.containsKey(ch))
                return false;
            freq.put(ch, freq.get(ch) - 1);
            if (freq.get(ch) == 0)
                freq.remove(ch);
        }
        return freq.isEmpty();
    }
}
