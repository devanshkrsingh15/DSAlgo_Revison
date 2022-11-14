import java.util.*;

public class BiweeklyContest91 {
    // 2465. Number of Distinct Averages
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;

        HashSet<Double> hs = new HashSet<>();
        while (i <= j) {
            int a = nums[i];
            int b = nums[j];

            if (a != b) {
                i++;
                j--;
            } else {
                i++;
            }
            hs.add((double) ((double) a + (double) b) / 2);
        }

        return hs.size();
    }

    // 2466. Count Ways To Build Good Strings
    long mod = (long) 1e9 + 7;

    public int countGoodStrings(int low, int high, int zero, int one) {
        long[] dp = new long[(int) 1e5 + 10];
        Arrays.fill(dp, -1l);
        return (int) helper(0, low, high, zero, one, dp);

    }

    public long helper(int currLen, int low, int high, int ca, int cb, long[] dp) {
        if (currLen >= high) {
            return (currLen == high) ? 1 : 0;
        }

        if (dp[currLen] != -1l)
            return dp[currLen];

        long ans = (currLen >= low) ? 1 : 0;
        ans = (ans % mod + (helper(currLen + ca, low, high, ca, cb, dp) % mod)) % mod;
        ans = (ans % mod + (helper(currLen + cb, low, high, ca, cb, dp) % mod)) % mod;

        return dp[currLen] = ans;
    }

    // 2467. Most Profitable Path in a Tree
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        ArrayList<Integer> graph[] = buildGraph(edges);
        HashMap<Integer, Integer> bobPath = findBobPath(graph, bob); // mapping of node traveled by bob and time at
                                                                     // which was stepped on
        int aliceTime = 0;
        int max = -(int) 1e9;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        HashSet<Integer> vis = new HashSet<>();
        q.add(new int[] { 0, 0 }); // idx,csum

        while (q.size() != 0) {
            int s = q.size();
            while (s-- > 0) {
                int[] arr = q.remove();
                int ridx = arr[0];
                int csum = arr[1];

                if (vis.contains(ridx))
                    continue;

                vis.add(ridx);

                if (!bobPath.containsKey(ridx)) {
                    csum += amount[ridx];
                } else {
                    int bobTime = bobPath.get(ridx);

                    if (bobTime == aliceTime) {
                        csum += (amount[ridx] / 2);
                    } else if (bobTime > aliceTime) {
                        csum += amount[ridx];
                    }
                }

                if (ridx != 0 && graph[ridx].size() == 1) {
                    // leaf node
                    max = Math.max(max, csum);
                }

                for (int nbr : graph[ridx]) {
                    if (!vis.contains(nbr)) {
                        q.add(new int[] { nbr, csum });
                    }
                }

            }
            aliceTime++;
        }

        return max;

    }

    private HashMap<Integer, Integer> findBobPath(ArrayList<Integer>[] graph, int bob) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> vis = new HashSet<>();
        dfs(graph, map, bob, 0, vis);
        return map;
    }

    private boolean dfs(ArrayList<Integer>[] graph, HashMap<Integer, Integer> map, int src, int time,
            HashSet<Integer> vis) {
        map.putIfAbsent(src, time);
        vis.add(src);

        if (src == 0)
            return true;

        boolean res = false;

        for (int nbr : graph[src]) {
            if (vis.contains(nbr) == false) {
                res = res || dfs(graph, map, nbr, time + 1, vis);
            }
        }

        if (res == false)
            map.remove(src);

        return res;

    }

    private ArrayList<Integer>[] buildGraph(int[][] edges) {
        int n = edges.length + 1;
        ArrayList<Integer> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] ed : edges) {
            int u = ed[0];
            int v = ed[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        return graph;
    }
}