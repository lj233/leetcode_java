package LinkedList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author lijian
 * @description 环形链表
 * @date 2020/1/13
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main141 {
    public static void main(String[] args) {

    }


}

class Solution141 {

    //双指针，一个快，一个慢
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        //如果存在环，两者终究会相等
        while (slow != fast) {
            //存在环，fast就不能一直指下去了
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }


    //利用hash表的去重特性
    public boolean hasCycle2(ListNode head) {
        Set<ListNode> listNodes = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (listNodes.contains(curr)) return true;
            listNodes.add(curr);
            curr = curr.next;
        }
        return false;
    }

    //环形链表2，返回链表开始入环的第一个结点
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<ListNode>();

        ListNode node = head;
        while (node != null) {
            if (visited.contains(node)) {
                return node;
            }
            visited.add(node);
            node = node.next;
        }

        return null;

    }

}