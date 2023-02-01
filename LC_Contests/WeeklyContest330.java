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

    

}
