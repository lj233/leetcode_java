package dataStructure.hash;

import java.util.LinkedHashMap;

/**
 * @author lijian
 * @description 字符串中第一个唯一 字符
 * @date 2020/1/16
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 案例:
 * <p>
 * s = "leetcode"
 * 返回 0.
 * <p>
 * s = "loveleetcode",
 * 返回 2.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main387 {
}


class Solution387 {
    public int firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> c = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            c.put(chars[i], c.getOrDefault(chars[i], 0) + 1);
        }
        for (int i = 0; i < chars.length; i++) {
            if (c.get(chars[i]) == 1) return i;
        }
        return -1;
    }
}