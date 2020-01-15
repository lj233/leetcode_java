package hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lijian
 * @description 快乐树
 * @date 2020/1/15
 */
public class Main202 {
}

class Solution202 {

    public boolean isHappy(int n) {
        //利用set判断死循环，是否得到重复的值
        Set<Integer> set = new HashSet<>();
        set.add(n);
        while (n != 1) {
            n = change(n);
            if (set.contains(n)) return false;
            set.add(n);
        }
        return true;
    }

    public int change(int n) {
        int sum = 0;
        int num;
        while (n != 0) {
            num = n % 10;
            n /= 10;
            sum += num * num;
        }
        return sum;
    }


}