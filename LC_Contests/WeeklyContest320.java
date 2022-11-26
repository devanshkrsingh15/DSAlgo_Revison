import java.util.*;

public class WeeklyContest320 {

    // 2475. Number of Unequal Triplets in Array
    public int unequalTriplets(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] != nums[j] && nums[i] != nums[k] && nums[j] != nums[k])
                        ans++;
                }
            }

        }
        return ans;
    }

    // 2476. Closest Nodes Queries in a Binary Search Tree
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        TreeSet<Integer> set = new TreeSet<>();
        fill(set, root);
        List<List<Integer>> ans = new ArrayList<>();
        for (int tar : queries) {
            List<Integer> tmp = new ArrayList<>();
            int floor = set.floor(tar) == null ? -1 : set.floor(tar);
            int ceil = set.ceiling(tar) == null ? -1 : set.ceiling(tar);
            tmp.add(floor);
            tmp.add(ceil);
            ans.add(tmp);
        }

        return ans;
    }

    private void fill(TreeSet<Integer> set, TreeNode root) {
        if (root == null)
            return;
        set.add(root.val);
        fill(set, root.left);
        fill(set, root.right);

    }

    // 2477. Minimum Fuel Cost to Report to the Capital

    long mincost = 0;

    public long minimumFuelCost(int[][] roads, int seats) {
        if (roads.length == 0)
            return 0;
        ArrayList<Integer> graph[] = createGraph(roads, seats);

        boolean[] vis = new boolean[(int) 1e5 + 10];
        _minimumFuelCost(graph, 0, seats, vis);

        return mincost;
    }

    private long _minimumFuelCost(ArrayList<Integer>[] graph, int src, int seats, boolean[] vis) {
        long size = 1l;
        vis[src] = true;
        for (int child : graph[src]) {

            if (vis[child] == false) {
                size += _minimumFuelCost(graph, child, seats, vis);
            }
        }

        if (src != 0)
            mincost += size / seats + (size % seats == 0 ? 0 : 1);//
        return size;
    }

    private ArrayList<Integer>[] createGraph(int[][] roads, int seats) {
        int n = roads.length;
        ArrayList<Integer> graph[] = new ArrayList[(int) 1e5 + 10];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] ed : roads) {
            int u = ed[0];
            int v = ed[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        return graph;
    }

}
//2478. Number of Beautiful Partitions
class Solution {
    int isPrime = 0;
    public void fillPrime(){
        isPrime|=(1<<2);isPrime|=(1<<3);isPrime|=(1<<5);isPrime|=(1<<7);
    }
    
    public void fillMap(String s,int minLength,HashMap<Integer,ArrayList<Integer>>map){
        int n = s.length();
        for(int i = 0;i<n;i++) map.putIfAbsent(i,new ArrayList<>());
        
        for(int i = 0;i<n;i++){
            for(int j = i+minLength -1;j<n;j++){
                int en=s.charAt(j) -'0';
                int st= (j+1<n)  ? s.charAt(j+1) -'0'  : 2;
                
                boolean nonPrimeCond = ((isPrime&(1<<en))==0);
                boolean primeCond = ((isPrime&(1<<st))!=0);
                
                if(nonPrimeCond&& primeCond){
                    map.get(i).add(j);
                }
                
            }
        }
        
        // for(int idx: map.keySet()){
        //     System.out.print(idx + " - ");
        //     for(int nidx :map.get(idx)) System.out.print(nidx + " ");
        //     System.out.println();
        // }

    }
    int minL;
    long mod = (long)1e9 + 7;
    public int beautifulPartitions(String s, int k, int minLength) {
        fillPrime();
        minL = minLength;
        int n = s.length();
        if(k*minLength>n) return 0;
                
        int st = s.charAt(0) - '0';
        int en = s.charAt(n-1) - '0';
        if((isPrime&(1<<st))==0 || (isPrime&(1<<en))!=0 ) return 0;
        
        if(k==1) return 1;
 
        long[][]dp = new long[n+1][k+1];
        for(long[]d:dp) Arrays.fill(d,-1l);
        
        HashMap<Integer,ArrayList<Integer>>map = new HashMap<>();  //valid end pos from current idx
        fillMap(s,minLength,map);

        
        int ans = (int)beautifulPartitions_(s,0,k,dp,map);
        return ans;
        
    }
    
    public long  beautifulPartitions_(String s,int idx,int k,long[][]dp,HashMap<Integer,ArrayList<Integer>>map){
        if(idx==s.length() || k==0){
            return idx==s.length() && k==0 ? 1l : 0;
        }
        
        if(dp[idx][k]!=-1) return dp[idx][k];
        
        long ans = 0;
        
        for(int en: map.get(idx)){
            if( en + (k-1)*minL > s.length() ) continue;
            long fans= beautifulPartitions_(s,en+1,k-1,dp,map);
            ans = (ans%mod + fans%mod)%mod;
        }
        
        return dp[idx][k] = ans;
    }
}