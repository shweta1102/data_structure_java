package src.java.main.hashmap;

import java.util.HashMap;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "egg", t = "add"
 * <p>
 * Output: true
 * <p>
 * Explanation:
 * <p>
 * The strings s and t can be made identical by:
 * <p>
 * Mapping 'e' to 'a'.
 * Mapping 'g' to 'd'.
 * Example 2:
 * <p>
 * Input: s = "foo", t = "bar"
 * <p>
 * Output: false
 * <p>
 * Explanation:
 * <p>
 * The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "paper", t = "title"
 * <p>
 * Output: true
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s and t consist of any valid ascii character.
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> sTot = new HashMap<Character, Character>();
        for (int i = 0; i < s.length(); i++) {
            Character sCh = s.charAt(i);
            Character tCh = t.charAt(i);
            if (sTot.containsKey(sCh) && sTot.get(sCh) != tCh)
                return false;
            if (!sTot.containsKey(sCh) && sTot.containsValue(tCh))
                return false;
            sTot.put(sCh, tCh);
        }
        return true;
    }
}
