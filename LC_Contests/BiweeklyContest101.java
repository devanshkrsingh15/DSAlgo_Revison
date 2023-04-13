import java.util.*;

public class BiweeklyContest101 {
    //2605. Form Smallest Number From Two Digit Arrays
    public int minNumber(int[] nums1, int[] nums2) {
        int[]freq = new int[10];
        for(int ele : nums1) freq[ele]++;
        for(int ele : nums2) freq[ele]++;

        for(int i = 1 ;i<=9;i++){
            if(freq[i]==2) return i;
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int a = nums1[0] < nums2[0] ? nums1[0] :nums2[0] ;
        int b = nums1[0] < nums2[0] ? nums2[0] :nums1[0] ;

        return a*10 + b;
    }
    
    //2606. Find the Substring With Maximum Cost
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[]has  = new int[26];
        Arrays.fill(has,(int)1e4 );
        for(int i =0;i<chars.length();i++){
            has[chars.charAt(i)-'a'] = vals[i];
        }

        int gmax = 0;
        int cmax = -(int)1e9;

        for(int i = 0;i<s.length();i++){
            char ele = s.charAt(i);
            int tmp = has[ele-'a']!=(int)1e4 ? has[ele-'a'] : (ele-'a') +1;

            cmax = Math.max(cmax + tmp,tmp);
            gmax = Math.max(cmax,gmax);

        }

        return gmax;
    }
}
