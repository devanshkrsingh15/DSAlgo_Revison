import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class WeeklyContest329 {
    // 2544. Alternating Digit Sum

    public int alternateDigitSum(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add(n % 10);
            n = n / 10;
        }

        int ans = 0;
        boolean flag = true;
        for (int i = list.size() - 1; i >= 0; i--) {
            int ele = flag ? list.get(i) : -list.get(i);
            ans += ele;
            flag = !flag;
        }

        return ans;
    }

    // 2545. Sort the Students by Their Kth Score
    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, (a, b) -> {
            return b[k] - a[k];
        });

        return score;
    }

    // 2546. Apply Bitwise Operations to Make Strings Equal
    public boolean makeStringsEqual(String s, String t) {
        /*
         * we need just a pair (s,t) : (1,1) => to convert s to t
         * if it is present directly return true other wise find a pair which get (1,1)
         * 1) (0,1)
         * 2) (1,0)
         */

        if (s.equals(t))
            return true;

        int n = s.length();

        for (int i = 0; i < n; i++) {
            char sch = s.charAt(i);
            char tch = t.charAt(i);
            if (sch == '1' && tch == '1')
                return true;
        }

        boolean zero_one = false;
        boolean one_zero = false;

        for (int i = 0; i < n; i++) {
            char sch = s.charAt(i);
            char tch = t.charAt(i);
            if (sch == '1' && tch == '0')
                one_zero = true;
            else if (sch == '0' && tch == '1')
                zero_one = true;
        }

        return one_zero && zero_one;
    }

}
