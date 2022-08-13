package Tree;

import java.util.ArrayList;
import java.util.LinkedList;

import Tree.Tree.TreeNode;

public class IterativeTreeTraversal {

    public class Pair{
        TreeNode  node;
        boolean  leftDone; boolean rightDone;  boolean selfDone;

        Pair(TreeNode node,boolean  leftDone, boolean rightDone,  boolean selfDone){
            this.node  = node;
            this.leftDone  = leftDone;
            this.selfDone  = selfDone;
            this.rightDone  = rightDone;
        }
    }


    public void  PreInPostTraversals(TreeNode root){
        if(root==null) return;
        ArrayList<TreeNode>pre =  new ArrayList<>();
        ArrayList<TreeNode>post =  new ArrayList<>();
        ArrayList<TreeNode>in =  new ArrayList<>();

        LinkedList<Pair>stack= new LinkedList<>();
        stack.addFirst(new  Pair(root,false,false,false));

        while(stack.size()!=0){
            Pair rp= stack.getFirst();

            if(!rp.leftDone){
                rp.leftDone =  true;
                pre.add(rp.node);
                if(rp.node.left!=null) stack.addFirst(new  Pair(rp.node.left,false,false,false));

            }else if(!rp.selfDone){
                rp.selfDone =  true;
                in.add(rp.node);

            }else if(!rp.rightDone){
                rp.rightDone =  true;
               
                if(rp.node.right!=null) stack.addFirst(new  Pair(rp.node.right,false,false,false));

            }else{
                post.add(rp.node);
                stack.removeFirst();
            }
        
        }

    }
    
}
