package Graphs;

import java.util.*;
import Graphs.*;

public class dfs {

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

    public int printAllPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis,
            ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> temp) {
        vis[src] = true;
        temp.add(src);
        if (src == dest) {
            ans.add(new ArrayList<>(temp));
            temp.remove(temp.size() - 1);
            return 1;
        }

        int res = 0;
        for (Edge e : graph[src]) {
            int nbr = e.v;
            if (vis[nbr] == false) {
                res += printAllPath(graph, nbr, dest, vis, ans, temp);
            }

        }

        vis[src] = false;
        temp.remove(temp.size() - 1);
        return res;
    }

    public class HeavyPair {
        int wt;
        String psf;

        HeavyPair(int wt, String psf) {
            this.wt = wt;
            this.psf = psf;
        }
    }

    public HeavyPair heaviestPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest) {
            return new HeavyPair(0, "" + src);
        }

        vis[src] = true;
        HeavyPair myans = new HeavyPair(-(int) 1e9, "");

        for (Edge e : graph[src]) {
            int nbr = e.v;
            int wt = e.wt;
            if (vis[nbr] == false) {
                HeavyPair recans = heaviestPath(graph, nbr, dest, vis);
                if (recans.wt != -(int) 1e9 && recans.wt + wt > myans.wt) {
                    myans.wt = recans.wt + wt;
                    myans.psf = src + " " + recans.psf;
                }
            }

        }

        vis[src] = false;
        return myans;
    }

    public int HamiltonianPath(ArrayList<Edge> graph[]) {
        // this function is finding number of Hamiltonian paths or cycles
        // Hamiltonian Path that visits each vertex of the graph exactly once
        // if src ==last vertex visited in Hamiltonian Path => Hamiltonian Cycle

        int n = graph.length;
        if (n <= 1)
            return n; // if n==1 only path exists no cycle

        int count = 0;

        HashSet<Integer> vis = new HashSet<>();
        int src = 0;
        int osrc = 0;
        count += HamiltonianPath_(graph, vis, src, osrc, "");
        return count;

    }

    private int HamiltonianPath_(ArrayList<Edge>[] graph, HashSet<Integer> vis, int src, int osrc, String path) {
        //each  vertex is visited only once, n-1 as we  have not put last vertex
        if (vis.size() == graph.length - 1) {
            boolean hasCycle = false;
            // if last vtx has an edge between osrc
            for (Edge e : graph[src]) {
                if (e.v == osrc) {
                    hasCycle = true;
                    break;
                }
            }

            if (hasCycle) {
                System.out.println("Cycle: " + path + " " + src);
            } else {
                System.out.println("Path: " + path + " " + src);
            }

            return 1;
        }

        int cnt = 0;
        vis.add(src);
        for (Edge e : graph[src]) {
            if (!vis.contains(e.v)) {
                cnt += HamiltonianPath_(graph, vis, e.v, osrc, path + " " + src);
            }
        }

        vis.remove(src);
        return cnt;
    }

    public int  getConnectedComponents(ArrayList<Edge>[] graph){
        int n  =graph.length;
        if(n<=1) return n;

        int cnt =0 ;
        boolean[]vis =  new boolean[n];
        ArrayList<ArrayList<Integer>>overallAns=new ArrayList<>();
        for(int i =0;i<n;i++){
            if(vis[i]==false){
                ArrayList<Integer>myPath=new ArrayList<>();
                getConnectedComponents_(graph,i,myPath,vis);
                overallAns.add(myPath);
                cnt++;
            }
        }

        return cnt;
    }

    private void getConnectedComponents_(ArrayList<Edge>[] graph, int i, ArrayList<Integer> myPath, boolean[] vis) {
        vis[i] =  true;
        myPath.add(i);
        for(Edge e:graph[i]){
            int nbr  = e.v;
            if(!vis[nbr]){
                getConnectedComponents_(graph,nbr,myPath,vis);
            }
        }
    }

}
