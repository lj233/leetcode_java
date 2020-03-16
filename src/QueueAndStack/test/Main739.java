package QueueAndStack.test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author lijian
 * @description 每日温度
 * @date 2019/12/17
 */
public class Main739 {
    public static void main(String[] args) {
        int[] arr = {73, 74, 75, 71, 69, 72, 76, 73};
        Solution739 solution739 = new Solution739();
        int[] arr2 = solution739.dailyTemperatures(arr);
        System.out.println(1);
    }
}

class Solution739 {
    public int[] dailyTemperatures(int[] T) {
        //
        int[] ans = new int[T.length];
        //按照温度记录是第几位数字
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = T.length - 1; i >= 0; --i) {
            int warmer_index = Integer.MAX_VALUE;
            //遍历高于他的温度
            for (int t = T[i] + 1; t <= 100; ++t) {
                if (next[t] < warmer_index)
                    warmer_index = next[t];
            }
            if (warmer_index < Integer.MAX_VALUE)
                ans[i] = warmer_index - i;
            next[T[i]] = i;
        }
        return ans;
    }


}


/**
 * 利用栈解决问题
 */
class Solution739_2 {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack();
        for (int i = T.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }
}

