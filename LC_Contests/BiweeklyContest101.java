import java.util.*;

public class BiweeklyContest101 {
    // 2605. Form Smallest Number From Two Digit Arrays
    public int minNumber(int[] nums1, int[] nums2) {
        int[] freq = new int[10];
        for (int ele : nums1)
            freq[ele]++;
        for (int ele : nums2)
            freq[ele]++;

        for (int i = 1; i <= 9; i++) {
            if (freq[i] == 2)
                return i;
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int a = nums1[0] < nums2[0] ? nums1[0] : nums2[0];
        int b = nums1[0] < nums2[0] ? nums2[0] : nums1[0];

        return a * 10 + b;
    }

    // 2606. Find the Substring With Maximum Cost
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] has = new int[26];
        Arrays.fill(has, (int) 1e4);
        for (int i = 0; i < chars.length(); i++) {
            has[chars.charAt(i) - 'a'] = vals[i];
        }

        int gmax = 0;
        int cmax = -(int) 1e9;

        for (int i = 0; i < s.length(); i++) {
            char ele = s.charAt(i);
            int tmp = has[ele - 'a'] != (int) 1e4 ? has[ele - 'a'] : (ele - 'a') + 1;

            cmax = Math.max(cmax + tmp, tmp);
            gmax = Math.max(cmax, gmax);

        }

        return gmax;
    }

    // 2608. Shortest Cycle in a Graph
    public int findShortestCycle(int n, int[][] edges) {
        ArrayList<Integer>graph[] = buildGraph(n,edges);
        int min = (int)1e9;

        for(int i = 0;i<n;i++) min= Math.min(min,bfs(graph,i););

        return min==(int)1e9 ? -1 : min ;
        
    }

    public int bfs(ArrayList<Integer> graph[], int src) {
        int n = graph.length;
        int[] par = new int[n];
        int[] dis = new int[n];
        Arrays.fill(par, -1);

        int min = (int) 1e9;
        int level = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.add(src);
        dis[src] = 0;
        par[src] = -1;

        while (q.size() != 0) {
            int s = q.size();
            while (s-- > 0) {
                int ridx = q.remove();
                for (int nbr : graph[ridx]) {
                    if (par[nbr] == -1) {
                        par[nbr] = ridx;
                        dis[nbr] = level + 1;
                        q.add(nbr);
                    } else {
                        if (par[nbr] != ridx && par[ridx] != nbr) {
                            min = Math.min(min, dis[nbr] + dis[ridx] + 1);
                        }

                    }
                }
            }
            level++;
        }

        return min;
    }

    public ArrayList<Integer>[] buildGraph(int n, int[][] edges) {
        ArrayList<Integer> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] ed : edges) {
            int u = ed[0];
            int v = ed[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        return graph;
    }
}