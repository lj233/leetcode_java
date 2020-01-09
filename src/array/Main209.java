package array;

import java.util.Arrays;

/**
 * @author lijian
 * @description 长度最小的子数组
 * @date 2020/1/6
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例: 
 * <p>
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 * <p>
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 */
public class Main209 {
    public static void main(String[] args) {
        Solution209 solution209 = new Solution209();
        int i = solution209.minSubArrayLen(15, new int[]{1, 2, 3, 4, 5});
        System.out.println(i);

    }
}


class Solution209 {
    public int minSubArrayLen(int s, int[] nums) {
        int last = 0;
        int sum = 0;
        //先定义一个大的
        int res = nums.length+1;
        //滑动窗口
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
            if (sum>=s) {
                while (sum >= s) sum -= nums[last++];
                res = Math.min(res, i - last + 1+1);
            }
        }
         return res>nums.length?0:res;
    }
}