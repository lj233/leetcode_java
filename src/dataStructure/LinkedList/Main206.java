package dataStructure.LinkedList;

/**
 * @author lijian
 * @description 反转链表
 * @date 2020/1/13
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main206 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        Solution206 solution206 = new Solution206();
        ListNode listNode1 = solution206.reverseList(listNode);
        System.out.println(listNode1.val);
    }
}

class Solution206 {

    //迭代
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (head != null && head.next != null) {
            ListNode temp = head.next;
            head.next = head.next.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }
        return dummy.next;
    }

    //递归
    public ListNode reverseList2(ListNode head) {
        while (head.next != null) {
            reverseList2(head.next);
        }
        return null;
    }


}
