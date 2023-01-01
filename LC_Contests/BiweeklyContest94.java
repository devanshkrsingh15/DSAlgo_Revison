import java.util.*;

public class BiweeklyContest94 {

    // 2511. Maximum Enemy Forts That Can Be Captured
    public int captureForts(int[] forts) {
        int ans = 0;
        int idx = 0;
        int n = forts.length;

        while (idx < n) {
            if (forts[idx] == 1) {
                int npos = idx + 1;
                int nans = 0;
                while (npos < n && forts[npos] != -1 && forts[npos] == 0) {
                    npos++;
                    nans++;
                    if (npos < n && forts[npos] == -1)
                        ans = Math.max(ans, nans);
                }
                idx = npos;
            } else {
                idx++;
            }
        }

        idx = n - 1;

        while (idx >= 0) {
            if (forts[idx] == 1) {
                int npos = idx - 1;
                int nans = 0;
                while (npos >= 0 && forts[npos] != -1 && forts[npos] == 0) {
                    npos--;
                    nans++;
                    if (npos >= 0 && forts[npos] == -1)
                        ans = Math.max(ans, nans);
                }
                idx = npos;
            } else {
                idx--;
            }
        }

        return ans;

    }

    // 2512. Reward Top K Students
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report,
            int[] student_id, int k) {
        HashSet<String> pos = fill(positive_feedback);
        HashSet<String> neg = fill(negative_feedback);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return (int) (a[0] - b[0]);
            else
                return (int) (b[1] - a[1]);
        });

        int n = report.length;
        for (int i = 0; i < n; i++) {
            long pts = findPoints(report[i], pos, neg);
            long id = (long) student_id[i];

            pq.add(new long[] { pts, id });
            if (pq.size() > k)
                pq.remove();
        }

        List<Integer> ans = new ArrayList<>();

        while (pq.size() != 0) {
            // System.out.print(pq.peek()[0] + " ");
            ans.add((int) (pq.remove()[1]));
        }

        Collections.reverse(ans);

        return ans;

    }

    private long findPoints(String s, HashSet<String> pos, HashSet<String> neg) {
        String[] arr = s.trim().split(" ");
        // for(String p : arr) System.out.println(p);
        int n = arr.length;
        long ans = 0;

        int i = 0;
        while (i < n) {
            String str = arr[i];

            if (pos.contains(str)) {
                ans += 3;
                i++;
            } else if (neg.contains(str)) {
                ans -= 1;
                i++;
            } else {
                i++;
            }
        }

        return ans;

    }

    private HashSet<String> fill(String[] arr) {
        HashSet<String> hs = new HashSet<>();
        for (String s : arr)
            hs.add(s);
        return hs;
    }

    // 2513. Minimize the Maximum of Two Arrays

    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        long lo = 1;
        long hi = (long) 1e18;
        long ans = -1;

        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;

            if (isValid(divisor1, divisor2, uniqueCnt1, uniqueCnt2, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }

        }

        return (int) ans;
    }

    public long getLCM(long a, long b) {
        long gcd = getGCD(a, b);

        return (a * b) / gcd;
    }

    public long getGCD(long a, long b) {
        if (b == 0)
            return a;
        return getGCD(b, a % b);
    }

    public boolean isValid(long d1, long d2, long c1, long c2, long myMax) {
        long valid1 = myMax - myMax / d1;
        long valid2 = myMax - myMax / d2;
        long lcm = getLCM(d1, d2);
        long validBoth = myMax - myMax / lcm;

        return (valid1 >= c1) && (valid2 >= c2) && (validBoth >= (c1 + c2));

    }

}
