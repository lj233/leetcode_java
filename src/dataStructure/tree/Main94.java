package dataStructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * @author lijian
 * @description 二叉树的中序遍历
 * @date 2020/1/8
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main94 {
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

        Solution94 solution94 = new Solution94();
        List a = solution94.inorderTraversal2(treeNode);
        for (Object o : a) {
            System.out.println(o);
        }
    }
}

class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> arrayList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            //将 左节点压入栈中
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            //弹出节点，放入List中
            cur = stack.pop();
            arrayList.add(cur.val);
            //切换到右节点直到将根节点的左子树遍历完
            cur = cur.right;
        }
        return arrayList;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        recursive(root, res);
        return res;
    }

    public void recursive(TreeNode root, List<Integer> res) {
        //递归中不能用while，
        if (root.left != null) {
            recursive(root.left, res);
        }
        res.add(root.val);
        if (root.right != null) {
            recursive(root.right, res);
        }
    }

}



