package dataStructure.LinkedList;

/**
 * @author lijian
 * @description 删除链表的倒数第N个结点
 * @date 2020/1/13
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 */
public class Main19 {
}


class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //新增一个结点保护头结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        //快指针先走出n个
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        // 慢指针停的位置就是删除的前一个
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}