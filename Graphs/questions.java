import java.util.*;


public class questions {
    // Leetcode 200. Number of Islands
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        boolean[][] vis = new boolean[n][m];

        int cnt = 0;
        ;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && !vis[i][j]) {
                    cnt++;

                    numIslands_(grid, i, j, direcs, vis);
                }
            }
        }

        return cnt;

    }

    private void numIslands_(char[][] grid, int i, int j, int[][] direcs, boolean[][] vis) {
        vis[i][j] = true;
        for (int k = 0; k < direcs.length; k++) {
            int x = i + direcs[k][0];
            int y = j + direcs[k][1];
            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && !vis[x][y] && grid[x][y] == '1') {
                numIslands_(grid, x, y, direcs, vis);
            }
        }
    }

    // Leetcode 695. Max Area of Island
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        boolean[][] vis = new boolean[n][m];

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {

                    max = Math.max(max, maxAreaOfIsland_(grid, i, j, direcs, vis));
                }
            }
        }

        return max;
    }

    private int maxAreaOfIsland_(int[][] grid, int i, int j, int[][] direcs, boolean[][] vis) {
        int cnt = 1;
        vis[i][j] = true;
        for (int k = 0; k < direcs.length; k++) {
            int x = i + direcs[k][0];
            int y = j + direcs[k][1];
            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && !vis[x][y] && grid[x][y] == 1) {
                cnt += maxAreaOfIsland_(grid, x, y, direcs, vis);
            }
        }

        return cnt;
    }

    // Leetcode 463. Island Perimeter
    public int islandPerimeter(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int perimeter = 0;

        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        // perimeter = perimeter of 1 node - surrounding nbr
        // 4xTotal Node - Number of Valid Nbr
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;

                    for (int k = 0; k < direcs.length; k++) {
                        int x = i + direcs[k][0];
                        int y = j + direcs[k][1];
                        if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1) {
                            perimeter--; // subtracting common parts
                        }
                    }

                }
            }
        }

        return perimeter;

    }

    // Leetcode 130. Surrounded Regions
    public void SurroundedRegions(char[][] board) {
        // to capture all regions that are 4-directionally surrounded by 'X'.

        int n = board.length;
        int m = board[0].length;
        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        checkAndModifyBoundaries(board, 'O', '#', direcs);

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (board[i][j] == 'O') {
                    dfs(board, 'O', 'X', i, j, direcs);
                }
            }
        }

        checkAndModifyBoundaries(board, '#', 'O', direcs);

    }

    private void checkAndModifyBoundaries(char[][] board, char oc, char nc, int[][] direcs) {
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            if (board[i][0] == oc)
                dfs(board, oc, nc, i, 0, direcs);
            if (board[i][m - 1] == oc)
                dfs(board, oc, nc, i, m - 1, direcs);
        }

        for (int j = 0; j < m; j++) {
            if (board[0][j] == oc)
                dfs(board, oc, nc, 0, j, direcs);
            if (board[n - 1][j] == oc)
                dfs(board, oc, nc, n - 1, j, direcs);
        }
    }

    private void dfs(char[][] board, char oc, char nc, int i, int j, int[][] direcs) {
        board[i][j] = nc;

        for (int k = 0; k < direcs.length; k++) {
            int x = i + direcs[k][0];
            int y = j + direcs[k][1];
            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == oc) {
                dfs(board, oc, nc, x, y, direcs);
            }
        }

    }

    // Leetcode 785. Is Graph Bipartite?
    // basically distribute in two sets
    /*
     * -1 -> unvisited
     * 1 -> setA
     * 0 -> setB
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                boolean res = isBipartite_(graph, i, vis);
                if (!res)
                    return false;
            }
        }

        return true;
    }

    public boolean isBipartite_(int[][] graph, int src, int[] vis) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(src);

        int level = 0;
        while (q.size() != 0) {
            int s = q.size();
            while (s-- > 0) {
                int rv = q.remove();
                if (vis[rv] != -1) {
                    if (vis[rv] != level)
                        return false;
                    continue;
                }
                vis[rv] = level;

                for (int nbr : graph[rv]) {
                    if (vis[nbr] == -1) {
                        q.add(nbr);
                    }
                }
            }
            level = (level + 1) % 2;
        }

        return true;

    }

    // Leetcode 994. Rotting Oranges
    // 0 representing an empty cell,
    // 1 representing a fresh orange, or
    // 2 representing a rotten orange.
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean vis[][] = new boolean[n][m];

        int fresh = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    fresh++;
                if (grid[i][j] == 2) {
                    vis[i][j] = true;
                    q.add(i * m + j);
                }
            }
        }
        if (fresh == 0)
            return 0;

        if (q.size() == 0)
            return -1;

        int time = 0;
        while (q.size() != 0) {
            int s = q.size();
            while (s-- > 0) {
                int ridx = q.remove();
                int r = ridx / m;
                int c = ridx % m;

                for (int k = 0; k < direcs.length; k++) {
                    int x = r + direcs[k][0];
                    int y = c + direcs[k][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && !vis[x][y] && grid[x][y] == 1) {
                        q.add(x * m + y);
                        fresh--;
                        vis[x][y] = true;
                        if (fresh == 0)
                            return time + 1;
                    }
                }
            }
            time++;

        }
        return (fresh == 0) ? time : -1;
    }

    // Leetcode 1091. Shortest Path in Binary Matrix
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] direcs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 } };
        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1)
            return -1;

            ArrayDeque<Integer> q = new ArrayDeque<>();
        int len = 1;
        q.add(0);
        boolean vis[] = new boolean[n * m];
        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                int ridx = q.remove();
                int r = ridx / m;
                int c = ridx % m;

                if (r == n - 1 && c == m - 1)
                    return len;

                if (vis[ridx])
                    continue;

                vis[ridx] = true;

                for (int k = 0; k < direcs.length; k++) {
                    int x = r + direcs[k][0];
                    int y = c + direcs[k][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && !vis[x * m + y] && grid[x][y] == 0) {
                        q.add(x * m + y);
                    }
                }

            }
            len++;
        }

        return -1;
    }

    // Leetcode 286. Walls and Gates
    // -1 -> A wall or an obstacle.
    // 0 -> A gate.
    // INF - Infinity means an empty room.
    public void wallsAndGates(int[][] rooms) {
        int EMPTY = 2147483647;
        int n = rooms.length;
        int m = rooms[0].length;
        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean vis[][] = new boolean[n][m];

        int emptyRooms = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == EMPTY)
                    emptyRooms++;
                if (rooms[i][j] == 0) {
                    vis[i][j] = true;
                    q.add(i * m + j);
                }
            }
        }
        if (EMPTY == 0)
            return;

        if (q.size() == 0) {
            // FILL EACH EMPTY SPACE WITH EMPTY
            return;
        }

        int steps = 0;
        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                int ridx = q.remove();
                int r = ridx / m;
                int c = ridx % m;

                for (int k = 0; k < direcs.length; k++) {
                    int x = r + direcs[k][0];
                    int y = c + direcs[k][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && !vis[x][y] && rooms[x][y] != -1) {
                        q.add(x * m + y);
                        vis[x][y] = true;
                        if (rooms[x][y] == EMPTY) {
                            rooms[x][y] = steps + 1;
                            emptyRooms--;
                        }
                        if (emptyRooms == 0)
                            return;
                    }
                }
            }
            steps++;
        }

        return;

    }

    // Leetcode 815. Bus Routes
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int minBuses = 0;
        HashMap<Integer, HashSet<Integer>> busesPossible = new HashMap<>();

        for (int bi = 0; bi < routes.length; bi++) {

            for (int st : routes[bi]) {
                busesPossible.putIfAbsent(st, new HashSet<>());

                busesPossible.get(st).add(bi);

            }
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        HashSet<Integer> vis_bus = new HashSet<>();
        HashSet<Integer> vis = new HashSet<>();
        vis.add(source);
        q.add(source);
        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                int remStop = q.remove();
                if (remStop == target)
                    return minBuses;
                for (int bi : busesPossible.get(remStop)) {
                    if (vis_bus.contains(bi))
                        continue;
                    vis_bus.add(bi);

                    for (int st : routes[bi]) {
                        if (!vis.contains(st)) {
                            q.add(st);
                            vis.add(st);
                        }
                    }
                }

            }
            minBuses++;

        }

        return -1;
    }

    // Mr. President =>
    // https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/
    // minimum number of roads which need to be transformed
    public int minTransformation(int n, int[][] roads, int k, int deletionCost) {
        /*
         * 1) Get MST from the given graph
         * 2) Start sub/del the edges till we have not met the criteria
         * 3) If cost<=k return number of deletions else -1
         */

        int[] par = new int[n];
        for (int i = 0; i < n; i++)
            par[i] = i;
        Arrays.sort(roads, (a, b) -> {
            return a[2] - b[2];
        });

        int comp = n;
        // the graph should be connected ie total components = 1
        int cost = 0;
        ArrayList<int[]> mst = new ArrayList<>();
        for (int[] rd : roads) {
            int u = rd[0];
            int v = rd[1];

            int p1 = findParent(par, u);
            int p2 = findParent(par, v);

            if (p1 != p2) {
                par[p2] = p1;
                mst.add(rd);
                cost += rd[2];
                comp--;
            }

        }
        // if graph is disconnected then ans is not possible
        if (comp > 1)
            return -1;

        if (cost <= k)
            return 0;

        int tr = 0;

        // starting from the maximum wt => we need min transformation
        for (int i = mst.size() - 1; i >= 0; i--) {
            int[] rd = mst.get(i);
            cost = cost - rd[2] + deletionCost;
            tr++;

            if (cost <= k)
                return tr;
        }

        return -1;
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

    // 959. Regions Cut By Slashes
    public int regionsBySlashes(String[] grid) {
        // will find cycle created with boundary
        int cnt = 1;
        int n = grid.length + 1;
        int[] par = new int[n * n];
        for (int i = 0; i < par.length; i++)
            par[i] = i;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
                    par[i * n + j] = 0;
            }
        }

        for (int i = 0; i < grid.length; i++) {
            String str = grid[i];
            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);

                if (ch != ' ') {
                    int[] ps = findLeaders(ch, i, j, n, par);

                    int p1 = ps[0];
                    int p2 = ps[1];

                    if (p1 == p2) {
                        cnt++;
                    } else {
                        par[p2] = p1;
                    }
                }
            }
        }

        return cnt;

    }

    private int[] findLeaders(char ch, int i, int j, int n, int[] par) {
        int p1 = -1;
        int p2 = -1;
        if (ch == '/') {
            int x1 = i + 1;
            int y1 = j;
            int x2 = i;
            int y2 = j + 1;

            p1 = findParent(par, x1 * n + y1);
            p2 = findParent(par, x2 * n + y2);
        } else if (ch == '\\') {
            int x1 = i;
            int y1 = j;
            int x2 = i + 1;
            int y2 = j + 1;

            p1 = findParent(par, x1 * n + y1);
            p2 = findParent(par, x2 * n + y2);
        }

        return new int[] { p1, p2 };
    }

    // Leetcode 787. Cheapest Flights Within K Stops
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // K stops => K+1 Flights

        int[] costs = new int[n];
        Arrays.fill(costs, (int) 1e9);
        costs[src] = 0;

        for (int f = 0; f <= k; f++) {
            int ncosts[] = new int[n];
            for (int i = 0; i < n; i++) {
                ncosts[i] = costs[i];
            }

            for (int[] fl : flights) {
                int u = fl[0];
                int v = fl[1];
                int w = fl[2];
                ncosts[v] = Math.min(ncosts[v], costs[u] + w);

            }

            costs = ncosts;
        }

        return costs[dst] == (int) 1e9 ? -1 : costs[dst];

    }

    public class DPair {
        int wsf;
        int par;
        int src;

        DPair(int par, int src, int wsf) {
            this.wsf = wsf;
            this.src = src;
            this.par = par;

        }
    }

    // Leetcode 743. Network Delay Time
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<int[]> graph[] = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();

        for (int[] e : times) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            graph[u].add(new int[] { v, w });
        }

        PriorityQueue<DPair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        int src = k;
        int minWt = -1;
        boolean[] vis = new boolean[n + 1];
        int[] dis = new int[n + 1];
        Arrays.fill(dis, (int) 1e9);
        int[] par = new int[n + 1]; // optional
        Arrays.fill(par, -1);

        pq.add(new DPair(-1, src, 0));
        dis[src] = 0;
        int numberEdges = 0;
        while (pq.size() != 0) {

            DPair rp = pq.remove();
            if (vis[rp.src])
                continue;

            vis[rp.src] = true;

            if (rp.par != 1) {
                // only if graph is connected we can use numberEdges
                numberEdges++;
            }

            minWt = rp.wsf;

            for (int[] e : graph[rp.src]) {
                // better edge possible to reach e.v (with less weight)
                if (!vis[e[0]] && e[1] + rp.wsf < dis[e[0]]) {
                    pq.add(new DPair(rp.src, e[0], e[1] + rp.wsf));
                    dis[e[0]] = e[1] + rp.wsf;
                    par[e[0]] = rp.src;
                }
            }

        }

        // check if any vtx is not visited
        for (int i = 1; i <= n; i++) {
            if (vis[i] == false)
                return -1;
        }

        return minWt;

    }
}
