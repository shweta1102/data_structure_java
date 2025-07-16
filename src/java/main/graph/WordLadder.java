package src.java.main.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * Example 2:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 */
public class WordLadder {
    public int ladderLengthWithSingleDirectionSearch(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<String>();
        //maintain visited set for the strings that are already visited and added to queue. We don't compare it again while calculating the character difference for the current processing node
        HashSet<String> visited = new HashSet<String>();
        HashSet<String> wordSet = new HashSet(wordList);
        visited.add(beginWord);
        queue.add(beginWord);
        //for each level add null. we increase mutation number at each level
        queue.add(null);
        int steps = 0;
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current == null) {
                if (!queue.isEmpty())
                    queue.add(null);
                // we increase mutation number at each level
                steps++;
            } else {
                if (current.equals(endWord)) {
                    return steps + 1;
                }
                //identify all available neighbours and add them to queue
                for (int i = 0; i < current.length(); i++) {
                    char[] currentChar = current.toCharArray();
                    for (int j = 0; j < 26; j++) {
                        char[] temp = currentChar;
                        if (j != currentChar[i]) {
                            temp[i] = (char) ('a' + j);
                            String tempWord = String.valueOf(temp);
                            if (!visited.contains(tempWord) && wordSet.contains(tempWord)) {
                                queue.add(tempWord);
                                visited.add(tempWord);
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
}

