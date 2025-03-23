package src.java.main.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 * <p>
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * <p>
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * <p>
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 * <p>
 * Note:
 * <p>
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * Example 2:
 * <p>
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified because it contains only one word.
 * Example 3:
 * <p>
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 * "Science  is  what we",
 * "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] consists of only English letters and symbols.
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> justifiedList = new ArrayList<>();
        // trverse list of words
        // for each word count the characters
        // maintain start index and end index for the words to club
        // once we reach the maximum words in a sentence, count extrac space that is
        // needed to be inserted
        // if it is not the last line insert equal spaces between all words
        int startIndex = 0;
        int endIndex = 0;
        int currentLength = 0;
        for (int i = 0; i < words.length; i++) {
            // add word length to the current lent of the sentence
            currentLength += words[i].length();
            if (currentLength > maxWidth) {
                justifiedList
                        .add(getSentence(words, startIndex, i - 1, maxWidth, false,
                                currentLength - words[i].length() - 1));
                startIndex = i;
                currentLength = words[i].length();
            }
            if (currentLength == maxWidth) {
                justifiedList
                        .add(getSentence(words, startIndex, i, maxWidth, false, currentLength));
                startIndex = i + 1;
                currentLength = 0;
            }
            // add space after each word if it is not the first word
            if (currentLength > 0)
                currentLength++;
        }
        if (startIndex < words.length) {
            justifiedList
                    .add(getSentence(words, startIndex, words.length - 1, maxWidth, true, currentLength));
        }
        return justifiedList;
    }

    private String getSentence(String[] words, int start, int end, int maxWidth, boolean isLast, int totalLength) {
        int space = (maxWidth - totalLength) < (end - start) ? 0 : (maxWidth - totalLength);
        int extraspace = end == start ? 0 : (maxWidth - totalLength) % (end - start);

        StringBuilder sentence = new StringBuilder();
        if (isLast) {
            space = (maxWidth - totalLength);
            for (int i = start; i <= end; i++) {
                sentence.append(words[i]);
                sentence.append(" ");
            }
            if (space > 0) {
                sentence.append(" ".repeat(space));
            }
            return sentence.toString();
        }
        int totalSpace = end == start ? space : space / (end - start);
        for (int i = start; i <= end; i++) {
            sentence.append(words[i]);
            if (i != end)
                sentence.append(" ");
            if (space > 0) {
                for (int j = 1; j <= totalSpace; j++) {
                    sentence.append(" ");
                    space--;
                }
            }
            if (extraspace > 0) {
                sentence.append(" ");
                extraspace--;
                space--;
            }
        }
        return sentence.toString();
    }
}
