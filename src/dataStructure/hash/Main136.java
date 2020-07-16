package dataStructure.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijian
 * @description 只出现一次的数字
 * @date 2020/1/15
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main136 {
}

class Solution136 {
    public int singleNumber(int[] nums) {
        int single = nums[0];
        for (int i = 1; i < nums.length; i++) {
            single = single ^ nums[i];
        }
        return single;
    }

    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("公众号", "Java3y");
        hashMap.put("交流群", "回复1");

        // 使用增强for的方式来遍历hashMap
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        // 使用Lambda表达式的方式来遍历hashMap
        hashMap.forEach((s, s2) -> System.out.println(s + ":" + s2));
    }
}