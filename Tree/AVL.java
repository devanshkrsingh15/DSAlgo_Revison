package Tree;

public class AVL {
    // -1<=(lh - rh)<=1 Balancing Factor

    /*
     * BL
     * 2,1 => ll
     * 2,-1 => lr
     * -2,1 => rl
     * -2,-1 => rr
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }
    }

    public int getHeight(TreeNode root) {
        if (root == null)
            return -1;

        int lh = getHeight(root.left);
        int rh = getHeight(root.right);

        return Math.max(lh, rh) + 1;
    }

    public int getBal(TreeNode root) {
        if (root == null)
            return 0;
        int lh = getHeight(root.left);
        int rh = getHeight(root.right);

        return lh - rh;
    }

    public TreeNode getRotations(TreeNode root) {
        if (root == null)
            return null;

        if (getBal(root) >= 2) {
            if (getBal(root.left) >= 1) {
                return rightRotations(root);
            } else {
                root.left = leftRotations(root.left);
                return rightRotations(root);
            }
        } else if (getBal(root) <= -2) {
            if (getBal(root.right) <= -1) {
                return leftRotations(root);
            } else {
                root.right = rightRotations(root.right);
                return leftRotations(root);
            }
        }
        return root;
    }

    public TreeNode rightRotations(TreeNode A) {
        if (A == null)
            return A;

        TreeNode B = A.left;
        TreeNode Br = B.right;

        B.right = A;
        A.left = Br;
        B.right = getRotations(A);
        return getRotations(B);
    }

    public TreeNode leftRotations(TreeNode A) {
        if (A == null)
            return A;

        TreeNode B = A.right;
        TreeNode Bl = B.left;

        B.left = A;
        A.right = Bl;
        B.left = getRotations(A);
        return getRotations(B);
    }

    public TreeNode insertIntoAVL(TreeNode root, int v) {
        if (root == null)
            return new TreeNode(v);

        if (root.val > v)
            root.left = insertIntoAVL(root.left, v);
        else if (root.val < v)
            root.right = insertIntoAVL(root.right, v);

        return root;
    }

    public TreeNode deleteFromAVL(TreeNode root, int v) {
        if (root == null)
            return new TreeNode(v);

        if (root.val > v)
            root.left = deleteFromAVL(root.left, v);
        else if (root.val < v)
            root.right = deleteFromAVL(root.right, v);
        else {

            if (root.left == null && root.right == null)
                return null;
            else if (root.left == null || root.right == null)
                return (root.left != null) ? root.left : root.right;
            else {
                int lmax = getMax(root.left);
                root.val = lmax;
                root.left = deleteFromAVL(root.left, lmax);
            }

        }

        return root;
    }

    public int getMax(TreeNode root) {
        int ans = root.val;

        while (root != null) {
            ans = root.val;
            root = root.right;
        }

        return ans;
    }

}
