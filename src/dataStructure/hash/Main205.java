package dataStructure.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijian
 * @description 同构字符串
 * @date 2020/1/16
 * 示例 1:
 * <p>
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/isomorphic-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main205 {
    public static void main(String[] args) {

    }
}

class Solution205 {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            //s的映射不能够对用t的两个映射
            if (map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) != t.charAt(i)) {
                return false;
            }
            //t的映射也不能对s的两个映射
            else if (map.containsValue(t.charAt(i)) && !map.containsKey(s.charAt(i))) return false;
            map.put(s.charAt(i), t.charAt(i));
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.indexOf(ch1[i]) != t.indexOf(ch2[i])) {
                return false;
            }
        }
        return true;
    }
}