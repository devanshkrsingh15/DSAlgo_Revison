import java.util.*;

public class BiweeklyContest108 {

    public int alternatingSubarray(int[] nums) {
        int max = -1;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int diff = 1;
            int st = i + 1;
            while (st < n && nums[st] - nums[st - 1] == diff) {
                diff *= -1;
                st++;
            }
            if (st - i > 1)
                max = Math.max(max, st - i);

        }

        return max;
    }

    // 2766. Relocate Marbles
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        HashMap<Integer, Integer> posMapping = new HashMap<>();
        for (int ele : nums) {
            posMapping.put(ele, posMapping.getOrDefault(ele, 0) + 1);
        }

        int n = moveFrom.length;
        for (int i = 0; i < n; i++) {
            int opos = moveFrom[i];
            int npos = moveTo[i];

            if (npos == opos)
                continue;

            if (posMapping.containsKey(opos)) {
                int amt = posMapping.get(opos);
                posMapping.put(npos, posMapping.getOrDefault(npos, 0) + amt);
                posMapping.remove(opos);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int k : posMapping.keySet()) {
            if (posMapping.get(k) > 0)
                ans.add(k);
        }

        Collections.sort(ans);
        return ans;
    }

    // 2767. Partition String Into Minimum Beautiful Substrings
    HashSet<Integer> set = new HashSet<>();

    public int minimumBeautifulSubstrings(String s) {
        for (int i = 1; i < 65536; i = i * 5) {
            set.add(i);
        }
        int ans = minimumBeautifulSubstrings_(s, 0);
        return ans == (int) 1e9 ? -1 : ans;
    }

    public int minimumBeautifulSubstrings_(String s, int idx) {
        int len = s.length();
        if (idx == len)
            return 0;

        int ans = (int) 1e9;

        if (s.charAt(idx) != '0') {
            for (int i = idx; i < s.length(); i++) {
                String ns = s.substring(idx, i + 1);
                if (isPowerofFive(ns)) {
                    ans = Math.min(ans, minimumBeautifulSubstrings_(s, i + 1) + 1);
                }
            }
        }

        return ans;
    }

    public boolean isPowerofFive(String s) {
        int n = s.length();
        int tmp = 0;
        int idx = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                tmp |= (1 << idx);
            }

            idx++;
        }

        return set.contains(tmp);
    }

    // 2768. Number of Black Blocks
    public long[] countBlackBlocks(int n, int m, int[][] coordinates) {
        long[] ans = new long[5];
        HashMap<Long, Integer> map = new HashMap<>();

        for (int[] cord : coordinates) {
            int x = cord[0];
            int y = cord[1];
            if (isValid(x, y, n, m))
                add(x, y, map, m);
            if (isValid(x - 1, y - 1, n, m))
                add(x - 1, y - 1, map, m);
            if (isValid(x, y - 1, n, m))
                add(x, y - 1, map, m);
            if (isValid(x - 1, y, n, m))
                add(x - 1, y, map, m);
        }

        for (long key : map.keySet()) {
            int cnt = map.get(key);
            ans[cnt]++;
        }

        ans[0] = (long) (n - 1) * (long) (m - 1) - map.keySet().size();

        return ans;
    }

    public void add(int x, int y, HashMap<Long, Integer> map, int m) {
        long idx = (long) x * (long) m + (long) y;
        map.put(idx, map.getOrDefault(idx, 0) + 1);
    }

    public boolean isValid(int x, int y, int n, int m) {
        return x >= 0 && y >= 0 && x + 1 < n && y + 1 < m;
    }
}
