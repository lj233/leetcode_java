package string;

import java.util.Arrays;

/**
 * @author lijian
 * @description 二进制求和
 * @date 2020/1/2
 * <p>
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 */
public class Main67 {
    public static void main(String[] args) {
        Solution67 solution67 = new Solution67();
        String a = solution67.addBinary("11", "1");
        System.out.println(a);
    }
}

class Solution67 {
    public String addBinary(String a, String b) {
        //直接以二进制作为运算
        StringBuilder ans = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            ca = sum / 2;
        }
        ans.append(ca == 1 ? ca : "");
        return ans.reverse().toString();
    }
}