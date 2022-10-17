import java.util.*;

public class WeeklyContest315 {
    //2441. Largest Positive Integer That Exists With Its Negative
    public int findMaxK(int[] nums) {
        HashSet<Integer>set = new HashSet<>();

        for(int ele:nums){
            set.add(ele);
        }

        Arrays.sort(nums);

        for(int i = nums.length-1;i>=0;i--){
            if( set.contains(-nums[i])) return nums[i];
        }

        return -1;
    }

    //2442. Count Number of Distinct Integers After Reverse Operations

    public int countDistinctIntegers(int[] nums) {
        HashSet<Integer>set = new HashSet<>();

        for(int ele:nums){
            set.add(ele);
            int rele = reverse(ele);
            set.add(rele);
        }

        return set.size();
    }

    private int reverse(int ele) {
        int n = 0;

        while(ele!=0){
            int ld = ele%10;
            n = n*10 + ld;
            ele=ele/10;
        }
        return n;
    }

    //2443. Sum of Number and Its Reverse
    public boolean sumOfNumberAndReverse(int num) {
        if(num==0) return true;
        int ele = num-1;
        while(ele>0){
            int a = ele;
            int b= reverse(ele);

            if(a+b==num) return true;
            ele--;
        }

        return false;
    }

    //2444. Count Subarrays With Fixed Bounds
    

}
