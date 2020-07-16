package dataStructure.LinkedList;

/**
 * @author lijian
 * @description 删除链表元素
 * @date 2020/1/13
 * <p>
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class Main203 {
    public static void main(String[] args) {

    }
}


class Solution203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dumy = new ListNode(0);
        dumy.next = head;
        ListNode curr = dumy;
        while (curr != null && curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else
                curr = curr.next;
        }
        return dumy.next;
    }
}
