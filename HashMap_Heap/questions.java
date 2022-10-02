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

    // Trapping Rainwater II
    public int trapRainWater(int[][] heightMap) {
        int n = heightMap.length;
        int m = heightMap[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return heightMap[a / m][a % m] - heightMap[b / m][b % m];
        });

        int ans = 0;
        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        boolean[][] vis = new boolean[n][m];
        int maxSupport = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    pq.add(i * m + j);
                    vis[i][j] = true;
                }
            }
        }

        while (pq.size() != 0) {
            int idx = pq.remove();
            int r = idx / m;
            int c = idx % m;

            maxSupport = Math.max(maxSupport, heightMap[r][c]);
            ans += Math.max(0, maxSupport - heightMap[r][c]);

            for (int k = 0; k < direcs.length; k++) {
                int x = r + direcs[k][0];
                int y = c + direcs[k][1];

                if (x >= 0 && y >= 0 && x < n && y < m && !vis[x][y]) {
                    pq.add(x * m + y);
                    vis[x][y] = true;
                }
            }

        }

        return ans;

    }

    // 778. Swim in Rising Water
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return grid[a / m][a % m] - grid[b / m][b % m];
        });

        int ans = 0;
        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        boolean[][] vis = new boolean[n][m];
        int max = 0;

        pq.add(0);
        vis[0][0] = true;

        while (pq.size() != 0) {
            int idx = pq.remove();
            int r = idx / m;
            int c = idx % m;

            ans += Math.max(0, grid[r][c] - max);
            max = Math.max(max, grid[r][c]);

            if (r == n - 1 && c == n - 1)
                return ans;

            for (int k = 0; k < direcs.length; k++) {
                int x = r + direcs[k][0];
                int y = c + direcs[k][1];

                if (x >= 0 && y >= 0 && x < n && y < m && !vis[x][y]) {
                    pq.add(x * m + y);
                    vis[x][y] = true;
                }
            }
        }

        return ans;
    }

    // 502. IPO
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        int n = profits.length;
        PriorityQueue<Integer> capitalPQ = new PriorityQueue<>((a, b) -> {
            return capital[a] - capital[b];
        });
        for (int i = 0; i < n; i++)
            capitalPQ.add(i);

        PriorityQueue<Integer> profitPQ = new PriorityQueue<>((a, b) -> {
            return profits[b] - profits[a];
        });

        int cap = w;
        while (k-- > 0) {
            while (capitalPQ.size() != 0 && cap >= capital[capitalPQ.peek()]) {
                int idx = capitalPQ.remove();
                profitPQ.add(idx);
            }

            if (profitPQ.size() == 0)
                return cap;

            int pidx = profitPQ.remove();
            cap += profits[pidx];

        }

        return cap;

    }

    // 632. Smallest Range Covering Elements from K Lists
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return nums.get(a[0]).get(a[1]) - nums.get(b[0]).get(b[1]);
        });

        int max = -(int) 1e9;
        for (int i = 0; i < nums.size(); i++) {
            pq.add(new int[] { i, 0 });
            max = Math.max(max, nums.get(i).get(0));
        }

        int sp = -1;
        int en = -1;
        int range = (int) 1e9;

        while (pq.size() == nums.size()) {
            int[] rarr = pq.remove();
            int r = rarr[0];
            int c = rarr[1];

            int myrange = max - nums.get(r).get(c);

            if (range > myrange) {
                range = myrange;
                sp = nums.get(r).get(c);
                en = max;
            }

            c++;
            if (c < nums.get(r).size()) {
                pq.add(new int[] { r, c });
                max = Math.max(max, nums.get(r).get(c));
            }
        }

        return new int[] { sp, en };
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
   // {x,h}
        ArrayList<int[]> list = new ArrayList<>();
        for (int[] b : buildings) {
            list.add(new int[] { b[0], b[2] });
            list.add(new int[] { b[1], -b[2] });
        }
        Collections.sort(list, (a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            return a[0] - b[0];
        });

        //for hts
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        pq.add(0);
        int max = 0;
        for (int[] b : list) {
            int x = b[0];
            int y = b[1];

            if(y>0) pq.add(y);
            else pq.remove(Math.abs(y));

            if(max!=pq.peek()){
                List<Integer>tmp = new ArrayList<>();
                tmp.add(x);
                tmp.add(pq.peek());
                max = pq.peek();
                ans.add(tmp);
            }


        }

        return ans;

    }

}