package QueueAndStack.test;

import java.util.Stack;

/**
 * @author lijian
 * @description 用栈实现队列
 * @date 2020/1/8
 */
public class Main232 {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();

        queue.push(1);
        queue.push(2);
        int peek = queue.peek();// 返回 1
        System.out.println(peek);
        int pop = queue.pop();// 返回 1
        System.out.println(pop);
        boolean empty = queue.empty();// 返回 false
        System.out.println(empty);

    }
}

class MyQueue {

    Stack stack;
    Stack temp =new Stack();//利用两个队列实现栈，主要是保证push的顺序
    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (!stack.isEmpty()){
            while (!stack.isEmpty()) {
                temp.push(stack.pop());
            }
        }
        stack.push(x);
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return (int)stack.pop();
    }

    /** Get the front element. */
    public int peek() {
        return (int)stack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}