import java.util.*;

public class WeeklyContest359 {
    //2828. Check if a String Is an Acronym of Words
    public boolean isAcronym(List<String> words, String s) {
        if(s.length()!=words.size()) return false;

        for(int i = 0 ; i<s.length() ; i++){
            if(s.charAt(i)!=words.get(i).charAt(0)) return false;
        }


        return true;
    }


    //2829. Determine the Minimum Sum of a k-avoiding Array
    public int minimumSum(int n, int k) {
        int cnt = 0;
        int ptr = 1;
        int sum = 0;
        HashSet<Integer>set = new HashSet<>();
        while(cnt<n){
            if(set.contains(ptr)){
                ptr++;
            }else{
                sum += ptr;
                set.add(k - ptr);
                cnt++;
                ptr++;
            }
        }
        
        return sum;
    }

    //2830. Maximize the Profit as the Salesman
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        Collections.sort(offers,(a,b)->{
            return a.get(0) - b.get(0) ;
        });
        

        int[]dp = new int[offers.size()+1];
        Arrays.fill(dp,-1);


        return maximizeTheProfit_(n,offers,dp,0);
    }


    public int maximizeTheProfit_(int n,List<List<Integer>> offers,int[]dp,int idx){
        if(idx>=offers.size()) return 0;

        if(dp[idx]!=-1) return dp[idx];

        int ans = -(int)1e9;

        //inc
        int pr = offers.get(idx).get(2);
        int en =  offers.get(idx).get(1);
        int nidx = find(offers,idx+1,en+1);
        ans = Math.max(ans,maximizeTheProfit_(n,offers,dp,nidx)+pr);


        //exc
        ans = Math.max(ans,maximizeTheProfit_(n,offers,dp,idx+1));

        return dp[idx];

    }

    public int find(List<List<Integer>> offers,int st,int tar){
        int n = offers.size();
        int lo = st;
        int hi = n-1;

        int ans = n;

        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            if(offers.get(mid).get(0) >= tar){
                ans= mid;
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }


        return ans;
    }

    //2831. Find the Longest Equal Subarray
    public int longestEqualSubarray(List<Integer> nums, int k) {
        HashMap<Integer,ArrayList<Integer>>map = new HashMap<>();
        int n = nums.size();
        for(int i = 0 ;i<n;i++ ){
            int ele= nums.get(i);
            map.putIfAbsent(ele,new ArrayList<>());
            map.get(ele).add(i);
        }

        int max = 1;
        for(int ele: map.keySet()){
            max = Math.max(max,getMaxForThisElement(map.get(ele),k)); 
        }

        return max;
    }

    public int getMaxForThisElement(ArrayList<Integer>list,int k){
        int st = 0;
        int en = 0;

        int n= list.size();
        int cnt = 1;
        int max = 1;
        int kUsed = 0;
        while(en +1 < n){
            cnt++;
            en++;
            kUsed += list.get(en) - list.get(en-1) - 1;

            while(kUsed > k){
                st++;
                cnt--;
                kUsed -= list.get(st) - list.get(st-1) - 1;
            }

            max= Math.max(max,cnt);
        }

        return max;
    }
}
