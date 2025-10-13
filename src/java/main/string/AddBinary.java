package src.java.main.string;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 * <p>
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 */
public class AddBinary {

    public String addBinary(String a, String b) {
        int bindex = b.length() - 1;
        int aindex = a.length() - 1;
        char carry = '0';
        StringBuilder sb = new StringBuilder();
        while (bindex >= 0 && aindex >= 0) {
            if (a.charAt(aindex) == '0' && b.charAt(bindex) == '0') {
                if (carry == '1') {
                    sb.append("1");
                    carry = '0';
                } else
                    sb.append("0");
            } else if (((a.charAt(aindex) == '1' && b.charAt(bindex) == '0')
                    || (a.charAt(aindex) == '0' && b.charAt(bindex) == '1'))) {
                if (carry == '0') {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            } else if (a.charAt(aindex) == '1' && b.charAt(bindex) == '1') {
                if (carry == '0') {
                    sb.append("0");
                    carry = '1';
                } else {
                    sb.append("1");
                }
            }
            aindex--;
            bindex--;
        }
        while (aindex >= 0) {
            if (carry == '0')
                sb.append(a.charAt(aindex));
            else {
                if (a.charAt(aindex) == '1') {
                    sb.append("0");
                } else {
                    sb.append("1");
                    carry = '0';
                }
            }
            aindex--;
        }
        while (bindex >= 0) {
            if (carry == '0')
                sb.append(b.charAt(bindex));
            else {
                if (b.charAt(bindex) == '1') {
                    sb.append("0");
                } else {
                    sb.append("1");
                    carry = '0';
                }
            }
            bindex--;
        }
        if (carry == '1')
            sb.append("1");
        return sb.reverse().toString();
    }

    public String addBinaryUsingMod(String a, String b) {
        int bindex = b.length() - 1;
        int aindex = a.length() - 1;
        int carry = 0;
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        while (bindex >= 0 || aindex >= 0) {
            sum = carry;
            sum += aindex >= 0 ? a.charAt(aindex--) - '0' : 0;
            sum += bindex >= 0 ? b.charAt(bindex--) - '0' : 0;
            //this works because (0+1)=1 (1%2=1), (1+0)=1 (1%2=1), (1+1)=2 (2%2=0),(1+1+1)=3 (3%2=1)
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry > 0)
            sb.append(carry);
        return sb.reverse().toString();
    }
}
