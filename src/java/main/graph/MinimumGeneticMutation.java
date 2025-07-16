package src.java.main.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 * <p>
 * Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.
 * <p>
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 * <p>
 * Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.
 * <p>
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
 * Output: 1
 * Example 2:
 * <p>
 * Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= bank.length <= 10
 * startGene.length == endGene.length == bank[i].length == 8
 * startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
 */
public class MinimumGeneticMutation {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Queue<String> queue = new LinkedList<String>();
        //maintain visited set for the strings that are already visited and added to queue. We don't compare it again while calculating the character difference for the current processing node
        HashSet<String> visited = new HashSet<String>();
        visited.add(startGene);
        queue.add(startGene);
        //for each level add null. we increase mutation number at each level
        queue.add(null);
        int mutations = 0;
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current == null) {
                if (!queue.isEmpty())
                    queue.add(null);
                // we increase mutation number at each level
                mutations++;
            } else {
                if (current.equals(endGene)) {
                    return mutations;
                }
                for (int i = 0; i < bank.length; i++) {
                    if (!visited.contains(bank[i]) && characterDiff(current, bank[i]) == 1) {
                        queue.add(bank[i]);
                        visited.add(bank[i]);
                    }
                }
            }
        }
        return -1;
    }

    private int characterDiff(String gene1, String gene2) {
        int diff = 0;
        char[] gene1arr = gene1.toCharArray();
        char[] gene2arr = gene2.toCharArray();
        for (int i = 0; i < gene1.length(); i++) {
            if (gene1arr[i] != gene2arr[i])
                diff++;
        }
        return diff;
    }
}
