import java.math.BigInteger;
import java.util.*;

public class WeeklyContest302 {
    // 2341. Maximum Number of Pairs in Array
    public int[] numberOfPairs(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        int freq[] = new int[110];

        for (int ele : nums) {
            freq[ele]++;
        }

        for (int ele : freq) {
            if (ele > 0) {
                ele = ((ele & 1) != 0) ? ele - 1 : ele;

                cnt += ele / 2;
            }
        }

        return new int[] { cnt, n - 2 * cnt };
    }

    // 2342. Max Sum of a Pair With Equal Sum of Digits
    public int maximumSum(int[] nums) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int ele : nums) {
            int sum = countSumOfDigits(ele);
            map.putIfAbsent(sum, new ArrayList<>());
            map.get(sum).add(ele);
        }

        int ans = -1;
        for (int k : map.keySet()) {
            Collections.sort(map.get(k));
            if (map.get(k).size() >= 2) {
                ans = Math.max(ans, map.get(k).get(map.get(k).size() - 1) + map.get(k).get(map.get(k).size() - 2));
            }
        }

        return ans;

    }

    private int countSumOfDigits(int ele) {
        int sum = 0;
        while (ele != 0) {
            sum += ele % 10;
            ele = ele / 10;
        }
        return sum;
    }

    // 2343. Query Kth Smallest Trimmed Number
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int ans[] = new int[queries.length];
        int idx = 0;
        for (int[] q : queries) {
            PriorityQueue<String[]> list = new PriorityQueue<>((a, b) -> {
                if (a[1].compareTo(b[1]) == 0)
                    return Integer.parseInt(b[0]) - Integer.parseInt(a[0]);
                return b[1].compareTo(a[1]);
            }); // {idx,val}

            int k = q[0];
            int t = q[1];
            for (int i = 0; i < nums.length; i++) {
                int n = nums[i].length();
                String s = nums[i].substring(n - t);
                list.add(new String[] { i + "", s });
                if (list.size() > k)
                    list.poll();

            }

            ans[idx++] = Integer.parseInt(list.peek()[0]);
        }

        return ans;
    }

    //2344. Minimum Deletions to Make Array Divisible

    public int minOperations(int[] nums, int[] numsDivide) {
        Arrays.sort(nums);
        
        int currGCD = numsDivide[0];
        for(int i =1;i<numsDivide.length;i++){
            currGCD = getGCD(numsDivide[i],currGCD);
        }
        
        int ans  = 0;
        for(int ele:nums ){
            if(ele<currGCD && currGCD%ele!=0){
                ans++;
            }
            else if(ele<=currGCD && currGCD%ele==0) return ans;
        }
        
        return -1;
    }
    
    public int getGCD(int a,int b){
        if(b==0) return a;
        return getGCD(b,a%b);
    }
}
