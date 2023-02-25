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
