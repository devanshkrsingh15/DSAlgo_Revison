import java.util.*;

public class WeeklyContest309 {
    // 2399. Check Distances Between Same Letters
    public boolean checkDistances(String s, int[] distance) {
        int[] arr = new int[26];
        Arrays.fill(arr, -1);
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (arr[ch - 'a'] == -1) {
                arr[ch - 'a'] = i;
            } else {
                int st = arr[ch - 'a'];
                arr[ch - 'a'] = i - st - 1;
            }
        }

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (distance[ch - 'a'] != arr[ch - 'a'])
                return false;
        }

        return true;

    }

    // 2400. Number of Ways to Reach a Position After Exactly k Steps
    public int numberOfWays(int startPos, int endPos, int k) {
        long[][] dp = new long[3005][k + 1];
        for (long[] d : dp)
            Arrays.fill(d, -1l);
        return (int) numberOfWays_(dp, startPos + k, endPos + k, k);
    }

    public long numberOfWays_(long[][] dp, int st, int en, int k) {
        long mod = (long) 1e9 + 7;

        if (k == 0) {
            long w = (st == en) ? 1l : 0l;
            dp[st][k] = w;
            return w;
        }

        if (k < 0) {
            return 0;
        }

        if (dp[st][k] != -1)
            return dp[st][k];

        long ways = 0;
        ways = (ways % mod + (numberOfWays_(dp, st + 1, en, k - 1) % mod)) % mod;
        ways = (ways % mod + (numberOfWays_(dp, st - 1, en, k - 1) % mod)) % mod;

        return dp[st][k] = ways;
    }

    // 2401. Longest Nice Subarray
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int en = 0;
        int st = 0;
        int max = 0;

        int[] freq = new int[32];
        boolean flag = false;

        while (en < n) {
            increment(freq, nums[en++]);

            for (int i = 0; i < 32; i++) {
                if (freq[i] == 2) {
                    flag = true;
                    break;
                }
            }

            while (flag) {
                decrement(freq, nums[st++]);
                boolean check = false;
                int cnt = 0;
                for (int i = 0; i < 32; i++) {
                    if (freq[i] == 2) {
                        check = true;
                        break;
                    }
                }

                if (check == false)
                    flag = false;
            }

            max = Math.max(max, en - st);
        }

        return max;
    }

    private void increment(int[] freq, int ele) {
        for (int i = 0; i < 32; i++) {
            int mask = (1 << i);
            if ((ele & mask) != 0)
                freq[i]++;
        }

    }

    private void decrement(int[] freq, int ele) {
        for (int i = 0; i < 32; i++) {
            int mask = (1 << i);
            if ((ele & mask) != 0)
                freq[i]--;
        }

    }

    // 2402. Meeting Rooms III
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> {
            return a[0] - b[0];
        });

        PriorityQueue<Integer> unUsed_pq = new PriorityQueue<>((a, b) -> {
            return a - b;
        });
        PriorityQueue<int[]> Used_pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            return a[1] - b[1];
        }); // {idx,time}

        for (int i = 0; i < n; i++) {
            unUsed_pq.add(i);
        }

        int[] cnt = new int[n];
        int idx = 0;
        int len = meetings.length;
        while (idx < len) {
            int curr[] = meetings[idx];

            while (Used_pq.size() != 0 && curr[0] >= Used_pq.peek()[1]) {
                int[] removeRoom = Used_pq.remove();
                int room = removeRoom[0];
                unUsed_pq.add(room);
            }

            if (unUsed_pq.size() != 0) {
                int room = unUsed_pq.remove();
                cnt[room]++;
                Used_pq.add(new int[] { room, meetings[idx++][1] });
            } else {
                int[] removeRoom = Used_pq.remove();
                int room = removeRoom[0];
                int currTime = removeRoom[1];
                cnt[room]++;
                int durations = meetings[idx][1] - meetings[idx][0];
                Used_pq.add(new int[] { room, currTime + durations });
                idx++;
            }
        }

        int max = 0;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (max < cnt[i]) {
                max = cnt[i];
                ans = i;
            }
        }

        return ans;

    }

}
