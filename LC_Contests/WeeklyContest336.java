import java.util.*;

public class WeeklyContest336 {

    // 2586. Count the Number of Vowel Strings in Range
    public int vowelStrings(String[] words, int left, int right) {
        int cnt = 0;
        for (int i = left; i <= right; i++) {
            if (isVowelString(words[i]))
                cnt++;
        }

        return cnt;
    }

    public boolean isVowelString(String s) {
        return isVowel(s.charAt(0)) && isVowel(s.charAt(s.length() - 1));
    }

    public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    // 2587. Rearrange Array to Maximize Prefix Score
    public int maxScore(int[] nums) {
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();
        ArrayList<Integer> zeros = new ArrayList<>();

        for (int ele : nums) {
            if (ele > 0) {
                pos.add(ele);
            } else if (ele < 0) {
                neg.add(ele);
            } else {
                zeros.add(ele);
            }
        }

        int ans = pos.size();
        long sof = 0l;
        for (int p : pos)
            sof += (long) p;
        if (sof > 0)
            ans += zeros.size();

        // reverse order
        Collections.sort(neg, (a, b) -> {
            return a < b ? 1 : -1;
        });

        for (int n : neg) {
            if ((long) n + sof > 0) {
                ans++;
                sof += (long) n;
            } else {
                break;
            }
        }

        return ans;

    }

    // 2588. Count the Number of Beautiful Sub-arrays
    public long beautifulSubarrays(int[] nums) {
        // we are subtract each set bit from all elements in an sub-array
        // if we want all elements to be 0 => xor of all elements should be 0
        HashMap<Integer, Long> map = new HashMap<>();
        int pxor = 0;
        map.put(0, 1l);
        long ans = 0;
        for (int ele : nums) {
            pxor ^= ele;
            ans += map.getOrDefault(pxor, 0l);
            map.put(pxor, map.getOrDefault(pxor, 0l) + 1l);
        }

        return ans;
    }

    /// 2589. Minimum Time to Complete All Tasks
    public int findMinimumTime(int[][] tasks) {
        /*
         * tasks should be ran as late as possible
         * sort on the basis of end time, so that the above statement can be achieved
         */
        
        int[] arr = new int[2000 + 10];
        int ans = 0;
        Arrays.sort(tasks, (a, b) -> {
            return (a[1] != b[1]) ? a[1] - b[1] : b[2] - a[2];
        });

        for (int[] t : tasks) {
            int st = t[0];
            int en = t[1];
            int dur = t[2];

            for (int i = en; i >= st; i--) {
                if (arr[i] > 0)
                    dur--;
            }

            if (dur > 0) {
                for (int i = en; i >= st; i--) {
                    if (arr[i] == 0) {
                        arr[i]++;
                        dur--;
                    }
                    if (dur == 0)
                        break;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0)
                ans++;
        }

        return ans;
    }
}
