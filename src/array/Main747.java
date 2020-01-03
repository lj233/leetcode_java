package array;

/**
 * @author lijian
 * @description 至少是两倍的 最大数
 * @date 2019/12/30
 *
 * 在一个给定的数组nums中，总是存在一个最大元素 。
 *
 * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
 *
 * 如果是，则返回最大元素的索引，否则返回-1。
 *
 * 示例 1:
 *
 * 输入: nums = [3, 6, 1, 0]
 * 输出: 1
 * 解释: 6是最大的整数, 对于数组中的其他整数,
 * 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
 */
public class Main747 {
    public static void main(String[] args) {
        int[] arr = {0,0,0,1};
        int i = new Solution747().dominantIndex(arr);
        System.out.println(i);
    }
}

class Solution747{

    public int dominantIndex(int[] nums) {
        int max = 0;
        int k =0;
        for (int i = 0; i < nums.length; i++) {
            if (max/2<nums[i]){
                max = nums[i]*2;
                k=i;
            }
        }
        for (int j = 0; j < nums.length; j++) {
            if (j!=k&&nums[j]*4>max){
                return -1;
            }
        }
        return k;
    }
}
