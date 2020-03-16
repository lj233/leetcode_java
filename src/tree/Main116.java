package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import binary_search.Main658;

/**
 * @author lijian
 * @description 填充每个节点的下一个右侧节点指针
 * 完美二叉树、next指向下一个右侧节点，没有为null
 * @date 2020/3/16
 */
public class Main116 {

    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        Node connect = new Main116().connect(node);
        System.out.println(connect.left.next);
    }
    public Node connect(Node root) {
        if (root==null) return root;
        Queue<Node> queue = new LinkedList<>();
        if(root.left!=null)queue.add(root.left);
        if(root.right!=null)queue.add(root.right);
        queue.add(null);
        //找到左右节点入栈
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            if (poll==null) continue;
            poll.next = queue.isEmpty()==false?queue.peek():null;
            if(poll.left!=null)queue.add(poll.left);
            if(poll.right!=null)queue.add(poll.right);
            if (queue.peek()==null) queue.add(null);
        }
        return root;
    }
}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}