package dataStructure.QueueAndStack.test;

/**
 * @author lijian
 * @description 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * bfs
 * 队列：用来存储每一轮遍历得到的节点
 * 标记：对于遍历过的节点，应该将它进行标记，防止重复遍历
 * @date 2019/12/5
 */
public class Main279 {

    public static void main(String[] args) {
        int num = new Solution279().numSquares(21);
        System.out.println(num);

    }
}

class Solution279 {

    public int numSquares(int n) {
        int[] dp = new int[n + 1]; // 默认初始化值都为0
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏的情况就是每次+1
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
            }
        }
        return dp[n];
    }
}


