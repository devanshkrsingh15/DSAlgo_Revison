import java.util.*;

public class BiweeklyContest104 {
    // 2678. Number of Senior Citizens
    public int countSeniors(String[] details) {
        int cnt = 0;

        for (String s : details) {
            if (Integer.parseInt(s.substring(11, 13)) > 60)
                cnt++;
        }

        return cnt;
    }

    // 2679. Sum in a Matrix
    public int matrixSum(int[][] nums) {
        int n = nums.length;
        int m = nums[0].length;
        PriorityQueue<Integer> rowPQs[] = new PriorityQueue[n];
        for (int i = 0; i < n; i++)
            rowPQs[i] = new PriorityQueue<>((a, b) -> {
                return nums[b / m][b % m] - nums[a / m][a % m];
            });

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowPQs[i].add(i * m + j);
            }
        }

        int itr = 0;
        int ans = 0;
        while (itr++ < m) {
            int max = 0;
            for (int i = 0; i < n; i++) {
                int idx = rowPQs[i].remove();
                max = Math.max(max, nums[idx / m][idx % m]);
            }
            ans += max;
        }

        return ans;
    }

    // 2680. Maximum OR
    public long maximumOr(int[] nums, int k) {
        // it is always better to shift a number k times or multiple 2^k times rather
        // than one x times and other k-x times;
        // as we want the max OR, ie, set bit should be at max pos
        // 2^9 > 2^5 + 2^4 ALWAYS
        int n = nums.length;
        long[] prefixOr = getORSummation(nums, true);
        long[] suffixOr = getORSummation(nums, false);

        long max = 0l;
        for (int i = 0; i < n; i++) {
            long left = i == 0 ? 0 : prefixOr[i - 1];
            long right = i == n - 1 ? 0 : suffixOr[i + 1];
            long center = ((long) nums[i] << k);
            max = Math.max(max, ((left | center) | right));
        }
        return max;

    }

    public long[] getORSummation(int[] nums, boolean fromLeft) {
        int n = nums.length;
        long[] ans = new long[n];
        int st = fromLeft ? 0 : n - 1;
        ans[st] = nums[st];

        for (int i = 1; i < n; i++) {
            int curr = fromLeft ? i : n - i - 1;
            int next = fromLeft ? curr - 1 : curr + 1;
            ans[curr] = (ans[next] | (long) nums[curr]);
        }

        return ans;
    }

    // 2681. Power of Heroes
    public int sumOfPower(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        long mod = (long) 1e9 + 7;
        long ans = 0;
        long pre = 0;

        for (int i = 0; i < n; i++) {
            ans = (ans % mod + ((long) nums[i] % mod * ((long) nums[i] % mod * (long) nums[i] % mod) % mod) % mod)
                    % mod;
            ans = (ans % mod + (pre % mod * ((long) nums[i] % mod * (long) nums[i] % mod) % mod) % mod) % mod;
            pre = ((2 * pre) % mod + (long) nums[i] % mod) % mod;
        }

        return (int) (ans % mod);

    }
}
