import java.util.*;

public class BiweeklyContest99 {
    // 2578. Split With Minimum Sum
    public int splitNum(int num) {
        int[] arr = new int[10];
        while (num != 0) {
            int n = num % 10;
            arr[n]++;
            num = num / 10;
        }

        int a = 0;
        int b = 0;
        boolean firstTaken = false;

        for (int i = 0; i < 10; i++) {

            while (arr[i] != 0) {
                if (!firstTaken) {
                    a *= 10;
                    a += i;
                    firstTaken = true;
                } else {
                    b *= 10;
                    b += i;
                    firstTaken = false;
                }

                arr[i]--;

            }
        }

        return a + b;

    }

    // 2579. Count Total Number of Colored Cells
    public long coloredCells(int n) {
        if (n == 1)
            return 1l;
        long ans = 1l;
        long prv = 4l;
        for (int i = 2; i <= n; i++) {
            ans += prv;
            prv += 4l;
        }
        return ans;

    }

    //2580. Count Ways to Group Overlapping Ranges
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges,(a,b)->{
            if(a[0]==b[0]) return a[1] - b[1];
            return a[0] - b[0]; 
        });

        Stack<int[]>st = new Stack<>();
        for(int[]rn :ranges ){
            int start = rn[0];
            int end = rn[1];

            while(st.size()!=0 && st.peek()[1]>=rn[0]){
                int[]rem_rn = st.pop();
                start = Math.min(start,rem_rn[0]);
                end = Math.max(end,rem_rn[1]);
            }

            st.push(new int[]{start,end});
        }

        int size = st.size();
        long mod = (long)1e9 + 7;
        long ans = 1;
        for(int i = 0 ; i<size;i++){
            ans= (ans%mod * 2%mod)%mod;
        }

        return (int)ans;
    }


    //2581. Count Number of Possible Root Nodes
    class Solution {
        int idx = 0;
        public int rootCount(int[][] edges, int[][] guesses, int k) {
            int n = edges.length+1;
            ArrayList<Integer>graph[] = buildGraph(edges);
            boolean[]vis = new boolean[n];
            int[]par = new int[n]; Arrays.fill(par,-1);
            int[]eulerArr = new int[n];
            HashMap<Integer,int[]>subtreeLim = new HashMap<>(); //lidx,ridx
            builderEuler(graph,0,-1,vis,par,eulerArr,subtreeLim);
            
            int[]guessCountArray = new int[n];
            for(int[]g :guesses ){
                int u = g[0];
                int v = g[1];
                if(par[v]==u){
                    guessCountArray[subtreeLim.get(0)[0]]++;
                    if(subtreeLim.get(0)[1]+1<n) guessCountArray[subtreeLim.get(0)[1]+1]--;
                    int l = subtreeLim.get(v)[0];
                    int r = subtreeLim.get(v)[1];
                    guessCountArray[l]--;
                    if(r+1<n)guessCountArray[r+1]++;
                }else{
                    int l = subtreeLim.get(u)[0];
                    int r = subtreeLim.get(u)[1];
                    // System.out.println( v + " - " + l + " " + r);
                    guessCountArray[l]++;
                    if(r+1<n)guessCountArray[r+1]--;
                }
            }
    
            int ans= 0;
            for(int i =1;i<n;i++){
                guessCountArray[i]+=guessCountArray[i-1];
            }
    
            for(int i =0;i<n;i++){
                if(guessCountArray[i] >= k) ans++; 
            }
            return ans;
        }
    
        public void builderEuler(ArrayList<Integer>graph[],int src,int pr,boolean[]vis,int[]par,int[]eulerArr,HashMap<Integer,int[]>subtreeLim ){
            vis[src] = true;
            par[src] = pr;
            eulerArr[idx] = src;
            int st = idx;
           
            for(int nbr : graph[src]){
                if(!vis[nbr]){
                    idx++;
                    builderEuler(graph,nbr,src,vis,par,eulerArr,subtreeLim);
                }
            }
            int en = idx;
            subtreeLim.put(src,new int[]{st,en});
        }
    
        public ArrayList<Integer>[] buildGraph(int[][]edges){
            int n = edges.length+1;
            ArrayList<Integer>[]graph = new ArrayList[n];
            for(int i = 0 ; i<n;i++) graph[i] = new ArrayList<>();
    
            for(int[]ed: edges){
                int u = ed[0];
                int v = ed[1];
                graph[u].add(v);
                graph[v].add(u);
            }
    
            return graph;
        }
    }
}
