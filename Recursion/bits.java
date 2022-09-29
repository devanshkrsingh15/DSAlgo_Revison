package Recursion;

public class bits {
    /*
     * LSB : x<<1 => x*2
     * RSB : x>>1 => x/2
     * 
     * removing last set bit => x&(x-1)
     * get the last set bit => x&(-x)
     */

    // 338. Counting Bits
    public int[] countBits(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 0;

        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i & (i - 1)] + 1;
        }

        return arr;
    }

    // 191. Number of 1 Bits
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            n = n & (n - 1);
            cnt++;
        }
        return cnt;
    }

    // 231. Power of Two
    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }

    // 326. Power of Three
    public boolean isPowerOfThree(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    // 342. Power of Four
    // should be power 2 and even number of zeros
    public boolean isPowerOfFour(int n) {
        if (!(n > 0 && ((n & (n - 1)) == 0)))
            return false;

        int cnt = 0;

        while (n != 0) {
            if ((n & 1) == 0)
                cnt++;
            n >>>= 1;
        }

        return (cnt & 1) == 0;
    }

    // 136. Single Number
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int ele : nums) {
            ans ^= ele;
        }
        return ans;
    }

    // 137. Single Number II
    // eachh number appears k times, except one
    public int singleNumberII(int[] nums) {
        int k = 3;
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int mask = (1 << i);
            int cnt = 0;

            for (int ele : nums) {
                if ((ele & mask) != 0)
                    cnt++;
            }

            if (cnt % k == 1)
                ans |= mask;
        }

        return ans;
    }

    // 260. Single Number III
    public int[] singleNumberIII(int[] nums) {
        int x = 0;
        for (int ele : nums) {
            x ^= ele;

        }
        int e1 = 0;
        int e2 = 0;
        int lsb = x & (-x);

        for (int ele : nums) {
            if ((lsb & ele) == 0)
                e1 ^= ele;
            else
                e2 ^= ele;
        }

        return new int[] { e1, e2 };
    }

}
