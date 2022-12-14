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
