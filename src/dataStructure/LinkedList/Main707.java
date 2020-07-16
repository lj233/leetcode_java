package dataStructure.LinkedList;


/**
 * @author lijian
 * @description 设置链表707
 * @date 2020/1/10
 */
public class Main707 {
    public static void main(String[] args) {

    }
}


class MyLinkedList {

    private class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    //链表中只需要记录一个头结点的位置
    private Node dummyHead;
    int size;


    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        dummyHead = new Node(-1);
        size = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size)
            return -1;
        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++)
            curr = curr.next;
        return curr.val;

    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size)
            return;
        if (index < 0)
            addAtHead(val);
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node node = new Node(val);
        node.next = prev.next;
        prev.next = node;
        size++;

    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size)
            return;
        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;
        Node reNode = prev.next;
        prev.next = reNode.next;
        reNode.next = null;
        size--;
    }
}