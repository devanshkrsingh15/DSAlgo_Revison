package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class cutSet {

    // Matrix Chain Multiplication (GFG)
    // if A - p*q && B - q*r
    // A*B -> pqr multiplications and q-1 additions
    public int matrixMultiplication(int N, int arr[]) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return MCM_memo(arr, 0, n - 1, dp);
    }

    private int MCM_memo(int[] arr, int st, int en, int[][] dp) {
        if (st + 1 == en) {
            return dp[st][en] = 0; // single matrix don't require any multiplication
        }

        if (dp[st][en] != -1)
            return dp[st][en];

        int ans = (int) 1e9;

        for (int cut = st + 1; cut < en; cut++) {
            int lans = MCM_memo(arr, st, cut, dp);
            int rans = MCM_memo(arr, cut, en, dp);
            int myans = arr[st] * arr[cut] * arr[en];

            ans = Math.min(ans, lans + rans + myans);
        }

        return dp[st][en] = ans;

    }

    private int MCM_tab(int[] arr) {

        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int st = 0, en = st + gap; en < n; st++, en++) {
                if (st + 1 == en) {
                    dp[st][en] = 0; // single matrix don't require any multiplication
                } else {
                    int ans = (int) 1e9;

                    for (int cut = st + 1; cut < en; cut++) {
                        int lans = dp[st][cut];
                        int rans = dp[cut][en];
                        int myans = arr[st] * arr[cut] * arr[en];

                        ans = Math.min(ans, lans + rans + myans);
                    }

                    dp[st][en] = ans;

                }
            }
        }

        return dp[0][n - 1];

    }

    // Printing brackets in Matrix Chain Multiplication Problem
    class MCMPair {
        String psf;
        int cost;

        MCMPair(String psf, int cost) {
            this.psf = psf;
            this.cost = cost;
        }
    }

    public String matrixChainOrder(int p[], int n) {
        MCMPair[][] dp = new MCMPair[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int st = 0, en = st + gap; en < n; st++, en++) {
                if (st + 1 == en) {
                    String path = (char) ('A' + st) + "";
                    dp[st][en] = new MCMPair(path, 0);
                } else {
                    int min = (int) 1e9;
                    String ms = "";

                    for (int cut = st + 1; cut < en; cut++) {
                        MCMPair lpair = dp[st][cut];
                        MCMPair rpair = dp[cut][en];

                        int mycost = p[st] * p[cut] * p[en];

                        if (min > lpair.cost + mycost + rpair.cost) {
                            min = lpair.cost + mycost + rpair.cost;
                            ms = "(" + lpair.psf + rpair.psf + ")";

                        }

                    }
                }
            }

        }

        return dp[0][n - 1].psf;
    }

    // Minimum and Maximum values of an expression with * and +
    class Pair {
        int max;
        int min;
        String maxexp;
        String minexp;

        Pair(int max, int min, String maxexp, String minexp) {
            this.max = max;
            this.min = min;
            this.maxexp = maxexp;
            this.minexp = minexp;
        }
    }

    public void MinMaxEval(String exp) {
        int n = exp.length();
        Pair[][] dp = new Pair[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int st = 0, en = st + gap; en < n; st++, en++) {
                if (st == en) {
                    int min = exp.charAt(st) - '0';
                    int max = exp.charAt(st) - '0';
                    String maxexp = exp.charAt(st) + "";
                    String minexp = exp.charAt(st) + "";
                    dp[st][en] = new Pair(max, min, maxexp, minexp);
                } else {
                    Pair mypair = new Pair(-(int) 1e9, (int) 1e9, "", "");

                    for (int cut = st + 1; cut < en; cut += 2) {
                        Pair lpair = dp[st][cut - 1];
                        Pair rpair = dp[cut + 1][en];
                        mypair = eval(lpair, mypair, rpair, exp.charAt(cut));
                    }

                    dp[st][en] = mypair;
                }
            }
        }

    }

    public Pair eval(Pair lpair, Pair mypair, Pair rpair, char op) {
        int max = mypair.max;
        int min = mypair.min;

        String mxS = mypair.maxexp;
        String miS = mypair.minexp;

        if (op == '+') {
            if (max < lpair.max + rpair.max) {
                max = lpair.max + rpair.max;
                mxS = "(" + lpair.maxexp + "" + op + "" + rpair.maxexp + ")";
            }

            if (min > lpair.min + rpair.min) {
                min = lpair.min + rpair.min;
                miS = "(" + lpair.minexp + "" + op + "" + rpair.minexp + ")";
            }
        } else if (op == '*') {
            if (max < lpair.max * rpair.max) {
                max = lpair.max * rpair.max;
                mxS = "(" + lpair.maxexp + "" + op + "" + rpair.maxexp + ")";
            }

            if (min > lpair.min * rpair.min) {
                min = lpair.min * rpair.min;
                miS = "(" + lpair.minexp + "" + op + "" + rpair.minexp + ")";
            }
        }

        return new Pair(max, min, mxS, miS);
    }

    // 312. Burst Balloons
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return maxCoins_memo(nums, dp, 0, n - 1);
    }

    // tab is similar to gap strategy
    private int maxCoins_memo(int[] nums, int[][] dp, int st, int en) {
        if (st == en) {
            int left = (st - 1 < 0) ? 1 : nums[st - 1];
            int right = (en + 1 >= nums.length) ? 1 : nums[en + 1];
            return dp[st][en] = left * right * nums[st];
        }

        if (dp[st][en] != -1)
            return dp[st][en];

        int left = (st - 1 < 0) ? 1 : nums[st - 1];
        int right = (en + 1 >= nums.length) ? 1 : nums[en + 1];

        int max = -(int) 1e9;
        for (int cut = st; cut <= en; cut++) {
            int lans = (cut == st) ? 0 : maxCoins_memo(nums, dp, st, cut - 1);
            int rans = (cut == en) ? 0 : maxCoins_memo(nums, dp, cut + 1, en);
            int myans = left * right * nums[cut]; // this will burst at the last
            // to resolve dependency we will burst in post-order
            max = Math.max(max, lans + myans + rans);
        }
        return dp[st][en] = max;
    }

    // 1039. Minimum Score Triangulation of Polygon
    public int minScoreTriangulation(int[] values) {
        int n = values.length;

        int[][] dp = new int[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int st = 0, en = st + gap; en < n; st++, en++) {
                if (en - st <= 1) {
                    dp[st][en] = 0;
                } else {
                    int min = (int) 1e9;
                    for (int cut = st + 1; cut < en; cut++) {
                        int lans = dp[st][cut];
                        int rans = dp[cut][en];
                        int myans = values[st] * values[cut] * values[en];
                        min = Math.min(min, lans + myans + rans);
                    }
                    dp[st][en] = min;
                }
            }
        }

        return dp[0][n - 1];
    }

    // Boolean Parenthesization (GFG)
    class BPair {
        int t;
        int f;

        BPair(int t, int f) {
            this.t = t;
            this.f = f;
        }
    }

    public int countWays(int N, String S) {
        // code here
        int n = S.length();
        BPair[][] dp = new BPair[n][n];

        BPair res = countWays_memo(S, dp, 0, n - 1);
        return res.t;

    }

    private BPair countWays_memo(String s, BPair[][] dp, int st, int en) {
        if (st == en) {
            int t = s.charAt(st) == 'T' ? 1 : 0;
            int f = s.charAt(st) == 'F' ? 1 : 0;
            return dp[st][en] = new BPair(t, f);
        }

        if (dp[st][en] != null)
            return dp[st][en];

        BPair myans = new BPair(0, 0);

        for (int cut = st + 1; cut < en; cut += 2) {
            BPair left = countWays_memo(s, dp, st, cut - 1);
            BPair right = countWays_memo(s, dp, cut + 1, en);

            BPair tmp = evaluate(left, right, s.charAt(cut));
            myans.f = (myans.f % 1003 + tmp.f % 1003) % 1003;
            myans.t = (myans.t % 1003 + tmp.t % 1003) % 1003;
        }
        return dp[st][en] = myans;
    }

    private BPair evaluate(BPair left, BPair right, char ch) {
        int mod = 1003;
        int t = 0;
        int f = 0;
        int total = ((left.t + left.f) % mod * (right.t + right.f) % mod) % mod;

        if (ch == '|') {
            f = (left.f % mod * right.f % mod) % mod;
            t = (total % mod - f % mod + mod) % mod;

        } else if (ch == '&') {
            t = (left.t % mod * right.t % mod) % mod;
            f = (total % mod - t % mod) % mod;
        } else if (ch == '^') {
            t = (left.t % mod * right.f % mod + right.t % mod * left.f % mod) % mod;
            f = (total % mod - t % mod + mod) % mod;
        }

        return new BPair(t, f);

    }

    /*
     * Catalan number
     * 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, ....
     * (2n)!/((n+1)!*(n)!)
     */

    // 96. Unique Binary Search Trees
    public int catalanNum(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int lo = 0;
            int hi = i - 1;
            int num = 0;
            while (lo < i) {
                num += dp[lo] * dp[hi];
                lo++;
                hi--;
            }
            dp[i] = num;
        }

        return dp[n];
    }

    // 1278. Palindrome Partitioning III
    public int palindromePartitionIII(String s, int k) {
        int n = s.length();
        int[][] minCuts = getMinCuts(s);
        int[][] dp = new int[n + 1][k + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return palindromePartitionIII_memo(s, 0, k, dp, minCuts);

    }

    private int palindromePartitionIII_memo(String s, int st, int k, int[][] dp, int[][] minCuts) {
        if (st == s.length() || k == 0) {
            if (k == 0 && st == s.length())
                return dp[st][k] = 0;
            else
                return dp[st][k] = (int) 1e9;
        }

        if (dp[st][k] != -1)
            return dp[st][k];

        int min = (int) 1e9;
        for (int cut = st; cut < s.length(); cut++) {
            int mycuts = minCuts[st][cut];
            int futureCuts = palindromePartitionIII_memo(s, cut + 1, k - 1, dp, minCuts);
            min = Math.min(min, mycuts + futureCuts);
        }
        return dp[st][k] = min;
    }

    private int[][] getMinCuts(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int st = 0, en = st + gap; en < n; st++, en++) {
                if (gap == 0) {
                    dp[st][en] = 0;
                } else if (gap == 1) {
                    dp[st][en] = (s.charAt(st) == s.charAt(en)) ? 0 : 1;
                } else {
                    int inc = (s.charAt(st) == s.charAt(en)) ? 0 : 1;
                    dp[st][en] = dp[st + 1][en - 1] + inc;
                }
            }
        }

        return dp;
    }

    // 22. Generate Parentheses
    // if number of ways were asked then return catalan number
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();

        generateParenthesis_(n, "", ans, 0, 0);
        return ans;
    }

    public void generateParenthesis_(int n, String s, List<String> ans, int oc, int cc) {
        if (oc == n && cc == n) {
            ans.add(s);
            return;
        }

        if (oc < n) {
            generateParenthesis_(n, s + "(", ans, oc + 1, cc);
        }

        if (cc < oc) {
            generateParenthesis_(n, s + ")", ans, oc, cc + 1);
        }
    }
}

