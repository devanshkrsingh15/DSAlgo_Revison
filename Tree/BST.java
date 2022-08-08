package Tree;

public class BST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

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

    public int minEle(TreeNode root) {
        int ans = root.val;
        while (root != null) {
            ans = root.val;
            root = root.left;
        }
        return ans;
    }

    public int maxEle(TreeNode root) {
        int ans = root.val;
        while (root != null) {
            ans = root.val;
            root = root.right;
        }
        return ans;
    }

    // arr is sorted

    // TC => worst/best/avg - O(n)
    public TreeNode createBST(int[] arr) {
        return createBST_(arr, 0, arr.length - 1);
    }

    public TreeNode createBST_(int[] arr, int lo, int hi) {
        if(lo>hi) return null;
        int mid = lo + (hi - lo) / 2;

        TreeNode root = new TreeNode(arr[mid]);
        root.left = createBST_(arr, lo, mid - 1);
        root.right = createBST_(arr, mid + 1, hi);

        return root;

    }

    public boolean findDataItr(TreeNode root, TreeNode tar) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr == tar)
                return true;

            if (curr.val > tar.val)
                curr = curr.left;
            else
                curr = curr.right;
        }

        return false;
    }

    public boolean findDataRec(TreeNode root, TreeNode tar) {
        if (root == null)
            return false;

        if (root == tar)
            return true;

        if (root.val > tar.val)
            return findDataRec(root.left, tar);
        else
            return findDataRec(root.right, tar);
    }

    // Leetcode 701. Insert into a Binary Search Tree
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        else
            root.right = insertIntoBST(root.right, val);

        return root;
    }

    // Leetcode 450. Delete Node in a BST
    // Worst - 2(logn)
    // Best - (logn)
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (root.val > key)
            root.left = deleteNode(root.left, key);
        else if (root.val < key)
            root.right = deleteNode(root.right, key);
        else {
            if (root.left == null || root.right == null) {
                if (root.left == null && root.right == null)
                    return null;
                else if (root.left == null)
                    return root.right;
                else
                    return root.left;
            } else {
                int lmax = maxEle(root.left);
                root.val = lmax;
                root.left = deleteNode(root.left, lmax);
            }
        }

        return root;
    }

    // Leetcode 235. Lowest Common Ancestor of a Binary Search Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        else if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        else
            return root;
    }
}
