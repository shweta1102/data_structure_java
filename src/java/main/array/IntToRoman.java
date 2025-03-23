package src.java.main.array;

import java.util.HashMap;

/**
 * Seven different symbols represent Roman numerals with the following values:
 * <p>
 * Symbol	Value
 * I	1
 * V	5
 * X	10
 * L	50
 * C	100
 * D	500
 * M	1000
 * Roman numerals are formed by appending the conversions of decimal place values from highest to lowest. Converting a decimal place value into a Roman numeral has the following rules:
 * <p>
 * If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the input, append that symbol to the result, subtract its value, and convert the remainder to a Roman numeral.
 * If the value starts with 4 or 9 use the subtractive form representing one symbol subtracted from the following symbol, for example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less than 10 (X): IX. Only the following subtractive forms are used: 4 (IV), 9 (IX), 40 (XL), 90 (XC), 400 (CD) and 900 (CM).
 * Only powers of 10 (I, X, C, M) can be appended consecutively at most 3 times to represent multiples of 10. You cannot append 5 (V), 50 (L), or 500 (D) multiple times. If you need to append a symbol 4 times use the subtractive form.
 * Given an integer, convert it to a Roman numeral.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = 3749
 * <p>
 * Output: "MMMDCCXLIX"
 * <p>
 * Explanation:
 * <p>
 * 3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
 * 700 = DCC as 500 (D) + 100 (C) + 100 (C)
 * 40 = XL as 10 (X) less of 50 (L)
 * 9 = IX as 1 (I) less of 10 (X)
 * Note: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal places
 * Example 2:
 * <p>
 * Input: num = 58
 * <p>
 * Output: "LVIII"
 * <p>
 * Explanation:
 * <p>
 * 50 = L
 * 8 = VIII
 * Example 3:
 * <p>
 * Input: num = 1994
 * <p>
 * Output: "MCMXCIV"
 * <p>
 * Explanation:
 * <p>
 * 1000 = M
 * 900 = CM
 * 90 = XC
 * 4 = IV
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= num <= 3999
 */
public class IntToRoman {
    private final HashMap<Integer, String> intToString;

    IntToRoman() {
        intToString = new HashMap<Integer, String>();
        intToString.put(1, "I");
        intToString.put(4, "IV");
        intToString.put(5, "V");
        intToString.put(9, "IX");
        intToString.put(10, "X");
        intToString.put(40, "XL");
        intToString.put(50, "L");
        intToString.put(90, "XC");
        intToString.put(100, "C");
        intToString.put(400, "CD");
        intToString.put(500, "D");
        intToString.put(900, "CM");
        intToString.put(1000, "M");
    }

    public String intToRomanWithHashMap(int num) {
        int divider = 10;
        int remainder = 0;
        StringBuilder converted = new StringBuilder();
        while (divider <= num) {
            remainder = num % divider;
            converted.insert(0, getConverted(remainder));
            divider = divider * 10;
            num = num - remainder;
        }
        remainder = num % divider;
        converted.insert(0, getConverted(remainder));
        return converted.toString();
    }

    public String intToRomanWithArray(int num) {
        // arraange the values in descending order
        final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder converted = new StringBuilder();
        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                // keep subtracting the value from the num till it is greater than the num
                converted.append(symbols[i]);
                num -= values[i];
            }
        }
        return converted.toString();
    }

    private String getConverted(int num) {
        if (intToString.containsKey(num))
            return intToString.get(num);
        StringBuilder converted = new StringBuilder();
        if (num < 10) {
            while (num > 0) {
                if (num > 5) {
                    num -= 5;
                    converted.append(intToString.get(5));
                } else {
                    num -= 1;
                    converted.append(intToString.get(1));
                }
            }
            return converted.toString();
        }
        if (num > 10 && num < 100) {
            while (num > 0) {
                if (num > 50) {
                    num -= 50;
                    converted.append(intToString.get(50));
                } else {
                    num -= 10;
                    converted.append(intToString.get(10));
                }
            }
            return converted.toString();
        }
        if (num > 100 && num < 1000) {
            while (num > 0) {
                if (num > 500) {
                    num -= 500;
                    converted.append(intToString.get(500));
                } else {
                    num -= 100;
                    converted.append(intToString.get(100));
                }
            }
            return converted.toString();
        }
        while (num > 0) {
            num -= 1000;
            converted.append(intToString.get(1000));
        }
        return converted.toString();
    }
}
