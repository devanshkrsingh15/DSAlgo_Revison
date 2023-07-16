import java.util.*;

public class WeeklyContest351 {

    // 2748. Number of Beautiful Pairs
    public int countBeautifulPairs(int[] nums) {
        int ans = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int firstDig = getFirst(nums[i]);
                int lastDig = getLast(nums[j]);

                ans += (gcd(firstDig, lastDig) == 1) ? 1 : 0;
            }
        }

        return ans;
    }

    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public int getLast(int ele) {
        return ele % 10;

    }

    public int getFirst(int ele) {
        while (ele / 10 != 0) {
            ele = ele / 10;
        }

        return ele;

    }

    // 2749. Minimum Operations to Make the Integer Zero

    public int makeTheIntegerZero(int num1, int num2) {
        if (num2 > num1)
            return -1;

        for (int k = 0; k <= 32; k++) {
            long diff = num1 - (long) k * num2;

            int minSetBits = countSetBits(diff);
            ;
            long maxSetBits = diff;

            if ((long) k <= maxSetBits && k >= minSetBits)
                return k;
        }

        return -1;
    }

    public int countSetBits(long ele) {
        int cnt = 0;
        while (ele != 0) {
            cnt++;
            ele = ele & (ele - 1);
        }
        return cnt;
    }

    // 2750. Ways to Split Array Into Good Subarrays
    long mod = (long) 1e9 + 7;

    public int numberOfGoodSubarraySplits(int[] nums) {
        int n = nums.length;
        int[] psum = new int[n];
        int sof = 0;
        for (int i = 0; i < n; i++) {
            sof += nums[i];
            psum[i] = sof;
        }

        if (psum[n - 1] == 0)
            return 0;
        if (psum[n - 1] == 1)
            return 1;

        int[] freq = new int[psum[n - 1] + 1];
        for (int ele : psum)
            freq[ele]++;

        long ans = 1;

        for (int i = 1; i < freq.length - 1; i++) {
            ans = (ans % mod * freq[i] % mod) % mod;
        }

        return (int) ans;
    }

    // 2751. Robot Collisions
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        ArrayList<int[]> list = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int lcount = 0;
        int rcount = 0;
        int n = positions.length;
        for (int i = 0; i < n; i++) {
            list.add(new int[] { i, positions[i], healths[i], directions.charAt(i) - 'A' });
            if (directions.charAt(i) == 'L')
                lcount++;
            else
                rcount++;
        }

        if (lcount == n || rcount == n) {
            for (int i = 0; i < n; i++)
                ans.add(healths[i]);
            return ans;
        }

        Collections.sort(list, (a, b) -> {
            return a[1] - b[1];
        });

        Stack<int[]> lstack = new Stack<>();
        Stack<int[]> rstack = new Stack<>();

        int idx = 0;

        while (idx < n) {
            int[] arr = list.get(idx);
            char dir = (char) (arr[3] + 'A');
            // System.out.println(dir);
            if (dir == 'R') {
                rstack.add(arr);
            } else {
                if (rstack.size() != 0) {
                    if (rstack.peek()[2] == arr[2]) {
                        rstack.pop();
                    } else if (rstack.peek()[2] < arr[2]) {
                        while (rstack.size() != 0 && rstack.peek()[2] < arr[2]) {
                            rstack.pop();
                            arr[2]--;
                        }
                        if (rstack.size() == 0) {
                            lstack.add(arr);
                        } else if (rstack.peek()[2] == arr[2]) {
                            rstack.pop();
                        } else if (rstack.peek()[2] > arr[2]) {
                            rstack.peek()[2]--;
                        }

                    } else {
                        rstack.peek()[2]--;
                    }
                } else {
                    lstack.add(arr);
                }
            }
            idx++;
        }

        ArrayList<int[]> tmp = new ArrayList<>();
        while (rstack.size() != 0) {
            tmp.add(rstack.pop());
        }

        while (lstack.size() != 0) {
            tmp.add(lstack.pop());
        }

        Collections.sort(tmp, (a, b) -> a[0] - b[0]);

        for (int[] t : tmp) {
            ans.add(t[2]);
        }

        return ans;

    }
}