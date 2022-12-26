import java.util.*;

public class WeeklyContest325 {
    // 2515. Shortest Distance to Target String in a Circular Array
    public int closetTarget(String[] words, String target, int st) {
        st += words.length;
        words = build(words);
        int n = words.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                set.add(i);
            }
        }

        if (set.size() == 0)
            return -1;

        int f = set.floor(st) == null ? -(int) 1e8 : set.floor(st);
        int c = set.ceiling(st) == null ? (int) 1e8 : set.ceiling(st);

        return Math.min(st - f, c - st);
    }

    private String[] build(String[] words) {
        int n = words.length;
        // -----prev -----middle ---- frd
        String narr[] = new String[3 * n];
        for (int i = 0; i < 3 * n; i++) {
            narr[i] = words[i % n];
        }

        return narr;

    }

    // 2516. Take K of Each Character From Left and Right
    public int takeCharacters(String s, int k) {
        if (k == 0)
            return 0;
        int n = s.length();
        int[][] left_farr = buildFrequencyArray(s, true);
        int[][] right_farr = buildFrequencyArray(s, false);

        for (int i = 0; i < 3; i++) {
            if (left_farr[i][n - 1] < k)
                return -1;
        }

        int[][] foccc = new int[3][n];
        for (int[] d : foccc)
            Arrays.fill(d, n);
        for (int i = n - 1; i >= 0; i--) {
            for (int c = 0; c < 3; c++) {
                int f = right_farr[c][i];
                if (foccc[c][f] == n)
                    foccc[c][f] = i;
            }
        }


        int ans = n;
        for (int i = 0; i < n; i++) {
            int rsteps = n;
            for (int c = 0; c < 3; c++) {
                if (left_farr[c][i] < k) {
                    int diff = k - left_farr[c][i];
                    rsteps = Math.min(foccc[c][diff], rsteps);
                }
            }

            int lsteps = i + 1;
            rsteps = n - rsteps;

            ans = Math.min(ans, lsteps + rsteps);
        }

        foccc = new int[3][n];
        for (int[] d : foccc)
            Arrays.fill(d, n);
        for (int i = 0; i < n; i++) {
            for (int c = 0; c < 3; c++) {
                int f = left_farr[c][i];
                if (foccc[c][f] == n)
                    foccc[c][f] = i;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int lsteps = -1;
            for (int c = 0; c < 3; c++) {
                if (right_farr[c][i] < k) {
                    int diff = k - right_farr[c][i];
                    lsteps = Math.max(foccc[c][diff], lsteps);
                }
            }

            lsteps = lsteps + 1;
            int rsteps = n - i;

            ans = Math.min(ans, lsteps + rsteps);
        }

        return ans;
    }

    private int[][] buildFrequencyArray(String s, boolean isLeft) {
        int n = s.length();
        int[][] dp = new int[3][n]; // a,b,c

        for (int idx = 0; idx < n; idx++) {
            int i = isLeft ? idx : n - 1 - idx;
            char ch = s.charAt(i);
            dp[ch - 'a'][i] = 1;

            if ((i != 0 && isLeft) || (i != n - 1 && !isLeft)) {
                for (int j = 0; j < 3; j++) {
                    int next = isLeft ? i - 1 : i + 1;
                    dp[j][i] += dp[j][next];
                }
            }
        }

        return dp;
    }

}
