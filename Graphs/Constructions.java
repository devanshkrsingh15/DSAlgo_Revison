package Graphs;

import java.util.*;
import Graphs.*;

public class Constructions {

    // {u,v,w}
    // bidirectional
    public ArrayList<Edge>[] SparseRepresentations(int n, int[][] edges) {
        ArrayList<Edge> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<Edge>();

        for (int[] ed : edges) {
            int u = ed[0];
            int v = ed[1];
            int w = ed[2];
            AddEdge(u, v, w, graph);

        }

        return graph;

    }

    public void AddEdge(int u, int v, int w, ArrayList<Edge> graph[]) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));
    }

    public int[][] MatrixRepresentations(int n, int[][] edges) {
        int graph[][] = new int[n][n];

        for (int[] ed : edges) {
            int u = ed[0];
            int v = ed[1];
            int w = ed[2];
            graph[u][v] += w;
            graph[v][u] += w;
        }

        return graph;

    }

    public void display(ArrayList<Edge> graph[]) {
        int n = graph.length;

        for (int i = 0; i < n; i++) {
            System.out.print(i + "-> ");

            for (Edge ed : graph[i]) {
                int v = ed.v;
                int w = ed.wt;
                System.out.print("(" + v + "|" + w + ") ");
            }
            System.out.println();
        }
    }

    public void DeleteEdge(ArrayList<Edge> graph[], Edge edge) {
        int u = edge.u;
        int v = edge.v;
        int wt = edge.wt;

        for (int i = 0; i < graph[u].size(); i++) {
            if (graph[u].get(i).v == v) {
                graph[u].remove(i);
                break;
            }
        }

        for (int i = 0; i < graph[v].size(); i++) {
            if (graph[v].get(i).u == u) {
                graph[v].remove(i);
                break;
            }
        }

    }

    //always remove from the  end   -> shifting is  less
    public void DeleteVertex(ArrayList<Edge>[] graph, int vertex) {
        graph[vertex] = new ArrayList<>();
        int n = graph.length;
        for (int i = n-1; i>= 0; i--) {
            if (i != vertex) {
                for (int j = 0; j < graph[i].size(); j++) {
                    Edge ed = graph[i].get(j);
                    if (ed.v == vertex)
                        graph[i].remove(j);
                }
            }
        }
    }

    public boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest)
            return true;
        vis[src] = true;

        boolean res = false;
        for (Edge e : graph[src]) {
            int nbr = e.v;
            if (vis[nbr] == false) {
                res = res || hasPath(graph, nbr, dest, vis);
            }

        }

        return res;
    }

    public int printAllPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis,ArrayList<ArrayList<Integer>>ans,ArrayList<Integer>temp){
        vis[src]  =  true;
        temp.add(src);
        if(src==dest){
            ans.add(new ArrayList<>(temp));
            temp.remove(temp.size()-1);
            return 1;
        }

        int res  =  0;
        for (Edge e : graph[src]) {
            int nbr = e.v;
            if (vis[nbr] == false) {
                res += printAllPath(graph,nbr,dest,vis,ans,temp);
            }

        }

        vis[src]  =  false;
        temp.remove(temp.size()-1);
        return res;
    }

    public  class HeavyPair{
        int wt;  String psf;
        HeavyPair(int wt,  String psf){
            this.wt = wt;
            this.psf = psf;
        }
    }

    public HeavyPair heaviestPath(ArrayList<Edge>[] graph,int src,int  dest,boolean[] vis){
        if(src==dest){
            return  new HeavyPair(0,""+src);
        }

        vis[src]= true;
        HeavyPair myans = new HeavyPair(-(int)1e9,"");

        for (Edge e : graph[src]) {
            int nbr = e.v;int  wt =e.wt;
            if (vis[nbr] == false) {
                HeavyPair recans  = heaviestPath(graph,nbr,dest,vis);
                if(recans.wt!=-(int)1e9 &&  recans.wt+wt>myans.wt){
                    myans.wt = recans.wt+wt;
                    myans.psf= src  +" "+recans.psf;
                }
            }

        }


        vis[src]= false;
        return myans;
    }



}
