package src.java.main.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 * <p>
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 * <p>
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * Example 2:
 * <p>
 * Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 * Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= accounts.length <= 1000
 * 2 <= accounts[i].length <= 10
 * 1 <= accounts[i][j].length <= 30
 * accounts[i][0] consists of English letters.
 * accounts[i][j] (for j > 0) is a valid email.
 */
public class AccountsMerge {
    public List<List<String>> accountsMergeWithUnionFind(List<List<String>> accounts) {
        HashMap<String, String> parent = new HashMap<String, String>();
        HashMap<String, String> emailToName = new HashMap<>();

        //prepare union-find map
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            String name = account.get(0);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                emailToName.put(email, name);
                parent.putIfAbsent(email, email);
                union(parent, account.get(1), email);
            }
        }

        //club all emails with same root in one sorted set
        HashMap<String, TreeSet<String>> unions = new HashMap<>();
        for (String mail : parent.keySet()) {
            String root = find(parent, mail);
            unions.computeIfAbsent(root, x -> new TreeSet<String>()).add(mail);
        }

        List<List<String>> result = new ArrayList<>();

        for (String root : unions.keySet()) {
            String name = emailToName.get(root);
            List<String> mergedList = new ArrayList<>();
            mergedList.add(name);
            mergedList.addAll(unions.get(root));
            result.add(mergedList);
        }
        return result;
    }

    String find(HashMap<String, String> parent, String email) {
        if (parent.get(email) != email) {
            parent.put(email, find(parent, parent.get(email)));
        }
        return parent.get(email);
    }

    void union(HashMap<String, String> parent, String email1, String email2) {
        String root1 = find(parent, email1);
        String root2 = find(parent, email2);
        if (root1 != root2) {
            parent.put(root2, root1);
        }
    }
}
