package LC_Contests;

import java.util.*;

public class WeekylyContest304 {
    // 2357. Make Array Zero by Subtracting Equal Amounts
    int ans = 0;

    public int minimumOperations(int[] nums) {
        int ans = 0;
        int min = (int) 1e9;
        int n = nums.length;

        int count = 0;
        for (int e : nums) {
            if (e > 0) {
                min = Math.min(min, e);
            }

            if (e == 0)
                count++;
        }

        while (count < n) {
            ans++;
            for (int i = 0; i < n; i++) {
                if (nums[i] > 0)
                    nums[i] -= min;
            }
            min = (int) 1e9;
            count = 0;
            for (int e : nums) {
                if (e > 0) {
                    min = Math.min(min, e);
                }

                if (e == 0)
                    count++;
            }

        }

        return ans;

    }

    // 2358. Maximum Number of Groups Entering a Competition
    public int maximumGroups(int[] grades) {
        int n = grades.length;
        int ans = 0;

        Arrays.sort(grades);

        int i = 0;
        int psum = 0;
        int pnum = 0;

        while (i < n) {
            int csum = 0;
            int cnum = 0;

            while (i < n) {
                cnum++;
                csum += grades[i];
                if (csum > psum && cnum > pnum) {
                    ans++;
                    psum = csum;
                    pnum = cnum;
                    break;
                }
                i++;
            }
            i++;

            System.out.println(pnum + " " + psum);
        }

        return ans;
    }

    public ArrayList<Integer>[] buildGraph(int[] edge, int k) {
        ArrayList<Integer>[] graph = new ArrayList[k];
        for (int i = 0; i < k; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < edge.length; i++) {
            int u = i;
            if (edge[i] != -1) {
                int v = edge[i];
                graph[u].add(v);
            }
        }

        return graph;

    }

    // 2359. Find Closest Node to Given Two Nodes

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        ArrayList<Integer> graph[] = buildGraph(edges, n);

        HashMap<Integer, Integer> map = new HashMap<>(); // {node,dist}

        bfs(node1, graph, map, 1);

        bfs(node2, graph, map, 2);

        return omax == (int) 1e9 ? -1 : ansId;

    }

    int omax = (int) 1e9;
    int ansId = -1;

    private void bfs(int src, ArrayList<Integer>[] graph, HashMap<Integer, Integer> map, int flag) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(src);
        int n = graph.length;

        boolean[] vis = new boolean[n];
        int level = 0;
        while (q.size() != 0) {
            int s = q.size();
            while (s-- > 0) {
                int ridx = q.remove();

                if (vis[ridx])
                    continue;

                vis[ridx] = true;

                if (flag == 1) {
                    map.putIfAbsent(ridx, level);
                } else if (flag == 2) {
                    if (map.containsKey(ridx)) {
                        int mydist = level;
                        int otherdist = map.get(ridx);
                        int tot = Math.max(level, otherdist);

                        if (tot <= omax) {
                            if (tot < omax) {
                                omax = tot;
                                ansId = ridx;
                            } else if (tot == omax) {
                                if (ridx < ansId)
                                    ansId = ridx;
                            }
                        }
                    }
                }

                for (int nbr : graph[ridx]) {
                    if (!vis[nbr])
                        q.add(nbr);
                }
            }
            level++;
        }
    }

    // 2360. Longest Cycle in a Graph
    int max = -(int) 1e9;

    public int longestCycle(int[] edges) {
        int n = edges.length;
        ArrayList<Integer> graph[] = buildGraph(edges, n);

        boolean[] vis = new boolean[graph.length];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                longestCycle_(i, graph, vis);
            }
        }

        return max == (-(int) 1e9) ? -1 : max;
    }

    private void longestCycle_(int src, ArrayList<Integer>[] graph, boolean[] vis) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(src);
        int n = graph.length;

        int level = 0;
        while (q.size() != 0) {
            int s = q.size();
            while (s-- > 0) {
                int ridx = q.remove();

                map.putIfAbsent(ridx, level);
                if (vis[ridx])
                    continue;

                vis[ridx] = true;
                for (int nbr : graph[ridx]) {
                    if (!vis[nbr])
                        q.add(nbr);

                    if (map.containsKey(nbr)) {
                        max = Math.max(max, level - map.get(nbr) + 1);
                    }
                }
            }
            level++;
        }
    }

}
