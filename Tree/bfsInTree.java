package Tree;

import java.util.*;
import Tree.Tree.TreeNode;

public class bfsInTree {

    public ArrayList<ArrayList<TreeNode>> LevelOrderTraversal(TreeNode root) {
        ArrayList<ArrayList<TreeNode>> ans = new ArrayList<>();

        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        while (q.size() != 0) {
            int s = q.size();
            ArrayList<TreeNode> tmp = new ArrayList<>();
            while (s-- > 0) {
                TreeNode rn = q.remove();
                tmp.add(rn);
                if (rn.left != null)
                    q.add(rn.left);
                if (rn.right != null)
                    q.add(rn.right);
            }

            ans.add(tmp);
        }

        return ans;
    }

    public class Vpair {
        TreeNode node;
        int vidx;
        int lidx;

        Vpair(TreeNode node, int vidx, int lidx) {
            this.node = node;
            this.vidx = vidx;
            this.lidx = lidx;
        }
    }

    public ArrayList<ArrayList<TreeNode>> VerticalOrderTraversal_1(TreeNode root) {
        ArrayList<ArrayList<TreeNode>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        HashMap<Integer, ArrayList<TreeNode>> hm = new HashMap<>();
        Queue<Vpair> q = new ArrayDeque<>();
        q.add(new Vpair(root, 0, 0));
        int min = (int) 1e9;
        int max = -(int) 1e9;

        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                Vpair rv = q.remove();
                min = Math.min(min, rv.vidx);
                max = Math.max(max, rv.vidx);

                hm.putIfAbsent(rv.vidx, new ArrayList<>());
                hm.get(rv.vidx).add(rv.node);

                if (rv.node.left != null)
                    q.add(new Vpair(rv.node.left, rv.vidx - 1, rv.lidx + 1));
                if (rv.node.right != null)
                    q.add(new Vpair(rv.node.right, rv.vidx + 1, rv.lidx + 1));
            }
        }

        for (int i = min; i <= max; i++) {
            ans.add(hm.get(i));
        }

        return ans;
    }

    public ArrayList<ArrayList<TreeNode>> VerticalOrderTraversal_2(TreeNode root) {
        ArrayList<ArrayList<TreeNode>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        
        Queue<Vpair> q = new ArrayDeque<>();
      
        int[] arr = new int[] { (int) 1e9, -(int) 1e9 }; // {min,max};
        findMinMaxValues(root, 0, arr);

        for(int i = arr[0];i<=arr[1];i++) ans.add(new ArrayList<>());

        q.add(new Vpair(root, -arr[0], 0));
        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                Vpair rv = q.remove();

                ans.get(rv.vidx).add(rv.node);
            
                if (rv.node.left != null)
                    q.add(new Vpair(rv.node.left, rv.vidx - 1, rv.lidx + 1));
                if (rv.node.right != null)
                    q.add(new Vpair(rv.node.right, rv.vidx + 1, rv.lidx + 1));
            }
        }

       

        return ans;
    }

    public void findMinMaxValues(TreeNode root, int vidx, int[] arr) {
        if (root == null)
            return;
        arr[0] = Math.min(arr[0], vidx);
        arr[1] = Math.max(arr[1], vidx);

        findMinMaxValues(root.left, vidx - 1, arr);
        findMinMaxValues(root.right, vidx + 1, arr);
    }

    public ArrayList<ArrayList<TreeNode>> LeftOrderDiagonalOrderTraversal(TreeNode root){
        ArrayList<ArrayList<TreeNode>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        HashMap<Integer, ArrayList<TreeNode>> hm = new HashMap<>();
        Queue<Vpair> q = new ArrayDeque<>();
        q.add(new Vpair(root, 0, 0));
        int min = (int) 1e9;
        int max = -(int) 1e9;

        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                Vpair rv = q.remove();
                min = Math.min(min, rv.vidx);
                max = Math.max(max, rv.vidx);

                hm.putIfAbsent(rv.vidx, new ArrayList<>());
                hm.get(rv.vidx).add(rv.node);

                if (rv.node.left != null)
                    q.add(new Vpair(rv.node.left, rv.vidx - 1, rv.lidx + 1));
                if (rv.node.right != null)
                    q.add(new Vpair(rv.node.right, rv.vidx, rv.lidx + 1));
            }
        }

        for (int i = min; i <= max; i++) {
            ans.add(hm.get(i));
        }

        return ans;
    }

    public ArrayList<ArrayList<TreeNode>> RightOrderDiagonalOrderTraversal(TreeNode root){
        ArrayList<ArrayList<TreeNode>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        HashMap<Integer, ArrayList<TreeNode>> hm = new HashMap<>();
        Queue<Vpair> q = new ArrayDeque<>();
        q.add(new Vpair(root, 0, 0));
        int min = (int) 1e9;
        int max = -(int) 1e9;

        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                Vpair rv = q.remove();
                min = Math.min(min, rv.vidx);
                max = Math.max(max, rv.vidx);

                hm.putIfAbsent(rv.vidx, new ArrayList<>());
                hm.get(rv.vidx).add(rv.node);

                if (rv.node.left != null)
                    q.add(new Vpair(rv.node.left, rv.vidx, rv.lidx + 1));
                if (rv.node.right != null)
                    q.add(new Vpair(rv.node.right, rv.vidx+1, rv.lidx + 1));
            }
        }

        for (int i = min; i <= max; i++) {
            ans.add(hm.get(i));
        }

        return ans;
    }
}
