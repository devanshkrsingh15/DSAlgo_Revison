package Tree;

import java.util.*;

import LinkedList.ListNode;
import Tree.GenericTreeNode;

public class GenericTree {

    // generally in GT we don't need base , as for loop automatically handles that
    // but before calling check in node is not null

    public int height(GenericTreeNode root) {
        if (root == null)
            return -1;
        int ht = 0;
        for (GenericTreeNode c : root.child) {
            ht = Math.max(ht, height(c));
        }

        return ht + 1;
    }

    public int size(GenericTreeNode root) {
        if (root == null)
            return 0;
        int size = 0;
        for (GenericTreeNode c : root.child) {
            size += size(c);
        }

        return size + 1;
    }

    public int findMax(GenericTreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;
        int max = root.val;
        for (GenericTreeNode c : root.child) {
            max = Math.max(max, findMax(c));
        }

        return max;
    }

    public int findMin(GenericTreeNode root) {
        if (root == null)
            return Integer.MAX_VALUE;
        int min = root.val;
        for (GenericTreeNode c : root.child) {
            min = Math.min(min, findMin(c));
        }

        return min;
    }

    public boolean find(GenericTreeNode root, int tar) {
        if (root == null)
            return false;

        boolean res = (root.val == tar);
        for (GenericTreeNode c : root.child) {
            res = res || find(c, tar);
        }

        return res;
    }

    public ArrayList<GenericTreeNode> rootToNodePath(GenericTreeNode root, int tar) {
        ArrayList<GenericTreeNode> res = new ArrayList<>();
        rootToNodePath_(root, tar, res);
        return res;
    }

    public boolean rootToNodePath_(GenericTreeNode root, int tar, ArrayList<GenericTreeNode> ans) {
        if (root == null)
            return false;

        if (root.val == tar) {
            ans.add(root);
            return true;
        }

        boolean res = false;
        for (GenericTreeNode child : root.child) {
            res = res || rootToNodePath_(child, tar, ans);
        }

        if (res)
            ans.add(root);
        return res;
    }

    public GenericTreeNode LowestCommonAncestor(GenericTreeNode root, GenericTreeNode p, GenericTreeNode q) {
        ArrayList<GenericTreeNode> plist = rootToNodePath(root, p.val);
        ArrayList<GenericTreeNode> qlist = rootToNodePath(root, q.val);

        int i = plist.size() - 1;
        int j = qlist.size() - 1;

        GenericTreeNode lca = null;

        while (i >= 0 && j >= 0 && plist.get(i) == qlist.get(j)) {
            lca = plist.get(i);
            i--;
            j--;

        }
        return lca;
    }

    public ArrayList<ArrayList<GenericTreeNode>> burningTreeNode(GenericTreeNode root, GenericTreeNode burnNode) {
        ArrayList<GenericTreeNode> path = rootToNodePath(root, burnNode.val);
        ArrayList<ArrayList<GenericTreeNode>> res = new ArrayList<>();

        GenericTreeNode block = null;
        for (int i = 0; i < path.size(); i++) {
            GenericTreeNode node = path.get(i);
            kDown(node, block, i, res);
            block = node;
        }

        return res;
    }

    public void kDown(GenericTreeNode node, GenericTreeNode block, int k, ArrayList<ArrayList<GenericTreeNode>> res) {
        if (node == null || node == block)
            return;

        if (res.size() == k)
            res.add(new ArrayList<>());
        res.get(k).add(node);

        for (GenericTreeNode child : node.child) {
            kDown(child, block, k + 1, res);
        }

    }

    public ArrayList<ArrayList<GenericTreeNode>> burningTreeNode2(GenericTreeNode root, GenericTreeNode burnNode) {
        ArrayList<ArrayList<GenericTreeNode>> res = new ArrayList<>();
        burningTreeNode2_(root, burnNode, res);
        return res;
    }

    public int burningTreeNode2_(GenericTreeNode root, GenericTreeNode burnNode,
            ArrayList<ArrayList<GenericTreeNode>> res) {

        if (root == null)
            return -1;

        if (root.val == burnNode.val) {
            kDown(root, null, 0, res);
            return 1; // mere se upr jaane mei fire ko 1 sec lgega
        }

        int dist = -1;
        GenericTreeNode block = null;
        for (GenericTreeNode child : root.child) {
            dist = burningTreeNode2_(child, burnNode, res);
            if (dist != -1) {
                block = child;
                break;
            }
        }

        if (dist != -1) {
            kDown(root, block, dist, res);
            dist++; // mere se upr jaane mei fire ko 1 sec lgega
        }

        return dist;

    }

