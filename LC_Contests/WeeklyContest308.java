
import java.util.*;

public class WeeklyContest308 {
    // 2389. Longest Subsequence With Limited Sum
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int[] ans = new int[queries.length];
        Arrays.sort(nums);
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }

        // for(int i = 0;i<n;i++) System.out.print(nums[i] + " ");
        for (int i = 0; i < queries.length; i++) {
            int myans = maxLen(nums, queries[i]);
            ans[i] = myans + 1;
        }

        return ans;
    }

    public int maxLen(int[] nums, int sum) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] >= sum) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return (hi + 1 < n && nums[hi + 1] == sum) ? hi + 1 : hi;
    }

    // 2390. Removing Stars From a String
    public String removeStars(String s) {
        Stack<Character> st = new Stack<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '*') {
                st.pop();
            } else {
                st.push(ch);
            }

        }
        StringBuilder sb = new StringBuilder();
        while (st.size() != 0) {
            sb.append(st.pop());
        }

        sb.reverse();
        return sb.toString();
    }

    // 2391. Minimum Amount of Time to Collect Garbage
    public int garbageCollection(String[] garbage, int[] travel) {
        int n = garbage.length;
        int[] timetakenToreach = new int[n];
        for (int i = 1; i < n; i++) {
            timetakenToreach[i] = timetakenToreach[i - 1] + travel[i - 1];
        }

        int mcount = 0;
        int pcount = 0;
        int gcount = 0;

        int endM = 0;
        int endP = 0;
        int endG = 0;

        int idx = 0;
        for (String s : garbage) {
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == 'M') {
                    mcount++;
                    endM = Math.max(endM, idx);
                } else if (ch == 'G') {
                    gcount++;
                    endG = Math.max(endG, idx);
                } else {
                    pcount++;
                    endP = Math.max(endP, idx);
                }
            }
            idx++;
        }

        return (timetakenToreach[endM] + mcount) + (timetakenToreach[endP] + pcount)
                + (timetakenToreach[endG] + gcount);
    }

    // 2392. Build a Matrix With Conditions
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int ans[][] = new int[k][k];
        ArrayList<Integer> rowSort = isPossible(k, rowConditions);
        if (rowSort.size() != k)
            return new int[0][0];

        ArrayList<Integer> colSort = isPossible(k, colConditions);
        if (colSort.size() != k)
            return new int[0][0];

        int[] row = new int[k + 1];
        int[] col = new int[k + 1];
        for (int i = 0; i < colSort.size(); i++) {
            if (colSort.get(i) != 0) {
                col[colSort.get(i)] = i;
            }

            if (rowSort.get(i) != 0) {
                row[rowSort.get(i)] = i;
            }
        }

        for (int i = 1; i <= k; i++) {
            ans[row[i]][col[i]] = i;
        }

        return ans;

    }

    public ArrayList<Integer>[] buildGraph(int[][] edge, int k) {
        ArrayList<Integer>[] graph = new ArrayList[k + 1];
        for (int i = 0; i <= k; i++)
            graph[i] = new ArrayList<>();

        for (int[] e : edge) {
            int v = e[1];
            int u = e[0];
            graph[u].add(v);
        }

        return graph;

    }

    public ArrayList<Integer> KahnsAlgo(ArrayList<Integer>[] graph) {
        int n = graph.length;
        int[] indegree = new int[n];
        for (int src = 0; src < n; src++) {
            for (int nbr : graph[src]) {
                indegree[nbr]++;
            }
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        ArrayList<Integer> tmp = new ArrayList<>();
        while (q.size() != 0) {
            int src = q.remove();
            tmp.add(src);

            for (int nbr : graph[src]) {
                indegree[nbr]--;
                if (indegree[nbr] == 0)
                    q.add(nbr);
            }
        }

        return tmp;

    }

    private ArrayList<Integer> isPossible(int k, int[][] edges) {
        ArrayList<Integer>[] graph = buildGraph(edges, k);
        return KahnsAlgo(graph);
    }

}
