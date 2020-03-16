package QueueAndStack.test;

import java.math.BigInteger;

/**
 * @author lijian
 * @description
 * @date 2019/12/20
 */
public class Tellhow {
    static int sum = 33;
    static int[] array = {1, 3, 10, 20, 13, 55, 6, 78};

    public static void main(String[] args) {
        Integer a = 100, b = 100, c = 150, d = 150;
        //150超过了128 由valueOf 变为new Integer
        System.out.println(a == b);
        System.out.println(c == d);

        BigInteger bigInteger = new BigInteger("1");
        BigInteger bigInteger2 = new BigInteger("2");
        BigInteger bigInteger3 = new BigInteger("3");
        BigInteger bigInteger4 = new BigInteger("0");

        bigInteger4.add(bigInteger);
        bigInteger4.add(bigInteger2);
        bigInteger4.add(bigInteger3);
        // 0加任何数都等于零
        System.out.println(bigInteger4.toString());


        test(array, sum);
    }


    public static void test(int[] array, int sum) {
        int tempSum = 0;
        for (int i = 0; i < array.length; i++) {
            tempSum += array[i];
            if (tempSum > sum) {
                vail(tempSum, 0, i);
            }
        }
    }

    public static boolean vail(int tempSum, int j, int i) {
        if (tempSum == sum) {
            System.out.println(j + "        " + i);
            return true;
        }
        while (j < i) {
            return vail(tempSum - array[j], j + 1, i);
        }
        return false;
    }
}
