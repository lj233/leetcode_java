package QueueAndStack.test;

import java.util.LinkedList;

/**
 * @author lijian
 * @description 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * idea   找到为 1 的陆地，通过递归排除塔4个方向的陆地
 * @date 2019/12/4
 */
public class Daoyu {

    static char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};

    public static void main(String[] args) {
        int num = new Solution().numIslands(grid);

        System.out.println(num);
    }

}

class Solution {

    private int row;

    private int col;

    public int numIslands(char[][] grid) {
        int res = 0;
        if (null == grid || grid.length == 0) {
            return res;
        }
        row = grid.length;
        col = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    backtrack(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void backtrack(char[][] grid, int x, int y) {
        if (!verify(x, y)) {
            return;
        }
        if (grid[x][y] == '1') {
            grid[x][y] = '2';
            backtrack(grid, x, y + 1);
            backtrack(grid, x + 1, y);
            backtrack(grid, x, y - 1);
            backtrack(grid, x - 1, y);
        }
    }

    private boolean verify(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}