import java.util.*;

public class BiweeklyContest99 {
    // 2578. Split With Minimum Sum
    public int splitNum(int num) {
        int[] arr = new int[10];
        while (num != 0) {
            int n = num % 10;
            arr[n]++;
            num = num / 10;
        }

        int a = 0;
        int b = 0;
        boolean firstTaken = false;

        for (int i = 0; i < 10; i++) {

            while (arr[i] != 0) {
                if (!firstTaken) {
                    a *= 10;
                    a += i;
                    firstTaken = true;
                } else {
                    b *= 10;
                    b += i;
                    firstTaken = false;
                }

                arr[i]--;

            }
        }

        return a + b;

    }

    // 2579. Count Total Number of Colored Cells
    public long coloredCells(int n) {
        if (n == 1)
            return 1l;
        long ans = 1l;
        long prv = 4l;
        for (int i = 2; i <= n; i++) {
            ans += prv;
            prv += 4l;
        }
        return ans;

    }
}
