import java.util.*;

public class WeeklyContest334 {

    //2574. Left and Right Sum Differences
    public int[] leftRigthDifference(int[] nums) {
        int n = nums.length;
        int[]lsum = new int[n];
        lsum[0] = nums[0];
        for(int i = 1 ; i<n;i++){
            lsum[i] += lsum[i-1] + nums[i];
        }
        
        
        int[]rsum = new int[n];
        rsum[n-1] = nums[n-1];
        for(int i = n-2 ; i>=0 ;i--){
            rsum[i] += rsum[i+1] + nums[i];
        }
        
        
        int[]ans= new int[n];
        for(int i = 0 ; i<n;i ++){
            int left = i==0 ? 0 :lsum[i-1];
            int right = i== n-1 ? 0 : rsum[i+1];
            
            ans[i] = Math.abs(left -right);
        }
        
        return ans;
    }

    //2575. Find the Divisibility Array of a String
    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[]ans= new int[n];
        long temp = 0;
        
        for(int i = 0 ;i<n;i++){
            temp = (temp*10l%m + (word.charAt(i)-'0')%m)%m;
            ans[i] = (temp)==0 ? 1 : 0;
        }
        
        return ans;
    }

    //2576. Find the Maximum Number of Marked Indices
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        
    }
}
