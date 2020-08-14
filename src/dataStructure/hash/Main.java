package dataStructure.hash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import jdk.nashorn.internal.ir.CallNode;

/**
 * @author lijian
 * @description hash集的用法
 * @date 2020/1/15
 */
public class Main {
    public static void main(String[] args) {
        topKFrequent(new int[]{1,2,2,3,3,3},2);
    }

    public void init(){
        // 1. initialize the dataStructure.hash set
        Set<Integer> hashSet = new HashSet<>();
        // 2. add a new key
        hashSet.add(3);
        hashSet.add(2);
        hashSet.add(1);
        // 3. remove the key
        hashSet.remove(2);
        // 4. check if the key is in the dataStructure.hash set
        if (!hashSet.contains(2)) {
            System.out.println("Key 2 is not in the dataStructure.hash set.");
        }
        // 5. get the size of the dataStructure.hash set
        System.out.println("The size of has set is: " + hashSet.size());
        // 6. iterate the dataStructure.hash set
        for (Integer i : hashSet) {
            System.out.print(i + " ");
        }
        System.out.println("are in the dataStructure.hash set.");
        // 7. clear the dataStructure.hash set
        hashSet.clear();
        // 8. check if the dataStructure.hash set is empty
        if (hashSet.isEmpty()) {
            System.out.println("dataStructure.hash set is empty now!");
        }
    }

    /**
     * 宝石与石头
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {
        Set<Character> Jset = new HashSet();
        for (char j: J.toCharArray())
            Jset.add(j);

        int ans = 0;
        for (char s: S.toCharArray())
            if (Jset.contains(s))
                ans++;
        return ans;
    }

    /**  滑动窗口
     * 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * 四数之和二
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int sum1 = a + b;
                map1.put(sum1, map1.getOrDefault(sum1, 0) + 1);
            }
        }
        for (int c : C) {
            for (int d : D) {
                int sum2 = c + d;
                if(map1.containsKey(-sum2))  res += map1.get(-sum2);
            }
        }
        return res;
    }

    /**
     * 前k个高频元素
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

        // keep k top frequent elements in the heap
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = heap.poll();

        }
        return ans;
    }


}
