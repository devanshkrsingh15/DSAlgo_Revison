import java.util.*;

public class WeeklyContest360 {
 
    //2833. Furthest Point From Origin
    public int furthestDistanceFromOrigin(String moves) {
        return Math.max(helper(moves,true),helper(moves,false));
    }
    
    
    public int helper (String moves,boolean considerLeft){
        int dis = 0;
        for(int i = 0 ;i<moves.length();i++){
            char ch = moves.charAt(i);
            if(ch=='L'  || (considerLeft && ch=='_')) dis--;
            else if(ch=='R' || (!considerLeft && ch=='_')) dis++;
           
        }
        
        return (int)Math.abs(dis);
    }
    

    //2834. Find the Minimum Possible Sum of a Beautiful Array
    public int minimumPossibleSum(int n, int target) {
        long mid = (long)target/2;
        long sumOfN = findSum(n);
        if(mid>=n){
            return (int)sumOfN;
        }

        long rn = (long)n - mid - 1; 
        long sumTillMid  = findSum(mid);
        long sumOfRem  = findSum(rn);

        long mod = (long)1e9 + 7;

        long ans = (sumTillMid%mod + (long)target%mod)%mod;
        ans = (ans%mod + sumOfRem%mod)%mod;
        ans = (ans%mod + ((long)target%mod * rn%mod)%mod)%mod;

        return (int)ans;
    }

    public long findSum(long n){
        return n*(n+1)/2;
    }

    //2835. Minimum Operations to Form Subsequence With Target Sum
    public int minOperations(List<Integer> nums, int target) {
        long sum = 0l;
        int  ans = 0;
        
        PriorityQueue<Integer>pq = new PriorityQueue<>((a,b)-> b-a);
        for(int ele : nums){
            pq.add(ele);
            sum += (long)ele;
        }


        if(sum < target) return -1;
        if(sum == target) return 0;

        while(target > 0){
            long e = (long)pq.remove();
            sum -= e;

            if(e <= (long)target){
                target -= (int)e;
            }else if(e > target && sum < target ){
                sum += e;
                pq.add((int)e/2);
                pq.add((int)e/2);
                ans ++;
            }
        }

        return ans;
    }

    

}
