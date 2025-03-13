package src.java.main.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Implement the RandomizedSet class:
 * <p>
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 * <p>
 * Explanation
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -231 <= val <= 231 - 1
 * At most 2 * 105 calls will be made to insert, remove, and getRandom.
 * There will be at least one element in the data structure when getRandom is called.
 */
public class InsertDeleteGetRandom {
    private final ArrayList<Integer> list;
    private final HashMap<Integer, Integer> numsToIndex;
    private final Random randomNum = new Random();

    public InsertDeleteGetRandom() {
        list = new ArrayList<Integer>();
        numsToIndex = new HashMap<Integer, Integer>();
    }

    public boolean insert(int val) {
        // check if the map contains the value, if yes return true
        if (numsToIndex.containsKey(val))
            return false;
        // add value to map
        numsToIndex.put(val, list.size());
        // add value to the list
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        // check if val exist in the map
        if (!numsToIndex.containsKey(val))
            return false;
        // get index of the value
        int index = numsToIndex.get(val);
        int lastIndex = list.size() - 1;
        // swap the last index of the list with the element the current element
        if (index != lastIndex) {
            // move last element from the list at the current val index
            list.set(index, list.get(lastIndex));
            // update index for the value in map
            numsToIndex.put(list.get(index), index);
        }
        // remove last element fromt the list
        list.remove(lastIndex);
        // remove val from map
        numsToIndex.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(randomNum.nextInt(list.size()));
    }

    public static void main(String[] args) {
        InsertDeleteGetRandom randomisedSet = new InsertDeleteGetRandom();
        System.out.println(randomisedSet.insert(0));
        System.out.println(randomisedSet.remove(1));
        System.out.println(randomisedSet.remove(0));
        System.out.println(randomisedSet.insert(1));
        System.out.println(randomisedSet.getRandom());
    }
}
