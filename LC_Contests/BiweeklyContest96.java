import java.util.*;

public class BiweeklyContest96 {
    //2540. Minimum Common Value
    public int getCommon(int[] nums1, int[] nums2) {
        HashSet<Integer>set = new HashSet<>();
        for(int ele : nums1) set.add(ele);

        for(int ele : nums2){
            if(set.contains(ele)) return ele;
        }

        return -1;
    }

    //2541. Minimum Operations to Make Array Equal II
    public long minOperations(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        if(k==0){
            for(int i = 0 ; i<n ; i++){
                if(nums1[i]!=nums2[i]) return -1l;
            }
            
            return 0l;
        }
    
        long[]diff = new long[n];
        
        for(int i = 0 ; i<n;i++){
            long d = (long)(nums2[i] - nums1[i]);
            if(d%(long)k != 0) return -1;
            diff[i] = d/(long)k; 
        }
        
        long sof = 0;
        long ans = 0;
        for(long ele : diff){
            sof += ele;
            if(ele>=0) ans += ele;
        }
        
        
        return sof!=0 ? -1 : ans;
    }

    //2542. Maximum Subsequence Score
    public long maxScore(int[] nums1, int[] nums2, int k) {
        return 0l;
    }

}
