package dataStructure.array;

/**
 * @author lijian
 * @description 加一
 * @date 2019/12/30
 * <p>
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 */
public class Main66 {
    public static void main(String[] args) {

    }
}

class Solution66 {


    public int[] plusOne(int[] digits) {
        int length = digits.length - 1;
        if (digits[length] == 9)
            return process9(digits, length);
        else {
            digits[length] += 1;
            return digits;
        }
    }

    private int[] process9(int[] digits, int i) {
        digits[i] = 0;
        if (i == 0) {
            int[] bak = new int[digits.length + 1];
            bak[0] = 1;
            bak[1] = 0;
            for (int i1 = bak.length - 1; i1 > 1; i1--) {
                bak[i1] = digits[i1 - 1];
            }
            return bak;
        }
        if (digits[i - 1] == 9) {
            return process9(digits, i - 1);
        } else digits[i - 1] += 1;
        return digits;
    }


}