package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author lijian
 * @description 二叉树的后序遍历
 * @date 2020/1/9
 */
public class Main145 {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(6);

        treeNode.left=treeNode1;
        treeNode.right=treeNode4;

        treeNode1.left=treeNode2;
        treeNode1.right=treeNode3;

        treeNode4.left=treeNode5;

        Solution145 solution145 = new Solution145();
        List<Integer> list = solution145.postorderTraversal(treeNode);
        for (Object o : list) {
            System.out.println(o);
        }

    }
}



class Solution145 {
    //迭代
    public List<Integer> postorderTraversal(TreeNode root) {
        /*
                从根节点开始依次迭代，弹出栈顶元素输出到输出列表中，然后依次压入它的所有孩子节点，按照从上到下、从左至右的顺序依次压入栈中。

        因为深度优先搜索后序遍历的顺序是从下到上、从左至右，所以需要将输出列表逆序输出。

        作者：LeetCode
        链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/er-cha-shu-de-hou-xu-bian-li-by-leetcode/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
        //没有办法既保留根节点值，在确定左右节点遍历完后放入队列，所以只能倒序放
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            //弹出栈中最后一个元素并删除
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
    }


    //递归
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        recursive(res, root);
        return res;
    }

    public void recursive(List<Integer> res, TreeNode root) {
        //后序遍历，左右根
        if (root != null) {
            if (root.left != null) {
                recursive(res, root.left);
            }
            //没有左子节点，判断右子节点
            if (root.right != null) {
                recursive(res, root.right);
            }
            //没有左右子节点了，增加根节点
            res.add(root.val);
        }

    }
}