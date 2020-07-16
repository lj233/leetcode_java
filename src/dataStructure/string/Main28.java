package dataStructure.string;

/**
 * @author lijian
 * @description 实现str
 * @date 2020/1/2
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 */
public class Main28 {
    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        System.out.println(haystack.indexOf(needle));
    }
}

class Solution28 {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

}