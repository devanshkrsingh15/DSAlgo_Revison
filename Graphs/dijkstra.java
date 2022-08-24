import  java.util.*;
public class dijkstra {
    // wrt to src, find lightest path to any dest

    class DijkstraPair {
        int par;
        int ch;
        int wsf; // edge wt

        DijkstraPair(int par, int ch, int wsf) {
            this.par = par;
            this.ch = ch;
            this.wsf = wsf;
        }
    }

    // Using par array
    public int method1(ArrayList<Edge> graph[]) {
        int n = graph.length;

        PriorityQueue<DijkstraPair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });
        int src = 0;
        pq.add(new DijkstraPair(-1, src, 0));
        boolean[] vis = new boolean[n];

        int  dest = 6;

        int minWt = -1;
        int numberEdges = 0;
        while (pq.size() != 0) {

            DijkstraPair rp = pq.remove();
            if (vis[rp.ch])
                continue;

            vis[rp.ch] = true;

            // only if graph is connected => we can use numberEdges
            if (rp.par != 1) {
                numberEdges++;
            }

            if(rp.ch==dest) return rp.wsf;

            for (Edge e : graph[rp.ch]) {
                if (!vis[e.v]) {
                    pq.add(new DijkstraPair(rp.ch, e.v, rp.wsf+e.wt));
                }
            }

        }

        return minWt;

    }

    // Using par+distance array
    public int method2(ArrayList<Edge> graph[]) {
        int n = graph.length;

        PriorityQueue<DijkstraPair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        int src = 0;
        int dest = 6;
        int minWt  = -1;
        boolean[] vis = new boolean[n];
        int[] dis = new int[n];
        Arrays.fill(dis, (int) 1e9);

        int[] par = new int[n]; // optional
        Arrays.fill(par, -1);

        pq.add(new DijkstraPair(-1, src, 0));
        dis[src] = 0;
        int numberEdges = 0;
        while (pq.size() != 0) {

            DijkstraPair rp = pq.remove();
            if (vis[rp.ch])
                continue;

            vis[rp.ch] = true;

            if (rp.par != 1) {
                // only if graph is connected  we can  use numberEdges
                numberEdges++;
            }

            if(rp.ch==dest)  return rp.wsf;

            for (Edge e : graph[rp.ch]) {
                // better edge possible to reach e.v (with less weight)
                if (!vis[e.v] && e.wt  + rp.wsf < dis[e.v]) {
                    pq.add(new DijkstraPair(rp.ch, e.v, e.wt  + rp.wsf));
                    dis[e.v] = e.wt  + rp.wsf;
                    par[e.v] = rp.ch;
                }
            }

        }

        return minWt;
    }


}
