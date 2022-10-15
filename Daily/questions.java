package Daily;

import java.util.*;

public class questions {
    // 1531. String Compression II
    public class Pair {
        char ch;
        int cnt;

        Pair(char ch, int cnt) {
            this.ch = ch;
            this.cnt = cnt;
        }
    }

    public int getLengthOfOptimalCompression(String s, int k) {
        ArrayList<Pair> list = getEncoding(s);
        // for(Pair rp :list) System.out.println(rp.ch + " " + rp.cnt);
        int n = s.length();
        int[][][] dp = new int[list.size() + 1][k + 1][n + 1]; // {current idx,moves,prv additions}
        for (int[][] d2 : dp) {
            for (int[] d : d2)
                Arrays.fill(d, -1);
        }

        return getLengthOfOptimalCompression_(s, list, 0, k, 0, dp);
    }

    private int getLengthOfOptimalCompression_(String s, ArrayList<Pair> list, int i, int k, int prv, int[][][] dp) {
        if (i == list.size()) {
            return dp[i][k][prv] = 0;
        }

        if (dp[i][k][prv] != -1)
            return dp[i][k][prv];

        Pair rp = list.get(i);
        int c = rp.cnt + prv; // total characters (inc prv)
        int add = contribution(c);

        // no removal
        int min = add + getLengthOfOptimalCompression_(s, list, i + 1, k, 0, dp);

        // removing current char, if decreases the length
        int[] decNumber = new int[] { 0, 1, 9, 99 }; // amount which dec the length
        for (int e : decNumber) {
            int del = c - e;
            add = contribution(e);
            if (del > 0 && del <= k) {
                min = Math.min(min, getLengthOfOptimalCompression_(s, list, i + 1, k - del, 0, dp) + add);
            }
        }
        // if we completely remove current set of characters => shifting can take place
        // = a2b2a3 => a5
        int moves = k;
        for (int j = i + 1; j < list.size() && moves >= 0; j++) {
            Pair cp = list.get(j);

            if (cp.ch == rp.ch) {
                min = Math.min(min, getLengthOfOptimalCompression_(s, list, j, moves, c, dp));
            }
            moves -= cp.cnt;
        }

        return dp[i][k][prv] = min;
    }

    private int contribution(int cnt) {
        if (cnt == 0)
            return 0;
        if (cnt == 1)
            return 1;
        if (cnt >= 2 && cnt <= 9)
            return 2;
        if (cnt >= 10 && cnt < 100)
            return 3;
        return 4;
    }

    public ArrayList<Pair> getEncoding(String s) {
        int n = s.length();
        int i = 0;
        ArrayList<Pair> list = new ArrayList<>();

        while (i < n) {
            char ch = s.charAt(i);
            int cnt = 1;
            while (i + 1 < n && ch == s.charAt(i + 1)) {
                i++;
                cnt++;
            }
            list.add(new Pair(ch, cnt));
            i++;
        }

        return list;
    }
}
