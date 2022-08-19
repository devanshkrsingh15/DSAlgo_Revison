import java.util.*;
public class Kosaraju {
    // ind the strongly connected components of a directed graph
    // a graph is said to be strongly connected if every vertex is reachable from
    // every other vertex
    // cyclic directed graph => acyclic directed graph : each strongly connected
    // components treated as vertex

    public ArrayList<ArrayList<Integer>> KosarajuAlgo(ArrayList<Edge>graph[]){
        int n= graph.length;
        if(n==0) return new ArrayList<>();

        ArrayList<ArrayList<Integer>>scc = new ArrayList<>();

        /*
         * Step 1  -  get topological order using DFS
         * Step 2  -  Inverse  the graph
         * Step 3  -  Run DFS using the topological order
         */

         ArrayList<Integer>topo  = new ArrayList<>();
         getTopologicalOrder(graph,topo);

         graph =  reverseTheGraph(graph);

         boolean vis[] = new boolean[n];
         for(int  i = topo.size()-1;i>=0;i--){
            int src = topo.get(i);
            if(!vis[src]){
                ArrayList<Integer>myComp  =  new ArrayList<>();
                dfsTopo(graph,src,vis,myComp);
                scc.add(myComp);
            }

         }

        return scc;

    }

    private void getTopologicalOrder(ArrayList<Edge>[] graph, ArrayList<Integer> topo) {
        int n  = graph.length;
        boolean vis[] = new boolean[n];
        for(int  i = 0;i<n;i++){
           int src = i;
           if(!vis[src]) dfsTopo(graph,src,vis,topo);
        }
           
    }

    private ArrayList<Edge>[] reverseTheGraph(ArrayList<Edge>[] graph) {
        int n  = graph.length;
        ArrayList<Edge>[]rgraph  =   new  ArrayList[n];
        for(int i =  0;i<n;i++) rgraph[i] = new ArrayList<Edge>();

        for(int i =  0;i<n;i++){
            for(Edge e:graph[i]){
                int u = e.u;
                int v = e.v;
                int w = e.wt;
                graph[v].add(new Edge(v,u,w));
            }


        }


        return rgraph; 
    }

    private void dfsTopo(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> list) {
        vis[src] = true;
        for(Edge e : graph[src]){
            int nbr= e.v;
            if(!vis[nbr]){
                dfsTopo(graph,nbr,vis,list);
            }
        }

        list.add(src);
    }

}
