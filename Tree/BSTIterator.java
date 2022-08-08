package Tree;
import java.util.*;
import Tree.Tree.TreeNode;

public class BSTIterator {

    // iterative inorder
    public Stack<TreeNode>st;
    public BSTIterator(TreeNode root) {
        st= new Stack<>();
        pushAllLeft(root);
    }

    public void pushAllLeft(TreeNode  root){
        while(root!=null){
            st.push(root);
            root =  root.left;
        }
    }
    
    public int next() {
        TreeNode rv = st.pop();
        pushAllLeft(rv.right);
        return rv.val;

    }
    
    public boolean hasNext() {
        return st.size()!=0;
    }
}
