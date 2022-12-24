import java.util.*;

public class BiweeklyContest93 {
    // 2496. Maximum Value of a String in an Array
    public int maximumValue(String[] strs) {
        int max = 0;

        for (String s : strs) {
            boolean flag = false;
            int val = s.length();

            for (int i = 0; i < val; i++) {
                if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                val = Integer.parseInt(s);
            }

            max = Math.max(val, max);

        }

        return max;
    }

    // 2497. Maximum Star Sum of a Graph
    HashMap<Integer, long[]> map = new HashMap<>();

    public int maxStarSum(int[] vals, int[][] edges, int k) {
        int n = vals.length;
        PriorityQueue<Integer> graph[] = createGraph(vals, n, edges, k);

        long max = -(long) 1e9;
        for (int ele : vals) {
            max = Math.max(max, (long) ele);
        }

        if (k == 0)
            return (int) max;

        for (int i = 0; i < n; i++) {
            long sum = (long) vals[i] + map.get(i)[1];
            max = Math.max(max, sum);
            // max=Math.max(max,vals[i]);
        }

        return max == -(long) 1e9 ? 0 : (int) max;

    }

    public PriorityQueue<Integer>[] createGraph(int[] vals, int n, int[][] ed, int k) {
        PriorityQueue<Integer>[] graph = new PriorityQueue[n];
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(i, new long[] { 0, -(long) 1e9 }); // csum ,omax
            graph[i] = new PriorityQueue<>((a, b) -> {
                return vals[a] - vals[b];
            });
        }

        for (int[] e : ed) {
            int u = e[0];
            int v = e[1];

            if (vals[v] > 0) {
                graph[u].add(v);
                map.get(u)[0] += (long) vals[v];
            }
            if (graph[u].size() > k) {
                int idx = graph[u].remove();
                map.get(u)[0] -= (long) vals[idx];
            }
            map.get(u)[1] = Math.max(map.get(u)[0], map.get(u)[1]);

            if (vals[u] > 0) {
                graph[v].add(u);
                map.get(v)[0] += (long) vals[u];
            }
            if (graph[v].size() > k) {
                int idx = graph[v].remove();
                map.get(v)[0] -= (long) vals[idx];
            }
            map.get(v)[1] = Math.max(map.get(v)[0], map.get(v)[1]);

        }

        return graph;
    }

    // 2498. Frog Jump II
    public int maxJump(int[] stones) {
        int n = stones.length;
        int hi = stones[n - 1] - stones[0];
        int lo = 1;
        int ans = 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (isPossible(stones, mid)) {
                hi = mid - 1;
                ans = mid;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    public boolean isPossible(int[] arr, int mid) {
        int n = arr.length;
        boolean isUsed[] = new boolean[n];

        // forward
        int ptr = 0;
        while (ptr < n) {
            int i = ptr + 1;
            int nPos = ptr;
            while (i < n && arr[i] - arr[ptr] <= mid) {
                if (isUsed[i] == false)
                    nPos = i;
                i++;
            }
            if (nPos == ptr)
                return false;
            if (nPos == n - 1)
                break;

            ptr = nPos;
            isUsed[ptr] = true;
        }

        // backward
        ptr = n - 1;
        while (ptr >= 0) {
            int i = ptr - 1;
            int nPos = ptr;
            while (i >= 0 && arr[ptr] - arr[i] <= mid) {
                if (isUsed[i] == false)
                    nPos = i;
                i--;
            }

            if (nPos == ptr)
                return false;
            if (nPos == 0)
                break;

            ptr = nPos;
            isUsed[ptr] = true;
        }

        return true;
    }

    //790. Domino and Tromino Tiling
    long mod = (long)1e9 + 7;
    public int numTilings(int n) {

        long[]dp = new long[n+1];
        Arrays.fill(dp,-1l);

       return (int)numTilings_(n,dp);
    }

    public long numTilings_(int n,long[]dp){
        if(n==0) return dp[n] = 1l;
        if(n==1) return dp[n]=1l;
        if(n==2) return dp[n] = 2l;

        if(dp[n]!=-1l) return dp[n];

        long a = (n-1<0) ? 0 : 2*numTilings_(n-1,dp)%mod;
        long b = (n-3<0) ? 0 : numTilings_(n-3,dp)%mod;

        long myAns = (a%mod + b%mod)%mod;


        return dp[n] = myAns;
    }

}