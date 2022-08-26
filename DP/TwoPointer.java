package DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class TwoPointer {
    public int fib01(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = fib01(n - 1, dp) + fib01(n - 2, dp);
    }

    public int fib02(int[] dp, int N) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }
            dp[n] = dp[n - 1] + dp[n - 2];
        }

        return dp[N];
    }

    public int fib03(int N) {
        int a = 0;
        int b = 1;
        for (int n = 2; n <= N; n++) {
            int sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }

    int[][] direcs = { { 0, 1 }, { 1, 0 }, { 1, 1 } };

    public int mazePaths01(int[][] grid, int[][] dp, int src) {
        int n = grid.length;
        int m = grid[0].length;
        int r = src / m;
        int c = src % m;
        if (r == n - 1 && c == m - 1)
            return 1;

        if (dp[r][c] != -1)
            return dp[r][c];

        int ans = 0;
        for (int k = 0; k < direcs.length; k++) {
            for (int j = 1; r + j * direcs[k][0] < n && c + j * direcs[k][1] < m; j++) {
                int x = r + j * direcs[k][0];
                int y = c + j * direcs[k][1];
                ans += mazePaths01(grid, dp, x * m + y);
            }
        }

        return dp[r][c] = ans;

    }

    public int mazePaths02(int[][] grid, int dp[][]) {
        int n = grid.length;
        int m = grid[0].length;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (i == n - 1 && j == m - 1)
                    dp[i][j] = 1;
                else {
                    int ans = 0;
                    for (int k = 0; k < direcs.length; k++) {
                        for (int jmp = 1; i + jmp * direcs[k][0] < n && j + jmp * direcs[k][1] < m; jmp++) {
                            int x = i + jmp * direcs[k][0];
                            int y = j + jmp * direcs[k][1];
                            ans += dp[x][y];
                        }
                    }
                    dp[i][j] = ans;

                }
            }
        }

        return dp[0][0];
    }

    public int diceMoves_01(int[] dp, int n, int N) {
        if (n == N)
            return dp[n] = 1;
        if (dp[n] != -1)
            return dp[n];

        int ans = 0;
        for (int j = 1; j <= 6; j++) {
            if (n + j <= N)
                ans += diceMoves_01(dp, n + j, N);
        }

        return dp[n] = ans;
    }

    public int diceMoves_02(int[] dp, int N) {
        for (int n = N; n >= 1; n--) {
            if (n == N)
                dp[n] = 1;
            else {
                int ans = 0;
                for (int j = 1; j <= 6; j++) {
                    if (n + j <= N)
                        ans += dp[n + j];
                }

                dp[n] = ans;
            }
        }

        return dp[1];
    }

    public int diceMoves_optimal(int N) {
        int moves = 6;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = N; i >= 0; i--) {
            if (i >= N - 1) {
                list.addFirst(1);
            } else {
                if (list.size() <= moves) {
                    list.add(list.getFirst() * 2);
                } else {
                    list.add(list.getFirst() * 2 - list.removeLast());
                }
            }
        }

        return list.getFirst();
    }

    public int diceMoves_movesProvided_01(int[] dp, int i, int N, int[] moves) {
        if (i == N)
            return 1;
        if (dp[i] != -1)
            return dp[i];

        int ans = 0;

        for (int ele : moves) {
            if (i + ele <= N)
                ans += diceMoves_movesProvided_01(dp, i + ele, N, moves);
        }

        return dp[i] = ans;
    }

    public int diceMoves_movesProvided_02(int[] dp, int N, int[] moves) {
        for (int i = N; i >= 0; i--) {
            if (i == N) {
                dp[i] = 1;
                continue;
            }
            int ans = 0;

            for (int ele : moves) {
                if (i + ele <= N)
                    ans += dp[i + ele];
            }

            dp[i] = ans;
        }
        return dp[0];
    }

    // 70. Climbing Stairs
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        int memo_ans = climbStairs_memo(n, 0, dp);
        int tab_ans = climbStairs_tab(n, 0);
        int opti_ans = climbStairs_optimal(n);

        return memo_ans;
    }

    private int climbStairs_optimal(int n) {
        if (n == 1)
            return 1;
        int a = 1;
        int b = 1;

        for (int i = 2; i <= n; i++) {
            int sum = a + b;
            b = a;
            a = sum;
        }
        return a;
    }

    private int climbStairs_tab(int n, int src) {
        int[] dp = new int[n + 1];
        for (int i = n; i >= src; i--) {
            if (i == n) {
                dp[i] = 1;

            } else {
                int ans = 0;
                if (i + 1 <= n)
                    ans += dp[i + 1];
                if (i + 2 <= n)
                    ans += dp[i + 2];

                dp[i] = ans;
            }
        }

        return dp[src];
    }

    private int climbStairs_memo(int n, int i, int[] dp) {
        if (i == n)
            return 1;
        if (dp[i] != -1)
            return dp[i];

        int ans = 0;
        if (i + 1 <= n)
            ans += climbStairs_memo(n, i + 1, dp);
        if (i + 2 <= n)
            ans += climbStairs_memo(n, i + 2, dp);

        return dp[i] = ans;
    }

    // 746. Min Cost Climbing Stairs
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        int mem_cost = Math.min(minCostClimbingStairs_memo(cost, dp, 0), minCostClimbingStairs_memo(cost, dp, 1));
        int tab_cost = minCostClimbingStairs_tab(cost);
        int optimal_cost = minCostClimbingStairs_optimal(cost);
        return mem_cost;
    }

    private int minCostClimbingStairs_optimal(int[] cost) {
        int n = cost.length;
        int a = cost[0];
        int b = cost[1];

        for (int i = 2; i <= n; i++) {
            int minCost = Math.min(a, b) + (i == n ? 0 : cost[i]);
            a = b;
            b = minCost;
        }

        return Math.min(a, b);

    }

    private int minCostClimbingStairs_tab(int[] cost) {
        int n = cost.length;

        int[] dp = new int[n + 1];
        for (int i = n; i >= 0; i--) {
            if (i == n) {
                dp[i] = 0;
            } else {
                int mycost = (int) 1e9;
                for (int j = 1; j <= 2; j++) {
                    if (i + j <= n)
                        mycost = Math.min(mycost, dp[i + j]);
                }

                dp[i] = mycost + cost[i];
            }
        }

        return Math.min(dp[0], dp[1]);
    }

    private int minCostClimbingStairs_memo(int[] cost, int[] dp, int i) {
        int n = cost.length;

        if (i == n)
            return 0;

        if (dp[i] != -1)
            return dp[i];

        int mycost = (int) 1e9;

        for (int j = 1; j <= 2; j++) {
            if (i + j <= n)
                mycost = Math.min(mycost, minCostClimbingStairs_memo(cost, dp, i + j));
        }

        return dp[i] = mycost + cost[i];
    }

    long mod = (long) 1e9 + 7;

    // Friends Pairing Problem (GFG)
    public long countFriendsPairings(int n) {
        // code here
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1l);
        long memo_ways = countFriendsPairings_memo(n, dp);
        long tab_ways = countFriendsPairings_tab(n);
        return memo_ways;

    }

    private long countFriendsPairings_tab(int n) {
        long[] dp = new long[n + 1];

        for (int i = 0; i <= n; i++) {
            if (i <= 1) {
                dp[i] = 1l;
                continue;
            }

            long single = dp[i - 1] % mod;
            long pairUp = (dp[i - 2] % mod * (i - 1) % mod) % mod;

            dp[i] = (single % mod + pairUp % mod) % mod;
        }

        return dp[n];
    }

    private long countFriendsPairings_memo(int n, long[] dp) {
        if (n <= 1)
            return dp[n] = (long) 1;

        if (dp[n] != -1l)
            return dp[n];

        long single = countFriendsPairings_memo(n - 1, dp) % mod;
        long pairUp = (countFriendsPairings_memo(n - 2, dp) % mod * (n - 1) % mod) % mod;

        return dp[n] = (single % mod + pairUp % mod) % mod;

    }

    public class FPair {
        int cnt;
        String str;

        FPair(int cnt, String str) {
            this.cnt = cnt;
            this.str = str;
        }
    }

    // Friends pairing problem printing all pairs/ways
    public FPair friendsPairing(String friends, HashMap<String, FPair> dp, boolean[] vis) {
        int n = friends.length();
        if (n <= 1) {
            if (n == 0) {
                return new FPair(1, "");
            } else {
                return new FPair(1, friends + "");
            }
        }

        if (dp.containsKey(friends))
            return dp.get(friends);

        int ans = 0;
        String str = "";

        for (int i = 0; i < friends.length(); i++) {
            if (vis[i] == false) {
                // SINGLE
                str += "(" + friends.charAt(i) + ") ";
                vis[i] = true;
                FPair single = friendsPairing(friends, dp, vis);
                ans += single.cnt;
                str += single.str;

                // PAIR-UP
                for (int j = i + 1; j < friends.length(); j++) {
                    if (vis[j] == false) {
                        vis[j] = true;
                        str += "(" + friends.charAt(i) + "," + friends.charAt(j) + ") ";

                        FPair pairUp = friendsPairing(friends, dp, vis);
                        ans += pairUp.cnt;
                        str += pairUp.str;
                        vis[j] = false;
                    }
                }
                vis[i] = false;
            }

        }

        FPair mypair = new FPair(ans, str);
        dp.put(friends, mypair);

        return mypair;

    }

    // Gold Mine Problem (GFG)
    public int maxGold(int n, int m, int M[][]) {
        int[][] dp = new int[n][m];
        int[][] direcs = { { 1, 1 }, { 0, 1 }, { -1, 1 } };
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int maxAns = 0;
        for (int i = 0; i < n; i++) {
            int ans_memo = maxGold_memo(i, 0, n, m, M, dp, direcs);
            maxAns = Math.max(maxAns, ans_memo);
        }

        int ans_tab = maxGold_tab(M);
        return maxAns;

    }

    public int maxGold_tab(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        int[][] direcs = { { 1, 1 }, { 0, 1 }, { -1, 1 } };

        for (int j = m - 1; j >= 0; j--) {
            for (int i = n - 1; i >= 0; i--) {
                if (j == m - 1) {
                    dp[i][j] = grid[i][j];
                } else {
                    for (int k = 0; k < direcs.length; k++) {
                        int x = i + direcs[k][0];
                        int y = j + direcs[k][1];

                        if (x >= 0 && y >= 0 && x < n && y < m) {
                            dp[i][j] = Math.max(dp[i][j], grid[i][j] + dp[x][y]);
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i][0]);
        }

        return ans;
    }

    private int maxGold_memo(int i, int j, int n, int m, int[][] grid, int[][] dp, int[][] direcs) {
        if (j == m - 1) {
            return dp[i][j] = grid[i][j];
        }

        if (dp[i][j] != -1)
            return dp[i][j];

        int ans = 0;

        for (int k = 0; k < direcs.length; k++) {
            int x = i + direcs[k][0];
            int y = j + direcs[k][1];

            if (x >= 0 && y >= 0 && x < n && y < m) {
                ans = Math.max(ans, grid[i][j] + maxGold_memo(x, y, n, m, grid, dp, direcs));
            }
        }

        return dp[i][j] = ans;
    }

    // 91. Decode Ways
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        int memo_ans = numDecodings_memo(s, 0, n, dp);
        int tab_ans = numDecodings_tab(s, 0, n);

        int optimal_ans = numDecodings_optimal(s);

        return memo_ans;
    }

    private int numDecodings_optimal(String s) {
        int n = s.length();
        int a = 1;
        int b = 0;

        for (int i = n - 1; i >= 0; i--) {
            char ch1 = s.charAt(i);
            int sum = 0;
            if (ch1 == '0') {

            } else {
                sum += a;

                if (i + 1 < n) {
                    char ch2 = s.charAt(i + 1);
                    int val = Integer.parseInt(ch1 + "" + ch2);
                    if (val >= 10 && val <= 26) {
                        sum += b;
                    }
                }
            }

            b = a;
            a = sum;
        }

        return a;

    }

    private int numDecodings_tab(String s, int src, int n) {
        int[] dp = new int[n + 1];

        for (int i = n; i >= src; i--) {
            if (i == n) {
                dp[i] = 1;
            } else {
                char ch1 = s.charAt(i);
                if (ch1 == '0')
                    dp[i] = 0;
                else {
                    int ans = 0;
                    ans += dp[i + 1];
                    if (i + 1 < n) {
                        char ch2 = s.charAt(i + 1);

                        int val = Integer.parseInt(ch1 + "" + ch2);
                        if (val >= 10 && val <= 26) {
                            ans += dp[i + 2];
                        }
                    }

                    dp[i] = ans;
                }
            }
        }

        return dp[src];
    }

    private int numDecodings_memo(String s, int i, int n, int[] dp) {
        if (i == n)
            return dp[n] = 1;
        if (dp[i] != -1)
            return dp[i];

        char ch1 = s.charAt(i);
        if (ch1 == '0')
            return dp[i] = 0;

        int ans = 0;
        // single
        ans += numDecodings_memo(s, i + 1, n, dp);

        if (i + 1 < n) {
            char ch2 = s.charAt(i + 1);

            int val = Integer.parseInt(ch1 + "" + ch2);
            if (val >= 10 && val <= 26) {
                ans += numDecodings_memo(s, i + 2, n, dp);
            }
        }

        return dp[i] = ans;

    }

    // 639. Decode Ways II
    public int numDecodings2(String s) {
        int n = s.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1l);
        long memo_ans = numDecodings2_memo(s, 0, n, dp);
        long tab_ans = numDecodings2_tab(s, 0, n);

        long optimal_ans = numDecodings2_optimal(s);

        return (int) memo_ans;
    }

    private long numDecodings2_optimal(String s) {
        int n = s.length();
        long a = 1l;
        long b = 0;
        long mod = (long) 1e9 + 7;

        for (int i = n - 1; i >= 0; i--) {
            char ch1 = s.charAt(i);
            long sum = 0;

            if (ch1 == '0') {

            } else {

                if (ch1 == '*') {
                    sum = (sum % mod + (9 * a) % mod) % mod;
                    if (i + 1 < n) {
                        char ch2 = s.charAt(i + 1);
                        if (ch2 == '*') {
                            sum = (sum % mod + (15 * b) % mod) % mod;
                        } else {

                            if (ch2 >= '0' && ch2 <= '6') {
                                sum = (sum % mod + (2 * b) % mod) % mod;
                            } else if (ch2 > '6') {
                                sum = (sum % mod + (b) % mod) % mod;
                            }
                        }
                    }

                } else {
                    sum = (sum % mod + (a) % mod) % mod;
                    if (i + 1 < n) {
                        char ch2 = s.charAt(i + 1);

                        if (ch2 == '*') {
                            if (ch1 == '1') {
                                sum = (sum % mod + (9 * b) % mod) % mod;
                            } else if (ch1 == '2') {
                                sum = (sum % mod + (6 * b) % mod) % mod;
                            }
                        } else {

                            int val = Integer.parseInt(ch1 + "" + ch2);
                            if (val <= 26) {
                                sum = (sum % mod + (b) % mod) % mod;
                            }

                        }
                    }

                }

            }

            b = a;
            a = sum;
        }

        return a;

    }

    private long numDecodings2_tab(String s, int src, int n) {
        long[] dp = new long[n + 1];

        for (int i = n; i >= src; i--) {
            if (i == n) {
                dp[i] = 1l;
            } else {
                long ans = 0;
                char ch1 = s.charAt(i);
                if (ch1 == '0') {
                    ans = 0;
                } else if (ch1 == '*') {
                    ans = (ans % mod + (9 * dp[i + 1]) % mod) % mod;
                    if (i + 1 < n) {
                        char ch2 = s.charAt(i + 1);
                        if (ch2 == '*') {
                            ans = (ans % mod + (15 * dp[i + 2]) % mod) % mod;
                        } else if (ch2 >= '0' && ch2 <= '6') {
                            ans = (ans % mod + (2 * dp[i + 2]) % mod) % mod;
                        } else if (ch2 > '6') {
                            ans = (ans % mod + (dp[i + 2]) % mod) % mod;
                        }
                    }
                } else {
                    ans = (ans % mod + (dp[i + 1]) % mod) % mod;
                    if (i + 1 < n) {
                        char ch2 = s.charAt(i + 1);

                        if (ch2 == '*') {
                            if (ch1 == '1') {
                                ans = (ans % mod + (9 * dp[i + 2]) % mod) % mod;
                            } else if (ch1 == '2') {
                                ans = (ans % mod + (6 * dp[i + 2]) % mod) % mod;
                            }

                        } else {
                            int val = Integer.parseInt(ch1 + "" + ch2);
                            if (val <= 26) {
                                ans = (ans % mod + (dp[i + 2]) % mod) % mod;
                            }
                        }
                    }
                }

                dp[i] = ans % mod;
            }
        }
        return dp[src];
    }

    private long numDecodings2_memo(String s, int i, int n, long[] dp) {
        if (i == n)
            return dp[i] = 1l;

        if (dp[i] != -1l)
            return dp[i];

        long ans = 0;

        char ch1 = s.charAt(i);
        if (ch1 == '0')
            return 0;

        if (ch1 == '*') {
            ans = (ans % mod + (9 * numDecodings2_memo(s, i + 1, n, dp)) % mod) % mod;
            if (i + 1 < n) {
                char ch2 = s.charAt(i + 1);
                if (ch2 == '*') {
                    ans = (ans % mod + (15 * numDecodings2_memo(s, i + 2, n, dp)) % mod) % mod;
                } else {
                    if (ch2 >= '0' && ch2 <= '6') {
                        ans = (ans % mod + (2 * numDecodings2_memo(s, i + 2, n, dp)) % mod) % mod;
                    } else if (ch2 >= '7') {
                        ans = (ans % mod + (numDecodings2_memo(s, i + 2, n, dp) % mod) % mod) % mod;
                    }
                }
            }

        } else {
            ans = (ans % mod + numDecodings2_memo(s, i + 1, n, dp) % mod) % mod;

            if (i + 1 < n) {
                char ch2 = s.charAt(i + 1);
                if (ch2 == '*') {
                    if (ch1 == '1') {
                        ans = (ans % mod + (9 * numDecodings2_memo(s, i + 2, n, dp)) % mod) % mod;
                    } else if (ch1 == '2') {
                        ans = (ans % mod + (6 * numDecodings2_memo(s, i + 2, n, dp)) % mod) % mod;
                    }

                } else {
                    int val = Integer.parseInt("" + ch1 + ch2);
                    if (val >= 10 && val <= 26) {
                        ans = (ans % mod + numDecodings2_memo(s, i + 2, n, dp) % mod) % mod;
                    }
                }

            }

        }

        return dp[i] = ans % mod;
    }

    // Count number of ways to partition a set into k subsets (GFG)
    public int partitionWays(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int memo_ans = partitionWays_memo(n, k, dp);

        return memo_ans;
    }

    public int partitionWays_memo(int n, int k, int[][] dp) {
        if (n == 0 || k == 0) {
            return dp[n][k] = (n == 0 && k == 0) ? 1 : 0;
        }

        if (n == k)
            return dp[n][k] = 1;

        if (k > n)
            return dp[n][k] = 0;

        if (dp[n][k] != -1)
            return dp[n][k];

        int single = partitionWays_memo(n - 1, k - 1, dp);
        int pairUp = k * partitionWays_memo(n - 1, k, dp);

        return dp[n][k] = single + pairUp;

    }

    public int partitionWays_tab(int N, int K) {
        int[][] dp = new int[N + 1][K + 1];

        for (int n = 0; n <= N; n++) {
            for (int k = 0; k <= K; k++) {
                if (n == 0 || k == 0) {
                    dp[n][k] = (n == 0 && k == 0) ? 1 : 0;
                    continue;
                }
                if (n == k || k == 1) {
                    dp[n][k] = 1;
                    continue;
                }

                if (k > n) {
                    dp[n][k] = 0;
                    continue;
                }

                dp[n][k]=   dp[n-1][k-1] +k*dp[n-1][k];
            }
        }

        return  dp[N][K];
    }

    //nCr= n-1Cr + n-1Cr-1

    



}
