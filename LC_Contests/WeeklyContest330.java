import java.util.*;

public class WeeklyContest330 {
    // 2549. Count Distinct Numbers on Board
    HashSet<Integer> hs = new HashSet<>();

    public int distinctIntegers_(int n) {
        hs.add(n);
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 1) {
                ans += distinctIntegers(i) + 1;
            }
        }

        return ans;
    }

    public int distinctIntegers(int n) {
        distinctIntegers_(n);
        return hs.size();

    }

    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        if (n == k)
            return 0l;

        ArrayList<Long> list = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            list.add((long) weights[i] + (long) weights[i - 1]);
        }

        long min = 0;
        Collections.sort(list);
        for (int i = 0; i < k - 1; i++) {
            min += list.get(i);
        }

        long max = 0;
        Collections.reverse(list);
        for (long ele : list)
            System.out.print(ele + " ");
        for (int i = 0; i < k - 1; i++) {
            max += list.get(i);
        }
        return max - min;

    }

}
