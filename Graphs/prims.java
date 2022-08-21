import java.util.*;

public class prims {
    /*
     * Gives MST like Kruskals, but better if Graph is given
     */

    public void AddEdge(int u, int v, int w, ArrayList<Edge> graph[]) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));
    }

    class PrimsPair {
        int par;
        int ch;
        int wt; // edge wt

        PrimsPair(int par, int ch, int wt) {
            this.par = par;
            this.ch = ch;
            this.wt = wt;
        }
    }

    // Using par array
    public ArrayList<Edge>[] method1(ArrayList<Edge> graph[]) {
        int n = graph.length;
        ArrayList<Edge> mst[] = new ArrayList[n];
        for (int i = 0; i < n; i++)
            mst[i] = new ArrayList<>();

        PriorityQueue<PrimsPair> pq = new PriorityQueue<>((a, b) -> {
            return a.wt - b.wt;
        });
        int src = 0;
        pq.add(new PrimsPair(-1, src, 0));
        boolean[] vis = new boolean[n];

        int numberEdges = 0;
        while (pq.size() != 0) {

            PrimsPair rp = pq.remove();
            if (vis[rp.ch])
                continue;

            vis[rp.ch] = true;

            if (rp.par != 1) {
                numberEdges++;
                AddEdge(rp.par, rp.ch, rp.wt, mst);
            }

            if (numberEdges == n - 1)
                return mst; // only if graph is connected

            for (Edge e : graph[rp.ch]) {
                if (!vis[e.v]) {
                    pq.add(new PrimsPair(rp.ch, e.v, e.wt));
                }
            }

        }

        return mst;

    }

    // Using par+distance array
    public ArrayList<Edge>[] method2(ArrayList<Edge> graph[]) {
        int n = graph.length;
        ArrayList<Edge> mst[] = new ArrayList[n];
        for (int i = 0; i < n; i++)
            mst[i] = new ArrayList<>();

        PriorityQueue<PrimsPair> pq = new PriorityQueue<>((a, b) -> {
            return a.wt - b.wt;
        });

        int src = 0;
        boolean[] vis = new boolean[n];
        int[] dis = new int[n];
        Arrays.fill(dis, (int) 1e9);

        int[] par = new int[n]; // optional
        Arrays.fill(par, -1);

        pq.add(new PrimsPair(-1, src, 0));
        dis[src] = 0;
        int numberEdges = 0;
        while (pq.size() != 0) {

            PrimsPair rp = pq.remove();
            if (vis[rp.ch])
                continue;

            vis[rp.ch] = true;

            if (rp.par != 1) {
                numberEdges++;
                AddEdge(rp.par, rp.ch, rp.wt, mst);
            }

            if (numberEdges == n - 1)
                return mst; // only if graph is connected

            for (Edge e : graph[rp.ch]) {
                // better edge possible to reach e.v (with less weight)
                if (!vis[e.v] && e.wt < dis[e.v]) {
                    pq.add(new PrimsPair(rp.ch, e.v, e.wt));
                    dis[e.v] = e.wt;
                    par[e.v] = rp.ch;
                }
            }

        }

        return mst;
    }

}
