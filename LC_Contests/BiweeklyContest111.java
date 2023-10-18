import java.math.BigInteger;
import java.util.*;

public class BiweeklyContest111 {

    // 2824. Count Pairs Whose Sum is Less than Target
    public int countPairs(List<Integer> nums, int target) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) < target)
                    ans++;
            }
        }

        return ans;
    }

    // 2825. Make String a Subsequence Using Cyclic Increments
    public boolean canMakeSubsequence(String str1, String str2) {
        if (str2.length() > str1.length())
            return false;

        int n = str1.length();
        int j = 0;

        for (int i = 0; i < n; i++) {
            if (j >= str2.length())
                return true;
            char chi = str1.charAt(i);
            char chj = str2.charAt(j);
            if (chi == chj) {
                j++;
                continue;
            } else if (chi != chj) {
                char nc = chi == 'z' ? 'a' : (char) ((int) (chi - 'a') + 1 + 'a');
                if (nc != chj) {

                } else {
                    j++;
                    continue;
                }
            }
        }

        return j >= str2.length();

    }

    // 2826. Sorting Three Groups
    public int minimumOperations(List<Integer> nums) {
        int n = nums.size();
        int[][] dp = new int[n + 1][4];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = (int) 1e9;

        for (int c = 1; c <= 3; c++) {
            ans = Math.min(ans, minimumOperations_(nums, 0, c, dp));
        }

        return ans;
    }

    public int minimumOperations_(List<Integer> nums, int idx, int lastGroup, int[][] dp) {
        if (idx == nums.size()) {
            return 0;
        }

        if (dp[idx][lastGroup] != -1)
            return dp[idx][lastGroup];

        int ans = (int) 1e9;

        int myGroup = nums.get(idx);
        if (myGroup == lastGroup) {
            ans = Math.min(ans, minimumOperations_(nums, idx + 1, lastGroup, dp));
        } else {
            if (myGroup > lastGroup) {
                ans = Math.min(ans, minimumOperations_(nums, idx + 1, myGroup, dp));
                ans = Math.min(ans, minimumOperations_(nums, idx + 1, lastGroup, dp) + 1);
            } else if (myGroup < lastGroup) {
                ans = Math.min(ans, minimumOperations_(nums, idx + 1, lastGroup, dp) + 1);
            }
        }

        return dp[idx][lastGroup] = ans;
    }

    // 2827. Number of Beautiful Integers in the Range
    int K;

    public int numberOfBeautifulIntegers(int low, int high, int k) {
        String r = high + "";
        String l = (low - 1) + "";

        K = k;
        // pos,boud,rem,cnt,firstPos
        Integer[][][][][] dp = new Integer[r.length()][2][21][21][2];

        int rcnt = numberOfBeautifulIntegers_(r, 0, true, 0, 0, true, dp);

        dp = new Integer[l.length()][2][21][21][2];
        int lcnt = numberOfBeautifulIntegers_(l, 0, true, 0, 0, true, dp);

        return (rcnt - lcnt);
    }

    public int numberOfBeautifulIntegers_(String s, int pos, boolean bound, int rem, int cnt, boolean firstPos,
            Integer[][][][][] dp) {
        if (pos == s.length()) {
            return rem == 0 && cnt == 0 ? 1 : 0;
        }

        if (dp[pos][bound ? 1 : 0][rem][cnt + 10][firstPos ? 1 : 0] != null)
            return dp[pos][bound ? 1 : 0][rem][cnt + 10][firstPos ? 1 : 0];

        int max = 9;
        if (bound) {
            max = s.charAt(pos) - '0';
        }

        int ans = 0;
        for (int i = 0; i <= max; i++) {
            int npos = pos + 1;
            boolean nbound = bound && (i == max);
            int nrem = (rem * 10 + i) % K;

            int ncnt = cnt;
            if (firstPos) {
                if (i != 0 && i % 2 == 0)
                    ncnt++;
                else if (i % 2 == 1)
                    ncnt--;
            } else {
                if (i % 2 == 0)
                    ncnt++;
                else if (i % 2 == 1)
                    ncnt--;
            }

            boolean nfirstPos = firstPos && (i == 0);
            ans += numberOfBeautifulIntegers_(s, npos, nbound, nrem, ncnt, nfirstPos, dp);
        }

        return dp[pos][bound ? 1 : 0][rem][cnt + 10][firstPos ? 1 : 0] = ans;
    }

    // 880. Decoded String at Index
    public String decodeAtIndex(String s, int K) {
        long finalLen = 0l;
        for (int i = 0; i < s.length(); i++) {
            char chi = s.charAt(i);
            finalLen = chi >= '2' && chi <= '9' ? finalLen * (long) (chi - '0') : finalLen + 1l;
        }

        long k = (long) K;

        for (int i = s.length() - 1; i >= 0; i--) {
            char chi = s.charAt(i);

            if (chi >= '2' && chi <= '9') {
                finalLen /= (int) (chi - '0');
                k = k % finalLen;
            } else {

                if (k == 0 || (long) k == finalLen)
                    return "" + s.charAt(i);
                finalLen--;
            }
        }

        return "";

    }

    // 2719. Count of Integers
    public int count(String num1, String num2, int min_sum, int max_sum) {
        String l = num2;
        String r = getLeft(num1);
        dp = new Long[50][3][500];

        long a = count_(l, 0, true, 0, min_sum, max_sum);
        dp = new Long[50][3][500];
        long b = count_(r, 0, true, 0, min_sum, max_sum);

        return (int) ((a % mod - b % mod + mod) % mod);

    }

    Long[][][] dp;

    long mod = (long) 1e9 + 7;

    public long count_(String s, int pos, boolean bound, int sum, int min_sum, int max_sum) {
        if (pos == s.length()) {
            return min_sum <= sum && sum <= max_sum ? 1l : 0l;
        }

        if (dp[pos][bound ? 1 : 0][sum] != null)
            return dp[pos][bound ? 1 : 0][sum];

        long ans = 0l;

        int max = 9;
        if (bound) {
            max = (int) (s.charAt(pos) - '0');
        }

        for (int i = 0; i <= max; i++) {
            int nsum = sum + i;
            ans = (ans % mod + count_(s, pos + 1, bound && (i == max), nsum, min_sum, max_sum) % mod) % mod;

        }

        return dp[pos][bound ? 1 : 0][sum] = ans % mod;
    }

    public String getLeft(String s) {
        BigInteger l = new BigInteger(s);
        BigInteger sl = l.subtract(new BigInteger("1"));
        return sl.toString();
    }

    // 456. 132 Pattern
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3)
            return false;
        if (check(nums, true) || check(nums, false))
            return false;

        int[] min = new int[n];
        min[0] = nums[0];
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }

        TreeSet<Integer> set = new TreeSet<>();
        set.add(nums[n - 1]);

        // fixing ith and jth first, then finding kth -> jth to n-1;
        for (int i = n - 2; i > 0; i--) {
            int jth = nums[i];
            int ith = min[i - 1];

            if (jth > ith) {
                // finding the element btw [ith,jth] => both exclusive
                if (set.ceiling(ith + 1) != null) {
                    int kth = set.ceiling(ith + 1);
                    if (kth < jth)
                        return true;
                }

            }

            set.add(nums[i]);
        }

        return false;
    }

    public boolean check(int[] nums, boolean inc) {
        for (int i = 0; i + 1 < nums.length; i++) {
            if (nums[i] > nums[i + 1] && inc)
                return false;
            else if (nums[i] < nums[i + 1] && !inc)
                return false;
        }

        return true;
    }

}

