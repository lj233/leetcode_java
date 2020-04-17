package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lijian
 * @description 验证二叉搜索树
 * 二叉搜索树是一个中序递增的排序树，左小于根，右大于根
 * @date 2020/3/16
 */
public class Main98 {
    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (! helper(node.right, val, upper)) return false;
        if (! helper(node.left, lower, val)) return false;
        return true;
    }

    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

}
