import java.util.*;

public class Kruskals {
    // used to find the minimum spanning tree for a connected weighted graph.

    public int findParent(int par[], int u) {
        if (par[u] == u)
            return u;
        else {
            int temp = findParent(par, par[u]);
            par[u] = temp; // path compression
            return temp;
        }
    }

    public void AddEdge(int u, int v, int w, ArrayList<Edge> graph[]) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));
    }



    //TC  -  ElogE +  V + Elog*V =>  ElogE +  V + EalphV
    // FOR DENSE GRAPH ; E =  V^2 : TC  => 2*ElogV + V + Elog*v
    public ArrayList<Edge>[] generateMST(int n, Edge[] edges) {
        int[] par = new int[n];
        for (int i = 0; i < n; i++)
            par[i] = i;
        ArrayList<Edge> mst[] = new ArrayList[n];
        for (int i = 0; i < n; i++)
            mst[i] = new ArrayList<>();

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> {
            return a.wt - b.wt;
        });

        Arrays.sort(edges,(a,b)->{
            return a.wt - b.wt;
        });


        int totwt = 0;
        for (Edge e : edges ) {
       
            int u = e.u;
            int v = e.v;
            int wt = e.wt;

            int p1 = findParent(par, u);
            int p2 = findParent(par, v);

            if (p1 != p2) {
                par[p2] = p1;
                totwt += wt;
                AddEdge(u, v, wt, mst);
            }
        }

        return mst;
    }

}