// 1269. Number of Ways to Stay in the Same Place After Some Steps

class LC1269 {
    public int numWays(int steps, int arrLen) {
        long[][] dp = new long[steps + 1][steps + 1];
        for (long[] d1 : dp)
            Arrays.fill(d1, -1l);

        return (int) numWays_(arrLen, 0, steps, dp);

    }

    long mod = (long) 1e9 + 7;

    public long numWays_(int n, int pos, int steps, long[][] dp) {
        if (pos >= n || pos < 0)
            return 0l;

        if (steps == 0) {
            return pos == 0 ? 1l : 0l;
        }

        if (dp[pos][steps] != -1l) {
            return dp[pos][steps];
        }

        long ans = 0;

        ans = (ans % mod + numWays_(n, pos + 1, steps - 1, dp) % mod) % mod;
        ans = (ans % mod + numWays_(n, pos - 1, steps - 1, dp) % mod) % mod;
        ans = (ans % mod + numWays_(n, pos, steps - 1, dp) % mod) % mod;

        return dp[pos][steps] = ans;
    }
}

// 1361. Validate Binary Tree Nodes
class LC1361 {

    public int findPar(int u, int[] par) {
        if (par[u] == u)
            return u;
        else {
            int t = findPar(par[u], par);
            return par[u] = t;
        }
    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        ArrayList<int[]> edges = new ArrayList<>();
        int[] par = new int[n];
        Arrays.fill(par, -1);

        for (int i = 0; i < n; i++) {
            int u = i;
            if (leftChild[i] != -1) {
                int v = leftChild[i];
                if (par[v] != -1)
                    return false;
                par[v] = u;
                edges.add(new int[] { u, v });
            }

            if (rightChild[i] != -1) {
                int v = rightChild[i];
                if (par[v] != -1)
                    return false;
                par[v] = u;
                edges.add(new int[] { u, v });
            }
        }

        int tot = n;

        for (int i = 0; i < n; i++) {
            par[i] = i;
        }

        for (int[] ed : edges) {
            int u = ed[0];
            int v = ed[1];

            int p1 = findPar(u, par);
            int p2 = findPar(v, par);

            if (p1 == p2)
                return false;
            else {
                tot--;
                par[p2] = p1;
            }
        }

        return tot == 1;

    }
}

// 2050. Parallel Courses III

class LC2050 {
    public int minimumTime(int n, int[][] relations, int[] time) {
        ArrayList<Integer> graph[] = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();

        int[] indegree = new int[n + 1];

        for (int[] ed : relations) {
            int u = ed[0];
            int v = ed[1];
            graph[u].add(v);
            indegree[v]++;
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.add(new int[] { i, time[i - 1] }); // idx,completion time;
            }
        }

        int ans = 0;
        int[] timeTakenByChilren = new int[n + 1];

        while (q.size() != 0) {
            int s = q.size();
            while (s-- > 0) {
                int[] rem = q.remove();
                int idx = rem[0];
                int myTime = rem[1];
                ans = Math.max(ans, myTime);

                for (int v : graph[idx]) {
                    indegree[v]--;
                    timeTakenByChilren[v] = Math.max(timeTakenByChilren[v], myTime);

                    if (indegree[v] == 0) {
                        q.add(new int[] { v, timeTakenByChilren[v] + time[v - 1] });
                    }
                }
            }
        }

        return ans;
    }
}
