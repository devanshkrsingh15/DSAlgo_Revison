package Tree;

import java.util.*;

public class ConstructTree {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {

        }

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    public TreeNode constructFromPreIn(int[] preorder, int[] inorder) {

    }

    public TreeNode constructFromInPost(int[] inorder, int[] postorder) {

    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {

    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder_Helper(preorder, new int[1], Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public TreeNode bstFromPreorder_Helper(int[] pre, int[] ptr, int min, int max) {
        int idx = ptr[0];

        if (idx >= pre.length || pre[idx] >= max || pre[idx] <= min)
            return null;

        ptr[0]++;
        TreeNode root = new TreeNode(pre[idx]);
        root.left = bstFromPreorder_Helper(pre, ptr, min, root.val);
        root.right = bstFromPreorder_Helper(pre, ptr, root.val, max);

        return root;

    }

    public TreeNode bstFromPostorder(int[] postorder) {
        return bstFromPostorder_Helper(postorder, new int[] { postorder.length - 1 }, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
    }

    public TreeNode bstFromPostorder_Helper(int[] post, int[] ptr, int min, int max) {
        int idx = ptr[0];

        if (idx < 0 || post[idx] >= max || post[idx] <= min)
            return null;

        ptr[0]--;
        TreeNode root = new TreeNode(post[idx]);
        root.right = bstFromPostorder_Helper(post, ptr, root.val, max);
        root.left = bstFromPostorder_Helper(post, ptr, min, root.val);

        return root;

    }

}
