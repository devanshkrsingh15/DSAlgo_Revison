import java.util.*;

public class WeeklyContest362 {

    // 2848. Points That Intersect With Cars
    public int numberOfPoints(List<List<Integer>> nums) {
        HashSet<Integer> set = new HashSet<>();
        for (List<Integer> l : nums) {
            for (int i = l.get(0); i <= l.get(1); i++) {
                set.add(i);
            }
        }

        return set.size();
    }

    // 2849. Determine if a Cell Is Reachable at a Given Time
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy)
            return t != 1;
        long xdis = (long) Math.abs((long) sx - (long) fx);
        long ydis = (long) Math.abs((long) sy - (long) fy);
        long totDis = Math.min(xdis, ydis) + Math.abs(xdis - ydis);

        return totDis <= t;

    }

    // 1658. Minimum Operations to Reduce X to Zero

    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int max = (int) 1e9;
        long[] lsum = new long[n];
        long[] rsum = new long[n];

        for (int i = 0; i < nums.length; i++) {
            int lidx = i;
            int ridx = n - 1 - i;

            lsum[lidx] = lidx - 1 >= 0 ? lsum[lidx - 1] + (long) nums[lidx] : (long) nums[lidx];
            rsum[ridx] = ridx + 1 < n ? rsum[ridx + 1] + (long) nums[ridx] : (long) nums[ridx];

            if (lsum[lidx] == (long) x) {
                max = Math.min(max, lidx + 1);
            }
            if (rsum[ridx] == (long) x) {
                max = Math.min(max, n - ridx);
            }
        }

        if (lsum[n - 1] < (long) x)
            return -1;

        Arrays.sort(rsum);

        for (int i = 0; i < n; i++) {
            if (lsum[i] < x) {
                int a = i + 1;
                int b = find(rsum, (long) x - lsum[i], i + 1);
                if (b != (int) 1e9)
                    max = Math.min(max, a + b);
            }

            if (rsum[i] < x) {
                int a = i + 1;
                int b = find(lsum, (long) x - rsum[i], i + 1);
                if (b != (int) 1e9)
                    max = Math.min(max, a + b);
            }

        }

        return max == (int) 1e9 ? -1 : max;
    }

    public int find(long[] arr, long tar, int i) {
        int n = arr.length;
        int lo = 0;
        int hi = n - 1 - i;

        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == tar) {
                ans = mid;
                hi = mid - 1;
            } else if (arr[mid] > tar) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans == -1 ? (int) 1e9 : ans + 1;
    }
}
