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
        Queue<Integer> q = new ArrayDeque<>();

        // first putting the least dependent vtx
        //  A --> B, A needs   B
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
        int[]indegree   = new int[numCourses];
        ArrayList<Integer>graph [] = new ArrayList[numCourses];
        for(int i  = 0;i<numCourses;i++){
            graph[i] = new ArrayList<>();
        }
        for(int[]pre:prerequisites){
            int u =  pre[0];
            int  v=  pre[1];
            graph[u].add(v);
            indegree[pre[1]]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
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

     

        return list.size()==numCourses;


    }

    //Leetcode 210. Course Schedule II
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[]indegree   = new int[numCourses];
        ArrayList<Integer>graph [] = new ArrayList[numCourses];
        for(int i  = 0;i<numCourses;i++){
            graph[i] = new ArrayList<>();
        }
        for(int[]pre:prerequisites){
            int u =  pre[0];
            int  v=  pre[1];
            graph[u].add(v);
            indegree[pre[1]]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
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

        if(list.size()!=numCourses) return  new int[0];
        Collections.reverse(list);
        int n = list.size();
        int[]arr= new  int[n];
        int idx=0;
        for(int ele:list) arr[idx++]= ele;

        return arr;
    }


}