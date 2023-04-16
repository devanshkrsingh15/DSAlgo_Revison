import java.util.*;

public class BiweeklyContest102 {
    // 2639. Find the Width of Columns of a Grid
    public int[] findColumnWidth(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int len = findLen(grid[i][j]);
                map.put(j, Math.max(map.getOrDefault(j, 0), len));
            }
        }

        int[] ans = new int[map.keySet().size()];
        for (int i : map.keySet()) {
            ans[i] = map.get(i);
        }
        return ans;
    }

    public int findLen(int ele) {
        if (ele == 0)
            return 1;
        int ans = 0;
        if (ele < 0) {
            ele = Math.abs(ele);
            ans++;
        }

        while (ele != 0) {
            ele /= 10;
            ans++;
        }

        return ans;
    }

    // 2640. Find the Score of All Prefixes of an Array
    public long[] findPrefixScore(int[] nums) {
        int n = nums.length;
        int[] pmax = getPrefixMax(nums);
        long[] converArray = new long[n];
        for (int i = 0; i < n; i++) {
            converArray[i] = (long) nums[i] + (long) pmax[i];
        }

        for (int i = 1; i < n; i++) {
            converArray[i] += converArray[i - 1];
        }

        return converArray;
    }

    public int[] getPrefixMax(int[] arr) {
        int n = arr.length;
        int[] pmax = new int[n];
        pmax[0] = arr[0];
        for (int i = 1; i < n; i++)
            pmax[i] = Math.max(pmax[i - 1], arr[i]);

        return pmax;
    }

    // 2641. Cousins in Binary Tree II
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public class Pair {
        TreeNode currNode;
        TreeNode parNode;

        Pair(TreeNode currNode, TreeNode parNode) {
            this.currNode = currNode;
            this.parNode = parNode;
        }
    }

    public TreeNode replaceValueInTree(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int level = 0;

        while (q.size() != 0) {
            int s = q.size();
            int sof = 0;
            while (s-- > 0) {
                TreeNode rn = q.remove();
                sof += rn.val;
                if (rn.left != null)
                    q.add(rn.left);
                if (rn.right != null)
                    q.add(rn.right);
            }

            map.put(level, sof);
            level++;
        }

        return helper(new Pair(root, null), 0, map);

    }

    public TreeNode helper(Pair root, int level, HashMap<Integer, Integer> map) {
        if (root.currNode == null)
            return null;

        int myVal = map.get(level);
        if (root.parNode == null)
            myVal = 0;
        else {
            myVal -= (root.parNode.left != null) ? root.parNode.left.val : 0;
            myVal -= (root.parNode.right != null) ? root.parNode.right.val : 0;
        }

        TreeNode ans = new TreeNode(myVal);
        ans.left = helper(new Pair(root.currNode.left, root.currNode), level + 1, map);
        ans.right = helper(new Pair(root.currNode.right, root.currNode), level + 1, map);

        return ans;
    }

}

// 2642. Design Graph With Shortest Path Calculator
class Graph {
    class Edge{
        int v;
        int w;
        Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }
    
    ArrayList<Edge>graph[];
    public Graph(int n, int[][] edges) {
        graph = new ArrayList[n];
        for(int i = 0 ;i<n;i++) graph[i] = new ArrayList<>();
        
        for(int[]ed:edges){
            this.addEdge(ed);
        }
    }
    
    public void addEdge(int[] edge) {
        int u = edge[0];
        int v = edge[1];
        int w = edge[2]; 
        graph[u].add(new Edge(v,w));
    }
    class Dpair{
        int u;
        int cost;
        Dpair(int u,int cost){
            this.u = u;
            this.cost = cost;
        }
    }
    public int shortestPath(int node1, int node2) {
        PriorityQueue<Dpair>q = new PriorityQueue<>((a,b)->{
            return a.cost - b.cost;
        });
        boolean[]vis =new boolean[graph.length];
        
        q.add(new Dpair(node1,0));
        while(q.size()!=0){
            int s = q.size();
            while(s-->0){
                Dpair dp = q.remove();
                if(dp.u==node2) return dp.cost;
                
                if(vis[dp.u]) continue;
                vis[dp.u] = true;
                
                for(Edge ed : graph[dp.u]){
                    if(!vis[ed.v]){
                        q.add(new Dpair(ed.v,dp.cost+ed.w));
                    }
                }
            }
        }
        
        return -1;
    }
}