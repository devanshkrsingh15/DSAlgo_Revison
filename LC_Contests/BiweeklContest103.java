import java.util.*;

public class BiweeklContest103 {

    // 2656. Maximum Sum With Exactly K Elements
    public int maximizeSum(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int ele : nums)
            pq.add(ele);

        int score = 0;
        while (k-- > 0) {
            int ele = pq.remove();
            score += ele;
            pq.add(ele + 1);
        }

        return score;
    }

    // 2657. Find the Prefix Common Array of Two Arrays
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] arr = new int[n];

        HashSet<Integer> setA = new HashSet<>();
        HashSet<Integer> setB = new HashSet<>();

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            setA.add(A[i]);
            setB.add(B[i]);

            if (A[i] != B[i]) {
                if (setB.contains(A[i]))
                    cnt++;
            }
            if (setA.contains(B[i]))
                cnt++;

            arr[i] = cnt;

        }

        return arr;
    }

    // 2658. Maximum Number of Fish in a Grid
    int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int findMaxFish(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int omax = 0;

        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j] && grid[i][j] > 0) {
                    int max = dfs(grid, i, j, vis);
                    omax = Math.max(omax, max);
                }
            }
        }

        return omax;

    }

    public int dfs(int[][] grid, int i, int j, boolean[][] vis) {
        int n = grid.length;
        int m = grid[0].length;
        vis[i][j] = true;
        int sum = grid[i][j];

        for (int k = 0; k < direcs.length; k++) {
            int x = i + direcs[k][0];
            int y = j + direcs[k][1];

            if (x >= 0 && y >= 0 && x < n && y < m && !vis[x][y] && grid[x][y] > 0) {
                sum += dfs(grid, x, y, vis);
            }
        }

        return sum;
    }

    //2659. Make Array Empty
    
}