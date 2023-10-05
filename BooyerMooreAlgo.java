import java.util.ArrayList;
import java.util.List;

public class BooyerMooreAlgo {
    // 229. Majority Element II
    // if we want to find elements which occur more than n/k times, n is length of
    // array
    // at max k-1 candidates can occur

    class LC229 {
        public List<Integer> majorityElement(int[] nums) {
            int n = nums.length;

            int a = nums[0];
            int b = n == 1 ? nums[0] : nums[1];
            int c1 = 1;
            int c2 = n == 1 ? 1 : 0;

            for (int i = 1; i < n; i++) {
                if (c1 > 0 && c2 > 0) {
                    if(nums[i]!=a && nums[i]!=b){
                        c1--;
                        c2--;
                    }else if(nums[i]==a){
                        c1++;
                    }else if(nums[i]==b){
                        c2++;
                    }
                } else if (c2 > 0) {
                    if (nums[i] == b)
                        c2++;
                    else {
                        a = nums[i];
                        c1++;
                    }
                } else if (c1 > 0) {
                    if (nums[i] == a)
                        c1++;
                    else {
                        b = nums[i];
                        c2++;
                    }
                } else {
                    a = nums[i];
                    c1++;
                }
            }

            List<Integer> ans = new ArrayList<>();
            int f1 = 0;
            int f2 = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] == a)
                    f1++;
                if (nums[i] == b)
                    f2++;
            }

            if (f1 > n / 3)
                ans.add(a);
            if (f2 > n / 3 && a != b)
                ans.add(b);

            return ans;
        }
    }

}
