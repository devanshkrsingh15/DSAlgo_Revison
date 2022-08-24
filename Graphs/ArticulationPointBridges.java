import java.util.ArrayList;
import java.util.List;

public class ArticulationPointBridges {
    // the point if removed, increases the gcc count then its is Articulation Point
    // root is only AP if num call from root is more than 1
    // if x is number times a vtx is an AP, then inc in gcc is x
    // in a number line every pt is AP
    // in a tree every node except leaf node is an AP

    int time = 0;
    int[] low;
    int[] dis;
    boolean[] vis;
    boolean[] isAP;
    int numberOfCallsFromRoot = 0;
    ArrayList<int[]> articulationBridges;

    public void ArticulationPB(ArrayList<ArrayList<Integer>> graph) {
        articulationBridges = new ArrayList<>();
        int n = graph.size();
        low = new int[n];
        dis = new int[n];
        vis = new boolean[n];
        isAP = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (vis[i] == false) {
                dfs(graph, i, -1);
                if (numberOfCallsFromRoot == 1) {
                    isAP[i] = false;
                }
                numberOfCallsFromRoot = 0;
            }
        }

    }

    public void dfs(ArrayList<ArrayList<Integer>> graph, int src, int par) {
        low[src] = dis[src] = time++;
        vis[src] = true;

        for (int nbr : graph.get(src)) {
            if (!vis[nbr]) {

                if (par == -1)
                    numberOfCallsFromRoot++;
                dfs(graph, nbr, src);

                if (dis[src] <= low[nbr]) // Condition for AP
                    isAP[src] = true;

                if (dis[src] < low[nbr]) // Condition for AB
                    articulationBridges.add(new int[] { src, nbr });

                low[src] = Math.min(low[src], low[nbr]);

            } else if (par != nbr) {
                low[src] = Math.min(low[src], dis[nbr]);
            }
        }

    }

}

//Leetcode 1192. Critical Connections in a Network
class CriticalConnectionsNetwork {
    List<List<Integer>> ans;
    int[] dis;
    int[] low;
    boolean[] vis;
    int time = 0;
    ArrayList<Integer> graph[];

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ans = new ArrayList<>();
        graph = new ArrayList[n];
        dis = new int[n];
        low = new int[n];
        vis = new boolean[n];
        makeGraph(connections, n);

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(i, -1);
            }
        }

        return ans;
    }

    public void makeGraph(List<List<Integer>> connections, int n) {
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (List<Integer> t : connections) {
            int u = t.get(0);
            int v = t.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }
    }

    public void dfs(int src, int par) {
        dis[src] = low[src] = time;
        time++;
        vis[src] = true;

        for (int nbr : graph[src]) {
            if (!vis[nbr]) {

                dfs(nbr, src);

                if (dis[src] < low[nbr]) {
                    ArrayList<Integer> b = new ArrayList<>();
                    b.add(src);
                    b.add(nbr);
                    ans.add(b);
                }

                low[src] = Math.min(low[src], low[nbr]);
            } else if (nbr != par) {
                low[src] = Math.min(low[src], dis[nbr]);
            }
        }
    }
}