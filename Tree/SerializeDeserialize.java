package Tree;
import Tree.Tree.TreeNode;
import java.util.*;


public class SerializeDeserialize {

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

      // Encodes a tree to a single string.
      public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root,sb);
        return  sb.toString();
    }

    public void serializeHelper(TreeNode root,StringBuilder sb){
      if(root==null){
        sb.append("NULL");
        sb.append("#");
        return;
      }
      sb.append(root.val+"");
      sb.append("#");
      serializeHelper(root.left,sb);
      serializeHelper(root.right,sb);

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[]arr= data.split("#");

        return deserializeHelper(arr,new int[1]);
    }

    public TreeNode deserializeHelper(String[]arr,int[]idx){
      int i  = idx[0];
      if(i>=arr.length) return null;
       idx[0]++;
      String str = arr[i];
      if(str.equals("NULL"))return null;

      TreeNode root = new TreeNode(Integer.parseInt(str));

      root.left = deserializeHelper(arr,idx);
      root.right = deserializeHelper(arr,idx);

      return root;

    }
    
}
