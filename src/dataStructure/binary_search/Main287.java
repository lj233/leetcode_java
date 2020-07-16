package dataStructure.binary_search;

/**
 * @author lijian
 * @description 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/3/13
 */
public class Main287 {
    public static void main(String[] args) {
        Main287 main287 = new Main287();
        Main287.Solution solution = main287.new Solution();
        int duplicate = solution.findDuplicate(new int[]{3, 1, 3, 4, 2});
        System.out.println(duplicate);
    }
    class Solution {
        public int findDuplicate(int[] nums) {
            int res = 0;
            if (nums==null) return -1;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i+1; j <nums.length ; j++) {
                    if (nums[i]-nums[j]==0) {
                        res = nums[j];
                        break;
                    }
                }
            }
            return res;
        }
    }
}
