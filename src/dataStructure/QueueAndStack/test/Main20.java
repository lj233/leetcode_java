package dataStructure.QueueAndStack.test;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author lijian
 * @description 有效的括号
 * 有左括号就加到栈中，有右括号就看栈顶是够类型匹配
 * @date 2019/12/17
 */
public class Main20 {
    public static void main(String[] args) {
        boolean flag = new Solution20().isValid("((((())))){}{}{}[]");
        System.out.println(flag);
    }
}

class Solution20 {
    // Hash table that takes care of the mappings.
    private HashMap<Character, Character> mappings;

    // Initialize dataStructure.hash map with mappings. This simply makes the code easier to read.
    public Solution20() {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }

    public boolean isValid(String s) {

        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (this.mappings.containsKey(c)) {

                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }

}
