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

    //2588. Count the Number of Beautiful Subarrays
}
