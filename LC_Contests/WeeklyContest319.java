import java.util.*;

import Tree.*;
import Tree.Tree.TreeNode;

public class WeeklyContest319 {

    // 2469. Convert the Temperature
    public double[] convertTemperature(double c) {
        /*
         * Kelvin = Celsius + 273.15
         * Fahrenheit = Celsius * 1.80 + 32.00
         */

        double k = c + 273.15;
        double f = c * 1.80 + 32.00;

        return new double[] { k, f };

    }

    // 2470. Number of Subarrays With LCM Equal to K
    public int subarrayLCM(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            double l = (double) nums[i];
            if (l == (double) k)
                ans++;
            for (int j = i + 1; j < n; j++) {
                double a = (double) nums[j];
                double b = (double) l;
                double g = (double) gcd((int) a, (int) b);
                l = (a * b) / g;
                if (l == (double) k)
                    ans++;
            }
        }

        return ans;
    }

    public int gcd(int a, int b) {
        if (b == 0)
            return a;

        return gcd(b, a % b);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 2471. Minimum Number of Operations to Sort a Binary Tree by Level
    public int minimumOperations(TreeNode root) {
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int ans = 0;
        while (q.size() != 0) {
            int s = q.size();
            ArrayList<Integer> list = new ArrayList<>();

            while (s-- > 0) {
                TreeNode node = q.remove();
                list.add(node.val);

                if (node.left != null) {
                    q.add(node.left);
                }

                if (node.right != null) {
                    q.add(node.right);
                }

            }

            if (list.size() > 1) {
                // min number of swaps => cyclen -1
                ans += countSwaps(list);
            }

        }

        return ans;

    }

    public int countSwaps(ArrayList<Integer> list) {
        ArrayList<int[]> tmp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            tmp.add(new int[] { i, list.get(i) });
        }

        boolean[] vis = new boolean[list.size()];
        Collections.sort(tmp, (a, b) -> {
            return a[1] - b[1];
        });

        int ans = 0;

        for (int i = 0; i < tmp.size(); i++) {
            int[] arr = tmp.get(i);
            int unSortedPos = arr[0];
            int SortedPos = i;

            if (vis[i] || unSortedPos == SortedPos)
                continue;
            int cycleLen = 0;
            int st = i;
            while (vis[st] == false) {
                vis[st] = true;
                st = tmp.get(st)[0];
                cycleLen++;

            }

            ans += cycleLen - 1;
        }

        return ans;

    }

}
