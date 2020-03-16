package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lijian
 * @description 二叉树的层次遍历
 * @date 2020/1/9
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main102 {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(6);

        treeNode.left = treeNode1;
        treeNode.right = treeNode4;

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode4.left = treeNode5;
        Solution102 solution102 = new Solution102();
        List<List<Integer>> lists = solution102.levelOrder(treeNode);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer);
            }
            System.out.println();
        }
    }
}

class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        if (root != null)
            treeNodes.add(root);
        recursive(lists, treeNodes);
        return lists;
    }

    public void recursive(List<List<Integer>> lists, LinkedList<TreeNode> treeNodes) {
        while (!treeNodes.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            //将本层输出，将下层记录到队列中
            int i = treeNodes.size();
            for (int j = 0; j < i; j++) {
                list.add(treeNodes.get(0).val);
                if (treeNodes.get(0).left != null) treeNodes.add(treeNodes.get(0).left);
                if (treeNodes.get(0).right != null) treeNodes.add(treeNodes.get(0).right);
                treeNodes.pop();
            }
            lists.add(list);
            recursive(lists, treeNodes);
        }
    }
}