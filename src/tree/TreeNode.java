package tree;

/**
 * @author lijian
 * @description 树
 * @date 2020/1/8
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    static {
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
    }
}
