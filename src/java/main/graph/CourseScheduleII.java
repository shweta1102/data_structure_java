package src.java.main.graph;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 * <p>
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 * <p>
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> orderList = new ArrayList<Integer>(numCourses);
        boolean[] dp = new boolean[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            graph.putIfAbsent(prerequisites[i][1], new ArrayList<Integer>());
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        HashSet<Integer> visited = new HashSet<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (!visited.contains(i)) {
                if (!hasCycle(visited, graph, i, dp, orderList))
                    return new int[0];
            }
        }
        return orderList.stream().mapToInt(i -> i).toArray();
    }

    private boolean hasCycle(HashSet<Integer> visited, HashMap<Integer, List<Integer>> graph, Integer currentNode,
                             boolean[] dp, List<Integer> orderList) {
        if (visited.contains(currentNode))
            return dp[currentNode];
        visited.add(currentNode);
        List<Integer> neighbours = graph.get(currentNode);
        if (neighbours != null) {
            for (Integer neighbour : neighbours) {
                if (!hasCycle(visited, graph, neighbour, dp, orderList)) {
                    dp[currentNode] = false;
                    return false;
                }
            }
        }
        dp[currentNode] = true;
        orderList.add(0, currentNode);
        return true;
    }

    /**
     * Time Complexity: O(V+E)
     * Space Complexity: O(V+E)
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrderWithBFSTopologicalSort(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> adjList = new HashMap<>();
        int[] indegrees = new int[numCourses];
        int[] courseOrder = new int[numCourses];
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
            courseOrder[courseCounter - 1] = current;
            List<Integer> list = adjList.get(current);
            if (list != null) {
                for (int neighbour : list) {
                    indegrees[neighbour]--;
                    if (indegrees[neighbour] == 0)
                        queue.add(neighbour);
                }
            }

        }
        return courseCounter == numCourses ? courseOrder : new int[0];
    }
}
