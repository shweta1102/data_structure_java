package src.java.main.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * <p>
 * Input: digits = ""
 * Output: []
 * Example 3:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */
public class LetterCombinationsOfPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<String>();
        if (digits.isEmpty())
            return list;
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        populateCombinations(digits, 0, "", mapping, list);
        return list;
    }

    /**
     * Time Complexity: O(4^n) where n is length of the input string. Atmost 4 characters represent a number.
     * The depth of the solution-space tree is the length of the phone number, and the branching factor is 4 (each digit maps to at most 4 letters, so 4 recursive calls are made).
     * So the number of nodes in the tree is given by the sum 1 + 4 + 42 + ... + 4n, which is asymptotically O(4n).
     * Space Complexity: O(4^n * n) The space complexity of this algorithm is determined by the size of the result list, which can contain up to 4n combinations (the number of nodes at the last level of the tree). Each combination contains n characters, so the space complexity is O(4n * n).
     *
     * @param digits
     * @param index
     * @param currentString
     * @param mapping
     * @param list
     */
    private void populateCombinations(String digits, int index, String currentString,
                                      String[] mapping, List<String> list) {
        if (index == digits.length()) {
            list.add(currentString);
            return;
        }
        String numMapping = mapping[digits.charAt(index) - '0'];
        for (Character c : numMapping.toCharArray()) {
            populateCombinations(digits, index + 1, currentString + c, mapping, list);
        }
    }
}
