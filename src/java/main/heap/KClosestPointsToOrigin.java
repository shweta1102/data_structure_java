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
        int[][] result = new int[k][2];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>((a, b) -> (b[0] - a[0]));
        for (int i = 0; i < points.length; i++) {
            maxHeap.add(new int[]{getDistance(points[i][0], points[i][1]), i});
            if (maxHeap.size() > k) {
                maxHeap.remove();
            }
        }
        int i = 0;
        while (maxHeap.size() > 0) {
            int[] index = maxHeap.remove();
            result[i] = points[index[1]];
            i++;
        }
        return result;
    }

    private int getDistance(int x, int y) {
        return (int) (Math.pow(x, 2) + Math.pow(y, 2));
    }
}
