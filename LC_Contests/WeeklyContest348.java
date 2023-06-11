import java.util.*;

public class WeeklyContest348 {

    // 2716. Minimize String Length
    public int minimizedStringLength(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += Math.min(arr[i], 1);
        }

        return ans;
    }

    // 2717. Semi-Ordered Permutation
    public int semiOrderedPermutation(int[] nums) {
        int n = nums.length;
        int oPos = find(nums, 1, true);
        int nPos = find(nums, n, false);

        return oPos + n - 1 - nPos + (oPos > nPos ? -1 : 0);
    }

    public int find(int[] arr, int tar, boolean fromStart) {
        int n = arr.length;
        int pos = -1;
        for (int i = 0; i < n; i++) {
            int idx = fromStart ? i : n - i - 1;
            if (arr[idx] == tar) {
                pos = idx;
                break;
            }
        }

        return pos;
    }

    // 2718. Sum of Matrix After Queries
    public long matrixSumQueries(int N, int[][] queries) {
        long ans = 0;
        long rowCount = 0l;
        long colCount = 0l;
        long n = N;

        boolean[] carr = new boolean[N];
        boolean[] rarr = new boolean[N];

        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];
            long val = q[2];
            int idx = q[1];

            if (q[0] == 0) {
                if (colCount >= n || rarr[idx])
                    continue;
                ans += (long) val * (n - colCount);
                rowCount++;
                rarr[idx] = true;
            } else {
                if (rowCount >= n || carr[idx])
                    continue;
                ans += (long) val * (n - rowCount);
                colCount++;
                carr[idx] = true;
            }
        }

        return ans;
    }

    

}
