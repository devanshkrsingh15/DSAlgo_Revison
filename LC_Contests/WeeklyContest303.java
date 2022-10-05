import java.util.*;

public class WeeklyContest303 {
    // 2351. First Letter to Appear Twice
    public char repeatedCharacter(String s) {
        int ei = 0;
        int[] freq = new int[26];

        int n = s.length();

        while (ei < n) {
            char ch = s.charAt(ei);
            if (freq[ch - 'a'] == 1)
                return ch;
            freq[ch - 'a']++;

            ei++;
        }

        return ' ';
    }

    // 2352. Equal Row and Column Pairs
    public int equalPairs(int[][] grid) {
        HashMap<String, Integer> map = new HashMap<>();
        int n = grid.length;
        for (int[] arr : grid) {
            String s = getEncoding(arr);
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        int ans = 0;
        for (int j = 0; j < n; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(grid[i][j]);
                sb.append("+");
            }

            if (map.containsKey(sb.toString()))
                ans += map.get(sb.toString());
        }

        return ans;
    }

    private String getEncoding(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int e : arr) {
            sb.append(e);
            sb.append("+");
        }

        return sb.toString();
    }

    // 2354. Number of Excellent Pairs
    public long countExcellentPairs(int[] nums, int k) {
        long ans = 0;
        /*
         * bitcount(x) = a
         * bitcount(y) = b
         * 
         * we need bitcount(x|y) + bitcount(x&y) >= k
         * bitcount(x&y) = c (common set bits)
         * bitcount(x|y) = a + b - c
         * bitcount(x|y) + bitcount(x&y) >= k
         * a+b-c + c >= k
         * a + b >= k
         * bitcount(x) + bitcount(y)>= k
         * 
         */

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int ele : nums) {
            int cnt = countSetBits(ele);
            map.putIfAbsent(cnt, new HashSet<>());
            map.get(cnt).add(ele);
        }
        HashSet<Integer> vis = new HashSet<>();

        for (int ele : nums) {
            if (vis.contains(ele))
                continue;
            int cnt = countSetBits(ele);

            int needed = Math.max(0, k - cnt);

            for (int v : map.keySet()) {
                if (v >= needed)
                    ans += (long) map.get(v).size();
            }

            vis.add(ele);
        }

        return ans;

    }

    private int countSetBits(int n) {
        int cnt = 0;
        while (n != 0) {
            n = n & (n - 1);
            cnt++;
        }
        return cnt;
    }

}

// 2353. Design a Food Rating System
class FoodRatings {

    HashMap<String, TreeSet<String>> cuisineMap = new HashMap<>();
    HashMap<String, Integer> ratingMap = new HashMap<>();
    HashMap<String, String> cMap = new HashMap<>();

    FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;

        for (int i = 0; i < n; i++) {
            String f = foods[i];
            int r = ratings[i];
            String c = cuisines[i];
            cMap.put(f, c);
            ratingMap.put(f, r);

            cuisineMap.putIfAbsent(c, new TreeSet<>((a, b) -> {
                if ((int) ratingMap.get(b) == (int) ratingMap.get(a))
                    return a.compareTo(b);
                return ratingMap.get(b) - ratingMap.get(a);
            }));
            cuisineMap.get(c).add(f);

        }

    }

    public void changeRating(String f, int r) {
        String c = cMap.get(f);
        cuisineMap.get(c).remove(f);

        ratingMap.put(f, r);
        cuisineMap.get(c).add(f);

    }

    public String highestRated(String cuisine) {
        return cuisineMap.get(cuisine).first();
    }
}
