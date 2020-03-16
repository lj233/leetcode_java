package hash;

import java.util.HashMap;

/**
 * @author lijian
 * @description 两个数组的交集2
 * @date 2020/1/16
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class Main350 {
    public static void main(String[] args) {
        Solution350 solution350 = new Solution350();
        int[] intersect = solution350.intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2, 2});
        System.out.println(intersect.length);
    }
}

class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        HashMap<Integer, Integer> hashMap2 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            hashMap.put(nums1[i], hashMap.getOrDefault(nums1[i], 0) + 1);
        }
        int j = 0;
        for (int i = 0; i < nums2.length; i++) {
            if (hashMap.containsKey(nums2[i]) && hashMap.get(nums2[i]) > 0) {
                hashMap.put(nums2[i], hashMap.get(nums2[i]) - 1);
                hashMap2.put(j, nums2[i]);
                j++;
            }
        }
        int[] arr = new int[j];
        for (int i = 0; i < j; i++) {
            arr[i] = hashMap2.get(i);
        }
        return arr;
    }
}