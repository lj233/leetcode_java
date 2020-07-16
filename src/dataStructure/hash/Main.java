package dataStructure.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lijian
 * @description hash集的用法
 * @date 2020/1/15
 */
public class Main {
    public static void main(String[] args) {
        // 1. initialize the dataStructure.hash set
        Set<Integer> hashSet = new HashSet<>();
        // 2. add a new key
        hashSet.add(3);
        hashSet.add(2);
        hashSet.add(1);
        // 3. remove the key
        hashSet.remove(2);
        // 4. check if the key is in the dataStructure.hash set
        if (!hashSet.contains(2)) {
            System.out.println("Key 2 is not in the dataStructure.hash set.");
        }
        // 5. get the size of the dataStructure.hash set
        System.out.println("The size of has set is: " + hashSet.size());
        // 6. iterate the dataStructure.hash set
        for (Integer i : hashSet) {
            System.out.print(i + " ");
        }
        System.out.println("are in the dataStructure.hash set.");
        // 7. clear the dataStructure.hash set
        hashSet.clear();
        // 8. check if the dataStructure.hash set is empty
        if (hashSet.isEmpty()) {
            System.out.println("dataStructure.hash set is empty now!");
        }
    }
}
