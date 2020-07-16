package dataStructure.QueueAndStack.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author lijian
 * @description 旋转锁问题
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * <p>
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * <p>
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * <p>
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 * @date 2019/12/5
 */
public class Xuanzhuansuo {
    public static void main(String[] args) {
        String[] dead = {"0201", "0201", "0102", "1212", "2002"};
        System.out.println(new XSolution().openLock(dead, "0202"));
    }
}

class XSolution {
    public int openLock(String[] deadends, String target) {
        if (target == null || target.length() == 0) {
            return -1;
        }
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        String start = "0000";
        if (deads.contains(target) || deads.contains(start)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        int step = 0;
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                String cur = queue.poll();
                if (target.equals(cur)) { // 找到了目标返回步骤数
                    return step;
                }
                List<String> nexts = getNexts(cur);
                for (String str : nexts) {
                    if (!deads.contains(str) && visited.add(str)) {
                        queue.offer(str);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    // 获得当前值转动一位可以转动到的所有情况
    private List<String> getNexts(String cur) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            StringBuilder curBuilder = new StringBuilder(cur);
            char modChar = cur.charAt(i);
            curBuilder.setCharAt(i, modChar == '0' ? '9' : (char) (modChar - 1));
            list.add(curBuilder.toString());
            curBuilder.setCharAt(i, modChar == '9' ? '0' : (char) (modChar + 1));
            list.add(curBuilder.toString());
        }
        return list;
    }

}