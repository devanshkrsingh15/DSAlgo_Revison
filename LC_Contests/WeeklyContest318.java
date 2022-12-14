import java.lang.invoke.ConstantCallSite;
import java.util.*;

public class WeeklyContest318 {
    // 2460. Apply Operations to an Array
    public int[] applyOperations(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] = 2 * nums[i];
                nums[i + 1] = 0;
            }
        }

        int i = 0;
        int j = 0;

        while (j < n) {
            if (nums[j] == 0) {
                j++;
            } else {
                swap(nums, i, j);
                i++;
                j++;
            }
        }

        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    // 2461. Maximum Sum of Distinct Subarrays With Length K
    public long maximumSubarraySum(int[] nums, int k) {
        long ans = 0;
        int ei = 0;
        int si = 0;
        int[] vis = new int[(int) 1e5 + 10];
        int n = nums.length;
        long sof = 0;
        boolean dflag = false;
        boolean lflag = false;

        while (ei < n) {
            int ele = nums[ei++];
            vis[ele]++;
            if (vis[ele] == 2)
                dflag = true;
            if (ei - si > k)
                lflag = true;

            sof = sof + (long) ele;
            while (dflag || lflag) {
                int sele = nums[si++];
                vis[sele]--;
                sof = sof - (long) sele;
                if (dflag && vis[sele] == 1)
                    dflag = false;
                if (lflag && ei - si <= k)
                    lflag = false;
            }

            if (ei - si == k)
                ans = Math.max(ans, sof);
        }

        return ans;
    }

    // 2462. Total Cost to Hire K Workers
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        PriorityQueue<Integer> lpq = new PriorityQueue<>((a, b) -> {
            if (costs[a] == costs[b])
                return a - b;
            else
                return costs[a] - costs[b];
        });

        PriorityQueue<Integer> rpq = new PriorityQueue<>((a, b) -> {
            if (costs[a] == costs[b])
                return a - b;
            else
                return costs[a] - costs[b];
        });

        long ans = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < candidates; i++) {
            set.add(i);
            lpq.add(i);
        }

        if (candidates == n) {
            while (k-- > 0) {
                ans += (long) costs[lpq.remove()];
            }

            return ans;
        }

        for (int i = n - 1; i >= n - candidates; i--) {
            if (set.contains(i) == false) {
                set.add(i);
                rpq.add(i);
            }
        }

        int i = candidates;
        int j = n - candidates - 1;

        while (k-- > 0) {
            if (lpq.size() != 0 && rpq.size() != 0) {

                if (costs[lpq.peek()] <= costs[rpq.peek()]) {
                    ans += (long) costs[lpq.remove()];
                    if (i <= j) {
                        lpq.add(i++);
                    }
                } else {
                    ans += (long) costs[rpq.remove()];
                    if (i <= j) {
                        rpq.add(j--);
                    }
                }

            } else if (lpq.size() != 0) {
                ans += (long) costs[lpq.remove()];
                if (i <= j) {
                    lpq.add(i++);
                }
            } else {
                ans += (long) costs[rpq.remove()];
                if (i <= j) {
                    rpq.add(j--);
                }
            }
        }

        return ans;
    }

    // 2463. Minimum Total Distance Traveled

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        ArrayList<Long> listOfFactories = new ArrayList<>();
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> {
            return a[0] - b[0];
        });

        for (int[] f : factory) {
            int pos = f[0];
            int k = f[1];
            // System.out.println(pos);
            while (k-- > 0)
                listOfFactories.add((long) pos);
        }

        long[][] dp = new long[robot.size() + 1][listOfFactories.size() + 1];
        for (long[] d : dp)
            Arrays.fill(d, -1l);
        return minimumTotalDistance_(0, 0, listOfFactories, robot, dp);

    }

    private long minimumTotalDistance_(int i, int j, ArrayList<Long> listOfFactories, List<Integer> robot,
            long[][] dp) {
        if (i == robot.size())
            return 0;
        if (j == listOfFactories.size())
            return (long) 1e16;

        if (dp[i][j] != -1l)
            return dp[i][j];

        long rpos = (long) robot.get(i);
        long fpos = listOfFactories.get(j);

        long dis = Math.abs(fpos - rpos);
        long inc = minimumTotalDistance_(i + 1, j + 1, listOfFactories, robot, dp) + dis; // taking jth pos
        long exc = minimumTotalDistance_(i, j + 1, listOfFactories, robot, dp); // ignoring jth pos

        return dp[i][j] = Math.min(inc, exc);

    }

}
