package array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijian
 * @description 杨辉三角2
 * @date 2020/1/6
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 * <p>
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class Main119 {
    public static void main(String[] args) {
        Solution119 solution119 = new Solution119();
        List<Integer> list = solution119.getRow(0);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}

class Solution119 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        Integer[][] nums = new Integer[rowIndex + 1][];
        for (int i = 0; i <= rowIndex; i++) {
            nums[i] = new Integer[i + 1];
            for (int j = 0; j <= i; j++) {
                if (i > 1 && j > 0 && i > j) {
                    nums[i][j] = nums[i - 1][j] + nums[i - 1][j - 1];
                } else nums[i][j] = 1;
                if (i == rowIndex)
                    list.add(nums[i][j]);
            }
        }
        return list;

    }
}