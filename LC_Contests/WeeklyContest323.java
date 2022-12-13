import java.util.*;

public class WeeklyContest323 {
    // 2500. Delete Greatest Value in Each Row
    public int deleteGreatestValue(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int ans = 0;
        for (int col = 0; col < m; col++) {
            int max = 0;
            for (int i = 0; i < n; i++) {
                int cmax = 0;
                for (int j = 0; j < m; j++) {
                    cmax = Math.max(cmax, grid[i][j]);
                }

                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == cmax) {
                        grid[i][j] = -1;
                        break;
                    }
                }

                max = Math.max(max, cmax);
            }

            ans += max;
        }

        return ans;

    }

    // 2501. Longest Square Streak in an Array
    public int longestSquareStreak(int[] nums) {
        HashSet<Long> set = new HashSet<>();
        for (int e : nums) {
            set.add((long) e);
        }

        int max = 0;
        for (long i = 2; i <= (long) 1e3; i++) {
            int cnt = 0;
            long t = i;
            while (t <= (long) 1e5 && set.contains(t)) {
                cnt++;
                t = t * t;
            }

            max = Math.max(max, cnt);
        }

        return max <= 1 ? -1 : max;
    }

}