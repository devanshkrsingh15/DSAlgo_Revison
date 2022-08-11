package Tree;

import java.util.*;

public class ConstructTree {

    //TC - O(nlogn) worst -  O(n^2)
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
        int[] indices = new int[4]; // {pre_st,pre_en,in_st,in_en}
        indices[0] = 0;
        indices[1] = preorder.length - 1;
        indices[2] = 0;
        indices[3] = inorder.length;
        return constructFromPreIn_Helper(preorder, inorder, indices);
    }

    private TreeNode constructFromPreIn_Helper(int[] preorder, int[] inorder, int[] indices) {
        int pre_st = indices[0];
        int pre_en = indices[1];
        int in_st = indices[2];
        int in_en = indices[3];

        if (pre_st > pre_en)
            return null;
        if (pre_st == pre_en)
            return new TreeNode(preorder[pre_st]);

        TreeNode root = new TreeNode(preorder[pre_st]);
        // find root.val in inorder
        int ptr = in_st;

        while (inorder[ptr] != preorder[pre_st])
            ptr++;
        int te = ptr - in_st;

        indices[0] = pre_st + 1;
        indices[1] = pre_st + te;
        indices[2] = in_st;
        indices[3] = ptr - 1;
        root.left = constructFromPreIn_Helper(preorder, inorder, indices);

        indices[0] = pre_st + te + 1;
        indices[1] = pre_en;
        indices[2] = ptr + 1;
        indices[3] = in_en;
        root.right = constructFromPreIn_Helper(preorder, inorder, indices);

        return root;

    }

    public TreeNode constructFromInPost(int[] inorder, int[] postorder) {
        int[] indices = new int[4]; // {pre_st,post_en,in_st,in_en}
        indices[0] = 0;
        indices[1] = postorder.length - 1;
        indices[2] = 0;
        indices[3] = inorder.length - 1;
        return constructFromPostIn_Helper(postorder, inorder, indices);
    }

    private TreeNode constructFromPostIn_Helper(int[] postorder, int[] inorder, int[] indices) {
        int post_st = indices[0];
        int post_en = indices[1];
        int in_st = indices[2];
        int in_en = indices[3];

        if (post_st > post_en)
            return null;
        if (post_st == post_en)
            return new TreeNode(postorder[post_en]);

        TreeNode root = new TreeNode(postorder[post_en]);
        // find root.val in inorder
        int ptr = in_st;

        while (inorder[ptr] != postorder[post_en])
            ptr++;
        int te = ptr - in_st;

        indices[0] = post_st;
        indices[1] = post_st + te - 1;
        indices[2] = in_st;
        indices[3] = ptr - 1;
        root.left = constructFromPostIn_Helper(postorder, inorder, indices);

        indices[0] = post_st + te;
        indices[1] = post_en - 1;
        indices[2] = ptr + 1;
        indices[3] = in_en;
        root.right = constructFromPostIn_Helper(postorder, inorder, indices);

        return root;
    }

    // for pre and post must be complete
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int[] indices = new int[4]; // {pre_st,pre_en,post_st,post_en}
        indices[0] = 0;
        indices[1] = preorder.length - 1;
        indices[2] = 0;
        indices[3] = postorder.length - 1;
        return constructFromPostPost_Helper(preorder, postorder, indices);
    }

    private TreeNode constructFromPostPost_Helper(int[] preorder, int[] postorder, int[] indices) {
        int pre_st = indices[0];
        int pre_en = indices[1];
        int post_st = indices[2];
        int post_en = indices[3];
        // System.out.println(pre_st+ "-"+ pre_en + " , " + post_st+ "-"+ post_en );
        if (pre_st > pre_en)
            return null;
        if (pre_st == pre_en)
            return new TreeNode(preorder[pre_st]);
        // pre[pre_st+1] will be last element of left subtree in postorder

        int ptr = post_st;
        while (postorder[ptr] != preorder[pre_st + 1])
            ptr++;
        int te = ptr - post_st + 1;

        TreeNode root = new TreeNode(preorder[pre_st]);

        indices[0] = pre_st + 1;
        indices[1] = pre_st + te;
        indices[2] = post_st;
        indices[3] = ptr;
        root.left = constructFromPostPost_Helper(preorder, postorder, indices);

        indices[0] = pre_st + te + 1;
        indices[1] = pre_en;
        indices[2] = ptr + 1;
        indices[3] = post_en - 1;
        root.right = constructFromPostPost_Helper(preorder, postorder, indices);

        return root;

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
