package src.java.main.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * You are given an integer n and a list of undirected edges where each entry in the list is a pair of integers representing an edge between nodes 1 and n. You have to write a function to check whether these edges make up a valid tree.
 * There will be no duplicate edges in the edges list. (i.e. [0, 1] and [1, 0] will not appear together in the list).
 * Input:
 * n = 4
 * edges = [[0, 1], [2, 3]]
 * <p>
 * Output:
 * false # the graph is not connected.
 */
public class ValidGraphTree {

    /**
     * To detect if graph is valid tree we need to check --> there is no cycle + all the nodes are connected
     *
     * @param n
     * @param edges
     * @return
     */
    public Boolean graph_valid_tree(Integer n, int[][] edges) {
        // Your code goes here
        if (edges.length == 0 && n <= 1)
            return true;
        boolean[] visited = new boolean[n];
        HashMap<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] edge : edges) {
            adjList.putIfAbsent(edge[0], new ArrayList<Integer>());
            adjList.putIfAbsent(edge[1], new ArrayList<Integer>());
            List<Integer> neighbors = adjList.get(edge[0]);
            neighbors.add(edge[1]);
            adjList.put(edge[0], neighbors);
            neighbors = adjList.get(edge[1]);
            neighbors.add(edge[0]);
            adjList.put(edge[1], neighbors);
        }
        if (!dfsHasCycle(adjList, 0, visited, -1)) {
            int notvisited = 0;
            for (int i = 0; i < n; i++) {
                if (!visited[i])
                    notvisited++;
            }
            return !(notvisited > 1);
        }
        return false;
    }

    private boolean dfsHasCycle(HashMap<Integer, List<Integer>> adjList, int current, boolean[] visited, int parent) {
        visited[current] = true;
        List<Integer> neighbors = adjList.get(current);
        for (Integer neighbor : neighbors) {
            if (neighbor != parent && (visited[neighbor] || dfsHasCycle(adjList, neighbor, visited, current))) {
                return true;
            }
        }
        return false;
    }
}
