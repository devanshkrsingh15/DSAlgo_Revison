package Recursion;

public class QueensProb {

    public int queensComb_1D(int tb, int tq, int cb, int cq, String ans) {
        if (cq == tq) {
            System.out.println(ans);
            return 1;
        }

        int cnt = 0;
        for (int i = cb; i < tb; i++) {
            cnt += queensComb_1D(tb, tq, i + 1, cq + 1, ans + "(q" + cq + "->" + "b" + i + ")");
        }

        return cnt;
    }

    int n;
    int m;

    // tb => n*m
    public int queensComb_2D(int tb, int tq, int cb, int cq, String ans) {
        if (cq == tq) {
            System.out.println(ans);
            return 1;
        }

        int cnt = 0;
        for (int i = cb; i < tb; i++) {
            int r = i / m;
            int c = i % m;
            cnt += queensComb_2D(tb, tq, i + 1, cq + 1, ans + "(q" + cq + "->" + "b" + r + "," + c + ")");
        }

        return cnt;
    }

    public int queensPerm_1D(int tb, int tq, int cb, int cq, String ans, boolean[] vis) {
        if (cq == tq) {
            System.out.println(ans);
            return 1;
        }

        int cnt = 0;
        for (int i = cb; i < tb; i++) {
            if (vis[i] == false) {
                vis[i] = true;
                cnt += queensPerm_1D(tb, tq, 0, cq + 1, ans + "(q" + cq + "->" + "b" + i + ")", vis);
                vis[i] = false;
            }
        }

        return cnt;
    }

    // tb => n*m
    public int queensPerm_2D(int tb, int tq, int cb, int cq, String ans, boolean[][] vis) {
        if (cq == tq) {
            System.out.println(ans);
            return 1;
        }

        int cnt = 0;
        for (int i = cb; i < tb; i++) {
            int r = i / m;
            int c = i % m;
            if (vis[r][c] == false) {
                vis[r][c] = true;
                cnt += queensPerm_2D(tb, tq, 0, cq + 1, ans + "(q" + cq + "->" + "b" + r + "," + c + ")", vis);
                vis[r][c] = false;
            }
        }

        return cnt;
    }

    public int queensComb_1D_sub(int tb, int tq, int cb, int cq, String ans) {
        if (cq == tq || cb == tb) {
            if (cq == tq) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int cnt = 0;
        cnt += queensComb_1D_sub(tb, tq, cb + 1, cq + 1, ans + "(q" + cq + "->" + "b" + tb + ")");
        cnt += queensComb_1D_sub(tb, tq, cb + 1, cq, ans);

        return cnt;
    }

    // tb => n*m
    public int queensComb_2D_sub(int tb, int tq, int cb, int cq, String ans) {
        if (cq == tq) {
            System.out.println(ans);
            return 1;
        }

        int cnt = 0;
        int r = cb / m;
        int c = cb % m;
        cnt += queensComb_2D_sub(tb, tq, cb + 1, cq + 1, ans + "(q" + cq + "->" + "b" + r + "," + c + ")");
        cnt += queensComb_2D_sub(tb, tq, cb + 1, cq, ans);

        return cnt;
    }

    public int queensPerm_1D_sub(int tb, int tq, int cb, int cq, String ans, boolean[] vis) {
        if (cq == tq || cb == tb) {
            if (cq == tq) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int cnt = 0;

        if (vis[cb] == false) {
            vis[cb] = true;
            cnt += queensPerm_1D_sub(tb, tq, 0, cq + 1, ans + "(q" + cq + "->" + "b" + tb + ")", vis);
            vis[cb] = false;
        }

        cnt += queensPerm_1D_sub(tb, tq, 0, cq, ans, vis);

        return cnt;
    }

    // tb => n*m
    public int queensPerm_2D_sub(int tb, int tq, int cb, int cq, String ans, boolean[][] vis) {
        if (cq == tq || cb == tb) {
            if (cq == tq) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int cnt = 0;

        int r = cb / m;
        int c = cb % m;
        if (vis[r][c] == false) {
            vis[r][c] = true;
            cnt += queensPerm_2D_sub(tb, tq, 0, cq + 1, ans + "(q" + cq + "->" + "b" + r + "," + c + ")", vis);
            vis[r][c] = false;
        }

        cnt += queensPerm_2D_sub(tb, tq, 0, cq, ans, vis);

        return cnt;
    }

}
