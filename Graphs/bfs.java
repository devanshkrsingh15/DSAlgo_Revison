

import java.util.*;

public class bfs {
    // edge by edge travel
    // shortest path possible for each node
    // ONLY cycle detection, NOT number of cycles
    // if at any moment -> src==dest then cycle is present
    // Level - Number of edges

    public void bfs_specific_cycle(ArrayList<Edge> graph[], int src, int dest) {
        int n = graph.length;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] vis = new boolean[n];
        q.add(src);
        boolean isCycle = false;
        int level = 0;
        int minLevelToReachDest = -1;
        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                int rn = q.remove();

                if (rn == dest && minLevelToReachDest == -1)
                    minLevelToReachDest = level;

                if (vis[rn]) {
                    isCycle = true;
                    continue;
                }

                vis[rn] = true;
                for (Edge e : graph[rn]) {
                    if (!vis[e.v])
                        q.add(e.v);
                }

            }
            level++;
        }
    }

    public void bfs_Optimized(ArrayList<Edge> graph[], int src, int dest) {
        // before adding any element into the q (also the source) mark that true
        // less nodes are processed
        // max size of q => number of vtx

        int n = graph.length;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] vis = new boolean[n];
        q.add(src);
        vis[src] = true;
        int minLevelToReachDest = -1;
        boolean hasCycle = false;
        int level = 0;
        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                int rn = q.remove();

                vis[rn] = true;
                for (Edge e : graph[rn]) {
                    if (!vis[e.v]) {

                        q.add(e.v);
                        vis[e.v] = true;
                        if (e.v == dest)
                            minLevelToReachDest = level + 1;
                    } else {
                        hasCycle = true;
                    }

                }

            }
            level++;
        }
    }

    public void bfs_SHORTEST_PATH(ArrayList<Edge> graph[], int src, int dest) {

        int n = graph.length;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] vis = new boolean[n];
        q.add(src);
        vis[src] = true;
        int minLevelToReachDest = -1;
        int level = 0;
        int[]par  =  new int[n];
        Arrays.fill(par,-1);

        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                int rn = q.remove();

                vis[rn] = true;
                for (Edge e : graph[rn]) {
                    if (!vis[e.v]) {

                        q.add(e.v);
                        vis[e.v] = true;
                        par[e.v] = rn;
                        if (e.v == dest)
                            minLevelToReachDest = level + 1;
                    }

                }

            }
            level++;
        }

        ArrayList<Integer>revPath = new ArrayList<>();
        int ptr =dest;
        while(ptr!=-1){
            revPath.add(ptr);
            ptr = par[ptr];
        }
    }

}