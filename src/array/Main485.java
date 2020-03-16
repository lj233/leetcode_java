package array;

/**
 * @author lijian
 * @description 最大连续1的个数
 * @date 2020/1/6
 * <p>
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 */
public class Main485 {
    public static void main(String[] args) {
        Solution485 solution485 = new Solution485();
        int maxConsecutiveOnes = solution485.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1});
        System.out.println(maxConsecutiveOnes);
    }
}


class Solution485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int k = 0;//记录1的个数
        int sum = 0;//记录最大连续1的个数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                k++;
//                  不在 '是' 的条件下做判断
//                while (k<nums.length&&nums[i]==1) {
//                    temp++;
//                    k++;
//                    i=k;
//                }
//                sum = sum>temp?sum:temp;
            } else {
                sum = Math.max(k, sum);
                k = 0;
            }
        }
        return Math.max(k, sum);
    }
}
