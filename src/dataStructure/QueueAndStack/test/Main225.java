package dataStructure.QueueAndStack.test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lijian
 * @description 用队列实现栈
 * @date 2020/1/8
 */
public class Main225 {
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        stack.push(1);
        stack.push(2);
        int peek = stack.top();// 返回 1
        System.out.println(peek);
        int pop = stack.pop();// 返回 1
        int top = stack.top();// 返回 1
        System.out.println(top);
        boolean empty = stack.empty();// 返回 false
        System.out.println(empty);
    }
}

class MyStack {

    int font;
    Queue queue = new LinkedList();
    Queue temp = new LinkedList();

    /**
     * Initialize your data structure here.
     */
    public MyStack() {

    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        if (!queue.isEmpty()) {
            while (!queue.isEmpty())
                temp.add(queue.poll());
        }
        queue.add(x);
        while (!temp.isEmpty()) {
            queue.add(temp.poll());
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        font = (int) queue.peek();
        queue.poll();
        return font;
    }

    /**
     * Get the top element.
     */
    public int top() {
        return (int) queue.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}