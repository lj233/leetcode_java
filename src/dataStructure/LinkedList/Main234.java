package dataStructure.LinkedList;

import java.util.LinkedList;

/**
 * @author lijian
 * @description 回文联表
 * @date 2020/1/14
 * <p>
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 */
public class Main234 {
}


class Solution {
    //遍历链表，存到队列中
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        LinkedList<ListNode> linkedList = new LinkedList<>();
        ListNode curr = head;
        while (curr != null) {
            linkedList.add(curr);
            curr = curr.next;
        }
        if (linkedList.size() % 2 == 1) return false;
        while (!linkedList.isEmpty() && linkedList.size() != 1) {
            if (linkedList.pollFirst().val != linkedList.pollLast().val) return false;
        }
        return true;
    }
}