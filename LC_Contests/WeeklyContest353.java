import java.util.*;

import javax.lang.model.util.Elements;

public class WeeklyContest353 {

    // 2769. Find the Maximum Achievable Number
    public int theMaximumAchievableX(int num, int t) {
        return num + 2 * t;
    }

    // 2770. Maximum Number of Jumps to Reach the Last Index
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, ArrayList<Integer>> jumps = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long up = (long) target + (long) nums[i];
            long lp = -((long) target - (long) nums[i]);
            jumps.putIfAbsent(i, new ArrayList<>());
            for (int j = i + 1; j < n; j++) {
                if ((long) nums[j] >= lp && (long) nums[j] <= up) {
                    jumps.get(i).add(j);
                }
            }
        }

        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        int ans = maximumJumps_(n, 0, jumps, dp);
        return ans <= -(int) 1e9 ? -1 : ans;
    }

    public int maximumJumps_(int n, int idx, HashMap<Integer, ArrayList<Integer>> jumps, int[] dp) {
        if (idx == n - 1)
            return 0;

        if (dp[idx] != -1)
            return dp[idx];

        int ans = -(int) 1e9;

        for (int nidx : jumps.get(idx)) {
            int fans = maximumJumps_(n, nidx, jumps, dp);
            if (fans != -(int) 1e9)
                ans = Math.max(ans, maximumJumps_(n, nidx, jumps, dp) + 1);
        }

        return dp[idx] = ans;

    }

    // 2771. Longest Non-decreasing Subarray From Two Arrays
    int[][] dp;

    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        dp = new int[n + 1][4];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return maxNonDecreasingLength_(nums1, nums2, 0, 3);

    }

    // 3 - 0 -> all choices
    // 1 - num1
    // 2 - num2
    // calculatig subarray only so , making the call only when its valid, else that
    // subarray wont be continued;
    public int maxNonDecreasingLength_(int[] nums1, int[] nums2, int idx, int last) {
        if (idx == nums1.length)
            return 0;
        if (dp[idx][last] != -1)
            return dp[idx][last];

        int ans = 0;
        if (last == 3) {
            ans = Math.max(ans, maxNonDecreasingLength_(nums1, nums2, idx + 1, 1) + 1); // sequence start at nums1[idx]
            ans = Math.max(ans, maxNonDecreasingLength_(nums1, nums2, idx + 1, 2) + 1); // sequence start at nums2[idx]
            ans = Math.max(ans, maxNonDecreasingLength_(nums1, nums2, idx + 1, 3)); // sequence start at next idx
        } else {
            int lele = last == 1 ? nums1[idx - 1] : nums2[idx - 1];
            if (nums1[idx] >= lele) {
                ans = Math.max(ans, maxNonDecreasingLength_(nums1, nums2, idx + 1, 1) + 1);
            }

            if (nums2[idx] >= lele) {
                ans = Math.max(ans, maxNonDecreasingLength_(nums1, nums2, idx + 1, 2) + 1);
            }
        }

        return dp[idx][last] = ans;
    }

    // 2772. Apply Operations to Make All Array Elements Equal to Zero
    public boolean checkArray(int[] nums, int k) {
       

    }
}