    public ArrayList<ArrayList<GenericTreeNode>> LevelOrderTraversal(GenericTreeNode root) {
        ArrayList<ArrayList<GenericTreeNode>> list = new ArrayList<>();
        if (root == null)
            return list;

        LinkedList<GenericTreeNode> q = new LinkedList<>();
        q.addLast(root);

        while (q.size() != 0) {
            int s = q.size();
            ArrayList<GenericTreeNode> tmp = new ArrayList<>();
            while (s-- > 0) {
                GenericTreeNode rn = q.removeFirst();
                tmp.add(rn);
                for (GenericTreeNode child : rn.child)
                    q.addLast(child);
            }
            list.add(tmp);
        }

        return list;
    }

    public List<List<GenericTreeNode>> ZigZagOrderTraversal(GenericTreeNode root) {
        List<List<GenericTreeNode>> list = new ArrayList<>();
        if (root == null)
            return list;

        LinkedList<GenericTreeNode> q = new LinkedList<>();
        q.addLast(root);

        int lvl = 0;
        while (q.size() != 0) {
            int s = q.size();
            List<GenericTreeNode> tmp = new ArrayList<>();
            while (s-- > 0) {
                GenericTreeNode rn = q.removeFirst();
                tmp.add(rn);
                for (GenericTreeNode child : rn.child)
                    q.addLast(child);
            }
            if (lvl % 2 == 0)
                Collections.reverse(tmp);
            list.add(tmp);
            lvl++;
        }

        return list;
    }

    // same structure,
    public boolean isIsomorphic(GenericTreeNode root1, GenericTreeNode root2) {
        if ((root1.child.size() != root2.child.size()))
            return false;

        for (int i = 0, j = root2.child.size() - 1; j >= 0; i++, j--) {
            GenericTreeNode child1 = root1.child.get(i);
            GenericTreeNode child2 = root2.child.get(j);

            if (isIsomorphic(child1, child2))
                return false;
        }

        return true;
    }

    // Mirror -> same structure, same value
    public boolean isSymmetric(GenericTreeNode root1, GenericTreeNode root2) {
        if ((root1.child.size() != root2.child.size()) || (root1.val != root2.val))
            return false;

        for (int i = 0, j = root2.child.size() - 1; j >= 0; i++, j--) {
            GenericTreeNode child1 = root1.child.get(i);
            GenericTreeNode child2 = root2.child.get(j);

            if (isSymmetric(child1, child2))
                return false;
        }

        return true;
    }

    public int diameterGenericTree(GenericTreeNode root) {
        // diameter = maxHt + smaxHt + 2;
        int[] arr = diameterGenericTree_(root); // {maxDia,maxHt,smaxHt}
        return Math.max(arr[0], arr[1] + arr[2] + 2);
    }

    public int[] diameterGenericTree_(GenericTreeNode root) {
        if (root == null)
            return new int[] { -(int) 1e9, -1, -1 };

        int maxDia = -(int) 1e9;
        int maxHt = -1;
        int smaxHt = -1;

        for (GenericTreeNode child : root.child) {
            int[] arr = diameterGenericTree_(child);
            maxDia = Math.max(arr[0], maxDia);
            if (arr[1] > maxHt) {
                smaxHt = maxHt;
                maxHt = arr[1];
            } else if (arr[1] > smaxHt) {
                smaxHt = arr[1];
            }
        }

        maxDia = Math.max(maxDia, maxHt + smaxHt + 2);
        maxHt++;
        smaxHt++;
        return new int[] { maxDia, maxHt, smaxHt };
    }

    // returning the tail
    public GenericTreeNode flattenGenericTree(GenericTreeNode root) {
        if (root == null)
            return null;

        if (root.child.size() == 0)
            return root;

        int n = root.child.size();
        GenericTreeNode lchild = root.child.get(n - 1);
        GenericTreeNode rtail = flattenGenericTree(lchild);

        for (int i = n - 2; i >= 0; i--) {
            GenericTreeNode tempTail = flattenGenericTree(root.child.get(i));
            tempTail.child.add(root.child.get(i + 1));
            root.child.remove(i + 1);
        }

        return rtail;

    }

}
