import java.util.*;

public class BiweeklyContest89 {
    // 2437. Number of Valid Clock Times
    public int countTime(String time) {
        int ans = 1;

        String[] arr = time.split(":");

        for (int i = 0; i < 2; i++) {
            String t = arr[i];
            if (i == 0) {
                if (t.equals("??"))
                    ans *= 24;
                else {
                    char ch = t.charAt(0);
                    if (ch == '?') {
                        char sch = t.charAt(1);
                        ans = ans * ((sch <= '3') ? 3 : 2);
                    }

                    ch = t.charAt(1);
                    if (ch == '?') {
                        char pch = t.charAt(0);
                        ans = ans * ((pch <= '1') ? 10 : 4);
                    }
                }
            } else {
                if (t.equals("??"))
                    ans *= 60;
                else {
                    char ch = t.charAt(0);
                    if (ch == '?') {
                        ans *= 6;
                    }
                    ch = t.charAt(1);
                    if (ch == '?') {
                        ans = ans * 10;
                    }
                }

            }
        }

        return ans;
    }

    // 2438. Range Product Queries of Powers
    public int[] productQueries(int n, int[][] queries) {
        ArrayList<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add(n & (-n));
            n = n & (n - 1);
        }

        long mod = (long) 1e9 + 7;
        int idx = 0;
        int[] ans = new int[queries.length];
        for (int[] q : queries) {
            int st = q[0];
            int en = q[1];

            long t = 1l;
            for (int i = st; i <= en; i++) {
                t = (t % mod + list.get(i) % mod) % mod;
            }

            ans[idx++] = (int) t;

        }

        return ans;
    }

    // 2439. Minimize Maximum of Array
    public int minimizeArrayValue(int[] nums) {
        long min = 0;
        long max = (long) 1e9;
        long ans = -1l;

        while (min <= max) {
            long mid = min + (max - min) / 2;

            if (isPossible(nums, mid)) {
                ans = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }

        }

        return (int) ans;
    }

    public boolean isPossible(int[] nums, long max) {
        long cnt = 1;
        long sof = 0;
        for (int ele : nums) {
            sof += (long) ele;
            if (sof > max * cnt)
                return false;
            cnt++;
        }

        return true;
    }

    // 2440. Create Components With Same Value
    public int componentValue(int[] nums, int[][] edges) {
        int n = nums.length;
        ArrayList<Integer> graph[] = new ArrayList[n];
        createGraph(graph, edges);

        int sof = 0;
        for (int ele : nums)
            sof += ele;
        
        int max = 0;

        for(int parts = 2 ;parts<=n ;parts++){
            if(sof%parts==0){
                int tar=  sof/parts;
                int tmp = dfs(graph,0,-1,nums,tar);
                if(tmp==0) max = Math.max(max,parts-1);
            }
        }

        return max;
    }

    private int dfs(ArrayList<Integer>[] graph, int src, int par,int[]arr,int tar) {
        int sum = arr[src];

        for(int nbr :graph[src]){
            if(nbr!=src){
                sum+= dfs(graph,nbr,src,arr,tar);
            }
        }

        if(sum==tar) return 0;
        return sum;
    }

    private void createGraph(ArrayList<Integer> graph[], int[][] edges) {
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            graph[u].add(v);
            graph[v].add(u);
        }
    }

    //2787. Ways to Express an Integer as Sum of Powers
    int x;
    long dp[][];
    public int numberOfWays(int n, int X) {
       x = X;
       dp  = new long[n+1][n+1];
       for(long[]d:dp)Arrays.fill(d,-1l);

       return (int)numberOfWays_(n,1);
    }

    long mod = (long)1e9 + 7;
    public long numberOfWays_(int n,int b){
        if(n==0) return 1;
        if(n < b) return 0;
        if(dp[n][b]!=-1l) return dp[n][b];

        long ans = 0l;

        if((long)n- (long)Math.pow(b,x)>=0l) ans = (ans%mod + numberOfWays_(n-(int)Math.pow(b,x) ,b+1)%mod)%mod;
        ans = (ans%mod + numberOfWays_(n,b+1)%mod)%mod;
        
        return dp[n][b] = ans;
    }
}
