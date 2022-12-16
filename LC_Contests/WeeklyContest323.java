import java.util.*;

public class WeeklyContest323 {
    // 2500. Delete Greatest Value in Each Row
    public int deleteGreatestValue(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int ans = 0;
        for (int col = 0; col < m; col++) {
            int max = 0;
            for (int i = 0; i < n; i++) {
                int cmax = 0;
                for (int j = 0; j < m; j++) {
                    cmax = Math.max(cmax, grid[i][j]);
                }

                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == cmax) {
                        grid[i][j] = -1;
                        break;
                    }
                }

                max = Math.max(max, cmax);
            }

            ans += max;
        }

        return ans;

    }

    // 2501. Longest Square Streak in an Array
    public int longestSquareStreak(int[] nums) {
        HashSet<Long> set = new HashSet<>();
        for (int e : nums) {
            set.add((long) e);
        }

        int max = 0;
        for (long i = 2; i <= (long) 1e3; i++) {
            int cnt = 0;
            long t = i;
            while (t <= (long) 1e5 && set.contains(t)) {
                cnt++;
                t = t * t;
            }

            max = Math.max(max, cnt);
        }

        return max <= 1 ? -1 : max;
    }

}

//2502. Design Memory Allocator
class Allocator {

    HashMap<Integer,ArrayList<Integer>>map;
    boolean[]arr;
    TreeSet<Integer>set;

    public Allocator(int n) {
        arr = new boolean[n];
        map = new HashMap<>();
        set= new TreeSet<>();
        for(int i = 0;i<n;i++) set.add(i);
    }
    
    public int allocate(int size, int mID) {
          if (size > arr.length) {
            return -1;
        }
        int findSt = getValidStartingPos(size);
        if(findSt==-1) return -1;

        map.putIfAbsent(mID,new ArrayList<>());
        int pos =findSt;
        while(size-->0){
            arr[pos]=true;
            map.get(mID).add(pos);
            set.remove(pos);
            pos++;
        }

        return findSt;
    }

    public int getValidStartingPos(int s){
        boolean flag = false;
        int pos = 0;
        while(!flag){
            int nextPos = -1;
            int t = s;
            int f = 0;
            int cd = pos;
            while(t-->0){
                if(pos==arr.length || arr[pos]){
                    f = 1;
                    nextPos = pos;
                    break;
                }
                pos++;
            }
            if(f==0) return cd;
            pos = set.ceiling(nextPos+1)==null ? -1 :set.ceiling(nextPos+1);
            if(pos==-1) return -1;
        }

       
        return -1;
    }
    
    public int free(int mID) {
        if(!map.containsKey(mID)) return 0;
        ArrayList<Integer>list = map.get(mID);
        for(int ele:list){
            arr[ele] = false;
            set.add(ele);
        }
        
        map.remove(mID);
        return list.size();
        
    }
}


//2503. Maximum Number of Points From Grid Queries
class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int k = queries.length;
        ArrayList<int[]>qs = new ArrayList<>();
        for(int i = 0 ;i<k;i++){
            qs.add(new int[]{i,queries[i]});
        }

        Collections.sort(qs,(a,b)->{
            return a[1] - b[1];
        });


        int n = grid.length;
        int m = grid[0].length;

        HashSet<Integer>nextStPos =new HashSet<>();
        HashMap<Integer,Integer>map = new HashMap<>();
        nextStPos.add(0);

        int[]ans = new int[k];
        boolean vis[][] = new boolean[n][m];
        int ansTillNow = 0;
        for(int i =  0 ;i<k;i++ ){
            
            HashSet<Integer>n_nextStPos = new HashSet<>();
            int[]a = qs.get(i);
            int ele = a[1];
            int idx = a[0];

            if(map.containsKey(ele)){
                ans[idx] = map.get(ele);
                continue;                
            }

            if(grid[0][0] >= ele) continue;
            
            int tmp = 0;
            for(int st :nextStPos ){
                tmp += bfs(st,vis,grid,ele,n_nextStPos);
            }
            ans[idx] = tmp + ansTillNow;
            map.put(ele,ans[idx]);
            ansTillNow+=tmp;
            nextStPos = n_nextStPos;
            }

        return  ans;
    }


    public int bfs(int idx,boolean vis[][],int[][] grid,int ele, HashSet<Integer>list ){
        int n = grid.length;
        int m = grid[0].length;
        
        int r= idx/m;
        int c = idx%m;
        ArrayDeque<Integer>q = new ArrayDeque<>();
        if(grid[r][c]>=ele){
            list.add(idx);
            return 0;
        }

        q.add(idx);
        int[][]direcs ={{-1,0},{0,-1},{1,0},{0,1}};
        int ans =0;

        while(q.size()!=0){
            int s = q.size();

            while(s-->0){
                int ridx = q.remove();
                r= ridx/m;
                c = ridx%m;
                if(grid[r][c]>=ele){ list.add(ridx); continue;}
 
                ans++;
                vis[r][c] = true;

                for(int k = 0;k<direcs.length;k++){
                    int x = direcs[k][0] + r;
                    int y = direcs[k][1] + c;
                    int nidx= x*m+y;
                    if(x>=0 && y>=0 && x<n && y<m &&!vis[x][y]){
                        vis[x][y]=true;
                        if(ele>grid[x][y])q.add(nidx);
                        else if(ele<=grid[x][y])list.add(nidx);
                    }
                }

            }
        }

        return ans;
    }
}
