package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


/**
 * @author lijian
 * @description 二叉树的前序遍历
 * @date 2020/1/8
 * 给定一个二叉树，返回它的 前序 遍历。
 * <p>
 *  示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main144 {
    static TreeNode treeNode;

    public static void main(String[] args) {
        treeNode = new TreeNode(1);
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

        Solution144 solution144 = new Solution144();
        List a = solution144.preorderTraversal2(treeNode);
        for (Object o : a) {
            System.out.println(o);
        }
    }
}


class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recursive(list, root);
        return list;
    }

    public void recursive(List<Integer> list, TreeNode root) {
        //前序遍历，根左右
        if (root != null) {
            list.add(root.val);
            if (root.left != null) {
                recursive(list, root.left);
            }
            if (root.right != null) {
                recursive(list, root.right);
            }
        }

    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                list.add(curr.val);
                curr = curr.left;
            }

            curr = stack.pop();
            curr = curr.right;
        }
        return list;
    }
}

