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

}
