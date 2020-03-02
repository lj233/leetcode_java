package binary_search;

/**
 * @author lijian
 * @description 寻找旋转排序数组中的最小值
 * @date 2020/2/21
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main153 {
    public static void main(String[] args) {
        Solution153 solution153 = new Solution153();
        int min = solution153.findMin(new int[]{4,5,6,7,0,1,2});
        System.out.println(min);
    }
}

class Solution153 {
    public int findMin(int[] nums) {
        if (nums==null||nums.length==0) return -1;
        int left = 0 ,right = nums.length-1;
        while (left<right){
            int mid = left+(right-left)/2;
            if (left==mid) return nums[left];
            if (nums[mid]>nums[mid+1]) left = mid+1;
            if (nums[mid]<nums[mid+1]) right = mid;
        }
        return nums[left];
    }
}