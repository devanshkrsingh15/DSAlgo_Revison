package Tree;
import java.util.*;

import Tree.Tree.TreeNode;

public class questions {
    
    public  ArrayList<TreeNode> NodeToRootPath(TreeNode root,TreeNode tar){
        if(root==null) return new ArrayList<TreeNode>();

        if(root.val==tar.val){
            ArrayList<TreeNode>base= new  ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<TreeNode>left_list= NodeToRootPath(root.left,tar);
        if(left_list.size()!=0){
            left_list.add(root);
            return left_list;
        }
        ArrayList<TreeNode>right_list= NodeToRootPath(root.right,tar);
        if(right_list.size()!=0){
            right_list.add(root);
            return right_list;
        }

        return new ArrayList<TreeNode>();
    }
    
    //Leetcode  236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode>lft = NodeToRootPath(root,p);
        ArrayList<TreeNode>rgt = NodeToRootPath(root,q);

        int  i= lft.size()-1;  int  j= rgt.size()-1;
        while(i>=0 &&  j>=0 && lft.get(i) ==  rgt.get(j)){
            i--;  j--;
        }

        return  lft.get(i+1);

    }

    //Leetcode  863
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> rootToNdePath = NodeToRootPath(root,target);

        ArrayList<Integer>ans  = new  ArrayList<>();
        
        TreeNode blocker = null;
        for(int i= 0;i<rootToNdePath.size();i++){
            fillAns(rootToNdePath.get(i),k,blocker,ans);
            blocker = rootToNdePath.get(i);
            k--;
        }

        return ans;

    }

    public  void fillAns(TreeNode root,int k,TreeNode blocker,ArrayList<Integer>ans){
        if(root==null || k<=0  ||  root==blocker) return;

        if(k==0)ans.add(root.val);
        fillAns(root.left,k-1,blocker,ans);
        fillAns(root.right,k-1,blocker,ans);
    }

    //Burn the binary tree starting from the target node (GFG)
    public  ArrayList<ArrayList<TreeNode>> BurningTree(TreeNode root,TreeNode burnNode){
        ArrayList<ArrayList<TreeNode>>list = new ArrayList<>();

        ArrayList<TreeNode>path =  NodeToRootPath(root,burnNode);
        
        TreeNode block=  null;
        for(int i=0;i<path.size();i++){
            int time = i;
            fillBurningNode(path.get(i),time,block,list);
            block= path.get(i);

        }

        //min time to  burn whole tree will be list.size()-1,( -1  because one node is already  burning)
        return list;
    }

    public void fillBurningNode(TreeNode root,int t, TreeNode block,ArrayList<ArrayList<TreeNode>>list){
        if(root==null ||  root==block) return;

        if(t==list.size())  list.add(new ArrayList<>());
        list.get(t).add(root);
        fillBurningNode(root.left,t+1,block,list);
        fillBurningNode(root.right,t+1,block,list);
    }
}
