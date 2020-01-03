package array;

/**
 * @author lijian
 * @description 寻找数组中心索引
 * @date 2019/12/30
 *
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
 *
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 *
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 *
 * 示例 1:
 *
 * 输入:
 * nums = [1, 7, 3, 6, 5, 6]
 * 输出: 3
 * 解释:
 * 索引3 (nums[3] = 6) 的左侧数之和(1 + 7 + 3 = 11)，与右侧数之和(5 + 6 = 11)相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 */
public class Main724 {
    public static void main(String[] args) {
        Solution724 solution724 = new Solution724();
        int [] arr = {1,7,3,6,5,6};
        int i =solution724.pivotIndex(arr);
        System.out.println(i);
    }

}

class Solution724{

    int sum = 0;

    public int pivotIndex(int[] nums) {
        if (nums.length==0){
            return -1;
        }
        //判断不了边界情况
//        for (int i = 0; i < nums.length; i++) {
//
//            int sum1 = 0;
//            for (int i1 = nums.length - 1; i1 > i+1; i1--) {
//                sum1+=nums[i1];
//            }
//            if (sum==sum1){
//                return i+1;
//            }
//            sum+=nums[i];
//        }
        for (int num : nums) {
            sum+=num;
        }
        int sum1=0;
        //
        for (int i = 0; i < nums.length; i++) {
            if (sum1*2+nums[i]==sum){
                return i;
            }
            sum1+=nums[i];
        }

        return -1;
    }

}
