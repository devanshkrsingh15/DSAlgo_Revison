package DP;

import java.util.Arrays;

public class LIS {
    // important test case
    // {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15,14}

    // 300. Longest Increasing Subsequence
    public int lengthOfLIS(int[] nums) {
        int max = 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        for (int i = 0; i < n; i++) {
            max = Math.max(max, lengthOfLIS_memo(nums, dp, i));
        }
        return max;
    }

    public int lengthOfLIS_memo(int[] nums, int[] dp, int st) {
        if (dp[st] != -1)
            return dp[st];
        int max = 0;
        for (int i = 0; i < st; i++) {
            if (nums[i] < nums[st]) {
                max = Math.max(max, lengthOfLIS_memo(nums, dp, i));
            }
        }

        return dp[st] = max + 1;
    }

    public int lengthOfLIS_tab(int[] nums) {
        int max = 0;
        int n = nums.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // Q. minimum number of deletions to be performed to make an sorted array
    // -1e7 <=ele<= 1e7

    // A. => make lis with duplicated allowed
    public int minDeletions(int[] nums) {
        int max = 0;
        int n = nums.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] <= nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }
            max = Math.max(max, dp[i]);
        }

        int minDel = n - max;

        return minDel;
    }

    // Maximum Sum Increasing Subsequence (GFG)
    public int maxSumIncreasing(int[] arr) {
        // max sum of inc subsequence
        int n = arr.length;
        int[] dp = new int[n];
        int max = -(int) 1e9;
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }

            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // Max Sum of Longest increasing subsequence
    public int maxSumLIS(int[] arr) {
        int n = arr.length;
        int[] len = new int[n];
        int[] sum = new int[n];
        int maxSum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            len[i] = 1;
            sum[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    len[i] = Math.max(len[i], len[j] + 1);
                    sum[i] = Math.max(sum[i], sum[i] + sum[j]);
                }

            }
            if (max <= len[i]) {
                if (maxSum < sum[i]) {
                    max = len[i];
                    maxSum = sum[i];
                }
            }
        }
        return maxSum;
    }

}
