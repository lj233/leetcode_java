package array;

/**
 * @author lijian
 * @description 旋转数组
 * @date 2020/1/6
 *
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 * 链接：https://leetcode-cn.com/problems/rotate-array
 */
public class Main189 {
    public static void main(String[] args) {
        Solution189 solution189 = new Solution189();
        int[] ints = {1, 2, 3, 4, 5, 6, 7};
        solution189.rotate2(ints,3);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
class Solution189 {

    //原地移动
    public void rotate(int[] nums, int k) {
        int length = nums.length-1;
        int temp;
        for (int i = 0; i < k; i++) {
            temp = nums[length];
            for (int j = length; j >0; j--) {
                nums[j]=nums[j-1];
            }
            nums[0]=temp;
        }
    }
    //向后移动K个单位
    public void rotate2(int[] nums, int k) {
        int[] ints = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ints[(i+k)%nums.length]=nums[i];
        }
        for (int i = 0; i < ints.length; i++) {
            nums[i]=ints[i];
        }
    }
}

