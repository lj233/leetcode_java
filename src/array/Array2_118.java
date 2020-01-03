package array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijian
 * @description 杨辉三角
 * @date 2019/12/31
 */
public class Array2_118 {
    public static void main(String[] args) {
        Solution118 solution118 = new Solution118();
        solution118.generate(1);
    }
}

class Solution118 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        if (numRows==0) return list;
        Integer[][] nums = new Integer[numRows][];
        for (int i = 0; i < numRows; i++) {
            nums[i] = new Integer[i + 1];
            List<Integer> list1 = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (i > 1 && j > 0 && i > j) {
                    nums[i][j] = nums[i - 1][j] + nums[i - 1][j - 1];
                } else nums[i][j] = 1;
                list1.add(nums[i][j]);
                System.out.print(nums[i][j]);
            }
            list.add(list1);
            System.out.println();
        }

        return list;
    }

}