package Tree;

import Tree.Tree.TreeNode;

public class SuccPred {

    /*
     * TC - O(N)
     * SC - O(1) + Recursive
     */

    public TreeNode[] BT_InorderSuccPred(TreeNode root, int tar) {
        TreeNode[] temp = new TreeNode[3]; // {prev,pred,succ}
        BT_InorderSuccPred_Helper(root, tar, temp);
        return new TreeNode[] { temp[1], temp[2] }; // {pred,succ}
    }

    public TreeNode[] BT_InorderCeilFloor(TreeNode root, int tar) {
        TreeNode[] temp = new TreeNode[2]; // {floor,ceil}

        BT_InorderCeilFloor_Helper(root, tar, temp);
        return new TreeNode[] { temp[0], temp[0] }; // {floor,ceil}
    }

    public void BT_InorderCeilFloor_Helper(TreeNode root, int tar, TreeNode[] temp) {
        if (root == null)
            return;

        BT_InorderCeilFloor_Helper(root.left, tar, temp);
        if (root.val < tar) {
            if (temp[0] == null)
                temp[0] = root;
            else {
                if (temp[0].val < root.val)
                    temp[0] = root;
            }

        }

        if (root.val > tar) {
            if (temp[1] == null)
                temp[0] = root;
            else {
                if (temp[0].val > root.val)
                    temp[0] = root;
            }

        }

        BT_InorderCeilFloor_Helper(root.right, tar, temp);
    }

    public void BT_InorderSuccPred_Helper(TreeNode root, int tar, TreeNode[] temp) {
        if (root == null)
            return;

        BT_InorderSuccPred_Helper(root.left, tar, temp);

        if (root.val == tar && temp[1] == null) {
            temp[1] = temp[0]; // pred
        }
        if (temp[0] != null && root.val == temp[0].val && temp[2] == null) {
            temp[2] = root; // succ
        }

        temp[0] = root;
        BT_InorderSuccPred_Helper(root.right, tar, temp);
    }

    /*
     * TC - O(logN)
     * SC - O(1) + Recursive
     */
    public TreeNode[] BST_InorderSuccPred(TreeNode root, int tar) {
        // For BST
        // succ -> ceil
        // pred -> floor

        TreeNode curr = root;
        TreeNode pred = null;
        TreeNode succ = null;

        while (curr != null) {

            if (curr.val > tar) {
                succ = curr;
                curr = curr.left;
            } else if (curr.val < tar) {
                pred = curr;
                curr = curr.right;
            } else {
                if (curr.left != null) {
                    pred = curr.left;
                    while (pred.right != null) {
                        pred = pred.right;
                    }
                }

                if (curr.right != null) {
                    succ = curr.right;
                    while (succ.left != null) {
                        succ = pred.left;
                    }
                }

                break;

            }

        }

        return new TreeNode[] { pred, succ };

    }

}

// in BST if the node parent(if it exists) is the successor then
// node.parent.left= node
