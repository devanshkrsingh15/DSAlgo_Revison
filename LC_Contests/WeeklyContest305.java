

import java.util.*;

public class WeeklyContest305 {
    // 2367. Number of Arithmetic Triplets
    public int arithmeticTriplets(int[] nums, int diff) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int a = nums[j] - nums[i];
                    int b = nums[k] - nums[j];

                    if (a == b && a == diff)
                        ans++;
                }
            }
        }

        return ans;

    }

    // 2368. Reachable Nodes With Restrictions
    public ArrayList<Integer>[] createGraph(int n, int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] ed : edges) {
            int u = ed[0];
            int v = ed[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        return graph;
    }

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        ArrayList<Integer>[] graph = createGraph(n, edges);
        HashSet<Integer> res = new HashSet<>();
        for (int e : restricted)
            res.add(e);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);
        int cnt = 0;
        boolean[] vis = new boolean[n];
        vis[0] = true;

        while (q.size() != 0) {
            int rnode = q.remove();
            cnt++;

            for (int nbr : graph[rnode]) {
                if (!vis[nbr] && res.contains(nbr) == false) {
                    q.add(nbr);
                    vis[nbr] = true;
                }
            }
        }

        return cnt;
    }

    // 2369. Check if There is a Valid Partition For The Array
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return validPartition_(nums, 0, dp) == 1;
    }

    public int validPartition_(int[] nums, int idx, int[] dp) {
        int n = nums.length;
        if (idx == n)
            return dp[idx] = 1;

        if (dp[idx] != -1)
            return dp[idx];

        boolean res = false;

        if (idx + 1 < n) {
            if (nums[idx] == nums[idx + 1]) {
                res = res || (validPartition_(nums, idx + 2, dp) == 1);

                if (idx + 2 < n && nums[idx + 1] == nums[idx + 2]) {
                    res = res || (validPartition_(nums, idx + 3, dp) == 1);
                }
            } else if (nums[idx + 1] - nums[idx] == 1) {
                if (idx + 2 < n && nums[idx + 2] - nums[idx + 1] == 1) {
                    res = res || (validPartition_(nums, idx + 3, dp) == 1);
                }

            }
        }

        return dp[idx] = (res) ? 1 : 0;

    }

    // 2370. Longest Ideal Subsequence
    public int longestIdealString(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n + 1][31];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return longestIdealString_(s, 0, k, 28, dp);
    }

    private int longestIdealString_(String s, int i, int k, int prv, int[][] dp) {
        int n = s.length();
        if (i == s.length())
            return dp[i][prv] = 0;
        if (dp[i][prv] != -1)
            return dp[i][prv];

        int ans = 0;
        ans = Math.max(ans, longestIdealString_(s, i + 1, k, prv, dp));

        if (prv == 28 || Math.abs((s.charAt(i) - 'a') - prv) <= k) {
            ans = Math.max(ans, longestIdealString_(s, i + 1, k, s.charAt(i) - 'a', dp) + 1);
        }

        return dp[i][prv] = ans;
    }
}
