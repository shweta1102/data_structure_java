package src.java.main.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * You are given a string s and an array of strings words. All the strings of words are of the same length.
 * <p>
 * A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
 * <p>
 * For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
 * Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * <p>
 * Output: [0,9]
 * <p>
 * Explanation:
 * <p>
 * The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
 * The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * <p>
 * Output: []
 * <p>
 * Explanation:
 * <p>
 * There is no concatenated substring.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * <p>
 * Output: [6,9,12]
 * <p>
 * Explanation:
 * <p>
 * The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
 * The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
 * The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * s and words[i] consist of lowercase English letters.
 */
public class SubStringWithConcatenationOfAllWords {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> answer = new ArrayList<Integer>();
        // create map of words with count as duplicate words are possible in the list
        HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            wordMap.put(words[i], wordMap.getOrDefault(words[i], 0) + 1);
        }
        // all strings are of the same length. get the length from the first word
        int wordLength = words[0].length();
        int left = 0;
        int right = 0;
        // if there is only one string
        if (wordMap.size() == 1) {
            HashMap<String, Integer> currentMap = new HashMap<String, Integer>();
            while (right < s.length() - wordLength + 1) {
                // System.out.println("left " + left + " right" + right);
                String currentWord = s.substring(right, right + wordLength);
                if (wordMap.containsKey(currentWord)) {
                    if (currentMap.getOrDefault(currentWord, 0) < wordMap.get(currentWord)) {
                        currentMap.put(currentWord, currentMap.getOrDefault(currentWord, 0) + 1);
                        if (currentMap.get(currentWord) == wordMap.get(currentWord)
                                && currentMap.size() == wordMap.size()) {
                            answer.add(left);
                            currentMap.clear();
                            left++;
                            right = left;
                        } else {
                            right = right + wordLength;
                        }

                    }
                } else {
                    currentMap.clear();
                    right++;
                    left = right;
                }
            }
            return answer;
        }
        // at each index check if the next word matches the words in the set, if yes
        // then check if all other words are there from the set
        HashMap<String, Integer> currentMap = new HashMap<String, Integer>();
        while (right < s.length() - wordLength + 1) {
            String currentWord = s.substring(right, right + wordLength);
            // System.out.println(currentWord);
            if (wordMap.containsKey(currentWord)) {
                // System.out.println("left " + left + " right" + right);
                if (currentMap.getOrDefault(currentWord, 0) < wordMap.get(currentWord)) {
                    currentMap.put(currentWord, currentMap.getOrDefault(currentWord, 0) + 1);
                    // check if the current string has all the words
                    if (currentMap.get(currentWord) == wordMap.get(currentWord)
                            && currentMap.size() == wordMap.size()) {
                        answer.add(left);

                        String removalString = s.substring(left, left + wordLength);
                        if (currentMap.get(removalString) > 1) {
                            currentMap.put(removalString, currentMap.get(removalString) - 1);
                        } else {
                            currentMap.remove(removalString);
                        }
                        left = left + wordLength;

                    }
                } else {
                    while (!s.substring(left, left + wordLength).equals(currentWord)) {
                        if (currentMap.get(s.substring(left, left + wordLength)) > 1) {
                            currentMap.put(s.substring(left, left + wordLength),
                                    currentMap.get(s.substring(left, left + wordLength)) - 1);
                        } else {
                            currentMap.remove(s.substring(left, left + wordLength));
                        }
                        left = left + wordLength;
                    }
                    left = left + wordLength;
                }
                right = right + wordLength;
            } else {
                currentMap.clear();
                right++;
                left = right;
            }
        }
        return answer;
    }
}
