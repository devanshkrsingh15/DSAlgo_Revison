package Arrays_Strings;

import java.util.*;

public class Kadanes {
    // Maximum Contiguous (sub-array) sum

    // if max subarray is -ve return 0;
    public int KadanesAlgo(int[] nums) {
        int gSum = 0;
        int cSum = 0;

        for (int ele : nums) {
            cSum = Math.max(cSum + ele, 0);
            gSum = Math.max(gSum, cSum);
        }
        return gSum;
    }

    public int KadanesAlgoGeneric(int[] nums) {
        int gSum = -(int) 1e9;
        int cSum = 0;

        for (int ele : nums) {
            cSum = Math.max(cSum + ele, ele);
            gSum = Math.max(gSum, cSum);
        }
        return gSum;
    }

    public void KadanesAlgo_Subarray(int[] nums) {
        int gSum = -(int) 1e9;
        int cSum = -(int) 1e9;

        int gsi = -1;
        int gei = -1;

        int si = -1;
        int ei = -1;

        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int ele = nums[i];
            if (cSum + ele < ele) {
                cSum = ele;
                si = i;
                ei = i;
            } else {
                cSum += ele;
                ei = i;
            }

            if (cSum > gSum) {
                gSum = cSum;
                gsi = si;
                gei = ei;
            }
        }
    }

    // 1191. K-Concatenation Maximum Sum
    // if max subarray is -ve return 0;
    long mod = (long) 1e9 + 7;

    public int kConcatenationMaxSum(int[] arr, int k) {

        long currSum = 0;
        long prevSum = 0;

        for (int i = 1; i <= k; i++) {
            prevSum = currSum;
            currSum = kadanes(arr, i);

            if (i == k)
                return (int) (currSum % mod);
        }

        return (int) ((prevSum % mod + (currSum - prevSum) * (k - 2) % mod) % mod);
    }

    private long kadanes(int[] arr, int k) {
        int n = arr.length;
        long gsum = 0;
        long csum = 0;
        for (int i = 0; i < n * k; i++) {
            long ele = (long) arr[i % n];
            csum = Math.max(csum + ele, ele);
            gsum = Math.max(csum, gsum);
        }

        return gsum % mod;
    }

}
