package Tree;

import java.util.*;

import Tree.Tree.TreeNode;

public class questions {

    public ArrayList<TreeNode> NodeToRootPath(TreeNode root, TreeNode tar) {
        if (root == null)
            return new ArrayList<TreeNode>();

        if (root.val == tar.val) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<TreeNode> left_list = NodeToRootPath(root.left, tar);
        if (left_list.size() != 0) {
            left_list.add(root);
            return left_list;
        }
        ArrayList<TreeNode> right_list = NodeToRootPath(root.right, tar);
        if (right_list.size() != 0) {
            right_list.add(root);
            return right_list;
        }

        return new ArrayList<TreeNode>();
    }

    // Leetcode 236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> lft = NodeToRootPath(root, p);
        ArrayList<TreeNode> rgt = NodeToRootPath(root, q);

        int i = lft.size() - 1;
        int j = rgt.size() - 1;
        while (i >= 0 && j >= 0 && lft.get(i) == rgt.get(j)) {
            i--;
            j--;
        }

        return lft.get(i + 1);

    }

    // Leetcode 863
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> rootToNdePath = NodeToRootPath(root, target);

        ArrayList<Integer> ans = new ArrayList<>();

        TreeNode blocker = null;
        for (int i = 0; i < rootToNdePath.size(); i++) {
            fillAns(rootToNdePath.get(i), k, blocker, ans);
            blocker = rootToNdePath.get(i);
            k--;
        }

        return ans;

    }

    public void fillAns(TreeNode root, int k, TreeNode blocker, ArrayList<Integer> ans) {
        if (root == null || k <= 0 || root == blocker)
            return;

        if (k == 0)
            ans.add(root.val);
        fillAns(root.left, k - 1, blocker, ans);
        fillAns(root.right, k - 1, blocker, ans);
    }

    // Burn the binary tree starting from the target node (GFG)
    public ArrayList<ArrayList<TreeNode>> BurningTree(TreeNode root, TreeNode burnNode) {
        ArrayList<ArrayList<TreeNode>> list = new ArrayList<>();

        ArrayList<TreeNode> path = NodeToRootPath(root, burnNode);

        TreeNode block = null;
        for (int i = 0; i < path.size(); i++) {
            int time = i;
            fillBurningNode(path.get(i), time, block, list);
            block = path.get(i);

        }

        // min time to burn whole tree will be list.size()-1,( -1 because one node is
        // already burning)
        return list;
    }

    public void fillBurningNode(TreeNode root, int t, TreeNode block, ArrayList<ArrayList<TreeNode>> list) {
        if (root == null || root == block)
            return;

        if (t == list.size())
            list.add(new ArrayList<>());
        list.get(t).add(root);
        fillBurningNode(root.left, t + 1, block, list);
        fillBurningNode(root.right, t + 1, block, list);
    }

    public List<Integer> distanceK_withoutExtraSpace(TreeNode root, TreeNode target, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        findHelper(root, target, k, ans);
        return ans;
    }

    public int findHelper(TreeNode root, TreeNode tar, int k, ArrayList<Integer> ans) {
        if (root == null)
            return 0;

        if (root.val == tar.val) {
            fillAns(root, k, null, ans);
            return 1;
        }

        int ldis = findHelper(root.left, tar, k, ans);
        if (ldis > 0) {
            fillAns(root, k - ldis, root.left, ans);
            return ldis + 1;
        }

        int rdis = findHelper(root.right, tar, k, ans);
        if (rdis > 0) {
            fillAns(root, k - rdis, root.right, ans);
            return rdis + 1;
        }

        return 0;
    }

    // in terms of edges
    public int NodeToRootDistance(TreeNode root, int tar) {
        if (root == null)
            return -1;

        if (root.val == tar)
            return 0;

        int ldis = NodeToRootDistance(root.left, tar);
        if (ldis >= 0)
            return ldis + 1;

        int rdis = NodeToRootDistance(root.right, tar);
        if (rdis >= 0)
            return rdis + 1;

        return -1;

    }

    // Find the maximum path sum between two leaves of a binary tree(GFG)
    public int MaximumPathSum_LeafNodes(TreeNode root) {
        if (root == null)
            return 0;
        int[] arr = MaximumPathSum_LeafNodes_Helper(root); // {root to leaf dist, max dist between any two leaves}
        if (root.left == null || root.right == null)
            return Math.max(arr[0], arr[1]);
        return arr[1];

    }

    public int[] MaximumPathSum_LeafNodes_Helper(TreeNode root) {
        if (root == null)
            return new int[] { 0, -(int) 1e9 };

        if (root.left == null && root.right == null)
            return new int[] { root.val, -(int) 1e9 };

        int[] larr = MaximumPathSum_LeafNodes_Helper(root.left);
        int[] rarr = MaximumPathSum_LeafNodes_Helper(root.right);

        int[] temp = new int[2];
        if (root.left != null && root.right != null) {
            temp[0] = Math.max(larr[0], rarr[0]) + root.val;
            temp[1] = Math.max(larr[0] + rarr[0] + root.val, Math.max(larr[1], rarr[1]));
        } else if (root.left != null) {
            temp[0] = larr[0] + root.val;
            temp[1] = larr[1];
        } else {
            temp[0] = rarr[0] + root.val;
            temp[1] = rarr[1];
        }

        return temp;

    }

    // Leetcode 124
    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;

        int[] arr = maxPathSumHelper(root); // {max dist from any node to curr node ,any node to any node}
        return arr[1];
    }


    public int[] maxPathSumHelper(TreeNode root) {
        if (root == null)
            return new int[] { 0, -(int) 1e9 };

        int[] larr = maxPathSumHelper(root.left);
        int[] rarr = maxPathSumHelper(root.right);

        int temp[] = new int[2];
        // max dist from any node to curr node
        temp[0] = max(larr[0] + root.val, rarr[0] + root.val, root.val);

        // max dist from any node to any node
        temp[1] = max(larr[1], rarr[1], root.val, larr[0] + root.val, rarr[0] + root.val, larr[0] + rarr[0] + root.val);
        return temp;

    }

    public int max(int... arr) {
        int ele = -(int) 1e9;
        for (int v : arr)
            ele = Math.max(ele, v);
        return ele;
    }

    // Leetcode 112
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return (targetSum - root.val) == 0;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // Leetcode 113
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        pathSumHelper(root, targetSum, ans, new ArrayList<>());
        return ans;
    }

    public void pathSumHelper(TreeNode root, int targetSum, List<List<Integer>> ans, List<Integer> temp) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            if (targetSum - root.val == 0) {
                List<Integer> base = new ArrayList<>(temp);
                base.add(root.val);
                ans.add(base);
            }
            return;
        }

        temp.add(root.val);
        pathSumHelper(root.left, targetSum - root.val, ans, temp);
        pathSumHelper(root.right, targetSum - root.val, ans, temp);
        temp.remove(temp.size() - 1);

    }

    // Leetcode 98
    public boolean isValidBST(TreeNode root) {
        return isValidBST_helper(root, new long[] { Long.MIN_VALUE });
    }

    public boolean isValidBST_helper(TreeNode root, long[] arr) {
        if (root == null)
            return true;

        boolean res = true;
        res = res && isValidBST_helper(root.left, arr);

        res = res && (arr[0] < (long) root.val);
        arr[0] = (long) root.val;

        res = res && isValidBST_helper(root.right, arr);

        return res;
    }

    // Leetcode 437
    public int pathSumIII(TreeNode root, int targetSum) {
        HashMap<Long, Integer> hm = new HashMap<>();
        hm.put((long) 0, 1);
        int[] cnt = new int[1];
        pathSumIII_(root, targetSum, hm, 0, cnt);

        return cnt[0];
    }

    public void pathSumIII_(TreeNode root, long tar, HashMap<Long, Integer> hm, long csum, int[] cnt) {
        if (root == null)
            return;

        csum += (long) root.val;
        long diff = csum - tar;
        cnt[0] += hm.getOrDefault(diff, 0);
        hm.put(csum, hm.getOrDefault(csum, 0) + 1);

        pathSumIII_(root.left, tar, hm, csum, cnt);
        pathSumIII_(root.right, tar, hm, csum, cnt);

        hm.put(csum, hm.get(csum) - 1);
        if (hm.get(csum) == 0)
            hm.remove(csum);
        csum -= (long) root.val;
    }

    // Leetcode 99
    public void recoverTree(TreeNode root) {
        TreeNode arr[] = new TreeNode[3];
        recoverTree_(root, arr); // {a,b,prev}

        TreeNode a = arr[0];
        TreeNode b = arr[1];
        if (a != null && b != null) {
            int t = a.val;
            a.val = b.val;
            b.val = t;
        }
    }

    // always use array,if static variable not allowed
    // variale will give ptrs/value errors => due to stack
    public void recoverTree_(TreeNode root, TreeNode arr[]) {
        if (root == null)
            return;

        recoverTree_(root.left, arr);

        if (arr[2] != null && arr[0] == null && root.val < arr[2].val)
            arr[0] = arr[2];
        if (arr[2] != null && arr[0] != null && root.val < arr[2].val)
            arr[1] = root;

        arr[2] = root;
        recoverTree_(root.right, arr);
    }

    // Leetcode 102
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        while (q.size() != 0) {
            int s = q.size();
            ArrayList<Integer> tmp = new ArrayList<>();
            while (s-- > 0) {
                TreeNode rn = q.remove();
                tmp.add(rn.val);
                if (rn.left != null)
                    q.add(rn.left);
                if (rn.right != null)
                    q.add(rn.right);
            }

            ans.add(tmp);
        }

        return ans;
    }

    // Leetcode 199
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        while (q.size() != 0) {
            int s = q.size();
            ans.add(q.getLast().val);
            while (s-- > 0) {
                TreeNode rn = q.remove();

                if (rn.left != null)
                    q.add(rn.left);
                if (rn.right != null)
                    q.add(rn.right);
            }

        }

        return ans;
    }

    public List<Integer> leftSideView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        while (q.size() != 0) {
            int s = q.size();
            ans.add(q.getFirst().val);
            while (s-- > 0) {
                TreeNode rn = q.remove();

                if (rn.left != null)
                    q.add(rn.left);
                if (rn.right != null)
                    q.add(rn.right);
            }
        }

        return ans;
    }

    // 987
    public class Vpair {
        TreeNode node;
        int vidx;
        int lidx;

        Vpair(TreeNode node, int vidx, int lidx) {
            this.node = node;
            this.vidx = vidx;
            this.lidx = lidx;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();

        PriorityQueue<Vpair> q = new PriorityQueue<>((a, b) -> {
            if (a.lidx != b.lidx)
                return a.lidx - b.lidx;
            else if (a.vidx != b.vidx)
                return a.vidx - b.vidx;
            else {
                return a.node.val - b.node.val;
            }
        });
        q.add(new Vpair(root, 0, 0));
        int min = (int) 1e9;
        int max = -(int) 1e9;

        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                Vpair rv = q.remove();
                min = Math.min(min, rv.vidx);
                max = Math.max(max, rv.vidx);

                hm.putIfAbsent(rv.vidx, new ArrayList<>());
                hm.get(rv.vidx).add(rv.node.val);

                if (rv.node.left != null)
                    q.add(new Vpair(rv.node.left, rv.vidx - 1, rv.lidx + 1));
                if (rv.node.right != null)
                    q.add(new Vpair(rv.node.right, rv.vidx + 1, rv.lidx + 1));
            }
        }

        for (int i = min; i <= max; i++) {
            ans.add(hm.get(i));
        }

        return ans;
    }

    public void findMinMaxValues(TreeNode root, int vidx, int[] arr) {
        if (root == null)
            return;
        arr[0] = Math.min(arr[0], vidx);
        arr[1] = Math.max(arr[1], vidx);

        findMinMaxValues(root.left, vidx - 1, arr);
        findMinMaxValues(root.right, vidx + 1, arr);
    }

    public int[] VerticalSum(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return new int[0];

        Queue<Vpair> q = new ArrayDeque<>();

        int[] arr = new int[] { (int) 1e9, -(int) 1e9 }; // {min,max};
        findMinMaxValues(root, 0, arr);

        int[] fans = new int[arr[1] - arr[0] + 1];
        // Arrays.fill(fans,-(int)1e9);

        q.add(new Vpair(root, -arr[0], 0));
        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                Vpair rv = q.remove();

                fans[rv.vidx] += rv.node.val;

                if (rv.node.left != null)
                    q.add(new Vpair(rv.node.left, rv.vidx - 1, rv.lidx + 1));
                if (rv.node.right != null)
                    q.add(new Vpair(rv.node.right, rv.vidx + 1, rv.lidx + 1));
            }
        }

        return fans;
    }

    public int[] TopView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return new int[0];

        Queue<Vpair> q = new ArrayDeque<>();

        int[] arr = new int[] { (int) 1e9, -(int) 1e9 }; // {min,max};
        findMinMaxValues(root, 0, arr);

        int[] fans = new int[arr[1] - arr[0] + 1];
        Arrays.fill(fans, -(int) 1e9);

        q.add(new Vpair(root, -arr[0], 0));
        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                Vpair rv = q.remove();

                if (fans[rv.vidx] == -(int) 1e9)
                    fans[rv.vidx] = rv.node.val;

                if (rv.node.left != null)
                    q.add(new Vpair(rv.node.left, rv.vidx - 1, rv.lidx + 1));
                if (rv.node.right != null)
                    q.add(new Vpair(rv.node.right, rv.vidx + 1, rv.lidx + 1));
            }
        }

        return fans;
    }

    public int[] BottomView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return new int[0];

        Queue<Vpair> q = new ArrayDeque<>();

        int[] arr = new int[] { (int) 1e9, -(int) 1e9 }; // {min,max};
        findMinMaxValues(root, 0, arr);

        int[] fans = new int[arr[1] - arr[0] + 1];
        Arrays.fill(fans, -(int) 1e9);

        q.add(new Vpair(root, -arr[0], 0));
        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                Vpair rv = q.remove();

                fans[rv.vidx] = rv.node.val;

                if (rv.node.left != null)
                    q.add(new Vpair(rv.node.left, rv.vidx - 1, rv.lidx + 1));
                if (rv.node.right != null)
                    q.add(new Vpair(rv.node.right, rv.vidx + 1, rv.lidx + 1));
            }
        }

        return fans;
    }

    // Boundary Traversal of binary tree (GFG)
    public List<TreeNode> BoundaryTraversal(TreeNode root) {
        ArrayList<TreeNode> ans = new ArrayList<>();
        if (root == null)
            return ans;

        ans.add(root);
        if (root.left == null && root.right == null)
            return ans;

        fillLeftBoundary(root.left, ans);
        fillLeavesNode(root, ans);
        fillRightBoundary(root.right, ans);

        return ans;
    }

    public void fillLeftBoundary(TreeNode root, ArrayList<TreeNode> ans) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            return;
        }

        ans.add(root);
        if (root.left != null)
            fillLeftBoundary(root.left, ans);
        else if (root.right != null)
            fillLeftBoundary(root.right, ans);

    }

    public void fillLeavesNode(TreeNode root, ArrayList<TreeNode> ans) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            ans.add(root);
            return;
        }

        fillLeavesNode(root.left, ans);
        fillLeavesNode(root.right, ans);

    }

    public void fillRightBoundary(TreeNode root, ArrayList<TreeNode> ans) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            return;
        }

        ans.add(root);
        if (root.right != null)
            fillRightBoundary(root.right, ans);
        else if (root.left != null)
            fillRightBoundary(root.left, ans);

        ans.add(root);
    }

}

