import java.util.*;

public class WeeklyContest332 {
    // 2562. Find the Array Concatenation Value

    public long findTheArrayConcVal(int[] nums) {
        long ans = 0;
        int n = nums.length;

        int i = 0;
        int j = n - 1;

        while (i <= j) {
            int a = nums[i];
            int b = (i == j) ? 0 : nums[j];

            long cval = getCon(a, b);
            ans += cval;

            i++;
            j--;
        }

        return ans;
    }

    private long getCon(long a, long b) {
        if (b == 0)
            return a;

        int cnt = getPower(b);
        return (long) Math.pow(10, cnt) * a + b;
    }

    private int getPower(long b) {
        int cnt = 0;
        while (b != 0) {
            b /= 10;
            cnt++;
        }
        return cnt;
    }

    // 2563. Count the Number of Fair Pairs
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        // lower <= cnt <= upper = (cnt <= lower-1) - (cnt <= upper)
        return countFairPairs_(nums, upper) - countFairPairs_(nums, lower - 1);
    }

    public long countFairPairs_(int[] nums, long tar) {
        int n = nums.length;
        int l = 0;
        int h = n - 1;
        long ans = 0;

        while (l < h) {
            long csum = (long) nums[l] + (long) nums[h];

            if (csum <= tar) {
                ans += (long) (h - l);
                l++;
            } else {
                h--;
            }
        }

        return ans;
    }

    public long countFairPairs_(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int i = 1; i < n; i++) {
            int ele = nums[i];

            int lo = findLower(nums, lower - ele, 0, i - 1);
            int hi = findUpper(nums, upper - ele, 0, i - 1);
            if (lo == -1 || hi == -1)
                continue;
            ans += (long) hi - (long) lo + 1;
        }

        return ans;
    }

    public int findLower(int[] nums, int ele, int l, int h) {
        int ans = -1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (nums[mid] >= ele) {
                ans = mid;
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    public int findUpper(int[] nums, int ele, int l, int h) {
        int ans = -1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (nums[mid] <= ele) {
                ans = mid;
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return ans;
    }

    // 2564. Substring XOR Queries
    public int[][] substringXorQueries(String s, int[][] queries) {
        HashMap<Integer, int[]> map = new HashMap<>();
        int n = s.length();

        // n
        for (int i = 0; i < n; i++) {
            // 32*32
            for (int j = 0; j < 31; j++) {
                if (i + j < n) {
                    String st = s.substring(i, i + j + 1);
                    int num = Integer.parseInt(st, 2);
                    if (map.containsKey(num) && st.length() < map.get(num)[1] - map.get(num)[0] + 1) {
                        map.put(num, new int[] { i, i + j });
                    } else
                        map.putIfAbsent(num, new int[] { i, i + j });

                }
            }
        }

        int[][] ans = new int[queries.length][];
        int idx = 0;
        for (int[] q : queries) {
            int num = (q[1] ^ q[0]);
            System.out.println(num);
            ans[idx++] = map.getOrDefault(num, new int[] { -1, -1 });
        }

        return ans;
    }
}
