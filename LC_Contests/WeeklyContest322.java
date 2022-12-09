import java.util.*;

public class WeeklyContest322 {
    // 2490. Circular Sentence
    public boolean isCircularSentence(String sentence) {
        String[] arr = sentence.split(" ");

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int currIdx = i;
            int nextIdx = (i == n - 1) ? 0 : i + 1;

            if (arr[currIdx].charAt(arr[currIdx].length() - 1) != arr[nextIdx].charAt(0))
                return false;
        }

        return true;
    }

    // 2491. Divide Players Into Teams of Equal Skill
    public long dividePlayers(int[] skill) {
        int n = skill.length;
        long sof = 0;
        long max = 0;

        for (int s : skill) {
            long sk = (long) s;
            sof += sk;
            max = Math.max(max, sk);
        }
        long te = (long) (n / 2);
        if (sof % te != 0 || sof / te <= max)
            return -1l;

        long tar = sof / te;
        Arrays.sort(skill);

        int lo = 0;
        int hi = n - 1;
        long ans = 0;
        while (lo < hi) {
            long a = (long) skill[lo];
            long b = (long) skill[hi];

            if (a + b == tar) {
                ans += (long) a * b;
                lo++;
                hi--;
            } else {
                return -1;
            }

        }
        return ans;

    }

    // 2492. Minimum Score of a Path Between Two Cities
    public int minScore(int n, int[][] roads) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Edge>[] graph = buildGraph(n, roads, map);
        boolean[] vis = new boolean[n + 1];
        ArrayList<Integer> list = new ArrayList<>();

        dfs(graph, 1, vis, list);
        if (n == 1)
            return 0;

        int min = (int) 1e9;

        for (int ele : list)
            min = Math.min(min, map.get(ele));

        return min;
    }

    private void dfs(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> list) {
        vis[src] = true;
        list.add(src);
        for (Edge e : graph[src]) {
            if (!vis[e.nbr]) {

                dfs(graph, e.nbr, vis, list);
            }
        }
    }

    class Edge {
        int nbr;
        int cost;

        Edge(int nbr, int cost) {
            this.nbr = nbr;
            this.cost = cost;
        }

    }

    public ArrayList<Edge>[] buildGraph(int n, int[][] roads, HashMap<Integer, Integer> map) {
        ArrayList<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int[] r : roads) {
            int u = r[0];
            int v = r[1];
            int w = r[2];

            map.putIfAbsent(u, w);
            map.putIfAbsent(v, w);

            if (w < map.get(u))
            map.put(u, w);
            if (w < map.get(v))
            map.put(v, w);

            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        return graph;
    }

    // 2493. Divide Nodes Into the Maximum Number of Groups
    class Solution {
        public ArrayList<HashSet<Integer>> getConnectedComp(ArrayList<Integer>[] graph){
            int n = graph.length;
            boolean[]vis = new boolean[n];
            ArrayList<HashSet<Integer>>ans =new ArrayList<>();
    
            for(int i = 1;i<n;i++){
                if(!vis[i]){
                    HashSet<Integer>list = new HashSet<>();
                    dfs(i,vis,list,graph);
                    ans.add(list);
                }
            }
    
            return ans;
        }
    
        public void dfs(int src,boolean[]vis,HashSet<Integer>list,ArrayList<Integer>[] graph){
            vis[src]= true;
            list.add(src);
            for(int nbr :graph[src]){
                if(!vis[nbr]){
                    dfs(nbr,vis,list,graph);
                }
            }
        }
      
        public ArrayList<Integer>[] buildGraph(int n, int[][] roads) {
            ArrayList<Integer>[] graph = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++)
                graph[i] = new ArrayList<>();
    
            for (int[] r : roads) {
                int u = r[0];
                int v = r[1];
    
                graph[u].add(v);
                graph[v].add(u);
            }
    
            return graph;
        }
    
        private int magnificentSets_(int src, ArrayList<Integer>[] graph) {
            int n = graph.length;
            boolean[] vis = new boolean[n];
            int bidx = 0;
            
            int[]bvis= new int[n];
            Arrays.fill(bvis,-1);
          
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(src);
            int level = 0;
            while (q.size() != 0) {
                int s = q.size();
                while (s-- > 0) {
                    int ridx = q.remove();
                    
                    if(bvis[ridx]!=-1){
                        if(bvis[ridx]!=bidx) return -1;
                    }
                    bvis[ridx]= bidx;
                    
                    if (vis[ridx])
                        continue;
                    vis[ridx] = true;
                    
                    for (int e : graph[ridx]) {
                        if (!vis[e]) {
                            q.add(e);
                        }
                    }
                }
                level++;
                bidx = (bidx+1)%2;
            }
    
            return level;
        }
    
       
    
        public int magnificentSets(int n, int[][] edges) {
            ArrayList<Integer>[] graph = buildGraph(n, edges);
            ArrayList<HashSet<Integer>>comp= getConnectedComp(graph);
    
            int ans = 0;
            for (HashSet<Integer>list : comp) {
                int tmp = 0;
                for(int ele:list ){
                    int cnt = magnificentSets_(ele, graph);
                    if(cnt==-1) return -1;
                    tmp = Math.max(tmp, cnt);
                }
                ans+=tmp;
               
            }
            return ans;
        }
    }
}