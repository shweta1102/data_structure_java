package src.java.main.bfs;

import java.util.*;

/**
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 * <p>
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
 * <p>
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * Example 2:
 * <p>
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * Output: -1
 * <p>
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 105
 * All the values of routes[i] are unique.
 * sum(routes[i].length) <= 105
 * 0 <= routes[i][j] < 106
 * 0 <= source, target < 106
 */
public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target)
            return 0;
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int busstop : routes[i]) {
                List<Integer> buses = adj.getOrDefault(busstop, new ArrayList<Integer>());
                if (!buses.contains(i)) {
                    buses.add(i);
                    adj.put(busstop, buses);
                }
            }
        }
        HashSet<Integer> visited = new HashSet<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        //add routes index where source is present
        if (adj.get(source) == null)
            return -1;
        for (int route : adj.get(source)) {
            queue.add(route);
            visited.add(route);
        }
        int busesTaken = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                int route = queue.remove();
                //check if route has target
                for (int busStop : routes[route]) {
                    if (busStop == target)
                        return busesTaken;
                    //add routes with common busStops to queue
                    for (int newRoute : adj.get(busStop)) {
                        if (!visited.contains(newRoute)) {
                            queue.add(newRoute);
                            visited.add(newRoute);
                        }

                    }
                }
            }
            busesTaken++;
        }
        return -1;
    }
}
