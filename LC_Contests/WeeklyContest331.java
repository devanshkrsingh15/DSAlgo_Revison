import java.util.Arrays;
import java.util.PriorityQueue;

public class WeeklyContest331 {

    //2558. Take Gifts From the Richest Pile
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer>pq = new PriorityQueue<>((a,b)->{return b-a;});
         
         for(int ele : gifts){
             pq.add(ele);
         }
         
         long ans = 0;
         
         while(k-->0&& pq.size()!=0){
             int ridx = pq.remove();
             int nridx = (int)Math.floor(Math.sqrt(ridx));
             
             if(nridx==1){
                 ans++;
             }else{
                 pq.add(nridx);
             }
         }
         
         while(pq.size()!=0){
             ans+=pq.remove();
         }
         
         return ans;
     }

     //2559. Count Vowel Strings in Ranges
     public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[]psum = new int[n];
        for(int i  = 0;i<n;i++){
            psum[i]+= (isVowel(words[i].charAt(0))  && isVowel(words[i].charAt(words[i].length()-1)) ) ? 1 : 0;  
            if(i>0) psum[i] += psum[i-1];
        }

        int[]ans = new int[queries.length];

        for(int i = 0;i<queries.length;i++){
            int l= queries[i][0];
            int r= queries[i][1];

            ans[i] =  psum[r] - (l==0 ? 0 : psum[l-1]);
        }

        return ans;
     }

    private boolean isVowel(char ch) {
        return ch=='a' ||ch=='e' ||ch=='i' ||ch=='o' || ch=='u' ;
    }

    //2560. House Robber IV

        public int minCapability(int[] nums, int k) {
            int lo =nums[0];
            int hi =nums[0];
            for (int ele: nums){
                lo = Math.min(lo,ele);
                 hi = Math.max(hi,ele);
            }
    
            int ans = -1;
            while(lo<=hi){
                int mid = lo + (hi-lo)/2;
                if(check(nums,mid,k)){
                    ans  = mid;
                    hi = mid-1;
                }else{
                    lo = mid+1;
                }
            }
    
            return ans;
           
        }
    
    
        public boolean check(int[]arr,int cele,int k){
            int[]dp = new int[arr.length+1];
            Arrays.fill(dp,-1);
           return k<=check_(arr,0,cele,dp);
    
        }
    
        public int check_(int[]arr,int idx,int cele,int[]dp){
            if(idx>=arr.length){
                return 0;
            }
    
            if(dp[idx]!=-1)  return dp[idx];
    
            int ans = 0;
    
            if(arr[idx]<=cele){
                ans =Math.max(ans,check_(arr,idx+2,cele,dp)+1);
            }
            
            ans =Math.max(ans,check_(arr,idx+1,cele,dp));
    
            return dp[idx ]= ans;
            
        }
    
}
