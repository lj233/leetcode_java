package hash;

import java.util.HashSet;

/**
 * @author lijian
 * @description 设计Hash集合
 * @date 2020/1/14
 */
public class Main705 {
    boolean[] set = new boolean[1000001];

    /**
     * Initialize your data structure here.
     */
    public Main705() {

    }

    public void add(int key) {
        set[key] = true;
    }

    public void remove(int key) {
        set[key] = false;
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        return set[key];
    }
}

