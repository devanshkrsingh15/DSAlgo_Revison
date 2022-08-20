import java.util.*;

public class DSU {
    // can be used for finding cycle (min 3 edges) in bidirectional graph
    // Spanning tree -> all vtx connected but NO cycle exists

    // INVERSE ACKERMANN
    // path compression + size check => parent tree ht <= 4
    // O(alpha(N)) <= O(4)

    public int findParent(int par[], int u) {
        if (par[u] == u)
            return u;
        else {
            int temp = findParent(par, par[u]);
            par[u] = temp; // path compression
            return temp;
        }
    }

    public void UnionFindAlgo(int n, Edge edges[]) {
        int[] par = new int[n];
        int[] size = new int[n];

        ArrayList<Edge> spanningTree[] = new ArrayList[n]; // spanning tree

        for (int i = 0; i < n; i++)
            spanningTree[i] = new ArrayList<>();
        Arrays.fill(size, 1);
        for (int i = 0; i < n; i++)
            par[i] = i;

        boolean hasCycle = false;

        // TC = E + V*alpha(N)
        // TC = E + V*logN
        for (Edge e : edges) {
            int u = e.u;
            int v = e.v;

            int p1 = findParent(par, u);
            int p2 = findParent(par, v);

            if (p1 != p2) {
                merge(par, size, u, v, p1, p2);
                AddEdge(u, v, e.wt, spanningTree);
            } else {
                hasCycle = true;
            }
        }
    }

    private void AddEdge(int u, int v, int w, ArrayList<Edge>[] graph) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));
    }

    private void merge(int[] par, int[] size, int u, int v, int p1, int p2) {
        if (size[p1] >= size[p2]) {
            size[p1] += size[p2];
            par[p2] = p1;
        } else {
            size[p2] += size[p1];
            par[p1] = p1;
        }
    }

    // Leetcode 684. Redundant Connection
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] par = new int[n + 2];
        for (int i = 0; i < par.length; i++)
            par[i] = i;

        int[] ans = null;

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            int p1 = findParent(par, u);
            int p2 = findParent(par, v);

            if (p1 != p2) {
                par[p2] = p1;
            } else {
                ans = new int[] { u, v };
            }
        }

        return ans != null ? ans : new int[0];
    }

    // Leetcode 1061 Lexicographically Smallest Equivalent String
    public String smallestEquivalentString(String A, String B, String S) {
        int[] par = new int[26];
        for (int i = 0; i < 26; i++)
            par[i] = i;

        int len = A.length();
        for (int i = 0; i < len; i++) {
            char cha = A.charAt(i);
            char chb = B.charAt(i);

            int u = cha - 'a';
            int v = chb - 'a';

            int p1 = findParent(par, u);
            int p2 = findParent(par, v);

            if (p1 != p2) {
                par[p1] = Math.min(p1, p2);
                par[p2] = Math.min(p1, p2);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            int p = findParent(par, ch - 'a');
            sb.append((char) (p + 65));
        }

        return sb.toString();
    }

    // Leetcode 839. Similar String Groups
    public int numSimilarGroups(String[] strs) {
        int grps = strs.length;

        int n = strs.length;
        int[] par = new int[n];
        for (int i = 0; i < n; i++)
            par[i] = i;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (areSimilar(strs, i, j)) {
                    int p1 = findParent(par, i);
                    int p2 = findParent(par, j);

                    if (p1 != p2) {
                        par[p2] = p1;
                        grps--;
                    }
                }
            }
        }

        return grps;
    }

    public boolean areSimilar(String[] arr, int i, int j) {
        String a = arr[i];
        String b = arr[j];

        int cnt = 0;
        if (a.length() != b.length())
            return false;

        for (int idx = 0; idx < a.length(); idx++) {
            if (a.charAt(idx) != b.charAt(idx))
                cnt++;
            if (cnt > 2)
                return false;
        }

        return true;
    }

    class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        int[][] mat = new int[n][m];
        int[] par = new int[n * m];
        for (int i = 0; i < par.length; i++)
            par[i] = i;

        ArrayList<Integer> ans = new ArrayList<>();

        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        int cnt = 0;
        for (Point p : operators) {
            if (mat[p.x][p.y] == 0) {
                mat[p.x][p.y] = 1;
                cnt++;
                for (int k = 0; k < direcs.length; k++) {
                    int i = p.x + direcs[k][0];
                    int j = p.y + direcs[k][1];

                    if (i >= 0 && j >= 0 && i < n && j < m && mat[i][j] == 1) {
                        int p1 = findParent(par, p.x * m + p.y);
                        int p2 = findParent(par, i * m + j);

                        if (p1 != p2) {
                            cnt--;
                            par[p2] = p1;
                        }
                    }
                }
            }

            ans.add(cnt);
        }

        return ans;
    }

    // Leetcode 1168 - Optimize Water Distribution in a Village
    /*
     * There are n houses in a village. We want to supply water for all the houses
     * by building wells and laying pipes.
     * 
     * For each house i, we can either build a well inside it directly with cost
     * wells[i], or pipe in water from another well to it. The costs to lay pipes
     * between houses are given by the array pipes, where each pipes[i] = [house1,
     * house2, cost] represents the cost to connect house1 and house2 together using
     * a pipe. Connections are bidirectional.
     * 
     * Find the minimum total cost to supply water to all houses.
     */

    public int minCost(int n, int[] wells, int[][] pipes) {
        int[] par = new int[n + 2];
        // {u,v,w}
        ArrayList<int[]> list = new ArrayList<>();

        for (int[] p : pipes)
            list.add(p);

        for (int i = 0; i < wells.length; i++) {
            list.add(new int[] { i, n, wells[i] });
        }

        Collections.sort(list, (a, b) -> {
            return a[2] - b[2];
        });

        int mincost = 0;

        for (int[] rp : list) {

            int u = rp[0];
            int v = rp[1];
            int wt = rp[2];

            int p1 = findParent(par, u);
            int p2 = findParent(par, v);

            if (p1 != p2) {
                par[p2] = p1;
                mincost += wt;
            }
        }

        return mincost;
    }
}

