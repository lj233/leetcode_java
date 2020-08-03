package dataStructure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

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
            Arrays.fill(ints[i], 1);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix[i].length; i1++) {
                if (matrix[i][i1] == 0) ints[i][i1] = 0;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix[i].length; i1++) {
                if (ints[i][i1] == 0) {
                    //将纵列set为0
                    for (int i2 = 0; i2 < matrix.length; i2++) {
                        matrix[i2][i1] = 0;
                    }
                    //将横列 set为0
                    Arrays.fill(matrix[i], 0);
                }
            }
        }

    }

    /**
     * 删除数组中的重复项 数组有序
     * 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * 移动零
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if (nums.length > 1) {
            int i = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] != 0) {
                    nums[i] = nums[j];
                    i++;
                }
            }
            Arrays.fill(nums, i, nums.length, 0);
        }
    }

    /*
    给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

    你可以假设数组是非空的，并且给定的数组总是存在多数元素。

     */
    public int majorityElement(int[] nums) {
        AtomicInteger k = new AtomicInteger();
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (integerIntegerHashMap.containsKey(nums[i])) {
                integerIntegerHashMap.put(nums[i], integerIntegerHashMap.get(nums[i]) + 1);
            } else integerIntegerHashMap.put(nums[i], 1);
        }

        Set<Integer> integers = integerIntegerHashMap.keySet();
        integers.forEach(integer -> {
            if (integerIntegerHashMap.get(integer) > (nums.length / 2)) {
                k.set(integer);
            }
        });
        return k.get();
    }

    /*
    合并两个有序数组
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < nums2.length; i++) {
            nums1[nums1.length - 1 - i] = nums2[i];
        }
        Arrays.sort(nums1);
    }


    public int[][] updateMatrix(int[][] matrix) {
        // 首先将 0 边上的 1 入队
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        Queue<int[]> queue = new LinkedList<>();
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x >= 0 && x < m && y >= 0 && y < n
                                && matrix[x][y] == 1 && res[x][y] == 0) {
                            // 这是在 0 边上的1。需要加上 res[x][y] == 0 的判断防止重复入队
                            res[x][y] = 1;
                            queue.offer(new int[]{x, y});
                        }
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX >= 0 && newX < m && newY >= 0 && newY < n
                        && matrix[newX][newY] == 1 && res[newX][newY] == 0) {
                    res[newX][newY] = res[x][y] + 1;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }

        return res;
    }

    /*
    钥匙和房间
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] seen = new boolean[rooms.size()];
        seen[0] = true;
        Stack<Integer> stack = new Stack();
        stack.push(0);

        //At the beginning, we have a todo list "stack" of keys to use.
        //'seen' represents at some point we have entered this room.
        while (!stack.isEmpty()) { // While we have keys...
            int node = stack.pop(); // Get the next key 'node'
            for (int nei : rooms.get(node)) // For every key in room # 'node'...
                if (!seen[nei]) { // ...that hasn't been used yet
                    seen[nei] = true; // mark that we've entered the room
                    stack.push(nei); // add the key to the todo list
                }
        }

        for (boolean v : seen)  // if any room hasn't been visited, return false
            if (!v) return false;
        return true;
    }


}