// 132. Palindrome Partitioning II
class PalindromePartitioningII {
    int[][] isPal;

    public void fillisPalArray(String s) {
        int n = s.length();

        for (int gap = 0; gap < n; gap++) {
            for (int st = 0, en = st + gap; en < n; st++, en++) {
                if (st == en) {
                    isPal[st][en] = 1;
                } else if (st + 1 == en) {
                    isPal[st][en] = (s.charAt(st) == s.charAt(en)) ? 2 : 0;
                } else {
                    if (s.charAt(st) == s.charAt(en) && isPal[st + 1][en - 1] > 0) {
                        isPal[st][en] = isPal[st + 1][en - 1] + 2;
                    } else {
                        isPal[st][en] = 0;
                    }
                }
            }
        }

    }

    public int minCut(String s) {
        int n = s.length();
        isPal = new int[n][n];
        fillisPalArray(s);
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return minCut_memo(s, 0, n - 1, dp);

    }

    // On3
    public int minCut_memo(String s, int st, int en, int[][] dp) {
        if (st == en || isPal[st][en] > 0)
            return dp[st][en] = 0;

        if (dp[st][en] != -1)
            return dp[st][en];

        int min = (int) 1e9;

        for (int cut = st; cut < en; cut++) {
            if (isPal[st][cut] > 0) {
                min = Math.min(min, minCut_memo(s, cut + 1, en, dp) + 1);
            }
        }

        return dp[st][en] = min;
    }

