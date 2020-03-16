package array;

/**
 * @author lijian
 * @description 二维数组斜遍历
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * <p>
 * 输出:  [1,2,4,7,5,3,6,8,9]
 * @date 2019/12/30
 */
public class Array2DiagonalOrder {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Solution498 solution498 = new Solution498();
        int[] arr2 = solution498.findDiagonalOrder(arr);
        for (int i : arr2) {
            System.out.println(i);
        }

    }
}


class Solution498 {
    public int[] findDiagonalOrder(int[][] matrix) {
        //考虑边界 []
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        //m行 n列
        int m = matrix.length;
        int n = matrix[0].length;
        //用来记录加入到结果数组的元素的下标
        int count = 0;
        //用来保存结果的数组 大小为矩阵的元素个数
        int[] result = new int[m * n];
        //m+n-1为需要遍历的次数
        for (int i = 0; i < m + n - 1; i++) {
            //i为0或偶数时 沿对角线向上遍历
            if (i == 0 || i % 2 == 0) {
                //发现规律x+y = i 然后找边界（x坐标超过主对角线时的情况）
                int x = i < m ? i : m - 1;
                int y = i - x;
                //开始遍历 x坐标不断减 y坐标不断加 当x减到0 或者 y加到列n的值
                while (x >= 0 && y < n) {
                    result[count] = matrix[x][y];
                    x--;
                    y++;
                    count++;
                }
            } else {
                //沿对角线向下遍历
                int y = i < n ? i : n - 1;
                int x = i - y;
                //y坐标不断减 x坐标不断加 当y减到0 或者 x加到行m的值
                while (y >= 0 && x < m) {
                    result[count] = matrix[x][y];
                    y--;
                    x++;
                    count++;
                }
            }
        }
        return result;
    }
}