class LeetcodeQs {
    int[][] direcs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    public boolean isValid(int i, int j, int n, int m) {
        return i >= 0 && j >= 0 && i < n && j < m;
    }

    public int findParent(int par[], int u) {
        if (par[u] == u)
            return u;
        else {
            int temp = findParent(par, par[u]);
            par[u] = temp; // path compression
            return temp;
        }
    }

    // 200. Number of Islands
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int cnt = 0;
        int[] par = new int[n * m];
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    cnt++;

                    for (int k = 0; k < 4; k++) {
                        int x = i + direcs[k][0];
                        int y = j + direcs[k][1];

                        if (isValid(x, y, n, m) && grid[x][y] == '1') {
                            int u = i * m + j;
                            int v = x * m + y;

                            int p1 = findParent(par, u);
                            int p2 = findParent(par, v);

                            if (p1 != p2) {
                                par[p2] = p1;
                                cnt--;
                            }

                        }
                    }
                }
            }
        }

        return cnt;
    }

    // 695. Max Area of Island
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[] par = new int[n * m];
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
        }
        int[] size = new int[n * m];
        Arrays.fill(size, 1);

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(1, max);
                    for (int k = 0; k < 4; k++) {
                        int x = i + direcs[k][0];
                        int y = j + direcs[k][1];

                        if (isValid(x, y, n, m) && grid[x][y] == 1) {
                            int u = i * m + j;
                            int v = x * m + y;

                            int p1 = findParent(par, u);
                            int p2 = findParent(par, v);

                            if (p1 != p2) {
                                par[p2] = p1;
                                size[p1] += size[p2];
                                max = Math.max(size[p1], max);
                            }

                        }
                    }
                }
            }
        }

        return max;
    }

    // HackerRank Journey to the Moon
    // https://www.hackerrank.com/challenges/journey-to-the-moon/problem
    public int journeyToMoon(int n, List<List<Integer>> astronaut) {
        int[] par = new int[n];
        int[] size = new int[n];
        Arrays.fill(size,1);
        for(int i  =0;i<n;i++)par[i] = i;

        for(List<Integer>tmp  : astronaut){
            int u = tmp.get(0);
            int v = tmp.get(1);

            int p1 = findParent(par, u);
            int p2 = findParent(par, v);

            if (p1 != p2) {
                par[p2]= p1;
                size[p1]+=size[p2];
            } 
        }

        int ans = 0;
        for(int i =0;i<n;i++){
            if(par[i]!=i) size[i ]= 0;
        }

        int sum = 0;
        for(int i =0 ;i<n;i++){
            sum+=size[i];
        }

        for(int i =0 ;i<n;i++){
            sum-=size[i];
            ans+=(sum*size[i]);
        }

        return ans;

    }

}