    // On2
    public int minCut_On2(String s) {
        int n = s.length();
        isPal = new int[n][n];
        fillisPalArray(s);
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        return minCut_memo_On2(s, 0, dp);
    }

    public int minCut_memo_On2(String s, int st, int[] dp) {
        int n = s.length();
        if (isPal[st][n - 1] > 0)
            return dp[st] = 0;

        if (dp[st] != -1)
            return dp[st];

        int min = (int) 1e9;

        for (int cut = st; cut < n; cut++) {
            if (isPal[st][cut] > 0) {
                min = Math.min(min, minCut_memo_On2(s, cut + 1, dp) + 1);
            }
        }

        return dp[st] = min;
    }

    public int minCut_Tan(String s) {
        int n = s.length();
        isPal = new int[n][n];
        fillisPalArray(s);
        int[] dp = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            if (isPal[i][n - 1] > 0)
                dp[i] = 0;
            else {
                int min = (int) 1e9;
                for (int j = i + 1; j < n; j++) {
                    if (isPal[i][j] > 0)
                        min = Math.min(min, dp[j + 1] + 1);
                }
                dp[i] = min;
            }
        }

        return dp[0];
    }

}

// 95. Unique Binary Search Trees II
class UniqueBST {
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {

        }

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    HashMap<Integer, List<TreeNode>> dp = new HashMap<>();
    // WE HAVE USED ENCODING KEY ie st(n) + en for using memoisation
    // string key was slow
    // if num of solutions were asked return catalan number

    int n;

    public List<TreeNode> generateTrees(int N) {
        n = N;
        List<TreeNode> ans = generateTrees_(1, N);

        return ans;
    }

    public List<TreeNode> generateTrees_(int st, int en) {
        int key = st * n + en;

        if (st > en) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(null);
            dp.put(key, base);
            return base;
        }

        if (dp.containsKey(key))
            return dp.get(key);

        ArrayList<TreeNode> myans = new ArrayList<>();

        for (int cut = st; cut <= en; cut++) {
            List<TreeNode> lans = generateTrees_(st, cut - 1);
            List<TreeNode> rans = generateTrees_(cut + 1, en);

            for (TreeNode l : lans) {
                for (TreeNode r : rans) {
                    TreeNode root = new TreeNode(cut);
                    root.left = l;
                    root.right = r;
                    myans.add(root);

                }
            }
        }

        dp.put(key, myans);

        return myans;
    }
}