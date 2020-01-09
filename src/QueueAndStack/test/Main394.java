package QueueAndStack.test;

import java.util.LinkedList;

/**
 * @author lijian
 * @description 字符串解码
 * @date 2020/1/8
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例:
 * <p>
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 */
public class Main394 {
    public static void main(String[] args) {
        Solution394 solution394 = new Solution394();
        String a =solution394.decodeString("3[a]2[bc]");
        System.out.println(a);
    }
}

class Solution394 {

    public String decodeString(String s) {
        return recursive(s, 0)[0];
    }

    public String[] recursive(String s, int i) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while (i < s.length()) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));// 0*10的作用 是解决多位数字的情况
            else if (s.charAt(i) == '[') {
                String[] tmp = recursive(s, i + 1);
                i = Integer.parseInt(tmp[0]);
                while (multi > 0) {
                    res.append(tmp[1]);
                    multi--;
                }
            } else if (s.charAt(i) == ']')
                //分别记录i值和res
                return new String[]{String.valueOf(i), res.toString()};
            else
                res.append(String.valueOf(s.charAt(i)));
            i++;
        }
        return new String[]{res.toString()};
    }

    public String decodeString2(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>(); //存储数字
        LinkedList<String> stack_res = new LinkedList<>();      //存储字母
        for(Character c : s.toCharArray()) {
            if(c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            }
            else if(c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for(int i = 0; i < cur_multi; i++) tmp.append(res);
                res = new StringBuilder(stack_res.removeLast() + tmp);
            }
            else if(c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + ""); // *10 是倍数累加
            else res.append(c);
        }
        return res.toString();
    }

}