package src.java.main.graph;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {
    public boolean canFinishWithDFS(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        //dp to remember the result of the node during DFS
        boolean[] dp = new boolean[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            graph.putIfAbsent(prerequisites[i][1], new ArrayList<Integer>());
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        HashSet<Integer> visited = new HashSet<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (!visited.contains(i)) {
                if (!hasCycle(visited, graph, i, dp))
                    return false;
            }
        }
        return true;
    }

    private boolean hasCycle(HashSet<Integer> visited, HashMap<Integer, List<Integer>> graph, Integer currentNode,
                             boolean[] dp) {
        if (visited.contains(currentNode))
            return dp[currentNode];
        visited.add(currentNode);
        List<Integer> neighbours = graph.get(currentNode);
        if (neighbours != null) {
            for (Integer neighbour : neighbours) {
                if (!hasCycle(visited, graph, neighbour, dp)) {
                    dp[currentNode] = false;
                    return false;
                }
            }
        }
        dp[currentNode] = true;
        return true;
    }

    /**
     * Time Complexity: O(V+E)
     * Space Complexity: O(V+E)
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishWithBFSTopologicalSort(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> adjList = new HashMap<>();
        int[] indegrees = new int[numCourses];
        for (int[] edge : prerequisites) {
            adjList.putIfAbsent(edge[1], new ArrayList<Integer>());
            List<Integer> list = adjList.get(edge[1]);
            list.add(edge[0]);
            adjList.put(edge[1], list);
            indegrees[edge[0]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0)
                queue.add(i);
        }
        int courseCounter = 0;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            //mark as visited?
            courseCounter++;
            List<Integer> list = adjList.get(current);
            if (list != null) {
                for (int neighbour : list) {
                    indegrees[neighbour]--;
                    if (indegrees[neighbour] == 0)
                        queue.add(neighbour);
                }
            }

        }
        return courseCounter == numCourses;
    }
}
