package DP;

import java.util.*;

public class TargetSet {
    // combination -> order matters
    // permutations -> order does not matter
    public int coinChangePerm_memo(int[] coins, int tar, int[] dp) {
        if (tar == 0)
            return 1;
        if (dp[tar] != -1)
            return dp[tar];
        int ans = 0;
        for (int ele : coins) {
            if (tar - ele >= 0) {
                ans += coinChangePerm_memo(coins, tar - ele, dp);
            }
        }
        return dp[tar] = ans;
    }

    public int coinChangePerm_tab(int[] coins, int tar) {
        int[] dp = new int[tar + 1];

        dp[0] = 1;
        for (int i = 1; i <= tar; i++) {
            for (int ele : coins) {
                if (i - ele >= 0)
                    dp[i] += dp[i - ele];
            }
        }

        return dp[tar];
    }

    public int coinChangeComb_tab(int[] coins, int tar) {
        int[] dp = new int[tar + 1];

        for (int ele : coins) {
            for (int i = ele; i <= tar; i++) {
                if (i - ele >= 0)
                    dp[i] += dp[i - ele];
            }
        }

        return dp[tar];
    }

    public int coinChangeComb_memo(int[] coins, int tar, int idx, int[][] dp) {
        if (tar == 0)
            return dp[idx][tar] = 1;

        if (dp[idx][tar] != -1)
            return dp[idx][tar];

        int ans = 0;

        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                ans += coinChangeComb_memo(coins, tar - coins[i], i, dp);
            }
        }

        return dp[idx][tar] = ans;
    }

    // 322. Coin Change
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        int res = coinChange_memo(dp, coins, amount);
        return res >= (int) 1e9 ? -1 : res;
    }

    private int coinChange_memo(int[] dp, int[] coins, int tar) {
        if (tar == 0)
            return dp[tar] = 0;
        if (dp[tar] != -1)
            return dp[tar];

        int min = (int) 1e9;

        for (int ele : coins) {
            if (tar - ele >= 0)
                min = Math.min(min, coinChange_memo(dp, coins, tar - ele));
        }

        return dp[tar] = min + 1;
    }

    public int coinChange_tab(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        Arrays.fill(dp, (int) 1e8);
        dp[0] = 0;
        for (int ele : coins) {
            for (int i = ele; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - ele] + 1);
            }
        }

        return dp[amount] >= (int) 1e8 ? -1 : dp[amount];
    }

    // Find number of solutions of a linear equation of n variables

    // 2x + y = 4
    // basically number of ways in which 2 and 1 gives rhs = 4
    public int solLinearEquations(int[] coeff, int tar) {
        int[] dp = new int[tar + 1];
        dp[0] = 1;

        for (int ele : coeff) {
            for (int i = ele; i <= tar; i++) {
                dp[i] += dp[i - ele];
            }
        }

        return dp[tar];
    }

    public void findAllSolLinearEquations(int[] coeff, int tar, int[] freq, int st, int otar) {
        if (tar == 0) {
            for (int i = 0; i < coeff.length; i++) {
                System.out.print(coeff[i] + "*" + freq[i]);
                if (i != coeff.length - 1)
                    System.out.print(" + ");
                else
                    System.out.print(" = ");
            }
            System.out.println(otar);

            return;
        }

        for (int i = st; i < coeff.length; i++) {
            if (tar - coeff[i] >= 0) {
                freq[i]++;
                findAllSolLinearEquations(coeff, tar - coeff[i], freq, i, otar);
                freq[i]--;
            }
        }
    }

    // Subset Sum Problem (GFG)
    public boolean isSubsetSum(int N, int arr[], int sum) {
        // code here
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return (isSubsetSum_memo(arr, sum, 0, dp) == 1);
    }

    public int isSubsetSum_memo(int[] arr, int tar, int idx, int[][] dp) {
        if (idx == 0 || tar == 0) {
            return dp[idx][tar] = (tar == 0) ? 1 : 0;
        }

        if (dp[idx][tar] != -1)
            return dp[idx][tar];

        boolean res = false;

        res = res || (isSubsetSum_memo(arr, tar, idx - 1, dp) == 1);
        if (tar - arr[idx - 1] >= 0)
            res = res || (isSubsetSum_memo(arr, tar - arr[idx], idx - 1, dp) == 1);

        return dp[idx][tar] = (res) ? 1 : 0;
    }

    public int isSubsetSum_tab(int[] arr, int tar) {
        int n = arr.length;
        int[][] dp = new int[n + 1][tar + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= tar; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = (j == 0) ? 1 : 0;
                } else {
                    int ans = 0;
                    ans += dp[i - 1][j];
                    if (j - arr[i - 1] >= 0)
                        ans += dp[i - 1][j - arr[i - 1]];
                    dp[i][j] = ans;
                }
            }
        }

        return dp[n][tar];
    }

    // dp is prefilled
    public void printAllTargetSum(int[] arr, int tar, int n, boolean[][] dp, String str) {
        if (tar == 0 || n == 0) {
            if (tar == 0)
                System.out.println(str);
            return;
        }

        if (dp[n - 1][tar]) {
            printAllTargetSum(arr, tar, n - 1, dp, str + " " + arr[n - 1]);
        }

        if (tar - arr[n - 1] >= 0 && dp[n - 1][tar - arr[n - 1]])
            printAllTargetSum(arr, tar - arr[n - 1], n - 1, dp, str + " " + arr[n - 1]);

        return;
    }

    // 416. Partition Equal Subset Sum
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int ele : nums)
            sum += ele;
        if (sum % 2 != 0)
            return false;
        int tar = sum / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][tar + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= tar; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = (j == 0) ? true : false;
                else {
                    dp[i][j] = dp[i - 1][j];
                    if (j - nums[i - 1] >= 0)
                        dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][tar];
    }

    // 494. Target Sum
    public int findTargetSumWays(int[] nums, int tar) {
        int sum = 0;
        for (int ele : nums)
            sum += ele;

        if (tar > sum || tar < -sum)
            return 0;
        int n = nums.length;
        int[][] dp = new int[n + 1][2 * sum + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        // we shift the origin as from 0 to sum to handle -ve cases
        // tar= tar + sum of ele
        return findTargetSumWays_memo(nums, sum + tar, sum, n, dp);

    }

    public int findTargetSumWays_memo(int[] nums, int ntar, int tar, int idx, int[][] dp) {
        if (idx == 0) {
            return dp[idx][tar] = (tar == ntar) ? 1 : 0;
        }

        if (dp[idx][tar] != -1)
            return dp[idx][tar];

        int ans = 0;
        int ele = nums[idx - 1];

        ans += findTargetSumWays_memo(nums, ntar, tar + ele, idx - 1, dp);
        ans += findTargetSumWays_memo(nums, ntar, tar - ele, idx - 1, dp);

        return dp[idx][tar] = ans;
    }
}
