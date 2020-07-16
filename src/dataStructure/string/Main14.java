package dataStructure.string;

import java.util.List;
import java.util.Objects;

import com.sun.org.apache.regexp.internal.RE;

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
//    public static void main(String[] args) {
//        String[] arr = {"d", "c", "e"};
//        Solution14 solution14 = new Solution14();
//        System.out.println(solution14.longestCommonPrefix(arr));
//    }
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

    /**
     * 搜索插入位置
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if (nums.length==0) return 0;
        if (nums[0]>=target) return 0;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] == target) return i;
            if (nums[i]<target&&target<=nums[i+1]) return i+1;
        }
        return nums.length;
    }

    /**
     * 最长回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        boolean[][] P = new boolean[length][length];
        int maxLen = 0;
        String maxPal = "";
        for (int len = 1; len <= length; len++) //遍历所有的长度
            for (int start = 0; start < length; start++) {
                int end = start + len - 1;
                if (end >= length) //下标已经越界，结束本次循环
                    break;
                P[start][end] = (len == 1 || len == 2 || P[start + 1][end - 1]) && s.charAt(start) == s.charAt(end); //长度为 1 和 2 的单独判断下
                if (P[start][end] && len > maxLen) {
                    maxPal = s.substring(start, end + 1);
                }
            }
        return maxPal;
    }

    /**
     * 反转字符串里的单词
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        String[] s1 = s.trim().split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = s1.length - 1; i >= 0; i--) {
            if (Objects.equals(s1[i], "")||Objects.equals(s1[i], " ")) continue;
            stringBuilder.append(s1[i]);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords2("Let's take LeetCode contest"));
    }

    /**
     * 反转字符串中的单词
     * @param s
     * @return
     */
    public static String reverseWords2(String s) {
        String[] s1 = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s1.length; i++) {
            StringBuilder replace = new StringBuilder(s1[i]);
            replace.reverse();
            stringBuilder.append(replace);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

}