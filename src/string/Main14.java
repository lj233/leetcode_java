package string;

/**
 * @author lijian
 * @description 最长公共前缀
 * @date 2020/1/2
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 */
public class Main14 {
    public static void main(String[] args) {
        String[] arr = {"d", "c", "e"};
        Solution14 solution14 = new Solution14();
        System.out.println(solution14.longestCommonPrefix(arr));
    }
}

class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                //不等于就退出，超出长度就退出
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

}