import java.util.*;

public class WeeklyContest335 {
    // 2582. Pass the Pillow
    public int passThePillow(int n, int time) {
        if (n > time)
            return time + 1;
        int pos = 1;
        int mul = 1;

        while (time-- > 0) {
            pos = pos + 1 * mul;
            if (pos == n + 1) {
                pos = n - 1;
                mul = -1;
            }
            if (pos == 0) {
                pos = 2;
                mul = 1;
            }
        }

        return pos;

    }

    //2583. Kth Largest Sum in a Binary Tree
    public long kthLargestLevelSum(TreeNode root, int k) {
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        PriorityQueue<Long> pq = new PriorityQueue<>();

        while (q.size() != 0) {
            int s = q.size();
            long sum = 0l;
            while (s-- > 0) {
                TreeNode rn = q.remove();
                sum += rn.val;

                if (rn.left != null)
                    q.add(rn.left);
                if (rn.right != null)
                    q.add(rn.right);

            }

            if (pq.size() < k)
                pq.add(sum);
            else if (pq.size() == k && pq.peek() < sum) {
                pq.remove();
                pq.add(sum);
            }
        }

        return pq.size() < k ? -1l : pq.peek();
    }
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
