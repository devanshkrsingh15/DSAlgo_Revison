import java.util.*;

public class WeeklyContest341 {

    // 2643. Row With Maximum Ones
    public int[] rowAndMaximumOnes(int[][] mat) {
        int ridx = -1;
        int cnt = -(int) 1e9;

        for (int i = 0; i < mat.length; i++) {
            int c = 0;
            for (int ele : mat[i]) {
                c += ele;
            }

            if (c > cnt) {
                cnt = c;
                ridx = i;
            }
        }

        // return {idx,cnt}

        return new int[] { ridx, cnt };
    }

    // 2644. Find the Maximum Divisibility Score
    public int maxDivScore(int[] nums, int[] divisors) {
        int n = divisors.length;
        int maxCnt = -1;
        int maxIdx = -1;

        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int ele : nums) {
                if (ele % divisors[i] == 0)
                    cnt++;
            }

            if (cnt > maxCnt) {
                maxCnt = cnt;
                maxIdx = divisors[i];
            } else if (cnt == maxCnt) {
                maxIdx = Math.min(maxIdx, divisors[i]);
            }
        }

        return maxIdx;
    }

    // 2645. Minimum Additions to Make Valid String
    public int addMinimum(String word) {
        int n = word.length();
        Stack<Character> st = new Stack<>();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            char ch = word.charAt(i);

            if (ch == 'a') {
                if (st.size() == 0)
                    st.push(ch);
                else {
                    if (st.peek() == 'a') {
                        ans += 2;
                        st.push('b');
                        st.push('c');
                        st.push(ch);
                    } else if (st.peek() == 'b') {
                        ans++;
                        st.push('c');
                        st.push(ch);
                    } else if (st.peek() == 'c') {
                        st.push(ch);
                    }
                }
            } else if (ch == 'b') {
                if (st.size() == 0) {
                    ans++;
                    st.push('a');
                    st.push(ch);
                } else {
                    if (st.peek() == 'a') {
                        st.push(ch);
                    } else if (st.peek() == 'b') {
                        ans += 2;
                        st.push('c');
                        st.push('a');
                        st.push(ch);
                    } else if (st.peek() == 'c') {
                        ans++;
                        st.push('a');
                        st.push(ch);
                    }
                }
            } else {
                if (st.size() == 0) {
                    ans += 2;
                    st.push('a');
                    st.push('b');
                    st.push(ch);
                } else {
                    if (st.peek() == 'a') {
                        ans++;
                        st.push('b');
                        st.push(ch);
                    } else if (st.peek() == 'b') {
                        st.push(ch);
                    } else if (st.peek() == 'c') {
                        ans += 2;
                        st.push('a');
                        st.push('b');
                        st.push(ch);
                    }
                }
            }
        }

        if (st.size() != 0 && st.peek() == 'a')
            ans += 2;
        if (st.size() != 0 && st.peek() == 'b')
            ans++;

        return ans;

    }
}
