package Arrays_Strings;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

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

    // 340: Longest Substring with At Most KTwo Distinct Characters
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

    // 1456. Maximum Number of Vowels in a Substring of Given Length

    public int maxVowels(String s, int k) {
        int n = s.length();
        int si = 0;
        int ei = 0;
        int cnt = 0;
        int max = 0;

        while (ei < n) {
            char ch = s.charAt(ei);
            ei++;
            if (isVowel(ch))
                cnt++;

            while (ei - si > k) {
                char sch = s.charAt(si);
                si++;
                if (isVowel(sch))
                    cnt--;
            }

            if (ei - si == k)
                max = Math.max(max, cnt);

        }

        return max;

    }

    private boolean isVowel(char ch) {
        return (ch == 'a') || (ch == 'e') || (ch == 'i') || (ch == 'o') || (ch == 'u');
    }

    // Smallest distinct window (GFG)
    public int findSubString(String str) {
        // Your code goes here
        int n = str.length();
        int uc = 0;
        int[] arr = new int[256];
        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if (arr[ch] == 0)
                uc++;
            arr[ch]++;
        }
        int[] sarr = new int[256];
        int min = (int) 1e9;
        int si = 0;
        int ei = 0;

        while (ei < n) {
            char ch = str.charAt(ei);
            ei++;
            if (sarr[ch] == 0 && arr[ch] != 0)
                uc--;
            sarr[ch]++;

            while (uc == 0) {
                min = Math.min(min, ei - si);
                char sch = str.charAt(si);
                if (sarr[sch] == 1)
                    uc++;
                sarr[sch]--;
                si++;
            }
        }

        return min == (int) 1e9 ? 0 : min;

    }

    // 1248. Count Number of Nice Subarrays
    public int numberOfSubarrays(int[] nums, int k) {
        return numberOfSubarrays_AtMost(nums, k) - numberOfSubarrays_AtMost(nums, k - 1);
    }

    public int numberOfSubarrays_AtMost(int[] nums, int k) {
        int ans = 0;
        int ei = 0;
        int si = 0;
        int oc = 0;
        int n = nums.length;

        while (ei < n) {
            if (nums[ei++] % 2 == 1)
                oc++;

            while (oc > k) {
                if (nums[si++] % 2 == 1)
                    oc--;
            }

            ans += (ei - si);
        }

        return ans;
    }

    // 992. Subarrays with K Different Integers

    public int subarraysWithKDistinct(int[] nums, int k) {
        return subarraysWithKDistinct_AtMost(nums, k) - subarraysWithKDistinct_AtMost(nums, k - 1);
    }

    private int subarraysWithKDistinct_AtMost(int[] nums, int k) {
        if (k <= 0)
            return 0;
        int n = nums.length;
        int ei = 0;
        int si = 0;
        int cnt = 0;
        int ans = 0;
        int[] freq = new int[(int) 1e5];

        while (ei < n) {
            if (freq[nums[ei++]]++ == 0)
                cnt++;

            while (cnt > k) {
                if (freq[nums[si++]]-- == 1)
                    cnt--;
            }
            ans += ei - si;
        }

        return ans;

    }

    // 395. Longest Substring with At Least K Repeating Characters
    public int longestSubstring_DnC(String s, int k) {
        int n = s.length();
        if (k > n)
            return 0;
        if (k <= 1)
            return n;

        int[] farr = createFreqArray(s);

        int inValidPos = 0;
        while (inValidPos < n && farr[s.charAt(inValidPos) - 'a'] >= k)
            inValidPos++;

        if (inValidPos >= n - 1)
            return inValidPos;

        int left = longestSubstring_DnC(s.substring(0, inValidPos), k);

        while (inValidPos < n && farr[s.charAt(inValidPos) - 'a'] < k)
            inValidPos++;
        int right = (inValidPos >= n) ? 0 : longestSubstring_DnC(s.substring(inValidPos), k);

        return Math.max(left, right);
    }

    private int[] createFreqArray(String s) {
        int n = s.length();
        int[] arr = new int[26];
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i) - 'a']++;
        }
        return arr;
    }

    public int longestSubstring_ITR(String s, int k) {
        int n = s.length();
        if (k > n)
            return 0;
        if (k <= 1)
            return n;

        int[] farr = createFreqArray(s);

        int max = 0;
        int i = 0;
        while (i < n) {
            char ch = s.charAt(i);
            int currLen = 0;
            int[] myarr = new int[26];
            if (farr[s.charAt(i) - 'a'] >= k) {
                int st = i;
                while (i < n && farr[s.charAt(i) - 'a'] >= k) {
                    currLen++;
                    myarr[s.charAt(i) - 'a']++;
                    i++;
                    if (isValid(myarr, k))
                        max = Math.max(max, currLen);
                }
                i = st + 1;
            } else {
                currLen = 0;
                myarr = new int[26];
                i++;
            }
        }

        return max;
    }

    public boolean isValid(int[] arr, int k) {
        for (int i = 0; i < 26; i++) {
            if (arr[i] > 0 && arr[i] < k)
                return false;
        }

        return true;
    }

    // Most Optimized => Using max unique count
    public int longestSubstring(String s, int k) {
        int n = s.length();
        if (k > n)
            return 0;
        if (k <= 1)
            return n;

        int maxUC = maxUniqueCount(s);
        int max = 0;
        // System.out.println(maxUC);
        for (int i = 1; i <= maxUC; i++) {
            int currrMax = longestSubstring_(s, k, i);
            max = Math.max(max, currrMax);
        }

        return max;
    }

    public int longestSubstring_(String s, int k, int maxAllowed) {
        int uc = 0;
        int ei = 0;
        int si = 0;
        int n = s.length();
        int[] farr = new int[26];
        int atLeastK = 0;
        int max = 0;

        while (ei < n) {
            char ch = s.charAt(ei++);
            if (farr[ch - 'a'] == 0)
                uc++;
            farr[ch - 'a']++;

            if (farr[ch - 'a'] == k)
                atLeastK++;

            while (uc > maxAllowed) {
                char sch = s.charAt(si);
                if (farr[sch - 'a'] == k)
                    atLeastK--;
                if (farr[sch - 'a'] == 1) {
                    uc--;
                }
                farr[sch - 'a']--;
                si++;
            }

            if (uc == maxAllowed && atLeastK == uc)
                max = Math.max(max, ei - si);
        }

        return max;

    }

    private int maxUniqueCount(String s) {
        int n = s.length();
        int[] arr = new int[26];
        int uc = 0;
        for (int i = 0; i < n; i++) {
            if (arr[s.charAt(i) - 'a'] == 0)
                uc++;
            arr[s.charAt(i) - 'a']++;
        }

        return uc;
    }

    // 904. Fruit Into Baskets => similar to max subarray with at most two
    public int totalFruit(int[] fruits) {
        int[] farr = new int[(int) 1e5 + 7];
        int cnt = 0;
        int ei = 0;
        int si = 0;
        int n = fruits.length;
        int max = 0;
        while (ei < n) {
            if (farr[fruits[ei++]]++ == 0)
                cnt++;
            while (cnt > 2) {
                if (farr[fruits[si++]]-- == 1)
                    cnt--;
            }

            max = Math.max(max, ei - si);
        }

        return max;
    }

    // 930. Binary Subarrays With Sum => generic method
    // can also be solved using at most method
    public int numSubarraysWithSum(int[] nums, int goal) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int ei = 0;
        int n = nums.length;
        int ans = 0;
        for (int ele : nums) {
            sum += ele;
            ans += map.getOrDefault(sum - goal, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }

    // 485. Max Consecutive Ones
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int ei = 0;
        int si = 0;
        boolean flag = false;
        int max = 0;
        while (ei < n) {
            if (nums[ei++] == 0)
                flag = true;

            while (flag) {
                if (nums[si++] == 0)
                    flag = false;
            }

            max = Math.max(max, ei - si);
        }

        return max;
    }

    // 1004. Max Consecutive Ones III
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int ei = 0;
        int si = 0;
        int cnt = 0;
        int max = 0;
        while (ei < n) {
            if (nums[ei++] == 0)
                cnt++;

            while (cnt > k) {
                if (nums[si++] == 0)
                    cnt--;
            }

            max = Math.max(max, ei - si);
        }

        return max;
    }

    // 974. Subarray Sums Divisible by K
    public int subarraysDivByK(int[] nums, int k) {
        int ans = 0;
        int[] arr = new int[k];
        arr[0] = 1;
        int sof = 0;
        for (int ele : nums) {
            sof += ele;
            int rem = (sof) % k;
            if (rem < 0)
                rem += k;
            ans += arr[rem];
            arr[rem]++;
        }

        return ans;
    }

    // Subarrays with equal 1s and 0s (GFG)
    // Same as sub-array sum, with tar sum==0, treating 1 as 1 and 0 as -1
    public int countSubarrWithEqualZeroAndOne(int nums[], int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int ei = 0;
        int ans = 0;
        for (int ele : nums) {
            sum = sum + ((ele == 1) ? 1 : -1);
            ans += map.getOrDefault(sum - 0, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }

    // General Method using count 0s and 1s
    public int countSubarrWithEqualZeroAndOne_generalMethod(int arr[], int n) {
        // add your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count0s = 0;
        int count1s = 0;
        int ans = 0;

        for (int ele : arr) {
            if (ele == 0)
                count0s++;
            if (ele == 1)
                count1s++;

            int diff = count0s - count1s;
            ans += map.getOrDefault(diff, 0);
            map.put(diff, map.getOrDefault(diff, 0) + 1);

        }

        return ans;
    }

    // Subarrays with equal 1s, 0s, 2s (GFG)
    public long getSubstringWithEqual012(String str) {
        // code here
        int n = str.length();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("0+0", 1);
        long count0s = 0;
        long count1s = 0;
        long count2s = 0;

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int ele = str.charAt(i) - '0';
            if (ele == 0)
                count0s++;
            if (ele == 1)
                count1s++;
            if (ele == 2)
                count2s++;

            long diff0n1 = count0s - count1s;
            long diff0n2 = count0s - count2s;

            String key = diff0n1 + "+" + diff0n2;
            ans += map.getOrDefault(key, 0);
            map.put(key, map.getOrDefault(key, 0) + 1);

        }

        return ans;

    }

    // Longest Sub-Array with Sum K (GFG)
    public int lenOfLongSubarr(int arr[], int n, int tar) {
        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ei = 0;
        int sof = 0;
        while (ei < n) {
            int ele = arr[ei];
            sof += ele;
            int diff = sof - tar;
            map.putIfAbsent(sof, ei);
            max = Math.max(max, ei - map.getOrDefault(diff, ei));
            ;
            ei++;
        }

        return max;
    }

    // 525. Contiguous Array
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ei = 0;
        int sof = 0;
        int max = 0;
        int n = nums.length;
        map.put(0, -1);
        while (ei < n) {
            int ele = (nums[ei] == 1) ? 1 : -1;

            sof += ele;
            map.putIfAbsent(sof, ei);
            max = Math.max(max, map.getOrDefault(sof, ei));
            ei++;
        }

        return max;
    }

    // 424. Longest Repeating Character Replacement
    // min number of letters to change (to make of character same) = length of
    // string - most freq character
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int ei = 0;
        int si = 0;
        int[] farr = new int[256];
        int maxFreq = 0;
        int ans = 0;
        while (ei < n) {
            char ch = s.charAt(ei++);
            farr[ch]++;
            maxFreq = Math.max(maxFreq, farr[ch]);
            int cnt = (ei - si) - maxFreq;

            while (cnt > k) {
                char sch = s.charAt(si++);
                cnt--;
                farr[sch]--;
            }

            ans = Math.max(ans, ei - si);
        }

        return ans;
    }

    // 239. Sliding Window Maximum
    public int[] maxSlidingWindow(int[] nums, int k) {
        return maxSlidingWindow_PQ(nums, k);
    }

    // TC - NlogN : SC- O(N)
    private int[] maxSlidingWindow_PQ(int[] nums, int k) {
        int n = nums.length;

        int[] ans = new int[n - k + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return nums[b] - nums[a];
        });

        int i = 0;
        int aptr = 0;
        while (i < n) {
            if (i < k) {
                pq.add(i);
            } else {
                while (pq.size() != 0 && pq.peek() <= i - k)
                    pq.remove();
                pq.add(i);
                ans[aptr++] = nums[pq.peek()];
            }
            i++;
        }

        return ans;
    }

    // TC- O(NLogK) : SC- O(N)
    public int[] maxSlidingWindow_TreeMap(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>((a, b) -> {
            if (nums[a] == nums[b])
                return b - a;
            return nums[a] - nums[b];
        });
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int j = 0;

        for (int i = 0; i < n; i++) {
            if (map.size() >= k)
                map.remove(i - k);
            map.put(i, nums[i]);
            if (i >= k - 1)
                ans[j++] = nums[map.lastKey()];
        }

        return ans;
    }

    // TC- O(N) : SC- O(N)
    // q always has max element at first pos
    private int[] maxSlidingWindow_Deque(int[] nums, int k) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int j = 0;

        for (int i = 0; i < n; i++) {
            while (q.size() != 0 && q.getFirst() <= i - k)
                q.removeFirst();
            while (q.size() != 0 && nums[q.getLast()] <= nums[i])
                q.removeLast();

            q.add(i);
            if (i >= k - 1)
                ans[j++] = nums[q.getFirst()];
        }

        return ans;
    }

    // 781. Rabbits in Forest
    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int ele : answers) {
            if (!map.containsKey(ele)) {
                map.put(ele, ele + 1);
                ans += ele + 1;
            } else {
                map.put(ele, map.get(ele) - 1);
            }
            if (map.get(ele) == 1)
                map.remove(ele);
        }

        return ans;
    }

    // 363. Max Sum of Rectangle No Larger Than K
    public int maxSumSubmatrixNoLargerThanK(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int max = -(int) 1e9 ;

        for (int fixed = 0; fixed < n; fixed++) {
            int[]arr  = new int[m];
            for (int i = fixed; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[j]+=matrix[i][j];
                }

                int maxIn1D= maxSumSubarrayNoLargerThanK(arr,k);
                max = Math.max(max,maxIn1D);
            }
        }

        return max;
    }

    private int maxSumSubarrayNoLargerThanK(int[] arr, int k) {
        int max = -(int) 1e9 ;
        TreeSet<Integer>set = new TreeSet<>();
        set.add(0);
        int sof= 0;
        for(int ele:arr){
            sof+=ele;
            if(set.ceiling(sof-k)!=null){
                int temp = sof - set.ceiling(sof-k);
                max = Math.max(max,temp);
            }
            set.add(sof);
        }

        return max;
    }

}
