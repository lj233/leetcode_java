package dataStructure.array;

/**
 * @author lijian
 * @description 双指针--反转字符串
 * @date 2020/1/2
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：['h','e','l','l','o']
 * 输出：['o','l','l','e','h']
 */
public class Main344 {
    public static void main(String[] args) {
        char[] arr = {'h', 'e', 'l', 'l', 'o'};
        Solution344 solution344 = new Solution344();
        solution344.reverseString(arr);
        for (char c : arr) {
            System.out.println(c);
        }
    }
}


class Solution344 {
    public void reverseString(char[] s) {
        int end = s.length - 1;
        int start = 0;
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}
