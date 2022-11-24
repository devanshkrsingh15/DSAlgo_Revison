import java.util.*;

public class WeeklyContest320 {

    // 2475. Number of Unequal Triplets in Array
    public int unequalTriplets(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] != nums[j] && nums[i] != nums[k] && nums[j] != nums[k])
                        ans++;
                }
            }

        }
        return ans;
    }

    // 2476. Closest Nodes Queries in a Binary Search Tree
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

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        TreeSet<Integer> set = new TreeSet<>();
        fill(set, root);
        List<List<Integer>> ans = new ArrayList<>();
        for (int tar : queries) {
            List<Integer> tmp = new ArrayList<>();
            int floor = set.floor(tar) == null ? -1 : set.floor(tar);
            int ceil = set.ceiling(tar) == null ? -1 : set.ceiling(tar);
            tmp.add(floor);
            tmp.add(ceil);
            ans.add(tmp);
        }

        return ans;
    }

    private void fill(TreeSet<Integer> set, TreeNode root) {
        if (root == null)
            return;
        set.add(root.val);
        fill(set, root.left);
        fill(set, root.right);

    }

    // 2477. Minimum Fuel Cost to Report to the Capital

    long mincost = 0;

    public long minimumFuelCost(int[][] roads, int seats) {
        if (roads.length == 0)
            return 0;
        ArrayList<Integer> graph[] = createGraph(roads, seats);

        boolean[] vis = new boolean[(int) 1e5 + 10];
        _minimumFuelCost(graph, 0, seats, vis);

        return mincost;
    }

    private long _minimumFuelCost(ArrayList<Integer>[] graph, int src, int seats, boolean[] vis) {
        long size = 1l;
        vis[src] = true;
        for (int child : graph[src]) {

            if (vis[child] == false) {
                size += _minimumFuelCost(graph, child, seats, vis);
            }
        }

        if (src != 0)
            mincost += size / seats + (size % seats == 0 ? 0 : 1);//
        return size;
    }

    private ArrayList<Integer>[] createGraph(int[][] roads, int seats) {
        int n = roads.length;
        ArrayList<Integer> graph[] = new ArrayList[(int) 1e5 + 10];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] ed : roads) {
            int u = ed[0];
            int v = ed[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        return graph;
    }

}
