import java.util.*;

public class BiweeklyContest109 {

    // 2784. Check if Array is Good
    public boolean isGood(int[] nums) {
        int n = nums.length;
        int max = 0;

        for (int ele : nums) {
            max = Math.max(max, ele);
        }
        int[] freq = new int[max + 1];
        for (int ele : nums) {
            freq[ele]++;
        }

        for (int i = 1; i <= max; i++) {
            if (i < max && freq[i] != 1)
                return false;
            else if (i == max && freq[i] != 2)
                return false;
        }

        return true;
    }

    // 2785. Sort Vowels in a String
    public String sortVowels(String s) {
        int n = s.length();
        ArrayList<Character> vowels = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (isVowel(ch))
                vowels.add(ch);
        }

        Collections.sort(vowels);
        int vidx = 0;
        int idx = 0;

        StringBuilder sb = new StringBuilder();

        while (idx < n) {
            char ch = s.charAt(idx);
            if (isVowel(ch)) {
                sb.append(vowels.get(vidx++));
            } else {
                sb.append(ch);
            }
            idx++;
        }

        return sb.toString();
    }

    public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I'
                || ch == 'O' || ch == 'U';
    }

    // 2786. Visit Array Positions to Maximize Score
    int[] nextEven;
    int[] nextOdd;

    public long maxScore(int[] nums, int x) {
        int n = nums.length;
        nextEven = new int[n];
        nextOdd = new int[n];
        fillArrays(nums, 0);
        fillArrays(nums, 1);
        long[][] dp = new long[n + 1][3];
        for (long[] d : dp)
            Arrays.fill(d, -1l);

        long ans = maxScore_(nums, x, dp, 0, nums[0] % 2);
        return ans + nums[0];
    }

    public void fillArrays(int[] arr, int mod) {
        int n = arr.length;
        int[] reqArray = mod == 0 ? nextEven : nextOdd;
        Arrays.fill(reqArray, n);

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (st.size() != 0 && arr[i] % 2 == mod) {
                reqArray[st.pop()] = i;
            }
            st.push(i);
        }

    }

    // isEven - 0 => even, 1 => odd
    public long maxScore_(int[] nums, int x, long[][] dp, int idx, int isEven) {
        if (idx == nums.length)
            return 0l;

        if (dp[idx][isEven] != -1l)
            return dp[idx][isEven];

        long ans = -(int) 1e9;
        // even
        int[] evenScore_Nidx = getScoreNidx(nums, idx, isEven, true, x); // score,idx,polarity
        ans = Math.max(ans, maxScore_(nums, x, dp, evenScore_Nidx[1], evenScore_Nidx[2]) + evenScore_Nidx[0]);

        // odd
        int[] oddScore_Nidx = getScoreNidx(nums, idx, isEven, false, x); // score,idx,polarity
        ans = Math.max(ans, maxScore_(nums, x, dp, oddScore_Nidx[1], oddScore_Nidx[2]) + oddScore_Nidx[0]);

        return dp[idx][isEven] = ans;
    }

    private int[] getScoreNidx(int[] nums, int idx, int isEven, boolean wantEven, int x) {
        int n = nums.length;
        int[] reqArray = wantEven ? nextEven : nextOdd;
        int nidx = reqArray[idx];

        if (nidx == n)
            return new int[] { 0, nidx, 2 };

        int nscore = nums[nidx] % 2 == isEven ? nums[nidx] : nums[nidx] - x;

        return new int[] { nscore, nidx, nums[nidx] % 2 };

    }
}
