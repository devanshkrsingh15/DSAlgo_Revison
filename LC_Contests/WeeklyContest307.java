
import java.util.*;

import Tree.Tree.TreeNode;

public class WeeklyContest307 {

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int ans = 0;

        int myEn = initialEnergy;
        int myEx = initialExperience;

        int n = energy.length;

        for (int i = 0; i < n; i++) {
            if (myEn <= energy[i]) {
                ans += energy[i] - myEn + 1;
                myEn = energy[i] + 1;
            }
            if (myEx <= experience[i]) {
                ans += experience[i] - myEx + 1;
                myEx = experience[i] + 1;
            }
            myEn -= energy[i];
            myEx += experience[i];

        }

        return ans;
    }

    public String largestPalindromic(String num) {
        int[] freq = new int[10];
        int n = num.length();
        for (int i = 0; i < n; i++) {
            freq[num.charAt(i) - '0']++;
        }

        int max = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            if (sb.length() == 0 && i == 0)
                continue;

            if (freq[i] % 2 != 0)
                max = Math.max(max, i);

            int f = freq[i] / 2;
            while (f-- > 0)
                sb.append(i + "");

        }

        String right = new StringBuilder(sb.toString()).reverse().toString();
        if (max != -1) {
            sb.append(max + "");
        }

        sb.append(right);
        if (sb.length() == 0 && freq[0] > 0)
            sb.append("0");
        if (sb.charAt(0) == '0')
            return "0";

        return sb.toString();
    }

    public int amountOfTime(TreeNode root, int start) {
        ArrayList<TreeNode> path = nodeToRootPath(root, start);

        ArrayList<ArrayList<TreeNode>> list = new ArrayList<>();
        TreeNode block = null;
        for (int i = 0; i < path.size(); i++) {
            update(path.get(i), block, i, list);
            block = path.get(i);
        }

        return list.size() - 1;
    }

    private void update(TreeNode node, TreeNode block, int i, ArrayList<ArrayList<TreeNode>> list) {
        if (node == null || node == block)
            return;

        if (i == list.size())
            list.add(new ArrayList<>());
        list.get(i).add(node);
        update(node.left, block, i + 1, list);
        update(node.right, block, i + 1, list);
    }

    public ArrayList<TreeNode> nodeToRootPath(TreeNode root, int val) {
        if (root == null)
            return new ArrayList<>();
        if (root.val == val) {
            ArrayList<TreeNode> tmp = new ArrayList<>();
            tmp.add(root);
            return tmp;
        }

        ArrayList<TreeNode> lres = nodeToRootPath(root.left, val);
        if (lres.size() != 0) {
            lres.add(root);
            return lres;
        }

        ArrayList<TreeNode> rres = nodeToRootPath(root.right, val);
        if (rres.size() != 0) {
            rres.add(root);
            return rres;
        }

        return new ArrayList<>();
    }

    public long kSum(int[] nums, int k) {
        long maxSum = 0;
        ArrayList<Long> list = new ArrayList<>();
        for (int ele : nums) {
            maxSum = maxSum + Math.max(0, ele);
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
        }

        Arrays.sort(nums);
        list.add(maxSum);
        // sum,last chosen index;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            return (int) b[0] - (int) a[0];
        });

        pq.add(new long[] { maxSum - (long) nums[0], 0 });

        while (pq.size() != 0 && pq.size() < k) {
            long[] arr = pq.remove();
            long sum = arr[0];
            int idx = (int) arr[1];

            list.add(sum);

            if (idx + 1 < nums.length) {
                long nidx = (long) idx + 1;
                long c1 = sum - nums[idx + 1];
                pq.add(new long[] { c1, nidx });

                long c2 = sum + nums[idx] - nums[idx + 1];
                pq.add(new long[] { c2, nidx });
            }
        }

        return list.get(k - 1);
    }
}
