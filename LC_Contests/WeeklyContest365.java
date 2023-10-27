import java.util.*;

public class WeeklyContest365 {
    // 2873. Maximum Value of an Ordered Triplet I
    public long maximumTripletValue(int[] nums) {
        long ans = 0l;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // (nums[i] - nums[j]) * nums[k].
                    long val = (long) ((long) nums[i] - (long) nums[j]) * (long) nums[k];

                    ans = Math.max(ans, val);
                }
            }
        }

        return ans;
    }

    // 2874. Maximum Value of an Ordered Triplet II
    public long maximumTripletValue_(int[] nums) {
        int n = nums.length;
        long ans = 0l;

        int[] lmax = generateMaxTillNowArray(nums, true);
        int[] rmax = generateMaxTillNowArray(nums, false);

        for (int j = 1; j < n - 1; j++) {
            long jth = nums[j];
            long ith = lmax[j - 1];
            long kth = rmax[j + 1];
            ans = Math.max(ans, (ith - jth) * kth);
        }

        return ans;
    }

    public int[] generateMaxTillNowArray(int[] nums, boolean fromLeft) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[fromLeft ? 0 : n - 1] = nums[fromLeft ? 0 : n - 1];

        for (int i = 1; i < n; i++) {
            int idx = fromLeft ? i : n - i - 1;
            int nidx = fromLeft ? idx - 1 : idx + 1;

            ans[idx] = Math.max(ans[nidx], nums[idx]);
        }

        return ans;
    }

    // 2875. Minimum Size Sub-array in Infinite Array
    // suffix array ------- infinite copy of array ---- suffix array
    public int minSizeSubarray(int[] nums, int target) {
        int n = nums.length;
        long sum = 0l;
        for (int ele : nums)
            sum += (long) ele;

        int inc = 0;
        if ((long) target > sum) {
            inc = (int) (target / (int) sum);
            target = target % (int) sum;
        }

        int en = 0;
        int st = 0;

        long sof = 0;
        long minLen = (long) 1e9;

        while (st < 2 * n) {
            sof += (long) nums[st % n];
            st++;

            while (sof > (long) target) {
                sof -= (long) nums[en % n];
                en++;
            }

            if (sof == (long) target) {
                minLen = Math.min(minLen, st - en);
            }
        }

        return minLen == (long) 1e9 ? -1 : (int) minLen + inc * n;
    }

    // 779. K-th Symbol in Grammar

    public int kthGrammar(int n, int k) {
        if (n == 1 && k == 1)
            return 0;

        int tot = (1 << (n - 1));
        int mid = tot / 2;

        if (k <= mid) {
            return kthGrammar(n - 1, k);
        } else {
            return Math.abs(1 - kthGrammar(n - 1, k - mid));
        }

    }

}

// 2876. Count Visited Nodes in a Directed Graph
class LC2876 {
    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();

        ArrayList<Integer> graph[] = buildGraph(edges, true);
        ArrayList<ArrayList<Integer>> strongComp = getStronglyConnectedComp(graph, edges);

        int[] ans = new int[n];
        for (ArrayList<Integer> cmp : strongComp) {
            if (cmp.size() > 1) {
                int size = cmp.size();
                for (int ele : cmp) {
                    ans[ele] = size;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (ans[i] == 0) {
                buildAns(i, graph, ans);
            }
        }

        return ans;

    }

    public int buildAns(int src, ArrayList<Integer> graph[], int[] ans) {
        if (ans[src] > 0)
            return ans[src];
        int tmp = 1;
        for (int nbr : graph[src]) {
            tmp += buildAns(nbr, graph, ans);

        }
        ans[src] = tmp;
        return tmp;

    }

    public ArrayList<ArrayList<Integer>> getStronglyConnectedComp(ArrayList<Integer> graph[], List<Integer> edges) {
        int n = edges.size();
        ArrayList<Integer> topo = new ArrayList<>();
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(i, graph, vis, topo);
            }
        }

        ArrayList<Integer> rgraph[] = buildGraph(edges, false);
        vis = new boolean[n];

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = topo.size() - 1; i >= 0; i--) {
            int idx = topo.get(i);
            if (!vis[idx]) {
                ArrayList<Integer> comp = new ArrayList<>();
                dfs(idx, rgraph, vis, comp);
                ans.add(comp);
            }
        }

        return ans;
    }

    public ArrayList<Integer>[] buildGraph(List<Integer> edges, boolean forward) {
        int n = edges.size();
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int u = forward ? i : edges.get(i);
            int v = forward ? edges.get(i) : i;
            graph[u].add(v);
        }

        return graph;
    }

    public void dfs(int src, ArrayList<Integer>[] graph, boolean[] vis, ArrayList<Integer> topo) {
        vis[src] = true;
        for (int nbr : graph[src]) {
            if (!vis[nbr]) {
                dfs(nbr, graph, vis, topo);
            }
        }
        topo.add(src);

    }
}