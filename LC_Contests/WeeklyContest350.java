import java.util.*;

public class WeeklyContest350 {

    // 2739. Total Distance Traveled
    public int distanceTraveled(int mainTank, int additionalTank) {
        int ans = 0;
        while (mainTank >= 1) {
            int amt = Math.min(5, mainTank);
            mainTank -= amt;
            if (amt == 5) {
                if (additionalTank >= 1) {
                    additionalTank -= 1;
                    mainTank += 1;
                }
            }
            ans += amt * 10;
        }

        return ans;

    }

    // 2740. Find the Value of the Partition
    public int findValueOfPartition(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int min = nums[1] - nums[0];
        for (int i = 2; i < n; i++) {
            min = Math.min(nums[i] - nums[i - 1], min);
        }

        return min;
    }

    // 2741. Special Permutations
    long mod = (long) 1e9 + 7;
    long[][] dp;

    public int specialPerm(int[] nums) {
        long ans = 0;
        dp = new long[15][20000];
        for (long[] d : dp)
            Arrays.fill(d, -1l);

        for (int i = 0; i < nums.length; i++) {
            int vis = (1 << i);
            ans = (ans % mod + specialPerm_(nums, i, vis) % mod) % mod;
        }

        return (int) ans;
    }

    public long specialPerm_(int[] nums, int idx, int vis) {
        if (getCount(vis) == nums.length)
            return 1l;

        if (dp[idx][vis] != -1l)
            return dp[idx][vis];

        long ans = 0;
        int ele = nums[idx];
        for (int i = 0; i < nums.length; i++) {
            int mask = (1 << i);
            if ((vis & mask) == 0) {
                int nele = nums[i];
                if (ele % nele == 0 || nele % ele == 0) {
                    ans = (ans % mod + specialPerm_(nums, i, (vis | mask)) % mod) % mod;
                }
            }
        }

        return dp[idx][vis] = ans;
    }

    public int getCount(int vis) {
        int cnt = 0;
        while (vis != 0) {
            cnt++;
            vis = (vis & (vis - 1));
        }

        return cnt;
    }


    //2742. Painting the Walls
}
