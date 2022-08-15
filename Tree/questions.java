package Tree;

import java.util.*;

import LinkedList.DLListNode;
import Tree.Tree.TreeNode;

public class questions {

    public class TreeNode {
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

    // 114. Flatten Binary Tree to Linked List
    public void flatten(TreeNode root) {
        flatten_On2(root);
        flatten_On(root);
    }

    private TreeNode flatten_On2(TreeNode root) {
        if (root == null)
            return null;

        TreeNode rt = root;
        TreeNode lc = root.left;
        TreeNode rc = root.right;
        TreeNode left = flatten_On2(root.left);
        TreeNode right = flatten_On2(root.right);

        root.left = null;
        if (left == null)
            return root;
        TreeNode tail = lc;
        while (tail.right != null)
            tail = tail.right;

        root.right = lc;
        if (right == null)
            return root;
        tail.right = right;

        return root;
    }

    private TreeNode flatten_On(TreeNode root) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null)
            return root;

        TreeNode lchild = root.left;
        TreeNode rchild = root.right;

        TreeNode ltail = flatten_On(root.left);
        TreeNode rtail = flatten_On(root.right);

        if (ltail == null) {
            return rtail;
        }

        root.left = null;
        if (root.right == null) {
            root.right = lchild;
            return ltail;

        }

        root.right = lchild;
        ltail.right = rchild;

