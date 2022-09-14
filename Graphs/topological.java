import java.util.*;

public class topological {
    // Dependency matters
    // start with any node
    // for directed graph

    public ArrayList<Integer> togologicalSortDFS(ArrayList<Edge> graph[]) {
        int n = graph.length;
        boolean[] vis = new boolean[n];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (vis[i] == false) {
                togologicalSortDFS_(graph, i, vis, list);
            }
        }

        Collections.reverse(list); // O(N)
        return list;
    }

    public void togologicalSortDFS_(ArrayList<Edge> graph[], int src, boolean[] vis, ArrayList<Integer> list) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            int nbr = e.v;
            if (!vis[nbr]) {
                togologicalSortDFS_(graph, nbr, vis, list);
            }
        }
        list.add(src);
    }

    // topological sort using BFS
    public ArrayList<Integer> KahnsAlgo(ArrayList<Edge> graph[]) {
        int n = graph.length;

        ArrayList<Integer> list = new ArrayList<>();
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (Edge e : graph[i]) {
                indegree[e.v]++;
            }
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();

        // first putting the least dependent vtx
        // A --> B, A needs B
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                int rv = q.remove();

                list.add(rv);

                for (Edge e : graph[rv]) {
                    indegree[e.v]--;

                    if (indegree[e.v] == 0)
                        q.add(e.v);
                }

            }
        }

        if (list.size() != n) {
            // cycle exists
            return null;
        }

        return list;
    }

    // Leetcode 207. Course Schedule
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        ArrayList<Integer> graph[] = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            int u = pre[0];
            int v = pre[1];
            graph[u].add(v);
            indegree[pre[1]]++;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                int rv = q.remove();

                list.add(rv);

                for (int nbr : graph[rv]) {
                    indegree[nbr]--;

                    if (indegree[nbr] == 0)
                        q.add(nbr);
                }

            }
        }

        return list.size() == numCourses;

    }

    // Leetcode 210. Course Schedule II
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        ArrayList<Integer> graph[] = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            int u = pre[0];
            int v = pre[1];
            graph[u].add(v);
            indegree[pre[1]]++;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                int rv = q.remove();

                list.add(rv);

                for (int nbr : graph[rv]) {
                    indegree[nbr]--;

                    if (indegree[nbr] == 0)
                        q.add(nbr);
                }

            }
        }

        if (list.size() != numCourses)
            return new int[0];
        Collections.reverse(list);
        int n = list.size();
        int[] arr = new int[n];
        int idx = 0;
        for (int ele : list)
            arr[idx++] = ele;

        return arr;
    }

    // Cycle detection using dfs in DIRECTED GRAPH
    // We start with a src and retrace the same path and reach destination again =>
    // then is has cycle

    public void DFS_HasCycle(ArrayList<Edge> graph[]) {
        int n = graph.length;
        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        // -1 -> unvis : 0 -> part of my path : 1 -> path completed , part of diff path

        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                boolean hasCycle[] = new boolean[1];
                ArrayList<Integer> topologicalSort = new ArrayList<>();
                DFS_HasCycle_(graph, i, vis, hasCycle, topologicalSort);
            }
        }
    }

    private void DFS_HasCycle_(ArrayList<Edge>[] graph, int src, int[] vis, boolean[] hasCycle,
            ArrayList<Integer> topologicalSort) {

        vis[src] = 0;

        for (Edge e : graph[src]) {
            int nbr = e.v;

            if (vis[nbr] == -1) {
                DFS_HasCycle_(graph, nbr, vis, hasCycle, topologicalSort);
            } else if (vis[nbr] == 0) {
                hasCycle[0] = true;
            }
        }

        topologicalSort.add(src);

        vis[src] = 1;

    }

    // Course Schedule using DFS
    public boolean canFinish_DFS(int n, int[][] prerequisites) {

        ArrayList<Integer> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] p : prerequisites) {
            int u = p[0];
            int v = p[1];
            graph[u].add(v);
        }

        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        // -1 -> unvis : 0 -> part of my path : 1 -> path completed , part of diff path
        boolean hasCycle[] = new boolean[1];
        ArrayList<Integer> topologicalSort = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                canFinishI_DFS_(graph, i, vis, hasCycle, topologicalSort);
                if (hasCycle[0])
                    return false;
            }
        }

        return true;

    }

    private void canFinishI_DFS_(ArrayList<Integer>[] graph, int src, int[] vis, boolean[] hasCycle,
            ArrayList<Integer> topologicalSort) {

        vis[src] = 0;

        for (int nbr : graph[src]) {

            if (vis[nbr] == -1) {
                canFinishI_DFS_(graph, nbr, vis, hasCycle, topologicalSort);
            } else if (vis[nbr] == 0) {
                hasCycle[0] = true;
            }
        }

        topologicalSort.add(src);

        vis[src] = 1;

    }

    // Leetcode 329. Longest Increasing Path in a Matrix
    public int longestIncreasingPath(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] direcs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

        ArrayList<Integer> graph[] = new ArrayList[n * m];
        longestIncreasingPath_buildGraph(matrix, direcs, graph);

        int[] indegree = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            for (int j : graph[i])
                indegree[j]++;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n * m; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        int max = 0;

        while (q.size() != 0) {
            int s = q.size();
            while (s-- > 0) {
                int src = q.remove();

                for (int nbr : graph[src]) {
                    indegree[nbr]--;
                    if (indegree[nbr] == 0)
                        q.add(nbr);
                }

            }
            max++;
        }

        return max;

    }

    private void longestIncreasingPath_buildGraph(int[][] matrix, int[][] direcs, ArrayList<Integer>[] graph) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int src = i * m + j;

                for (int k = 0; k < direcs.length; k++) {
                    int x = i + direcs[k][0];
                    int y = j + direcs[k][1];

                    if (isValid(matrix, i, j, x, y)) {
                        int nbr = x * m + y;
                        graph[src].add(nbr);
                    }
                }
            }
        }
    }

    private boolean isValid(int[][] matrix, int i, int j, int x, int y) {
        int n = matrix.length;
        int m = matrix[0].length;

        return x >= 0 && y >= 0 && x < n && y < m && matrix[i][j] < matrix[x][y];
    }

    

}