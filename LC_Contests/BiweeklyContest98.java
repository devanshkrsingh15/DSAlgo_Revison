import java.util.*;

public class BiweeklyContest98 {
    // 2566. Maximum Difference by Remapping a Digit
    public int minMaxDifference(int num) {
        HashMap<Integer, ArrayList<Integer>> places = new HashMap<>();
        fill(num, places);

        int minNumber = (int) 1e9;
        int maxNumber = -1;

        for (int key : places.keySet()) {
            minNumber = Math.min(change(places, key, 0), minNumber);
            maxNumber = Math.max(change(places, key, 9), maxNumber);
        }

        return maxNumber - minNumber;
    }

    public int change(HashMap<Integer, ArrayList<Integer>> places, int k, int ck) {
        int ans = 0;

        for (int key : places.keySet()) {
            int K = key == k ? ck : key;

            for (int pl : places.get(key)) {
                ans += pl * K;
            }
        }

        return ans;
    }

    public void fill(int n, HashMap<Integer, ArrayList<Integer>> places) {
        int p = 1;
        while (n != 0) {
            int last = n % 10;
            places.putIfAbsent(last, new ArrayList<>());
            places.get(last).add(p);
            n /= 10;
            p *= 10;
        }

    }

    //2567. Minimum Score by Changing Two Elements
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        /*
        score = maxDiff + minDiff

        minDiff is 0; only when duplicates
        maxDiff ??

        minimize the maxValue or maximize the minValue, with duplicates
        
        CASE 1 : Change max and second max with third max, minimize the maxValue
        CASE 2 : Change min and second min with third min, maximize the minValue
        CASE 3 : Change min with second min and dhange max with second max , minimize the maxValue and maximize the minValue

        */

        int minDiff = 0;
        int case1 = nums[n-3]  - nums[0];
        int case2 = nums[n-1]  - nums[2];
        int case3 = nums[n-2]  - nums[1];

        int maxDiff = Math.min(Math.min(case1,case2),case3);
        
        return minDiff + maxDiff;
    }


    //2568. Minimum Impossible OR
    public int minImpossibleOR(int[] nums) {
        int ans = 1;
        boolean flag = true;
  
        HashSet<Integer>set = new HashSet<>();
        for(int ele: nums) set.add(ele);
 
        while(flag){
            if(!set.contains(ans)){
                return ans;
            }
            ans*=2;
        }
 
        return -1;
     }


     
}
