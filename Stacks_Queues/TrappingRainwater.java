import java.util.Stack;

public class TrappingRainwater {

    public int TrappingRainwater_1(int[] height) {
        int n = height.length;
        int[] lmax = new int[n];
        lmax[0] = height[0];

        for (int i = 1; i < n; i++)
            lmax[i] = Math.max(lmax[i - 1], height[i]);

        int[] rmax = new int[n];
        rmax[n - 1] = height[n - 1];

        for (int i = n - 2; i >= 0; i--)
            rmax[i] = Math.max(rmax[i + 1], height[i]);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(rmax[i], lmax[i]) - height[i];
        }

        return ans;
    }

    // Using stack
    public int TrappingRainwater_2(int[] height) {
        int ans = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int n = height.length;

        for (int i = 0; i < n; i++) {
            while (st.size() > 1 && height[st.peek()] <= height[i]) {
                int evalHt = height[st.pop()];
                if (st.peek() == -1)
                    break;
                int rmax = height[st.peek()];
                int lmax = height[i];

                ans += (Math.min(rmax, lmax) - evalHt) * (i - st.peek() - 1);

            }
            st.push(i);
        }

        return ans;
    }

    // O(1) => Two pointers
    public int TrappingRainwater_3(int[] height) {
        int n = height.length;
        int i = 0;
        int j = n - 1;

        int lmax = height[0];
        int rmax = height[n - 1];
        int ans = 0;

        while (i < j) {
            lmax = Math.max(lmax, height[i]);
            rmax = Math.max(rmax, height[j]);
            if (lmax <= rmax) {
                ans += lmax - height[i];
                i++;
            } else {
                ans += rmax - height[j];
                j--;
            }
        }

        return ans;
    }
}
