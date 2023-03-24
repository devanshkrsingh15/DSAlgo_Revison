import java.util.*;

public class WeeklyContest337 {

    // 2595. Number of Even and Odd Bits
    public int[] evenOddBit(int n) {
        ArrayList<Integer> tmp = new ArrayList<>();
        while (n != 0) {
            int b = n % 2;
            n = n / 2;
            tmp.add(b);
        }

        int e = 0;
        int o = 0;
        for (int i = 0; i < tmp.size(); i++) {
            if (i % 2 == 0 && tmp.get(i) == 1)
                e++;
            if (i % 2 == 1 && tmp.get(i) == 1)
                o++;
        }

        return new int[] { e, o };
    }

    // 2596. Check Knight Tour Configuration
    int[][] direcs = { { 2, 1 }, { 1, 2 }, { -2, -1 }, { -1, -2 }, { 2, -1 }, { -1, 2 }, { -2, 1 }, { 1, -2 } };
    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0)
            return false;
        int pos = 0;
        int n = grid.length;
        int cp = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map.put(grid[i][j], i * n + j);
            }
        }

        while (pos < n * n - 1) {
            int fpos = map.get(pos + 1);
            int r1 = cp / n;
            int c1 = cp % n;

            int r2 = fpos / n;
            int c2 = fpos % n;

            boolean found = false;

            for (int k = 0; k < direcs.length; k++) {
                int x = r1 + direcs[k][0];
                int y = c1 + direcs[k][1];
                if (x >= 0 && y >= 0 && x < n && y < n) {
                    if (x == r2 && c2 == y) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                return false;
            }
            cp = fpos;
            pos++;
        }

        return true;
    }

    //2597. The Number of Beautiful Subsets
    public int beautifulSubsets(int[] nums, int k) {
        return beautifulSubsets_(nums,k,0,new HashMap<>()) - 1;
    }
    
    public int beautifulSubsets_(int[]nums,int k,int idx,HashMap<Integer,Integer>hm){
        if(idx==nums.length){
            return 1;
        }
        int ans = 0;
        int ele = nums[idx];
        if(!hm.containsKey(ele+k) && !hm.containsKey(ele-k)){
             hm.put(ele,hm.getOrDefault(ele,0)+1);
             ans += beautifulSubsets_(nums,k,idx+1,hm);
             hm.put(nums[idx],hm.get(nums[idx])-1);
             if(hm.get(ele)==0) hm.remove(ele);
        }
        
        ans += beautifulSubsets_(nums,k,idx+1,hm);
        
        return ans;
    }



    

}