        return rtail;
    }

    // 426. Convert Binary Search Tree to Sorted Doubly Linked List
    public TreeNode ConvertBSTtoDLL(TreeNode root) {
        TreeNode[] arr = new TreeNode[2]; // {head,curr}
        ConvertBSTtoDLL_helper(root, arr);
        return arr[0];
    }

    private void ConvertBSTtoDLL_helper(TreeNode root, TreeNode[] arr) {
        if (root == null)
            return;
        ConvertBSTtoDLL_helper(root.left, arr);

        if (arr[0] == null) {
            arr[0] = arr[1] = root;
        } else {
            arr[1].right = root;
            root.left = arr[1];
            arr[1] = arr[1].right;
        }

        ConvertBSTtoDLL_helper(root.right, arr);
    }

    // GFG : Convert Binary Tree to Circular Doubly Linked List
    public TreeNode ConvertBinaryTreetoCircularDoublyLinkedList(TreeNode root) {
        TreeNode[] arr = new TreeNode[2]; // {head,curr}
        ConvertBinaryTreetoCircularDoublyLinkedList_Helper(root, arr);
        // making it circular
        arr[1].right = arr[0];
        arr[0].left = arr[1];
        return arr[0];
    }

    private void ConvertBinaryTreetoCircularDoublyLinkedList_Helper(TreeNode root, TreeNode[] arr) {
        if (root == null)
            return;
        ConvertBinaryTreetoCircularDoublyLinkedList_Helper(root.left, arr);

        if (arr[0] == null) {
            arr[0] = arr[1] = root;
        } else {
            arr[1].right = root;
            root.left = arr[1];
            arr[1] = arr[1].right;
        }

        ConvertBinaryTreetoCircularDoublyLinkedList_Helper(root.right, arr);
    }

    // Construct BST from its given level order traversal (GFG)
    public class Pair {
        TreeNode node;
        int max;
        int min;

        Pair(TreeNode node, int min, int max) {
            this.node = node;
            this.max = max;
            this.min = min;
        }

    }

    public TreeNode ConnstructFromLevelOrder(int[] lvlOrder) {
        if (lvlOrder.length == 0)
            return null;

        int ptr = 0;
        TreeNode root = null;
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(null, Integer.MIN_VALUE, Integer.MAX_VALUE));
        while (q.size() != 0) {
            if (ptr == lvlOrder.length) {
                break;
            }
            TreeNode curr = new TreeNode(lvlOrder[ptr]);

            Pair rp = q.remove();
            TreeNode node = rp.node;
            int min = rp.min;
            int max = rp.max;

            if (max > curr.val && min < curr.val) {

                if (node == null) {
                    root = curr;
                } else {
                    if (curr.val < node.val)
                        node.left = curr;
                    else if (curr.val > node.val)
                        node.right = curr;
                }
                q.add(new Pair(curr, min, curr.val));
                q.add(new Pair(curr, curr.val, max));
                ptr++;
            }

        }

        return root;
    }

    // NOTE - for binary tree from level order, LEVEL ORDER SHOULD HAVE -1 FOR NULL
    // VALUES

    // Leetcode 968. Binary Tree Cameras
    public int minCameraCover(TreeNode root) {
        if (root == null)
            return 0;
        int[] minCam = new int[1];
        int temp = minCameraCoverHelper(root, minCam);
        // checking if root need a cam or not
        if (temp == -1)
            minCam[0]++;
        return minCam[0];
    }
    /*
     * 0 - i have a cam
     * -1 - i need a cam
     * 1 - my child has a cam / i am covered
     */

    public int minCameraCoverHelper(TreeNode root, int[] minCam) {
        if (root == null)
            return 1;

        int lc = minCameraCoverHelper(root.left, minCam);
        int rc = minCameraCoverHelper(root.right, minCam);

        if (lc == -1 || rc == -1) {
            minCam[0]++;
            return 0;
        } else if (lc == 0 || rc == 0) {
            return 1;
        }
        return -1;
    }

    // Binary tree from level and inorder
    public TreeNode BTfromLvlandInorder(int[] levelorder, int[] inorder) {
        return BTfromLvlandInorder_(levelorder, inorder);
    }

    public TreeNode BTfromLvlandInorder_(int[] lvl, int[] in) {
        if (lvl.length == 1)
            return new TreeNode(lvl[0]);
        if (lvl.length == 0)
            return null;

        TreeNode root = new TreeNode(lvl[0]);
        int ptr = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == root.val)
                ptr = i;
        }

        int[] leftIn = new int[ptr];
        int[] rightIn = new int[in.length - ptr];
        HashSet<Integer> leftMap = new HashSet<>();
        HashSet<Integer> rightMap = new HashSet<>();
        int li = 0, ri = 0;
        for (int i = 0; i < in.length; i++) {
            if (i < ptr) {
                leftIn[li++] = in[i];
                leftMap.add(in[i]);
            } else if (i > ptr) {
                rightIn[ri++] = in[i];
                rightMap.add(in[i]);
            }
        }
        li = 0;
        ri = 0;
        int[] leftLevel = new int[leftMap.size()];
        int[] righLevel = new int[rightMap.size()];
        for (int i = 1; i < lvl.length; i++) {
            if (leftMap.contains(lvl[i])) {
                leftLevel[li++] = lvl[i];
            } else if (rightMap.contains(lvl[i])) {
                righLevel[ri++] = lvl[i];
            }
        }

        root.left = BTfromLvlandInorder_(leftLevel, leftIn);
        root.right = BTfromLvlandInorder_(righLevel, rightIn);

        return root;

    }

    // Pref classes in interview, array is not readable
    // Leetcode 337. House Robber III
    public int HouseRobberIII(TreeNode root) {
        int[] temp = HouseRobberIII_(root); // {house inc,house exc}
        return Math.max(temp[0], temp[1]);
    }

    // {house inc,house exc}
    public int[] HouseRobberIII_(TreeNode root) {
        if (root == null) {
            return new int[] { 0, 0 };
        }

        int[] lans = HouseRobberIII_(root.left);
        int[] rans = HouseRobberIII_(root.right);

        int inc = root.val + lans[1] + rans[1];
        int exc = Math.max(lans[0], lans[1]) + Math.max(rans[0], rans[1]);

        return new int[] { inc, exc };
    }

    // Leetcode 1372. Longest ZigZag Path in a Binary Tree
    public int longestZigZag(TreeNode root) {
        if (root == null)
            return 0;

        int[] temp = longestZigZag_(root);// {left oriented,right oriented,overall max}
        return temp[2];
    }

    // {left oriented,right oriented,overall max}
    public int[] longestZigZag_(TreeNode root) {
        if (root == null)
            return new int[] { -1, -1, 0 };

        int[] leftAns = longestZigZag_(root.left);
        int[] rightAns = longestZigZag_(root.right);

        int leftOrientation = 1 + leftAns[1];
        int rightOrientation = 1 + rightAns[0];

        int overallMax = Math.max(leftAns[2], rightAns[2]);
        overallMax = Math.max(overallMax, Math.max(leftOrientation, rightOrientation));

        return new int[] { leftOrientation, rightOrientation, overallMax };
    }

    // Kth smallest element in BST => TC - O(N) ; SC- O(1) (OR) O(logN)
    public int kthSmallest_ConstantSpace(TreeNode root, int k) {
        // TC - O(N) ; SC - O(1) // using morris traversal

        TreeNode curr = root;
        int val = -1;
        while (curr != null && k > 0) {
            TreeNode next = curr.left;
            if (next == null) {
                // inorder
                val = curr.val;
                k--;
                curr = curr.right;
            } else {
                TreeNode rmost = findRightMostNode(curr, next);

                if (rmost.right == null) {
                    rmost.right = curr;
                    curr = curr.left;
                } else {
                    // inorder
                    rmost.right = null;
                    val = curr.val;
                    k--;
                    curr = curr.right;
                }
            }
        }

        return val;
    }

    public TreeNode findRightMostNode(TreeNode curr, TreeNode next) {
        while (next.right != null && next.right != curr)
            next = next.right;
        return next;
    }

    public int kthSmallest_LogNSpace(TreeNode root, int k) {
        // TC - O(N) ; SC - O(1) // using morris traversal

        TreeNode curr = root;
        int val = -1;
        Stack<TreeNode> st = new Stack<>();
        pushAllLeft(curr, st);

        while (k-- > 0) {
            TreeNode top = st.pop();
            val = top.val;
            pushAllLeft(top, st);
        }

        return val;
    }

    public int medianOfBST(TreeNode curr) {
        /*
         * if n - odd : med => (n+1)/2th element
         * if n - odd : med => (n/2th + (n/2+1)th)/2
         */

        int size = sizeOfBST(curr);
        int a = -1; // n+1/2th
        int b = -1; // n/2th
        int c = -1; // n+1/2th
        int k = 1;

        while (curr != null) {
            TreeNode next = curr.left;
            if (next == null) {
                // inorder
                if (k == (size + 1) / 2)
                    a = curr.val;
                if (k == size / 2)
                    b = curr.val;
                if (k == size / 2 + 1)
                    c = curr.val;
                curr = curr.right;
                k++;
            } else {
                TreeNode rmost = findRightMostNode(curr, next);

                if (rmost.right == null) {
                    rmost.right = curr;
                    curr = curr.left;
                } else {
                    // inorder
                    rmost.right = null;
                    if (k == (size + 1) / 2)
                        a = curr.val;
                    if (k == size / 2)
                        b = curr.val;
                    if (k == size / 2 + 1)
                        c = curr.val;
                    curr = curr.right;
                    k++;
                }
            }
        }

        if (size % 2 == 1)
            return a;
        return (b + c) / 2;

    }

    private int sizeOfBST(TreeNode curr) {
        int s = 0;
        while (curr != null) {
            TreeNode next = curr.left;
            if (next == null) {
                // inorder
                s++;
                curr = curr.right;
            } else {
                TreeNode rmost = findRightMostNode(curr, next);

                if (rmost.right == null) {
                    rmost.right = curr;
                    curr = curr.left;
                } else {
                    // inorder
                    rmost.right = null;
                    s++;
                    curr = curr.right;
                }
            }
        }
        return s;
    }

    // Leetcode 653. Two Sum IV - Input is a BST
    // TC - O(N) : SC - O(logN)
    public boolean TwoSumBST(TreeNode root, int tar) {
        Stack<TreeNode> lst = new Stack<>();
        Stack<TreeNode> rst = new Stack<>();
        pushAllLeft(root, lst);
        pushAllRight(root, rst);

        while (lst.size() != 0 && rst.size() != 0 && lst.peek().val < rst.peek().val) {
            TreeNode l = lst.peek();
            TreeNode r = rst.peek();

            if (l.val + r.val == tar)
                return true;
            else if (l.val + r.val > tar) {
                pushAllRight(rst.pop().left, rst);
            } else {
                pushAllLeft(lst.pop().right, lst);
            }
        }
        return false;
    }

    public void pushAllLeft(TreeNode root, Stack<TreeNode> st) {
        while (root != null) {
            st.push(root);
            root = root.left;
        }

    }

    public void pushAllRight(TreeNode root, Stack<TreeNode> st) {
        while (root != null) {
            st.push(root);
            root = root.right;
        }

    }

    // Leetcode 662. Maximum Width of Binary Tree
    public class WidthPair {
        int idx;
        TreeNode node;

        WidthPair(TreeNode node, int idx) {
            this.node = node;
            this.idx = idx;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        int wdth = 1;
        if (root == null)
            return 0;
        ArrayDeque<WidthPair> q = new ArrayDeque<>();
        q.add(new WidthPair(root, 0));

        while (q.size() != 0) {
            int s = q.size();
            int max = q.getLast().idx;
            int min = q.getFirst().idx;
            while (s-- > 0) {
                WidthPair rp = q.remove();

                if (rp.node.left != null) {
                    q.add(new WidthPair(rp.node.left, 2 * rp.idx + 1));
                }

                if (rp.node.right != null) {
                    q.add(new WidthPair(rp.node.right, 2 * rp.idx + 2));
                }

            }

            wdth = Math.max(wdth, max - min + 1);

        }

        return wdth;
    }
}
