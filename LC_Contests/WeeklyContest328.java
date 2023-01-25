import java.util.*;

public class WeeklyContest328 {

    // 2535. Difference Between Element Sum and Digit Sum of an Array
    public int differenceOfSum(int[] nums) {
        int sof = 0;
        int dig = 0;

        for (int ele : nums) {
            sof += ele;
            dig += getSum(ele);
        }

        return Math.abs(sof - dig);

    }

    private int getSum(int ele) {
        int sof = 0;
        while (ele != 0) {
            sof += ele % 10;
            ele /= 10;

        }

        return sof;
    }

    // 2536. Increment Submatrices by One

    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] ans = new int[n][n];

        for (int[] q : queries) {
            int sr = q[0];
            int sc = q[1];
            int er = q[2];
            int ec = q[3];

            for (int r = sr; r <= er; r++) {
                ans[r][sc]++;
                if (ec + 1 < n)
                    ans[r][ec + 1]--;
            }
        }

        for (int i = 0; i < n; i++) {
            int sof = 0;
            for (int j = 0; j < n; j++) {
                sof += ans[i][j];
                ans[i][j] = sof;
            }
        }

        return ans;
    }

    // 2537. Count the Number of Good Subarrays
    public long countGood(int[] nums, int k) {
        HashMap<Integer, Long> map = new HashMap<>();
        long cnt = 0;
        int en = 0;
        int st = 0;
        long ans = 0l;
        int n = nums.length;
        while (en < n) {
            int ele = nums[en];
            cnt += map.getOrDefault(ele, 0l);
            map.put(ele, map.getOrDefault(ele, 0l) + 1l);

            while (cnt >= (long) k) {
                ans += (long) (n - en);
                int sele = nums[st];
                map.put(sele, map.get(sele) - 1l);
                cnt -= map.get(sele);
                if (map.get(sele) == 0)
                    map.remove(sele);
                st++;
            }
            en++;
        }

        return ans;
    }

}
