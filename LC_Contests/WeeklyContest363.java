import java.util.*;

public class WeeklyContest363 {

    // 2859. Sum of Values at Indices With K Set Bits
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (setBits(i) == k) {
                ans += nums.get(i);
            }
        }

        return ans;
    }

    public int setBits(int n) {
        int cnt = 0;
        while (n != 0) {
            n = (n & (n - 1));
            cnt++;
        }
        return cnt;
    }

    // 2860. Happy Students
    public int countWays(List<Integer> nums) {
        int n = nums.size();
        Collections.sort(nums);
        int ans = nums.get(0) != 0 ? 1 : 0;

        if (nums.get(n - 1) < n)
            ans++;

        for (int i = 0; i + 1 < nums.size(); i++) {
            if (nums.get(i) < i + 1 && nums.get(i + 1) > i + 1)
                ans++;
        }

        return ans;
    }

    //2861. Maximum Number of Alloys
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int ans = 0;

        //n
        for(int i = 0 ; i< k;i++){
            // logn
            List<Integer>comp  = composition.get(i);
            int myans = -1;
            int lo = 0;
            int hi = (int)1e9;

            while(lo<=hi){
                int mid = lo + (hi-lo)/2;

                //n
                if(isPossible(comp,stock,cost,mid,budget)){
                    myans = mid;
                    lo = mid+1;
                }else{
                    hi = mid-1;
                }
            }


            ans = Math.max(ans,myans);
        }

        // TC = N*N*LogN
        return ans;
        
    }

    public boolean isPossible(List<Integer>composition,List<Integer> stock, List<Integer> cost,int tar,long budget){
        long myCost = 0;
        for(int i =0 ;i<composition.size() ; i++){
            long reqForOne = (long)composition.get(i);
            long totReq =  (long)tar*reqForOne;
            long inStock =  (long)stock.get(i);
            myCost += (long)Math.max(0,totReq - inStock)*(long)cost.get(i);
            
            if(myCost > budget) return false;
        }

        return true;
    }

    
}
