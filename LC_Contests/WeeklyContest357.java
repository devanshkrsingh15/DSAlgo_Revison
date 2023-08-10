import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class WeeklyContest357 {

    // 2810. Faulty Keyboard
    public String finalString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch != 'i')
                sb.append(ch);
            else {
                sb = sb.reverse();
            }
        }

        return sb.toString();
    }

    // 2811. Check if it is Possible to Split Array
    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        int[] psum = new int[n];
        for (int i = 0; i < n; i++) {
            psum[i] = nums.get(i);
            if (i - 1 >= 0)
                psum[i] += psum[i - 1];
        }

        int[][] dp = new int[n + 1][n + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return canSplitArray_(nums, 0, n - 1, m, dp, psum) == n;

    }

    public int canSplitArray_(List<Integer> nums, int st, int en, int m, int[][] dp, int[] psum) {
        if (en - st + 1 <= 2) {
            return en - st + 1;
        }

        if (dp[st][en] != -1)
            return dp[st][en];

        int ans = 0;

        if (psum[en] - psum[st] >= m) {
            ans = Math.max(ans, canSplitArray_(nums, st + 1, en, m, dp, psum) + 1);
        }

        if ((psum[en - 1] - (st == 0 ? 0 : psum[st - 1])) >= m) {
            ans = Math.max(ans, canSplitArray_(nums, st, en - 1, m, dp, psum) + 1);
        }

        return dp[st][en] = ans;
    }

    // 2812. Find the Safest Path in a Grid
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int m = grid.get(0).size();

        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(m - 1) == 1)
            return 0;
        int[][] sf = generateClosenessArray(grid);
        int ans = maximumSafenessFactor_(grid, sf, new boolean[n][m]);
        return ans == -(int) 1e9 ? 0 : ans;

    }

    public int[][] generateClosenessArray(List<List<Integer>> grid) {
        int n = grid.size();
        int m = grid.get(0).size();

        int[][] sf = new int[n][m];
        for (int[] arr : sf)
            Arrays.fill(arr, -1);

        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid.get(i).get(j) == 1) {
                    q.add(new int[] { i * m + j, i * m + j }); // one index, my index
                }
            }
        }

        boolean[][] vis = new boolean[n][m];
        int level = 0;

        while (q.size() != 0) {
            int s = q.size();
            while (s-- > 0) {
                int arr[] = q.remove();
                int ridx = arr[1];
                int oidx = arr[0];
                int r = ridx / m;
                int c = ridx % m;

                if (vis[r][c])
                    continue;

                vis[r][c] = true;
                if (sf[r][c] == -1)
                    sf[r][c] = oidx;

                for (int k = 0; k < direcs.length; k++) {
                    int x = r + direcs[k][0];
                    int y = c + direcs[k][1];
                    if (x >= 0 && y >= 0 && x < n && y < m && !vis[x][y] && grid.get(x).get(y) == 0) {
                        q.add(new int[] { oidx, x * n + y });
                    }
                }
            }
            level++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sf[i][j] = getSafenessFactor(i * n + j, sf[i][j], n, m);
            }
        }

        return sf;

    }

    int[][] direcs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    public int maximumSafenessFactor_(List<List<Integer>> grid, int[][] sf, boolean[][] vis) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[1] - a[1];
        }); // idx, sf

        int n = grid.size();
        int m = grid.get(0).size();

        pq.add(new int[] { 0, sf[0][0] });

        while (pq.size() != 0) {
            int s = pq.size();

            while (s-- > 0) {
                int[] arr = pq.remove();

                int idx = arr[0];
                int sff = arr[1];

                int r = idx / m;
                int c = idx % m;

                if (r == n - 1 && c == m - 1) {
                    return sff;
                }

                if (vis[r][c])
                    continue;

                vis[r][c] = true;

                for (int k = 0; k < direcs.length; k++) {
                    int x = r + direcs[k][0];
                    int y = c + direcs[k][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && !vis[x][y] && grid.get(x).get(y) == 0) {

                        pq.add(new int[] { x * m + y, Math.min(sf[x][y], sff) });
                    }
                }
            }
        }

        return -(int) 1e9;

    }

    public int getSafenessFactor(int idx, int tidx, int n, int m) {
        int r = idx / m;
        int c = idx % m;
        int tr = tidx / m;
        int tc = tidx % m;
        int sf = Math.abs(r - tr) + Math.abs(c - tc);
        return sf;
    }
}