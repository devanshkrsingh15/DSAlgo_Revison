package Tree;

import LinkedList.*;
import Tree.Tree.TreeNode;

public class VerticalSumShadow {

    public DLListNode VerticalSum(TreeNode root) {
        if (root == null)
            return new DLListNode();

        DLListNode node = new DLListNode();
        VerticalSumHelper(root, node);

        DLListNode ptr = node;
        DLListNode head = ptr;
        while (ptr != null) {
            head = ptr;
            ptr = ptr.prev;
        }

        return head; //print the list from  left to right to get vsum , with each sum  holding there own axis's sum
    }

    public void VerticalSumHelper(TreeNode root, DLListNode node) {
        if(root==null) return;

        node.val += root.val;
        if(root.left!=null){
            if(node.prev==null) node.AddLeft(node, 0);
            VerticalSumHelper(root.left,node.prev);
        }

        if(root.right!=null){
            if(node.next==null) node.AddRight(node, 0);
            VerticalSumHelper(root.right,node.next);
        }
        
    }
}
