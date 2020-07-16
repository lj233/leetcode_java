package dataStructure.binary_search;

/**
 * @author lijian
 * @description 猜数字大小
 * @date 2020/1/17
 */
public class Main374 {
}

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */
class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1, right = n, binary;
        while (left <= right) {
            binary = (left + right) / 2;
            int result = guess(binary);
            if (result == 0) return binary;
            if (result == -1) left = binary + 1;
            else right = binary - 1;
        }
        return -1;
    }
}


class GuessGame {

    public int guess(int num) {
        return -1;
    }
}