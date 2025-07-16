package src.java.main.stack;

import java.util.Stack;

/**
 * You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.
 * <p>
 * The rules of a Unix-style file system are as follows:
 * <p>
 * A single period '.' represents the current directory.
 * A double period '..' represents the previous/parent directory.
 * Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
 * Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
 * The simplified canonical path should follow these rules:
 * <p>
 * The path must start with a single slash '/'.
 * Directories within the path must be separated by exactly one slash '/'.
 * The path must not end with a slash '/', unless it is the root directory.
 * The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
 * Return the simplified canonical path.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: path = "/home/"
 * <p>
 * Output: "/home"
 * <p>
 * Explanation:
 * <p>
 * The trailing slash should be removed.
 * <p>
 * Example 2:
 * <p>
 * Input: path = "/home//foo/"
 * <p>
 * Output: "/home/foo"
 * <p>
 * Explanation:
 * <p>
 * Multiple consecutive slashes are replaced by a single one.
 * <p>
 * Example 3:
 * <p>
 * Input: path = "/home/user/Documents/../Pictures"
 * <p>
 * Output: "/home/user/Pictures"
 * <p>
 * Explanation:
 * <p>
 * A double period ".." refers to the directory up a level (the parent directory).
 * <p>
 * Example 4:
 * <p>
 * Input: path = "/../"
 * <p>
 * Output: "/"
 * <p>
 * Explanation:
 * <p>
 * Going one level up from the root directory is not possible.
 * <p>
 * Example 5:
 * <p>
 * Input: path = "/.../a/../b/c/../d/./"
 * <p>
 * Output: "/.../b/d"
 * <p>
 * Explanation:
 * <p>
 * "..." is a valid name for a directory in this problem.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 */
public class SimplifyPath {
    public String simplifyPathWithSplit(String path) {
        String[] words = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String word : words) {
            if (word.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (word.equals(".") || word.equals("")) {
                continue;
            } else {
                stack.push(word);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
            result.insert(0, "/");
        }
        return result.length() == 0 ? "/" : result.toString();
    }

    public String simplifyPathWithoutSplit(String path) {
        Stack<String> stack = new Stack<>();
        StringBuilder word = new StringBuilder();
        char prev = '*';//start
        for (char c : path.toCharArray()) {
            if (prev == '*') {
                prev = c;
                continue;
            }
            if (prev == '/') {
                if (c == '/') {
                    continue;
                } else {
                    word.append(c);
                    prev = c;
                }
            } else {
                if (c == '/') {
                    if (word.toString().equals("..")) {
                        if (!stack.isEmpty()) {
                            stack.pop();
                        }
                    } else if (word.toString().equals(".")) {
                        prev = c;
                    } else {
                        stack.push(word.toString());
                    }
                    prev = c;
                    word = new StringBuilder();
                } else {
                    word.append(c);
                    prev = c;
                }
            }
        }
        if (word.length() > 0) {
            if (word.toString().equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!word.toString().equals(".")) {
                stack.push(word.toString());
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
            result.insert(0, "/");
        }
        return result.length() == 0 ? "/" : result.toString();
    }
}
