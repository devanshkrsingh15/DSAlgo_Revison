package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LIS {
    // important test case
    // {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15,14}

    // 300. Longest Increasing Subsequence
    public int lengthOfLIS(int[] nums) {
        int max = 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        for (int i = 0; i < n; i++) {
            max = Math.max(max, lengthOfLIS_memo(nums, dp, i));
        }
        return max;
    }

    public int lengthOfLIS_memo(int[] nums, int[] dp, int st) {
        if (dp[st] != -1)
            return dp[st];
        int max = 0;
        for (int i = 0; i < st; i++) {
            if (nums[i] < nums[st]) {
                max = Math.max(max, lengthOfLIS_memo(nums, dp, i));
            }
        }

        return dp[st] = max + 1;
    }

    public int lengthOfLIS_tab(int[] nums) {
        int max = 0;
        int n = nums.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // Q. minimum number of deletions to be performed to make an sorted array
    // -1e7 <=ele<= 1e7

    // A. => make lis with duplicated allowed
    public int minDeletions(int[] nums) {
        int max = 0;
        int n = nums.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] <= nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }
            max = Math.max(max, dp[i]);
        }

        int minDel = n - max;

        return minDel;
    }

    // Maximum Sum Increasing Subsequence (GFG)
    public int maxSumIncreasing(int[] arr) {
        // max sum of inc subsequence
        int n = arr.length;
        int[] dp = new int[n];
        int max = -(int) 1e9;
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }

            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // Max Sum of Longest increasing subsequence
    public int maxSumLIS(int[] arr) {
        int n = arr.length;
        int[] len = new int[n];
        int[] sum = new int[n];
        int maxSum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            len[i] = 1;
            sum[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    len[i] = Math.max(len[i], len[j] + 1);
                    sum[i] = Math.max(sum[i], sum[i] + sum[j]);
                }

            }
            if (max <= len[i]) {
                if (maxSum < sum[i]) {
                    max = len[i];
                    maxSum = sum[i];
                }
            }
        }
        return maxSum;
    }

    // Longest Bitonic Subsequence (GFG)
    public int LongestBitonicSequence(int[] nums) {
        int n = nums.length;
        int max = 0;
        int[] lis = new int[n];
        for (int i = 0; i < n; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        int[] lds = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            lds[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            max = Math.max(max, lis[i] + lds[i] - 1);
        }
        return max;
    }

    // Maximum Sum Bitonic Subsequence
    public static int maxSumBS(int arr[]) {
        int n = arr.length;
        int max = 0;
        int[] lis = new int[n];
        for (int i = 0; i < n; i++) {
            lis[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + arr[i]);
                }
            }
        }

        int[] lds = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            lds[i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    lds[i] = Math.max(lds[i], lds[j] + arr[i]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            max = Math.max(max, lis[i] + lds[i] - arr[i]);
        }
        return max;
    }

    // 673. Number of Longest Increasing Subsequence
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        int max = 0;
        int maxCnt = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            cnt[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] <= dp[j] + 1) {
                        if (dp[i] < dp[j] + 1) {
                            dp[i] = dp[j] + 1;
                            cnt[i] = cnt[j];
                        } else if (dp[i] == dp[j] + 1) {
                            cnt[i] += cnt[j];
                        }
                    }

                }
            }

            if (max <= dp[i]) {
                if (max < dp[i]) {
                    max = dp[i];
                    maxCnt = cnt[i];
                } else if (max == dp[i]) {
                    maxCnt += cnt[i];
                }
            }
        }

        return maxCnt;

    }

    public void getAllLIS(int[] arr) {
        int n = arr.length;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>(); // len and all idx with this len
        int[] dp = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            map.putIfAbsent(dp[i], new ArrayList<>());
            map.get(dp[i]).add(i);
            max = Math.max(max, dp[i]);
        }

        for (int k : map.get(max)) {
            getAllLIS_Helper(arr, max, map, arr[k] + "", k);
        }
    }

    private void getAllLIS_Helper(int[] arr, int len, HashMap<Integer, ArrayList<Integer>> map, String str, int idx) {
        if (len == 1) {
            System.out.println(str + "," + arr[idx]);
            return;
        }

        for (int nidx : map.get(len - 1)) {
            if (idx > nidx && arr[idx] > arr[nidx]) {
                getAllLIS_Helper(arr, len - 1, map, str + "," + arr[nidx], nidx);
            }
        }
    }

    // Building Bridges (gfg)
    // {st,en}
    public int buildingBridges(int[][] arr) {
        // to find max number of bridges possible to build
        int n = arr.length;
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        int max = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i][0] > arr[j][0] && arr[i][1] > arr[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    //354. Russian Doll Envelopes
    //Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
    // sort first on the  basis of st  inc order, if st  are same  then dec order of en
    public int maxEnvelopes(int[][] arr) {
        int n = arr.length;
        Arrays.sort(arr,(a,b)->{
            if(a[0]==b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        ArrayList<Integer>list=  new  ArrayList<>();
        for(int[]a:arr){
            int e = a[1];
            int  ip = findInsertPos(list,e);
            if(ip==list.size()) list.add(e);
            else list.set(ip,e);
        }
        return list.size();
        
    }

    private int findInsertPos(ArrayList<Integer> list, int e) {
        int l = 0;
        int  h = list.size()-1;

        while(l<=h){
            int  m = l + (h-l)/2;

            if(list.get(m)<=e) l = m+1;
            else h = m-1;
        }

        return (l-1>=0 && list.get(l-1)==e) ? l-1 : l;
    }

    

}
