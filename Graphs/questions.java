
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

    //Leetcode 130. Surrounded Regions
    public void SurroundedRegions(char[][] board) {
        //to capture all regions that are 4-directionally surrounded by 'X'.

        int n = board.length;
        int m = board[0].length;
        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        checkAndModifyBoundaries(board,'O','#',direcs);

        for(int i= 1;i<n-1;i++){
            for(int j =1;j<m-1;j++){
                if(board[i][j]=='O'){
                    dfs(board,'O','X',i,j,direcs);
                }
            }
        }

        

        checkAndModifyBoundaries(board,'#','O',direcs);

        
    }

    private void checkAndModifyBoundaries(char[][] board, char oc, char nc,int[][]direcs) {
        int n = board.length;
        int m = board[0].length;

        for(int i =0 ;i<n;i++){
            if(board[i][0]== oc)dfs(board,oc,nc,i,0,direcs);
            if(board[i][m-1]==oc)dfs(board,oc,nc,i,m-1,direcs);
        }

        for(int j =0 ;j<m;j++){
            if(board[0][j]== oc)dfs(board,oc,nc,0,j,direcs);
            if(board[n-1][j]==oc)dfs(board,oc,nc,n-1,j,direcs);
        }
    }

    private void dfs(char[][] board, char oc,char nc, int i, int j,int[][]direcs) {
        board[i][j] = nc;

        for (int k = 0; k < direcs.length; k++) {
            int x = i + direcs[k][0];
            int y = j + direcs[k][1];
            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == oc) {
                dfs(board,oc,nc,x,y,direcs);
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
        Queue<Integer> q = new ArrayDeque<>();
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
        Queue<Integer> q = new ArrayDeque<>();
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

        Queue<Integer> q = new ArrayDeque<>();
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
        Queue<Integer> q = new ArrayDeque<>();
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

}
