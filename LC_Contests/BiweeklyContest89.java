import java.util.*;

public class BiweeklyContest89 {
    // 2437. Number of Valid Clock Times
    public int countTime(String time) {
        int ans = 1;

        String[] arr = time.split(":");

        for (int i = 0; i < 2; i++) {
            String t = arr[i];
            if (i == 0) {
                if (t.equals("??"))
                    ans *= 24;
                else {
                    char ch = t.charAt(0);
                    if (ch == '?') {
                        char sch = t.charAt(1);
                        ans = ans * ((sch <= '3') ? 3 : 2);
                    }

                    ch = t.charAt(1);
                    if (ch == '?') {
                        char pch = t.charAt(0);
                        ans = ans * ((pch <= '1') ? 10 : 4);
                    }
                }
            } else {
                if (t.equals("??"))
                    ans *= 60;
                else {
                    char ch = t.charAt(0);
                    if (ch == '?') {
                        ans *= 6;
                    }
                    ch = t.charAt(1);
                    if (ch == '?') {
                        ans = ans * 10;
                    }
                }

            }
        }

        return ans;
    }

    // 2438. Range Product Queries of Powers
    public int[] productQueries(int n, int[][] queries) {
        ArrayList<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add(n & (-n));
            n = n & (n - 1);
        }

        long mod = (long)1e9   + 7;
        int idx = 0;
        int[] ans = new int[queries.length];
        for (int[] q : queries) {
            int st = q[0];
            int en = q[1];

            long t  = 1l;
            for(int  i = st;i<=en;i++){
                t=( t%mod + list.get(i)%mod)%mod;
            }

            ans[idx++]= (int)t;

        }

        return ans;
    }
}
