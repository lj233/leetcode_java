package binary_search;

/**
 * @author lijian
 * @description x的平方根
 * @date 2020/1/17
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main69 {
    public static void main(String[] args) {
        Solution69 solution69 = new Solution69();
        int i = solution69.mySqrt(8);
        System.out.println(i);
    }
}

class Solution69 {
    public int mySqrt(int x) {
        if (x == 0 || x == 1)
            return x;
        long left = 1;
        long right = x / 2;
        while (left < right) {
            long mid = (left + right) / 2;
            if (mid * mid <= x && (mid + 1) * (mid + 1) > x)
                return (int) mid;
            else if (mid * mid < x)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return (int) left;
    }
}