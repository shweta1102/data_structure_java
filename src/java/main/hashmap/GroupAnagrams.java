package src.java.main.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * <p>
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * Explanation:
 * <p>
 * There is no string in strs that can be rearranged to form "bat".
 * The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
 * The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
 * Example 2:
 * <p>
 * Input: strs = [""]
 * <p>
 * Output: [[""]]
 * <p>
 * Example 3:
 * <p>
 * Input: strs = ["a"]
 * <p>
 * Output: [["a"]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class GroupAnagrams {

    /**
     *
     * Time Complexity: O(n*m) (n is length of strs and m is average length of each word)
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> uniqueMap = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; i++) {
            // create key from the word
            int[] count = new int[26];
            for (int j = 0; j < strs[i].length(); j++) {
                count[strs[i].charAt(j) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int num : count) {
                sb.append(num);
                sb.append("#");
            }
            String key = sb.toString();
            if (!uniqueMap.containsKey(key)) {
                uniqueMap.put(key, new ArrayList<String>());
            }
            uniqueMap.get(key).add(strs[i]);
        }
        return new ArrayList<>(uniqueMap.values());
    }

    /**
     * Time Complexity: O(n^2*m)
     * Space Complexity: O(n)
     */
    public List<List<String>> groupAnagramsBruteForce(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        HashSet<Integer> uniqueSet = new HashSet<Integer>();
        for (int i = 0; i < strs.length; i++) {
            if (!uniqueSet.contains(i)) {
                uniqueSet.add(i);
                List<String> anagrams = new ArrayList<String>();
                anagrams.add(strs[i]);
                for (int j = i + 1; j < strs.length; j++) {
                    if (!uniqueSet.contains(j) && isAnagram(strs[i], strs[j])) {
                        anagrams.add(strs[j]);
                        uniqueSet.add(j);
                    }
                }
                result.add(anagrams);
            }
        }
        return result;
    }

    private boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (count[t.charAt(i) - 'a'] <= 0)
                return false;
            count[t.charAt(i) - 'a']--;
        }
        return true;
    }
}
