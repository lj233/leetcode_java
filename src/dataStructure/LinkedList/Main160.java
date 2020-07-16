package dataStructure.LinkedList;

/**
 * @author lijian
 * @description 相交链表
 * @date 2020/1/13
 */
public class Main160 {
}


class Solution160 {
    //相交链表，消除长度差，如果一个节点相同，那么他之后的所有结点都会相同，这个相同指的是引用地址的相同，不仅仅是val的相同
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}