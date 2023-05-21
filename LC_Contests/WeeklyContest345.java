import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class WeeklyContest345 {

    // 2682. Find the Losers of the Circular Game
    public int[] circularGameLosers(int n, int k) {
        boolean[] vis = new boolean[n];
        int turn = 1;
        int pos = 0;
        int cnt = 0;
        while (true) {
            int steps = turn * k;
            if (vis[pos])
                break;
            else {
                cnt++;
                vis[pos] = true;
            }
            pos = (pos + steps) % n;
            turn++;
        }

        int[] ans = new int[n - cnt];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i])
                ans[idx++] = i + 1;
        }

        return ans;
    }

    // 2683. Neighboring Bitwise XOR
    int[][][] dp;

    public boolean doesValidArrayExist(int[] derived) {

        int n = derived.length;
        if (n == 1)
            return derived[0] == 0;
        dp = new int[n][3][3];
        for (int[][] d2 : dp) {
            for (int[] d1 : d2) {
                Arrays.fill(d1, -1);
            }
        }

        return doesValidArrayExist_(derived, 0, 2, 2) == 1;

    }

    public int doesValidArrayExist_(int[] der, int idx, int currBit, int stBit) {
        int n = der.length;
        if (idx == n - 1) {
            return dp[idx][currBit][stBit] = check(der[idx], currBit, stBit) ? 1 : 0;
        }

        if (dp[idx][currBit][stBit] != -1)
            return dp[idx][currBit][stBit];

        boolean res = false;

        int xor = der[idx];
        if (idx == 0) {
            if (xor == 0) {
                res = res || (doesValidArrayExist_(der, idx + 1, 0, 0) == 1);
                res = res || (doesValidArrayExist_(der, idx + 1, 1, 1) == 1);
            } else {
                res = res || (doesValidArrayExist_(der, idx + 1, 1, 0) == 1);
                res = res || (doesValidArrayExist_(der, idx + 1, 0, 1) == 1);
            }
        } else {
            if (xor == 0) {
                // 1,1 or 0,0
                if (currBit == 1) {
                    res = res || (doesValidArrayExist_(der, idx + 1, 1, stBit) == 1);
                } else {
                    res = res || (doesValidArrayExist_(der, idx + 1, 0, stBit) == 1);
                }
            } else {
                // 1,0 or 0,1
                if (currBit == 1) {
                    res = res || (doesValidArrayExist_(der, idx + 1, 0, stBit) == 1);
                } else {
                    res = res || (doesValidArrayExist_(der, idx + 1, 1, stBit) == 1);
                }
            }
        }
        return dp[idx][currBit][stBit] = res ? 1 : 0;

    }

    public boolean check(int xor, int a, int b) {
        if (xor == 0) {
            return (a == 0 && b == 0) || (a == 1 && b == 1);
        } else {
            return (a == 1 && b == 0) || (a == 0 && b == 1);
        }
    }

    // 2684. Maximum Number of Moves in a Grid
    public int maxMoves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int[][] vis = new int[n][m];
        for (int v[] : vis)
            Arrays.fill(v, -1);

        for (int i = 0; i < n; i++) {
            q.add(i * m);
            vis[i][0] = 0;
        }
        int level = 0;

        int[][] direcs = { { 0, 1 }, { -1, 1 }, { 1, 1 } };

        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                int idx = q.remove();
                int r = idx / m;
                int c = idx % m;

                for (int k = 0; k < direcs.length; k++) {
                    int x = r + direcs[k][0];
                    int y = c + direcs[k][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] > grid[r][c]) {
                        if (vis[x][y] == -1) {
                            q.add(x * m + y);
                            vis[x][y] = level + 1;
                        } else if (level + 1 > vis[x][y]) {
                            q.add(x * m + y);
                            vis[x][y] = level + 1;
                        }
                    }

                }
            }

            level++;
        }

        return level - 1;
    }

    // 2685. Count the Number of Complete Components
    public int countCompleteComponents(int n, int[][] edges) {
        HashMap<Integer, HashSet<Integer>> graph = buildGraph(n, edges);
        int cnt = 0;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                HashSet<Integer> vtx = new HashSet<>();
                dfs(graph, vis, i,vtx);
                if (check(graph, vtx))
                    cnt++;
            }
        }

        return cnt;
    }

    public void dfs( HashMap<Integer, HashSet<Integer>> graph,boolean[] vis ,int src, HashSet<Integer> vtx){
        vis[src]= true;
        vtx.add(src);
        for(int nbr : graph.get(src)){
            if(vis[nbr]==false){
                dfs(graph,vis,nbr,vtx);
            }
        }
    }

    public boolean check( HashMap<Integer,HashSet<Integer>>graph,HashSet<Integer>vtx ){
        for(int src : vtx){
            for(int nbr :vtx ){
                if(src!=nbr){
                    if(!graph.get(src).contains(nbr)) return false;
                }
            }
        }

        return true;
    }

    private HashMap<Integer, HashSet<Integer>> buildGraph(int n, int[][] edges) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.put(i, new HashSet<>());

        for (int[] ed : edges) {
            int u = ed[0];
            int v = ed[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;

    }

}
