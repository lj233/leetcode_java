package binary_search;

/**
 * @author lijian
 * @description 寻找峰值  大于左面，小于右面
 * @date 2020/1/19
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main162 {
}

class Solution162 {
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = left + 1, length = nums.length;
        boolean flag = true;
        while (right < length) {
            if (flag && nums[left] > nums[right]) return left;
            flag = nums[left] < nums[right];
            left++;
            right++;

        }
        if (nums[length - 1] > nums[left - 1]) return length - 1;
        return -1;
    }

    public int findPeakElement2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1||nums[0] > nums[1]) return 0;
        if (nums[nums.length-1]>nums[nums.length-2]) return nums.length-1;

        int left = 0, right = nums.length-1;
        while (left+1  < right) {
            int mid = left+1;
            if (nums[mid]>nums[left]&&nums[mid]>nums[mid+1]) return mid;
            left++;
        }
        return -1;
    }
}