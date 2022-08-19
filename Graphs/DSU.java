import java.util.*;

public class DSU {
    // can be used for finding cycle (min 3 edges) in bidirectional graph
    // Spanning tree -> all vtx connected but NO cycle exists

    // INVERSE ACKERMANN
    // path compression + size check => parent tree ht <= 4
    // O(alpha(N)) <= O(4)

    public int findParent(int par[], int u) {
        if (par[u] == u)
            return u;
        else {
            int temp = findParent(par, par[u]);
            par[u] = temp; // path compression
            return temp;
        }
    }

    public void UnionFindAlgo(int n, Edge edges[]) {
        int[] par = new int[n];
        int[] size = new int[n];

        ArrayList<Edge> spanningTree[] = new ArrayList[n]; // spanning tree

        for (int i = 0; i < n; i++)
            spanningTree[i] = new ArrayList<>();
        Arrays.fill(size, 1);
        for (int i = 0; i < n; i++)
            par[i] = i;

        boolean hasCycle = false;

        // TC = E + V*alpha(N)
        // TC = E + V*logN
        for (Edge e : edges) {
            int u = e.u;
            int v = e.v;

            int p1 = findParent(par, u);
            int p2 = findParent(par, v);

            if (p1 != p2) {
                merge(par, size, u, v, p1, p2);
                AddEdge(u, v, e.wt, spanningTree);
            } else {
                hasCycle = true;
            }
        }
    }

    private void AddEdge(int u, int v, int w, ArrayList<Edge>[] graph) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));
    }

    private void merge(int[] par, int[] size, int u, int v, int p1, int p2) {
        if (size[p1] >= size[p2]) {
            size[p1] += size[p2];
            par[p2] = p1;
        } else {
            size[p2] += size[p1];
            par[p1] = p1;
        }
    }

    // Leetcode 684. Redundant Connection
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] par = new int[n + 2];
        for (int i = 0; i < par.length; i++)
            par[i] = i;

        int[] ans = null;

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            int p1 = findParent(par, u);
            int p2 = findParent(par, v);

            if (p1 != p2) {
                par[p2] = p1;
            } else {
                ans = new int[] { u, v };
            }
        }

        return ans != null ? ans : new int[0];
    }

    // Leetcode 1061 Lexicographically Smallest Equivalent String
    public String smallestEquivalentString(String A, String B, String S) {
        int[] par = new int[26];
        for (int i = 0; i < 26; i++)
            par[i] = i;

        int len = A.length();
        for (int i = 0; i < len; i++) {
            char cha = A.charAt(i);
            char chb = B.charAt(i);

            int u = cha - 'a';
            int v = chb - 'a';

            int p1 = findParent(par, u);
            int p2 = findParent(par, v);

            if (p1 != p2) {
                par[p1] = Math.min(p1, p2);
                par[p2] = Math.min(p1, p2);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            int p = findParent(par, ch - 'a');
            sb.append((char) (p + 65));
        }

        return sb.toString();
    }

}
