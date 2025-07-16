package src.java.main.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 * <p>
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 * <p>
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * <p>
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 * <p>
 * Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * note: x is undefined => -1.0
 * Example 2:
 * <p>
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * Example 3:
 * <p>
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, HashMap<String, Double>> graph = buildGraph(equations, values);
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            HashSet<String> visited = new HashSet<String>();
            result[i] = processQuery(graph, visited, start, end, start);
        }
        return result;
    }

    double processQuery(HashMap<String, HashMap<String, Double>> graph, HashSet<String> visited, String start,
                        String end, String currentNode) {
        if (!graph.containsKey(start) || !graph.containsKey(end) || visited.contains(currentNode))
            return -1;
        if (currentNode.equals(end))
            return 1;
        visited.add(currentNode);
        HashMap<String, Double> neighbours = graph.get(currentNode);
        for (Map.Entry<String, Double> neighbour : neighbours.entrySet()) {
            //for each neighbour go till we find the end node
            double ans = processQuery(graph, visited, start, end, neighbour.getKey());
            if (ans != -1) {
                return ans * neighbour.getValue();
            }
        }
        return -1;
    }

    private HashMap<String, HashMap<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String divident = equations.get(i).get(0);
            String divisor = equations.get(i).get(1);

            graph.putIfAbsent(divident, new HashMap<String, Double>());
            graph.putIfAbsent(divisor, new HashMap<String, Double>());

            graph.get(divident).put(divisor, values[i]);
            graph.get(divisor).put(divident, 1.0 / values[i]);

        }
        return graph;
    }
}
