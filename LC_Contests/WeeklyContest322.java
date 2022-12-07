import java.util.*;

public class WeeklyContest322 {
    // 2490. Circular Sentence
    public boolean isCircularSentence(String sentence) {
        String[] arr = sentence.split(" ");

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int currIdx = i;
            int nextIdx = (i == n - 1) ? 0 : i + 1;

            if (arr[currIdx].charAt(arr[currIdx].length() - 1) != arr[nextIdx].charAt(0))
                return false;
        }

        return true;
    }

    // 2491. Divide Players Into Teams of Equal Skill
    public long dividePlayers(int[] skill) {
        int n = skill.length;
        long sof = 0;
        long max = 0;

        for (int s : skill) {
            long sk = (long) s;
            sof += sk;
            max = Math.max(max, sk);
        }
        long te = (long) (n / 2);
        if (sof % te != 0 || sof / te <= max)
            return -1l;

        long tar = sof / te;
        Arrays.sort(skill);

        int lo = 0;
        int hi = n - 1;
        long ans = 0;
        while (lo < hi) {
            long a = (long) skill[lo];
            long b = (long) skill[hi];

            if (a + b == tar) {
                ans += (long) a * b;
                lo++;
                hi--;
            } else {
                return -1;
            }

        }
        return ans;

    }
}
