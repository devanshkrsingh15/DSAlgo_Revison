import java.util.*;

public class WeeklyContest366 {
    // 458. Poor Pigs
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int trials = minutesToTest / minutesToDie;

        /*
         * if we have 1 pig
         * and trials 1 , max buckets that can be checked 2,
         * trials 2, max buckets that can be checked 3
         * trials 3, max buckets that can be checked 4 ..... and so on
         * 
         * for 1 pig, max buckets = trials + 1
         * for n pigs, max buckets = (trials + 1)^n
         * 
         * we have find min val of n, such that (trials + 1)^n >= given buckets
         */

        int pigs = 0;
        while ((int) Math.pow(trials + 1, pigs) < buckets)
            pigs++;

        return pigs;
    }

    // 2894. Divisible and Non-divisible Sums Difference
    public int differenceOfSums(int n, int m) {
        int num1 = 0;
        int num2 = 0;

        for (int i = 1; i <= n; i++) {
            if (i % m == 0) {
                num2 += i;
            } else {
                num1 += i;
            }
        }

        return num1 - num2;
    }

    // 2895. Minimum Processing Time
    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        Collections.sort(processorTime);
        Collections.sort(tasks);

        int timeTaken = 0;
        int pidx = 0;
        int coresUsed = 0;

        for (int i = tasks.size() - 1; i >= 0; i--) {
            timeTaken = Math.max(processorTime.get(pidx) + tasks.get(i), timeTaken);
            coresUsed++;

            if (coresUsed == 4) {
                pidx++;
                coresUsed = 0;
            }
        }

        return timeTaken;
    }

    // 2896. Apply Operations to Make Two Strings Equal
    int X;

    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        ArrayList<Integer> diffIdx = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffIdx.add(i);
            }
        }

        int len = diffIdx.size();

        if (len == 0)
            return 0;
        if (len % 2 == 1)
            return -1;

        X = x;

        int[][] dp = new int[len + 1][len + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return minOperations_(diffIdx, 0, len - 1, dp);
    }

    public int minOperations_(ArrayList<Integer> diffIdx, int i, int j, int[][] dp) {
        int len = diffIdx.size();

        if (i > len || j < 0 || i > j)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        int ans = (int) 1e9 + 7;

        if (i + 1 < len) {
            ans = Math.min(ans, diffIdx.get(i + 1) - diffIdx.get(i) + minOperations_(diffIdx, i + 2, j, dp));
        }

        if (j - 1 >= 0) {
            ans = Math.min(ans, diffIdx.get(j) - diffIdx.get(j - 1) + minOperations_(diffIdx, i, j - 2, dp));
        }

        ans = Math.min(ans, X + minOperations_(diffIdx, i + 1, j - 1, dp));

        return dp[i][j] = ans;
    }

}
