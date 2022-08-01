package Tree;
public class Tree{

    public class TreeNode{
        int val; TreeNode left; TreeNode right;

        TreeNode(){

        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right  = right;
        }

    }

    public int size(TreeNode root){
        if(root==null)  return 0;

        int ls=  size(root.left);
        int rs=  size(root.right);

        return ls+rs+1;
    }

    public int height_Nodes(TreeNode root){
        if(root==null) return 0;
        int ls=  height_Nodes(root.left);
        int rs=  height_Nodes(root.right);

        return Math.max(ls,rs)+1;
    }

    public int height_Edges(TreeNode root){
        if(root==null) return -1;
        int ls=  height_Edges(root.left);
        int rs=  height_Edges(root.right);

        return Math.max(ls,rs)+1;
    }

    public int max(TreeNode root){
        if(root==null) return Integer.MIN_VALUE;
        int ls=  max(root.left);
        int rs=  max(root.right);

        return Math.max(Math.max(ls,rs),root.val);
    }

    public int min(TreeNode root){
        if(root==null) return Integer.MAX_VALUE;
        int ls=  min(root.left);
        int rs=  min(root.right);

        return Math.min(Math.min(ls,rs),root.val);
    }

    public boolean find(TreeNode root,int tar){
        if(root==null) return false;
        boolean res = root.val==tar;

        return res || find(root.left,tar) || find(root.right,tar) ; 

    }
}