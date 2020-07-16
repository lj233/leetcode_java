package dataStructure.tree;


/**
 * @author lijian
 * @description Search in a Binary Search Tree
 * @date 2020/3/17
 */
public class MainSearch {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(4);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(5);


        treeNode.left = treeNode1;
        treeNode.right = treeNode3;
        treeNode1.left = treeNode2;
        TreeNode treeNode4 = new MainSearch().searchBST(treeNode, 2);
        System.out.println(treeNode4.val);

    }
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null ) return null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == val) return cur;
            else if (cur.val > val) cur = cur.left;
            else cur = cur.right;
        }
        return cur;
    }
}
