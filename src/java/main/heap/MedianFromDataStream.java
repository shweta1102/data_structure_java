package src.java.main.heap;

import java.util.PriorityQueue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 * <p>
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 * <p>
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 * <p>
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -105 <= num <= 105
 * There will be at least one element in the data structure before calling findMedian.
 * At most 5 * 104 calls will be made to addNum and findMedian.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */
public class MedianFromDataStream {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    int totalSize;

    public MedianFromDataStream() {
        minHeap = new PriorityQueue<Integer>((a, b) -> (a - b));
        maxHeap = new PriorityQueue<Integer>((a, b) -> (b - a));
        totalSize = 0;
    }

    public void addNum(int num) {
        //if size of both heap is same add the number to min heap
        //else move top element from max to min and add the number to max heap
        //add numbers from minheap to max heap till the number is greater than top of minheap
        if (minHeap.size() == maxHeap.size()) {
            minHeap.add(num);
            maxHeap.add(minHeap.remove());
        } else {
            maxHeap.add(num);
            minHeap.add(maxHeap.remove());
        }
        totalSize++;
    }

    public double findMedian() {
        return (totalSize % 2) == 0 ? ((double) (minHeap.peek() + maxHeap.peek()) / 2) : maxHeap.peek();
    }

    public void addNumClean(int num) {
        //if size of both heap is same add the number to min heap
        //else move top element from max to min and add the number to max heap
        if (minHeap.size() != maxHeap.size()) {
            maxHeap.add(num);
            minHeap.add(maxHeap.remove());
        } else {
            minHeap.add(num);
            maxHeap.add(minHeap.remove());
        }
    }

    public double findMedianClean() {
        if (minHeap.size() != maxHeap.size()) {
            return maxHeap.peek();
        }
        return (double) (minHeap.peek() + maxHeap.peek()) / 2;
    }
}
