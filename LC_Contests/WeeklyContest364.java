import java.util.*;

public class WeeklyContest364 {

    // 2864. Maximum Odd Binary Number
    public String maximumOddBinaryNumber(String s) {
        int n = s.length();
        int z = 0;
        int o = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0')
                z++;
            else
                o++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append('1');
        o--;

        while (z-- > 0)
            sb.append('0');
        while (o-- > 0)
            sb.append('1');

        sb.reverse();
        return sb.toString();
    }

    // 2865. Beautiful Towers I
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        long tot = 0l;
        int n = maxHeights.size();

        for (int ele : maxHeights) {
            tot += (long) ele;
        }

        long ans = 0;

        for (int i = 0; i < n; i++) {
            // if ith is peak;
            long myans = maxHeights.get(i);
            long prv = maxHeights.get(i);

            for (int j = i - 1; j >= 0; j--) {
                if (maxHeights.get(j) > prv) {
                    myans += prv;
                } else {
                    myans += (long) maxHeights.get(j);
                    prv = (long) maxHeights.get(j);
                }
            }

            prv = maxHeights.get(i);

            for (int j = i + 1; j < n; j++) {
                if (maxHeights.get(j) > prv) {
                    myans += prv;
                } else {
                    myans += (long) maxHeights.get(j);
                    prv = (long) maxHeights.get(j);
                }
            }

            ans = Math.max(ans, myans);

        }

        return ans;
    }

    // 2866. Beautiful Towers II
    public long maximumSumOfHeights_(List<Integer> maxHeights) {
        int n = maxHeights.size();

        long[] leftPeakSum = generatePeakSum(maxHeights, true);
        long[] rightPeakSum = generatePeakSum(maxHeights, false);

        long ans = 0l;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, leftPeakSum[i] + rightPeakSum[i] - (long) maxHeights.get(i));
        }

        return ans;
    }

    private long[] generatePeakSum(List<Integer> maxHeights, boolean onLeft) {
        int n = maxHeights.size();
        long sum[] = new long[n];
        int[] nsarr = generateNextSmaller(maxHeights, onLeft);

        for (int i = 0; i < n; i++) {
            int idx = onLeft ? i : n - i - 1;
            sum[idx] = onLeft ? (long) (idx - nsarr[idx]) * maxHeights.get(idx)
                    : (long) (nsarr[idx] - idx) * maxHeights.get(idx);
            sum[idx] += onLeft ? ((nsarr[idx] >= 0) ? sum[nsarr[idx]] : 0) : ((nsarr[idx] < n) ? sum[nsarr[idx]] : 0);
        }
        return sum;
    }

    public int[] generateNextSmaller(List<Integer> maxHeights, boolean onLeft) {
        int n = maxHeights.size();
        int[] ans = new int[n];
        Arrays.fill(ans, onLeft ? -1 : n);
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            int idx = onLeft ? n - i - 1 : i;

            while (st.size() != 0 && maxHeights.get(idx) <= maxHeights.get(st.peek())) {
                ans[st.pop()] = idx;
            }

            st.push(idx);
        }

        return ans;
    }
}
