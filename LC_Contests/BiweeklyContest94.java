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


    //2513. Minimize the Maximum of Two Arrays
    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        long a = smallestCandidate(divisor1);
        
        long b = smallestCandidate(divisor2);

        return (int)Math.max(a,b);
    }

    private long smallestCandidate(int div) {
        long ans = 0;
        for()
    }
}
