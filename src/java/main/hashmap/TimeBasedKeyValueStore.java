package src.java.main.hashmap;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.
 * <p>
 * Implement the TimeMap class:
 * <p>
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * Output
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 * <p>
 * Explanation
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
 * timeMap.get("foo", 1);         // return "bar"
 * timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
 * timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
 * timeMap.get("foo", 4);         // return "bar2"
 * timeMap.get("foo", 5);         // return "bar2"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= key.length, value.length <= 100
 * key and value consist of lowercase English letters and digits.
 * 1 <= timestamp <= 107
 * All the timestamps timestamp of set are strictly increasing.
 * At most 2 * 105 calls will be made to set and get.
 */
public class TimeBasedKeyValueStore {
    private class ValueObject {
        String value;
        int time;

        public ValueObject(String value, int time) {
            this.value = value;
            this.time = time;
        }
    }

    HashMap<String, TreeSet<ValueObject>> timeMap;

    public TimeBasedKeyValueStore() {
        timeMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        //to use tree set floor operation, natural ordering is required. If we sort it in descending order, floor cannot be used
        TreeSet<ValueObject> treeSet = timeMap.getOrDefault(key, new TreeSet<>((a, b) -> (a.time - b.time)));
        treeSet.add(new ValueObject(value, timestamp));
        timeMap.put(key, treeSet);
    }

    public String get(String key, int timestamp) {
        TreeSet<ValueObject> treeSet = timeMap.get(key);
        if (treeSet == null)
            return "";
        ValueObject searchObj = new ValueObject("", timestamp);
        ValueObject result = treeSet.floor(searchObj);
        /*for (ValueObject valueObj : treeSet) {
            if (valueObj.time <= timestamp)
                return valueObj.value;
        }*/
        return result == null ? "" : result.value;
    }
}
