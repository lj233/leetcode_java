package tree;

import java.util.Stack;

/**
 * @author lijian
 * @description 二叉树的最大深度
 * @date 2020/1/10
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 两种方法求最大深度，自顶向上和自底向下
 */
public class Main104 {
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

        Solution104 solution104 = new Solution104();
        int i = solution104.maxDepth2(treeNode);
        System.out.println(i);



    }
}


class Solution104 {
    //自底向上
    public int maxDepth(TreeNode root) {
        if (root==null){
            return 0;
        }
        //自底向上 遍历到 底层一层一层往上加
        int left_depth = maxDepth(root.left);
        int right_depth = maxDepth(root.left);
        //比较父节点下的两个子节点+1
        return Math.max(left_depth,right_depth)+1;
    }
    //自顶向下
    public int maxDepth2(TreeNode root) {
        return recursive(0,root);
    }

    public int recursive(int x,TreeNode treeNode){
        if (treeNode==null){
            return x;
        }
        x++;
        int left_depth = recursive(x,treeNode.left);
        int right_depth = recursive(x,treeNode.right);
        return Math.max(left_depth,right_depth);
    }


    //迭代
//    public int maxDepth3(TreeNode root) {
//        if (root==null) return 0;
//        Stack<TreeNode> stack = new Stack();
//        stack.add(root);
//        int depth = 1;
//        int right = 0;
//        TreeNode curr = root.left;
//        while (!stack.isEmpty()){
//            //将
//            while (curr!=null){
//                stack.push(curr);
//                depth++;
//                curr = curr.left;
//            }
//            curr = stack.pop();
//
//        }
//        return depth;
//    }
}