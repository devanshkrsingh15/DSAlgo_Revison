package Tree;
import java.util.*;
public class Tree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {

        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    public int size(TreeNode root) {
        if (root == null)
            return 0;

        int ls = size(root.left);
        int rs = size(root.right);

        return ls + rs + 1;
    }

    public int height_Nodes(TreeNode root) {
        if (root == null)
            return 0;
        int ls = height_Nodes(root.left);
        int rs = height_Nodes(root.right);

        return Math.max(ls, rs) + 1;
    }

    public int height_Edges(TreeNode root) {
        if (root == null)
            return -1;
        int ls = height_Edges(root.left);
        int rs = height_Edges(root.right);

        return Math.max(ls, rs) + 1;
    }

    public int max(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;
        int ls = max(root.left);
        int rs = max(root.right);

        return Math.max(Math.max(ls, rs), root.val);
    }

    public int min(TreeNode root) {
        if (root == null)
            return Integer.MAX_VALUE;
        int ls = min(root.left);
        int rs = min(root.right);

        return Math.min(Math.min(ls, rs), root.val);
    }

    public boolean find(TreeNode root, int tar) {
        if (root == null)
            return false;
        boolean res = root.val == tar;

        return res || find(root.left, tar) || find(root.right, tar);

    }

    // if we take two nodes, and if they are stretched, the dist between them will
    // act as a diameter of a circle covering all nodes
    // this distance will be the diameter of a binary tree
    // in-terms of edges
    public int diameter(TreeNode root) {
        if (root == null)
            return 0;

        int[] arr = diameterHelper(root);// {ht,dia}
        return arr[1];
    }

    public int[] diameterHelper(TreeNode root) {
        if (root == null)
            return new int[] { -1, 0 };

        int[] larr = diameterHelper(root.left);
        int[] rarr = diameterHelper(root.right);

        int myHt = Math.max(larr[0], rarr[0]) + 1;
        int myDia = Math.max(larr[1], Math.max(rarr[1], larr[0] + rarr[0] + 2));

        return new int[] { myHt, myDia };
    }

}