package QueueAndStack.test;

/**
 * @author lijian
 * @description 目标和
 * @date 2020/1/7
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 */
public class Main494 {
    public static void main(String[] args) {
        Solution494 solution494 = new Solution494();
        int targetSumWays = solution494.findTargetSumWays2(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println(targetSumWays);

    }
}


class Solution494 {
    int count = 0;

    public int findTargetSumWays(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }

    public void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (sum == S)
                count++;
        } else {
            calculate(nums, i + 1, sum + nums[i], S);
            calculate(nums, i + 1, sum - nums[i], S);
        }
    }

    /*
    这道题也是一个常见的背包问题，我们可以用类似求解背包问题的方法来求出可能的方法数。

我们用 dp[i][j] 表示用数组中的前 i 个元素，组成和为 j 的方案数。考虑第 i 个数 nums[i]，它可以被添加 + 或 -，因此状态转移方程如下：

dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
也可以写成递推的形式：

dp[i][j + nums[i]] += dp[i - 1][j]
dp[i][j - nums[i]] += dp[i - 1][j]
由于数组中所有数的和不超过 1000，那么 j 的最小值可以达到 -1000。在很多语言中，是不允许数组的下标为负数的，因此我们需要给 dp[i][j] 的第二维预先增加 1000，即：

dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000]
dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000]

作者：LeetCode
链接：https://leetcode-cn.com/problems/target-sum/solution/mu-biao-he-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    //动态规划
    public int findTargetSumWays2(int[] nums, int S) {
        //dp【x】【y】中 x代表第几个数，y代表和值（因为说了不超过1000），坐标值代表有几种解决方案。
        int[][] dp = new int[nums.length][11];
        dp[0][nums[0] + 5] = 1;//代表着 等于nums【0】 的正负情况各有一种解决方案
        dp[0][-nums[0] + 5] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -5; sum <= 5; sum++) {
                if (dp[i - 1][sum + 5] > 0) {
                    dp[i][sum + nums[i] + 5] += dp[i - 1][sum + 5];
                    dp[i][sum - nums[i] + 5] += dp[i - 1][sum + 5];

                    System.out.print(i + " " + (sum + nums[i] + 5) + "  " + dp[i][sum + nums[i] + 5] + "  ");
                    System.out.println(i + " " + (sum - nums[i] + 5) + "  " + dp[i][sum - nums[i] + 5]);
                }
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();

        for (int i = 0; i < dp.length; i++) {
            System.out.println();
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 5];
    }


}


// 动态规划 背包问题
class Maindp {
    public static void main(String[] args) {
        Maindp maindp = new Maindp();
        maindp.jinkuang();
    }

    public void beibao() {
        int[] w = {0, 2, 3, 4, 5, 9};// 物品的占用数
        int[] v = {0, 3, 4, 5, 8, 10}; // 物品的价值
        int N = 6, W = 21;//6种物品，20个格子空间
        int[][] b = new int[N][W];
        for (int k = 1; k < N; k++) {
            for (int c = 1; c < W; c++) {
                if (w[k] > c) {//重量剩余容量时
                    b[k][c] = b[k - 1][c];
                } else {
                    int value1 = b[k - 1][c - w[k]] + v[k]; // 拿第k件物品
                    int value2 = b[k - 1][c]; // 不拿第k件物品
                    b[k][c] = Math.max(value1, value2);
                }
            }
        }
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                System.out.print(b[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println(b[5][20]);
    }

    //金矿问题，动态规划 ，自底向上，递归，从上到下
    public void jinkuang() {
        int[] w = {0, 5, 5, 3, 4, 3};// 物品的占用数
        int[] v = {0, 400, 500, 200, 300, 350}; // 物品的价值
        int N = 6, W = 11;//6种物品，20个格子空间
        int[][] b = new int[N][W];
        for (int k = 1; k < N; k++) {
            for (int c = 1; c < W; c++) {
                if (w[k] > c) {//重量剩余容量时
                    b[k][c] = b[k - 1][c];
                } else {
                    int value1 = b[k - 1][c - w[k]] + v[k]; // 拿第k件物品
                    int value2 = b[k - 1][c]; // 不拿第k件物品
                    b[k][c] = Math.max(value1, value2);
                }
            }
        }
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                System.out.print(b[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println(b[5][10]);
    }
}