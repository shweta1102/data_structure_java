package src.java.main.prefixsum;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Write a function to efficiently count vowels within specified substrings of a given string.
 * The substrings will be given to you a list queries of [left, right] pairs, which correspond to the substring word[left:right + 1] in Python.
 * The function should return a list of integers, where each integer represents the vowel count for the corresponding query. You can assume the input string will only contain lowercase letters.
 * Your function should be optimized to run efficiently for a large number of queries.
 * Input:
 * word = "prefixsum"
 * queries = [[0, 2], [1, 4], [3, 5]]
 * Output: [1, 2, 1]
 * Explanation:
 * word[0:3] -> "pre" contains 1 vowels
 * word[1:5]-> "refi" contains 2 vowels
 * word[3:6]-> "fix" contains 1 vowels
 */
public class CountVowelsInSubStrings {
    HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i','o','u'));

    /**
     * Time Complexity: O(N + Q) where N is the length of the word string and Q is the number of queries. We iterate over the word string to create the prefix sum array (O(N)), then iterate over the queries array where each query takes O(1) time.
     * Space Complexity: O(N + Q) where N is the length of the word string and Q is the number of queries. We use O(N) space to store the prefix sum array, and O(Q) space to store the results of the queries.
     * @param word
     * @param queries
     * @return
     */
    public int[] vowelStrings(String word, int[][] queries) {
        // Your code goes here
        int[] vowelCount = new int[word.length()+1];
        vowelCount[0]=0;
        char[] chars = word.toCharArray();
        for(int i=1;i<=chars.length;i++) {
            if(vowels.contains(chars[i-1])) {
                vowelCount[i] = vowelCount[i-1]+1;
            } else {
                vowelCount[i] =  vowelCount[i-1];
            }
        }
        int[] result = new int[queries.length];
        int i=0;
        for(int[] interval:queries) {
            result[i] = vowelCount[interval[1]+1]-vowelCount[interval[0]];
            i++;
        }
        return result;
    }
}
