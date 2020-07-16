package dataStructure.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lijian
 * @description 存在重复元素
 * @date 2020/1/15
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main217 {
}

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> integerSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (integerSet.contains(nums[i])) return true;
            integerSet.add(nums[i]);
        }
        return false;
    }
}