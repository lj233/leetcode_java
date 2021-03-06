package dataStructure.array;

import java.util.Arrays;

/**
 * @author lijian
 * @description 数组拆分-双指针
 * @date 2020/1/3
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,4,3,2]
 * <p>
 * 输出: 4
 * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4)
 */
public class Main561 {
    public static void main(String[] args) {
        int[] nums = {1, 4, 3, 2};
        Solution561 solution561 = new Solution561();
        System.out.println(solution561.arrayPairSum(nums));
    }
}

class Solution561 {
    public int arrayPairSum(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        Arrays.sort(nums);
        int s = 0;
        int sum = 0;
        while (s < nums.length - 1) {
            sum += nums[s];
            s += 2;
        }
        return sum;
    }


}
