import java.util.*;

public class WeeklyContest362 {

    // 2848. Points That Intersect With Cars
    public int numberOfPoints(List<List<Integer>> nums) {
        HashSet<Integer> set = new HashSet<>();
        for (List<Integer> l : nums) {
            for (int i = l.get(0); i <= l.get(1); i++) {
                set.add(i);
            }
        }

        return set.size();
    }

    // 2849. Determine if a Cell Is Reachable at a Given Time
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy)
            return t != 1;
        long xdis = (long) Math.abs((long) sx - (long) fx);
        long ydis = (long) Math.abs((long) sy - (long) fy);
        long totDis = Math.min(xdis, ydis) + Math.abs(xdis - ydis);

        return totDis <= t;

    }

    // 1658. Minimum Operations to Reduce X to Zero

    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int max = (int) 1e9;
        long[] lsum = new long[n];
        long[] rsum = new long[n];

        for (int i = 0; i < nums.length; i++) {
            int lidx = i;
            int ridx = n - 1 - i;

            lsum[lidx] = lidx - 1 >= 0 ? lsum[lidx - 1] + (long) nums[lidx] : (long) nums[lidx];
            rsum[ridx] = ridx + 1 < n ? rsum[ridx + 1] + (long) nums[ridx] : (long) nums[ridx];

            if (lsum[lidx] == (long) x) {
                max = Math.min(max, lidx + 1);
            }
            if (rsum[ridx] == (long) x) {
                max = Math.min(max, n - ridx);
            }
        }

        if (lsum[n - 1] < (long) x)
            return -1;

        Arrays.sort(rsum);

        for (int i = 0; i < n; i++) {
            if (lsum[i] < x) {
                int a = i + 1;
                int b = find(rsum, (long) x - lsum[i], i + 1);
                if (b != (int) 1e9)
                    max = Math.min(max, a + b);
            }

            if (rsum[i] < x) {
                int a = i + 1;
                int b = find(lsum, (long) x - rsum[i], i + 1);
                if (b != (int) 1e9)
                    max = Math.min(max, a + b);
            }

        }

        return max == (int) 1e9 ? -1 : max;
    }

    public int find(long[] arr, long tar, int i) {
        int n = arr.length;
        int lo = 0;
        int hi = n - 1 - i;

        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == tar) {
                ans = mid;
                hi = mid - 1;
            } else if (arr[mid] > tar) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans == -1 ? (int) 1e9 : ans + 1;
    }

    // LC 2850 Minimum Moves to Spread Stones Over Grid
    public int minimumMoves(int[][] grid) {
        int empty = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0)
                    empty++;
            }
        }
        if (empty == 0)
            return 0;

        int min = (int) 1e9;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0) {
                    for (int ni = 0; ni < 3; ni++) {
                        for (int nj = 0; nj < 3; nj++) {
                            if (grid[ni][nj] > 1) {
                                int moves = Math.abs(ni - i) + Math.abs(nj - j);

                                grid[ni][nj]--;
                                grid[i][j]++;

                                min = Math.min(min, minimumMoves(grid) + moves);

                                grid[ni][nj]++;
                                grid[i][j]--;
                            }
                        }
                    }
                }
            }
        }

        return min;
    }

    // 4. Median of Two Sorted Arrays

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // binary search on smaller array
        // basically how many to choose from that, remaining from larger one

        int n = nums1.length;
        int m = nums2.length;
        if (n > m)
            return findMedianSortedArrays(nums2, nums1);
        // considering nums1 to be smaller than nums2

        int lo = 0; // no elements from nums1
        int hi = n; // all elements from nums1

        int total = (n + m + 1) / 2; // adding one so that odd can be handled, left part should have more elements

        while (lo <= hi) {

            int mid = lo + (hi - lo) / 2; // number of elements from array1
            int mid2 = total - mid; // number of elements from array2

            int l1 = -(int) 1e9;
            int r1 = (int) 1e9;
            int l2 = -(int) 1e9;
            int r2 = (int) 1e9;

            if (mid - 1 >= 0)
                l1 = nums1[mid - 1];
            if (mid2 - 1 >= 0)
                l2 = nums2[mid2 - 1];

            if (mid < n)
                r1 = nums1[mid];
            if (mid2 < m)
                r2 = nums2[mid2];

            if (l1 <= r2 && l2 <= r1) {
                if ((n + m) % 2 == 0)
                    return (double) ((double) Math.max(l1, l2) + (double) Math.min(r1, r2)) / 2.0;
                else
                    return (double) ((double) Math.max(l1, l2));
            } else if (l1 > r2) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }

        }

        return 0.0;

    }

}
