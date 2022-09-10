package HashMap_Heap;

import java.util.*;

public class questions {
    // 215. Kth Largest Element in an Array
    // TC - O(NlogK) => as max max size of pq will be k
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return a - b;
        });

        for (int ele : nums) {
            if (pq.size() < k)
                pq.add(ele);
            else {
                if (pq.peek() < ele) {
                    pq.remove();
                    pq.add(ele);
                }
            }
        }

        return pq.peek();
    }

    // TC - O(N) + O(K)
    public int findKthLargest_opti(int[] nums, int k) {
        // create our own heap
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            downHeapify(nums, i, n - 1);
        }

        // put max k-1 ele at the last => then kt largest will be at top of pq
        int li = n - 1;
        while (k-- > 1) {
            swap(nums, 0, li--);
            downHeapify(nums, 0, li);
        }

        return nums[0];

    }

    private void downHeapify(int[] nums, int pi, int li) {
        int max = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;

        if (lci <= li && nums[max] < nums[lci])
            max = lci;

        if (rci <= li && nums[max] < nums[rci])
            max = rci;

        if (max != pi) {
            swap(nums, max, pi);
            downHeapify(nums, max, li);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int ele : nums) {
            if (pq.size() < k)
                pq.add(ele);
            else {
                if (pq.peek() > ele) {
                    pq.remove();
                    pq.add(ele);
                }
            }
        }

        return pq.peek();
    }

    // 349. Intersection of Two Arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums1)
            set.add(ele);

        ArrayList<Integer> list = new ArrayList<>();
        for (int ele : nums2) {
            if (set.contains(ele)) {
                list.add(ele);
                set.remove(ele);
            }
        }

        int[] ans = new int[list.size()];
        int i = 0;
        for (int ele : list)
            ans[i++] = ele;
        return ans;
    }

    public HashMap<Integer, Integer> getFreqMap(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        return map;
    }

    // 350. Intersection of Two Arrays II
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = getFreqMap(nums1);
        HashMap<Integer, Integer> map2 = getFreqMap(nums2);

        ArrayList<Integer> list = new ArrayList<>();
        for (int e : map1.keySet()) {
            if (map2.containsKey(e)) {
                int f = Math.min(map1.get(e), map2.get(e));

                while (f-- > 0) {
                    list.add(e);
                }

                map2.remove(e);
            }
        }

        int[] ans = new int[list.size()];
        int i = 0;
        for (int ele : list)
            ans[i++] = ele;
        return ans;
    }

    // 128. Longest Consecutive Sequence
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums)
            set.add(ele);

        int max = 0;

        for (int ele : nums) {
            if (set.contains(ele)) {
                int lft = ele;
                int rgt = ele;
                while (set.contains(lft - 1)) {
                    set.remove(lft - 1);
                    lft--;
                }
                while (set.contains(rgt + 1)) {
                    set.remove(rgt + 1);
                    rgt++;
                }

                max = Math.max(max, rgt - lft + 1);

            }
        }

        return max;
    }

    // 347. Top K Frequent Elements
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return map.get(a) - map.get(b);
        });

        for (int e : map.keySet()) {
            pq.add(e);
            if (pq.size() > k)
                pq.remove();
        }

        int[] ans = new int[k];
        int i = 0;
        while (pq.size() != 0)
            ans[i++] = pq.remove();
        return ans;
    }

    // 973. K Closest Points to Origin
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int d1 = (int) (Math.pow(a[0], 2) + Math.pow(a[1], 2));
            int d2 = (int) (Math.pow(b[0], 2) + Math.pow(b[1], 2));
            return d2 - d1;
        });

        for (int[] p : points) {
            pq.add(p);
            if (pq.size() > k)
                pq.remove();
        }

        int[][] ans = new int[k][2];
        int i = 0;
        while (pq.size() != 0)
            ans[i++] = pq.remove();
        return ans;
    }

    // 378. Kth Smallest Element in a Sorted Matrix
    public int kthSmallest_SortedMatrix(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return matrix[a[0]][a[1]] - matrix[b[0]][b[1]];
        });

        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            pq.add(new int[] { i, 0 });
        }

        int ans = 0;

        while (k-- > 0) {
            int[] rp = pq.remove();
            int r = rp[0];
            int c = rp[1];
            ans = matrix[r][c];

            c++;
            if (c < m)
                pq.add(new int[] { r, c });
        }

        return ans;
    }
}