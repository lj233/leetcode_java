package array;

/**
 * @author lijian
 * @description 两数之和二--输入有序数组
 * @date 2020/1/3
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */
public class Main167 {
    public static void main(String[] args) {
        int [] nums = {2, 7, 11, 15};
        Solution167 solution167 = new Solution167();
        int[] temp = solution167.twoSum(nums,9);

        System.out.println(temp[0]+"  "+temp[1]);

    }
}

class Solution167 {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers==null||numbers.length<2) return null;
        int s = 0;
        int e = numbers.length-1;
        //利用了排序的性质
        while (s<e){
            int a = numbers[s]+numbers[e];
            if (a<target){
                s++;
            }else if (a>target){
                e--;
            }else return new int[]{s+1,e+1};
        }
        return null;
    }
}