package Arrays_Strings;

public class questions {
    // Rotate Array
    public void RotateByK(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        if (k < 0)
            k += n;

        reverse(arr, n - k, n - 1);
        reverse(arr, 0, n - k - 1);
        reverse(arr, 0, n - 1);
    }

    public void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Negative and Positive
    public void segregatePosNeg(int a[], long n) {
        int i = 0;
        int j = 0;

        while (j < n) {
            if (a[j] < 0) {
                swap(a, i, j);
                i++;
                j++;
            } else {
                j++;
            }
        }

    }

    // Sort an array of 0s, 1s and 2s
    public void sort012(int a[], int n) {
        /*
         * 0 - i -> 0s
         * i+1 - j -> 1s
         * j+1 - k-1 -> undefined
         * k - n-1 -> 2s
         */

        int i = 0;
        int j = 0;
        int k = n - 1;

        while (j <= k) {
            if (a[j] == 0) {
                swap(a, i, j);
                i++;
                j++;
            } else if (a[j] == 1) {
                j++;
            } else {
                swap(a, j, k);
                k--;
            }
        }
    }

    // Maximum sum of i*arr[i] among all rotations of a given array (GFG)
    public int MaxSumConfiguration(int[] arr) {
        int n = arr.length;

        int max = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            max += i * arr[i];
        }

        int csum = max;

        for (int i = 0; i < n; i++) {
            csum = csum - sum + (n) * arr[i];
            max = Math.max(max, csum);
        }

        return max;
    }

    // 11. Container With Most Water
    public int ContainerWithMostWater(int[] height) {
        int ans = 0;
        int n = height.length;
        int i = 0;
        int j = n - 1;

        while (i < j) {
            int myAns = Math.min(height[i], height[j]) * (j - i);
            ans = Math.max(ans, myAns);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return ans;
    }

    // 3. Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int[] arr = new int[256];
        int ei = 0;
        int st = 0;
        boolean flag = false;
        int max = 0;

        while (ei < n) {
            char ch = s.charAt(ei++);
            arr[ch]++;
            if (arr[ch] == 2)
                flag = true;

            while (flag) {
                char sch = s.charAt(st++);
                if (arr[sch] == 2)
                    flag = false;
                arr[sch]--;
            }

            max = Math.max(max, ei - st);

        }
        return max;
    }

    // 76. Minimum Window Substring
    public String minWindow(String s, String t) {
        int[] arr = new int[256];
        int cnt = 0;
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (arr[ch] == 0)
                cnt++;
            arr[ch]++;
        }

        int n = s.length();
        int ei = 0;
        int si = 0;
        int max = (int) 1e9;
        int msi = -1;

        while (ei < n) {
            char ch = s.charAt(ei++);
            arr[ch]--;
            if (arr[ch] == 0)
                cnt--;

            while (cnt == 0) {
                int len = ei - si;
                if (len < max) {
                    max = len;
                    msi = si;
                }

                char sch = s.charAt(si++);
                arr[sch]++;
                if (arr[sch] == 1)
                    cnt++;
            }
        }

        if (max == (int) 1e9)
            return "";
        return s.substring(msi, msi + max);
    }

    // 159. Longest Substring with At Most Two Distinct Characters
    public int lengthOfLongestSubstringAtMost2(String s) {
        int n = s.length();
        int[] arr = new int[256];
        int ei = 0;
        int st = 0;
        int count = 0;
        int max = 0;

        while (ei < n) {
            char ch = s.charAt(ei++);
            arr[ch]++;
            if (arr[ch] == 1)
                count++;

            while (count > 2) {
                char sch = s.charAt(st++);
                if (arr[sch] == 1)
                    count--;
                arr[sch]--;
            }

            max = Math.max(max, ei - st);

        }
        return max;
    }

    // 159. Longest Substring with At Most KTwo Distinct Characters
    public int lengthOfLongestSubstringAtMostK(String s, int k) {
        int n = s.length();
        int[] arr = new int[256];
        int ei = 0;
        int st = 0;
        int count = 0;
        int max = 0;

        while (ei < n) {
            char ch = s.charAt(ei++);
            arr[ch]++;
            if (arr[ch] == 1)
                count++;

            while (count > k) {
                char sch = s.charAt(st++);
                if (arr[sch] == 1)
                    count--;
                arr[sch]--;
            }

            max = Math.max(max, ei - st);

        }
        return max;
    }
}
