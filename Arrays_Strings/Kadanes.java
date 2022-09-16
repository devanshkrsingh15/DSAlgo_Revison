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

    public int Kadanes(int[] arr) {
        int gsum = -(int) 1e9;
        int csum = -(int) 1e9;

        for (int ele : arr) {
            csum = Math.max(csum + ele, ele);
            gsum = Math.max(gsum, csum);
        }

        return gsum;
    }

    // Maximum sum Rectangle (GFG)
    public int maximumSumRectangle(int R, int C, int M[][]) {
        // code here
        int n = M.length;
        int m = M[0].length;
        int max = -(int) 1e9;

        for (int fixed = 0; fixed < n; fixed++) {
            int[] arr = new int[m];
            for (int i = fixed; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[j] += M[i][j];
                }

                max = Math.max(max, Kadanes(arr));
            }
        }

        return max;

    }

    public int maximumSumRectangle_Coordinate(int R, int C, int M[][]) {
        // code here
        int n = M.length;
        int m = M[0].length;
        int max = -(int) 1e9;
        int gsr = -1;
        int gsc = -1;
        int ger = -1;
        int gec = -1;

        for (int fixed = 0; fixed < n; fixed++) {
            int[] arr = new int[m];
            for (int i = fixed; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[j] += M[i][j];
                }

                int[] maxArr = kadanes_coordinates(arr);
                int maxSum = maxArr[0];
                int si = maxArr[1];
                int ei = maxArr[2];

                if (maxSum > max) {
                    gsr = fixed;
                    ger = i;
                    gsc = si;
                    gec = ei;
                    max = maxSum;
                }
            }
        }

        // printing the matrix

        for (int i = gsr; i <= ger; i++) {
            for (int j = gsc; j <= gec; j++) {
                System.out.print(M[i][j] + "\t");
            }
            System.out.println();
        }

        return max;

    }

    private int[] kadanes_coordinates(int[] nums) {
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

        return new int[] { gSum, gsi, gei };
    }

    // 1074. Number of Submatrices That Sum to Target
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = 0;
        for (int fixed = 0; fixed < n; fixed++) {
            int[] arr = new int[m];
            for (int i = fixed; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[j] += matrix[i][j];
                }

                ans += numSubarraySumTar(arr, target);
            }
        }

        return ans;
    }

    private int numSubarraySumTar(int[] arr, int target) {
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sof = 0;
        int cnt = 0;

        for (int ele : arr) {
            sof += ele;
            cnt += map.getOrDefault(sof - target, 0);
            map.put(sof, map.getOrDefault(sof, 0) + 1);
        }

        return cnt;
    }
}
