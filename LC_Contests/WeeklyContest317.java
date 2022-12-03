import java.util.*;

public class WeeklyContest317 {
    // 2455. Average Value of Even Numbers That Are Divisible by Three
    public int averageValue(int[] nums) {
        double tot = 0;
        double cnt = 0;

        for (int ele : nums) {
            if (ele % 6 == 0) {
                tot += ele;
                cnt++;
            }
        }

        return (int) Math.floor(tot / cnt);
    }

    // 2456. Most Popular Video Creator
    class Pair {
        long v;
        String i;

        Pair(String i, long v) {
            this.i = i;
            this.v = v;
        }
    }

    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        List<List<String>> ans = new ArrayList<>();
        int n = creators.length;
        HashMap<String, ArrayList<Pair>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.putIfAbsent(creators[i], new ArrayList<>());
            map.get(creators[i]).add(new Pair(ids[i], views[i]));
        }

        long omax = 0;
        for (String c : map.keySet()) {
            long mySum = 0;
            ArrayList<Pair> list = map.get(c);
            Collections.sort(list, (a, b) -> {
                if (a.v == b.v)
                    return a.i.compareTo(b.i);
                return (int) b.v - (int) a.v;
            });
            String highest = list.get(0).i;
            for (Pair rp : list) {
                mySum += rp.v;
            }

            omax = Math.max(omax, mySum);
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(c);
            tmp.add(highest);
            tmp.add(mySum + "");
            ans.add(tmp);

        }
        List<List<String>> fans = new ArrayList<>();
        for (List<String> t : ans) {
            long sum = Long.parseLong(t.get(2));
            if (sum == omax) {
                t.remove(t.size() - 1);
                fans.add(t);
            }
        }

        return fans;

    }

    // 2457. Minimum Addition to Make Integer Beautiful

    public long countSumOfDig(long n) {
        long sum = 0;
        while (n != 0) {
            sum = n % 10;
            n = n / 10;
        }
        return sum;
    }

    public long makeIntegerBeautiful(long n, int target) {
        // if a tot of all digits is greater than tar , we will not get a valid ans
        // until we reach a nearest multiple of 10, 100,1000....
        long dsum = countSumOfDig(n);
        if (dsum <= target)
            return 0l;

        int p = 1;
        long beautyN = n;
        long copyN = n;
        while (dsum > target) {
            n /= 10;
            beautyN = (long) (n + 1) * (long) Math.pow(10, p);
            p++;
            dsum = countSumOfDig(beautyN);
        }

        return beautyN - copyN;

    }

    // 2458. Height of Binary Tree After Subtree Removal Queries
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {

        }

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    HashMap<Integer, int[]> levelMap;
    int[] levels;
    int[] hts;

    // 2458. Height of Binary Tree After Subtree Removal Queries
    public int[] treeQueries(TreeNode root, int[] queries) {
        levels = new int[(int) 1e5 + 10];
        hts = new int[(int) 1e5 + 10];
        levelMap = new HashMap<>();

        int ht = dfs(root, 0);

        // for(int level : levelMap.keySet()){
        // System.out.print(level + " ");
        // System.out.println(levelMap.get(level)[0] + " " +levelMap.get(level)[1]);
        // }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            if (q == root.val)
                ans[i] = 0;
            else {
                int l = levels[q];
                int[] arr = levelMap.get(l);
                // new ht after removing -> parent level + (max ht at that level after removing
                // current node)

                if (q != arr[0] && q != arr[1]) {
                    ans[i] = ht;
                } else {
                    int cand = (q == arr[0]) ? arr[1] : arr[0];
                    if (cand == -1)
                        ans[i] = l - 1;
                    else {
                        ans[i] = l + hts[cand];
                    }
                }

            }
        }

        return ans;

    }

    public int dfs(TreeNode root, int level) {
        if (root == null)
            return -1;

        int lh = dfs(root.left, level + 1);
        int rh = dfs(root.right, level + 1);

        levelMap.putIfAbsent(level, new int[] { -1, -1 }); // max,smax

        int myHt = Math.max(lh, rh) + 1;
        levels[root.val] = level;
        hts[root.val] = myHt;

        if (levelMap.get(level)[0] == -1 || myHt > hts[levelMap.get(level)[0]]) {
            levelMap.get(level)[1] = levelMap.get(level)[0];
            levelMap.get(level)[0] = root.val;
        } else if (levelMap.get(level)[1] == -1 || myHt > hts[levelMap.get(level)[1]]) {
            levelMap.get(level)[1] = root.val;
        }

        return myHt;
    }
}
