package src.java.main.array;

import java.util.HashMap;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "III"
 * Output: 3
 * Explanation: III = 3.
 * Example 2:
 * <p>
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Example 3:
 * <p>
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 15
 * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
public class RomanToInt {
    private final HashMap<Character, Integer> chaToInt;

    RomanToInt() {
        chaToInt = new HashMap<Character, Integer>(7);
        chaToInt.put('I', 1);
        chaToInt.put('V', 5);
        chaToInt.put('X', 10);
        chaToInt.put('L', 50);
        chaToInt.put('C', 100);
        chaToInt.put('D', 500);
        chaToInt.put('M', 1000);
    }

    public int romanToIntBruteForce(String s) {
        int converted = 0;
        int i = 0;
        while (i < s.length()) {
            Character ith = s.charAt(i);
            Character next = null;
            if ((i + 1) < s.length()) {
                next = s.charAt(i + 1);
            }
            if (next != null && ith == 'I' && next == 'V') {
                converted += 4;
                i = i + 2;
            } else if (next != null && ith == 'I' && next == 'X') {
                converted += 9;
                i = i + 2;
            } else if (next != null && ith == 'X' && next == 'L') {
                converted += 40;
                i = i + 2;
            } else if (next != null && ith == 'X' && next == 'C') {
                converted += 90;
                i = i + 2;
            } else if (next != null && ith == 'C' && next == 'D') {
                converted += 400;
                i = i + 2;
            } else if (next != null && ith == 'C' && next == 'M') {
                converted += 900;
                i = i + 2;
            } else {
                converted += chaToInt.get(s.charAt(i));
                i++;
            }

        }
        return converted;
    }

    public int romanToIntWithLessLoops(String s) {
        int converted = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if ((chaToInt.get(s.charAt(i)) < chaToInt.get(s.charAt(i + 1)))) {
                converted -= chaToInt.get(s.charAt(i));
            } else {
                converted += chaToInt.get(s.charAt(i));
            }
        }
        return converted + chaToInt.get(s.charAt(s.length() - 1));
    }
}
