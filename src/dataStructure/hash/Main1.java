package dataStructure.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijian
 * @description 两数之和
 * @date 2020/1/16
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main1 {
    public static void main(String[] args) {

    }

}

class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j;
        for (; i < nums.length; i++) {
            for (j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) return new int[]{i, j};
            }
        }
        return null;
    }


    public int[] twoSum2(int[] nums, int target) {
        //初始化大小
        double capacity = nums.length / 0.75 + 1.0;
        Map<Integer, Integer> map = new HashMap<>((int) capacity);
        int complement;
        for (int i = 0; i < nums.length; i++) {
            complement = target - nums[i];
            //存在值等于剩余值的键
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}
