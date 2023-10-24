import java.util.*;

public class WeeklyContest365 {
    // 2873. Maximum Value of an Ordered Triplet I
    public long maximumTripletValue(int[] nums) {
        long ans = 0l;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // (nums[i] - nums[j]) * nums[k].
                    long val = (long) ((long) nums[i] - (long) nums[j]) * (long) nums[k];

                    ans = Math.max(ans, val);
                }
            }
        }

        return ans;
    }

    // 2874. Maximum Value of an Ordered Triplet II
    public long maximumTripletValue_(int[] nums) {
        int n = nums.length;
        long ans = 0l;

        int[] lmax = generateMaxTillNowArray(nums, true);
        int[] rmax = generateMaxTillNowArray(nums, false);

        for (int j = 1; j < n - 1; j++) {
            long jth = nums[j];
            long ith = lmax[j - 1];
            long kth = rmax[j + 1];
            ans = Math.max(ans, (ith - jth) * kth);
        }

        return ans;
    }

    public int[] generateMaxTillNowArray(int[] nums, boolean fromLeft) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[fromLeft ? 0 : n - 1] = nums[fromLeft ? 0 : n - 1];

        for (int i = 1; i < n; i++) {
            int idx = fromLeft ? i : n - i - 1;
            int nidx = fromLeft ? idx - 1 : idx + 1;

            ans[idx] = Math.max(ans[nidx], nums[idx]);
        }

        return ans;
    }

    // 2875. Minimum Size Sub-array in Infinite Array
    // suffix array ------- infinite copy of array ---- suffix array
    public int minSizeSubarray(int[] nums, int target) {
        int n = nums.length;
        long sum = 0l;
        for (int ele : nums)
            sum += (long) ele;

        int inc = 0;
        if ((long) target > sum) {
            inc = (int) (target / (int) sum);
            target = target % (int) sum;
        }

        int en = 0;
        int st = 0;

        long sof = 0;
        long minLen = (long) 1e9;

        while (st < 2 * n) {
            sof += (long) nums[st % n];
            st++;

            while (sof > (long) target) {
                sof -= (long) nums[en % n];
                en++;
            }

            if (sof == (long) target) {
                minLen = Math.min(minLen, st - en);
            }
        }

        return minLen == (long) 1e9 ? -1 : (int) minLen + inc * n;
    }

}