package Tree;

import java.util.*;
import Tree.Tree.TreeNode;

public class MorrisTraversal {

    // TC - each node is visited 3 or 4 times -> Worst - O(4n) , O(3n)
    // Avg - O(n)
    // SC - O(1)
    public ArrayList<TreeNode> preOrder(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();

        TreeNode curr = root;

        while (curr != null) {
            TreeNode next = curr.left;

            if (next == null) {
                list.add(curr);
                curr = curr.right;
            } else {
                TreeNode rmost = getRightMost(curr, next);
                if (rmost.right == null) {
                    rmost.right = curr;
                    list.add(curr);
                    curr = curr.left;
                } else {
                    rmost.right = null;
                    curr = curr.right;

                }
            }
        }

        return list;
    }

    public ArrayList<TreeNode> inOrder(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();

        TreeNode curr = root;

        while (curr != null) {
            TreeNode next = curr.left;

            if (next == null) {
                list.add(curr);
                curr = curr.right;
            } else {
                TreeNode rmost = getRightMost(curr, next);
                if (rmost.right == null) {
                    rmost.right = curr;
                    curr = curr.left;
                } else {
                    rmost.right = null;
                    list.add(curr);
                    curr = curr.right;

                }
            }
        }

        return list;
    }

    //Jugaad  =>  not  given  by morris
    public ArrayList<TreeNode> postOrder(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();

        TreeNode curr = root;

        while (curr != null) {
            TreeNode next = curr.right;

            if (next == null) {
                list.add(curr);
                curr = curr.left;
            } else {
                TreeNode rmost = getLeftMost(curr, next);
                if (rmost.left == null) {
                    rmost.left = curr;
                    curr = curr.right;
                } else {
                    rmost.left = null;
                    list.add(curr);
                    curr = curr.right;

                }
            }
        }
        Collections.reverse(list);
        return list;
    }

    public TreeNode getLeftMost(TreeNode root, TreeNode curr) {
        while (curr.left != null && curr.left != root)
        curr = curr.left;
    return curr;
    }

    public TreeNode getRightMost(TreeNode root, TreeNode curr) {
        while (curr.right != null && curr.right != root)
            curr = curr.right;
        return curr;
    }
}
