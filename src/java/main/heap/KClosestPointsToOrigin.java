package src.java.main.heap;

import java.util.PriorityQueue;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 * <p>
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * <p>
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 * <p>
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= points.length <= 104
 * -104 <= xi, yi <= 104
 */
public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxQueue = new PriorityQueue<int[]>(
                (a, b) -> Double.compare(distance(b[0], b[1]), distance(a[0], a[1])));
        for (int[] point : points) {
            if (maxQueue.size() < k) {
                maxQueue.add(point);
            } else if (distance(point[0], point[1]) < distance(maxQueue.peek()[0], maxQueue.peek()[1])) {
                maxQueue.poll();
                maxQueue.add(point);
            }
        }
        int[][] result = new int[k][];
        int i = 0;
        while (!maxQueue.isEmpty()) {
            result[i++] = maxQueue.remove();
        }
        return result;
    }

    private double distance(int x, int y) {
        return Math.pow(x, 2) + Math.pow(y, 2);
    }
}
