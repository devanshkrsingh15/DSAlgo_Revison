
//2481. Minimum Cuts to Divide a Circle
import java.util.*;

public class BiweeklyContest92 {
    // 2481. Minimum Cuts to Divide a Circle
    public int numberOfCuts(int n) {
        if (n == 1)
            return 0;
        if (n % 2 == 0)
            return n / 2;
        return n;
    }

    // 2482. Difference Between Ones and Zeros in Row and Column
    public int[][] onesMinusZeros(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[] onesRow = new int[n];
        int[] onesCol = new int[m];

        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    cnt++;
            }
            onesRow[i] = cnt;
        }

        for (int j = 0; j < m; j++) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (grid[i][j] == 1)
                    cnt++;
            }
            onesCol[j] = cnt;
        }

        int[][] ans = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int a = onesRow[i];
                int b = onesCol[j];

                int c = n - a;
                int d = m - b;

                ans[i][j] = a + b - c - d;

            }
        }

        return ans;

    }

    // 2483. Minimum Penalty for a Shop
    public int bestClosingTime(String customers) {

        int n = customers.length();
        int[] ysum = new int[n];
        ysum[n - 1] = customers.charAt(n - 1) == 'Y' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            ysum[i] = customers.charAt(i) == 'Y' ? 1 : 0;
            ysum[i] += ysum[i + 1];
        }

        int nsum = 0;
        int ans = (int) 1e9;
        int ansIdx = -1;
        for (int i = 0; i < n; i++) {
            int cans = nsum + ysum[i];
            if (ans > cans) {
                ans = cans;
                ansIdx = i;
            }
            nsum += (customers.charAt(i) == 'N' ? 1 : 0);
        }

        if (ans > nsum) {
            ans = nsum;
            ansIdx = n;
        }

        return ansIdx;
    }

    // 2484. Count Palindromic Subsequences
    public int countPalindromes(String s) {
        int n = s.length();
        int[][][] preffixDigFreq = getFreq(s, true);
        int[][][] suffixDigFreq = getFreq(s, false);

        long ans = 0;
        long mod = (long) 1e9 + 7;
        for (int i = 2; i <= n - 3; i++) {
            // j-k-c-k-j
            // char c = s.charAt(i)-'0';
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {

                    ans = ans % mod
                            + ((long) preffixDigFreq[i - 1][j][k] % mod * (long) suffixDigFreq[i + 1][j][k] % mod)
                                    % mod;

                }
            }
        }

        return (int) ans;
    }

    public int[][][] getFreq(String s, boolean flag) {
        int n = s.length();
        int[][][] ans = new int[n][10][10];
        int[] cnt = new int[10];

        for (int idx = 0; idx < n; idx++) {
            int i = (flag) ? idx : n - idx - 1;
            int cd = s.charAt(i) - '0';

            if ((flag) ? i - 1 >= 0 : i + 1 < n) {
                for (int j = 0; j < 10; j++) {
                    for (int k = 0; k < 10; k++) {
                        ans[i][j][k] = ans[(flag) ? i - 1 : i + 1][j][k];
                        if (k == cd)
                            ans[i][j][k] += cnt[j];
                    }
                }
            }

            cnt[cd]++;
        }

        return ans;
    }
}