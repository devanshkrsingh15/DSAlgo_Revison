import java.util.*;

public class WeeklyContest334 {

    // 2574. Left and Right Sum Differences
    public int[] leftRigthDifference(int[] nums) {
        int n = nums.length;
        int[] lsum = new int[n];
        lsum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            lsum[i] += lsum[i - 1] + nums[i];
        }

        int[] rsum = new int[n];
        rsum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rsum[i] += rsum[i + 1] + nums[i];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int left = i == 0 ? 0 : lsum[i - 1];
            int right = i == n - 1 ? 0 : rsum[i + 1];

            ans[i] = Math.abs(left - right);
        }

        return ans;
    }

    // 2575. Find the Divisibility Array of a String
    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] ans = new int[n];
        long temp = 0;

        for (int i = 0; i < n; i++) {
            temp = (temp * 10l % m + (word.charAt(i) - '0') % m) % m;
            ans[i] = (temp) == 0 ? 1 : 0;
        }

        return ans;
    }

    // 2576. Find the Maximum Number of Marked Indices
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;

        int n = nums.length;
        int i = 0;
        int j = n / 2;

        while (i < n / 2 && j < n) {
            if (nums[i] * 2 <= nums[j]) {
                ans += 2;
                i++;
                j++;
            } else {
                j++;
            }
        }
        return ans;
    }

    // 2577. Minimum Time to Visit a Cell In a Grid
    public int minimumTime(int[][] grid) {
        if (grid[0][1] > 1 && grid[1][0] > 1)
            return -1;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        }); // idx, time
        pq.add(new int[] { 0, 0 });

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];
        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        while (pq.size() != 0) {
            int s = pq.size();
            while (s-- > 0) {
                int[] rarr = pq.remove();
                int ridx = rarr[0];
                int rtime = rarr[1];

                if (ridx == ((n * m) - 1))
                    return rtime;
                int r = ridx / m;
                int c = ridx % m;

                if (vis[r][c])
                    continue;
                vis[r][c] = true;

                for (int k = 0; k < direcs.length; k++) {

                    int x = r + direcs[k][0];
                    int y = c + direcs[k][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && !vis[x][y]) {
                        int jumpingFacctor = (grid[x][y] - rtime) % 2 == 0 ? 1 : 0;
                        pq.add(new int[] { x * m + y, Math.max(rtime + 1, jumpingFacctor + grid[x][y]) });
                    }
                }

            }
        }

        return -1;
    }
}
