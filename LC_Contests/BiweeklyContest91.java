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

    //2467. Most Profitable Path in a Tree
    
}