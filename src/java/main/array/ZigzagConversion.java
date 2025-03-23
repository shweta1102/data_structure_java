package src.java.main.array;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Example 3:
 * <p>
 * Input: s = "A", numRows = 1
 * Output: "A"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consists of English letters (lower-case and upper-case), ',' and '.'.
 * 1 <= numRows <= 1000
 */
public class ZigzagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length())
            return s;
        boolean zig = true;
        String[] convertedStrings = new String[numRows];
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            // get string from list and append the current character
            if (convertedStrings[j] == null)
                convertedStrings[j] = String.valueOf(s.charAt(i));
            else
                convertedStrings[j] = convertedStrings[j] + String.valueOf(s.charAt(i));
            if (zig) {
                j++;
                if (j == numRows - 1) {
                    zig = false;
                }
            } else {
                j--;
                if (j == 0) {
                    zig = true;
                }
            }
        }
        return String.join("", convertedStrings);
    }
}
