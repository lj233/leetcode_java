package binary_search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijian
 * @description 找到 K 个最接近的元素
 *给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。如果有两个数与 x 的差值一样，优先选择数值较小的那个数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5], k=4, x=3
 * 输出: [1,2,3,4]
 * @date 2020/3/13
 */
public class Main658 {
    public static void main(String[] args) {
        Main658 main658 = new Main658();
        Main658.Solution solution = main658.new Solution();
        List<Integer> closestElements = solution.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3);
        System.out.println(closestElements.toString());
    }
    class Solution {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int size = arr.length;

            int left = 0;
            int right = size - k;

            while (left < right) {
                // int mid = left + (right - left) / 2;
                int mid = (left + right) >>> 1;
                // 尝试从长度为 k + 1 的连续子区间删除一个元素
                // 从而定位左区间端点的边界值
                if (x - arr[mid] > arr[mid + k] - x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            List<Integer> res = new ArrayList<>();
            for (int i = left; i < left + k; i++) {
                res.add(arr[i]);
            }
            return res;
        }
    }


}
