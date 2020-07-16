package dataStructure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.org.apache.xerces.internal.xs.ItemPSVI;

/**
 * @author lijian
 * @description 二维数组
 * 可以定义动态二维数组
 * @date 2019/12/30
 */
// "static void main" must be defined in a public class.
public class Array2 {
    private static void printArray(int[][] a) {
        for (int i = 0; i < a.length; ++i) {
            System.out.println(a[i]);
        }
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; a[i] != null && j < a[i].length; ++j) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        setZeroes(new int[][]{{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});


//        System.out.println("Example I:");
//        int[][] a = new int[2][5];
//        printArray(a);
//        System.out.println("Example II:");
//        int[][] b = new int[2][];
//        printArray(b);
//        System.out.println("Example III:");
//        b[0] = new int[3];
//        b[1] = new int[5];
//        printArray(b);
    }

    /**
     * 合并区间
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) return res.toArray(new int[0][]);
        // 对起点终点进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            // 如果有重叠，循环判断哪个起点满足条件
            while (i < intervals.length - 1 && intervals[i + 1][0] <= right) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            // 将现在的区间放进res里面
            res.add(new int[]{left, right});
            // 接着判断下一个区间
            i++;
        }
        return res.toArray(new int[0][]);
    }

    /**
     * 旋转矩阵 90%
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int length = matrix.length;
        //定义一个新数组，赋值
        int[][] clone = new int[length][length];
        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix[i].length; i1++) {
                clone[i][i1] = matrix[i][i1];
            }
        }
        //根据规律更换数值
        for (int i = 0; i < clone.length; i++) {
            for (int i1 = 0; i1 < clone[i].length; i1++) {
                matrix[i1][length - 1 - i] = clone[i][i1];
            }
        }

    }

    /**
     * 零矩阵
     *
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        if (matrix == null) return;
        int length = matrix.length;
        int length1 = matrix[0].length;
        int[][] ints = new int[length][length1];
        for (int i = 0; i < ints.length; i++) {
            Arrays.fill(ints[i],1);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix[i].length; i1++) {
                if (matrix[i][i1]==0) ints[i][i1]=0;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix[i].length; i1++) {
                if (ints[i][i1]==0){
                    //将纵列set为0
                    for (int i2 = 0; i2 < matrix.length; i2++) {
                        matrix[i2][i1] = 0;
                    }
                    //将横列 set为0
                    Arrays.fill(matrix[i],0);
                }
            }
        }

    }
}