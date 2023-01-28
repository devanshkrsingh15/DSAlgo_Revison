import java.util.ArrayList;
import java.util.HashMap;

import java.util.*;

public class LC_Daily {

    class LC131 {
        List<List<String>> ans = new ArrayList<>();
        boolean[][] isPal;

        public List<List<String>> partition(String s) {
            int n = s.length();
            isPal = new boolean[n][n];
            for (int gap = 0; gap < n; gap++) {
                for (int st = 0, en = st + gap; en < n; st++, en++) {
                    if (gap == 0)
                        isPal[st][en] = true;
                    else if (gap == 1)
                        isPal[st][en] = s.charAt(st) == s.charAt(en);
                    else {
                        isPal[st][en] = isPal[st + 1][en - 1] && s.charAt(st) == s.charAt(en);
                    }
                }
            }

            partition_(s, 0, new ArrayList<>());
            return ans;
        }

        private void partition_(String s, int idx, ArrayList<Integer> tmp) {
            if (idx == s.length()) {
                List<String> myans = getStrings(s, tmp);
                if (myans != null)
                    ans.add(myans);
                return;
            }

            for (int i = idx; i < s.length(); i++) {
                char ch = s.charAt(i);
                tmp.add(i);
                partition_(s, i + 1, tmp);
                tmp.remove(tmp.size() - 1);

            }
        }

        private List<String> getStrings(String s, ArrayList<Integer> tmp) {
            List<String> ans = new ArrayList<>();
            int st = 0;
            for (int i = 0; i < tmp.size(); i++) {
                int en = (i == tmp.size() - 1) ? s.length() : tmp.get(i) + 1;
                if (!isPal[st][en - 1])
                    return null;
                ans.add(s.substring(st, en));
                st = tmp.get(i) + 1;
            }

            return ans;
        }
    }

    class LC997 {
        public int findJudge(int n, int[][] trust) {
            int[] indegree = new int[n + 1];
            int[] outdegree = new int[n + 1];

            for (int[] t : trust) {
                int u = t[0];
                int v = t[1];

                indegree[v]++;
                outdegree[u]++;
            }

            for (int i = 1; i <= n; i++) {
                if (outdegree[i] == 0 && indegree[i] == n - 1)
                    return i;
            }

            return -1;
        }
    }

    class LCC909 {
        public int snakesAndLadders(int[][] board) {
            int n = board.length;
            int m = board[0].length;
            HashMap<Integer, Integer> map = new HashMap<>();
            int val = 0;
            int idx = 1;
            for (int i = n - 1; i >= 0; i--) {
                if (val % 2 == 0) {
                    for (int j = 0; j < m; j++) {
                        map.put(idx, i * m + j);
                        idx++;
                    }
                } else {
                    for (int j = m - 1; j >= 0; j--) {
                        map.put(idx, i * m + j);
                        idx++;
                    }
                }
                val++;
            }

            ArrayDeque<Integer> q = new ArrayDeque<>();
            val = board[n - 1][0] == -1 ? 1 : board[n - 1][0];
            q.add(val);
            boolean[] vis = new boolean[(int) 1e5 + 10];
            int level = 0;

            while (q.size() != 0) {
                int s = q.size();
                while (s-- > 0) {
                    int nval = q.remove();

                    if (vis[nval])
                        continue;
                    vis[nval] = true;

                    if (nval == n * n)
                        return level;

                    for (int k = 1; k <= 6; k++) {
                        if (k + nval > n * n)
                            break;
                        int ridx = map.get(k + nval);

                        int r = ridx / n;
                        int c = ridx % n;

                        if (board[r][c] != -1) {
                            q.add(board[r][c]);
                        } else {
                            q.add(k + nval);
                        }
                    }
                }

                level++;
            }

            return -1;
        }

    }

    class LC2359 {
        int ans = (int) 1e9;
        int ansidx = -1;

        public int closestMeetingNode(int[] edges, int node1, int node2) {
            int n = edges.length;
            HashMap<Integer, Integer> map = new HashMap<>();
            getPath(edges, node1, map, 0, true);
            getPath(edges, node2, map, 0, false);
            return ansidx;

        }

        private void getPath(int[] edges, int src, HashMap<Integer, Integer> map, int dis, boolean isBuilding) {

            if (isBuilding == false) {
                if (map.containsKey(src)) {
                    int cans = Math.max(dis, map.get(src));
                    if (cans < ans) {
                        ans = cans;
                        ansidx = src;
                    } else if (cans == ans) {
                        ansidx = Math.min(ansidx, src);
                    }
                }
            }

            if (isBuilding)
                map.put(src, dis);
            else if (!isBuilding) {
                map.put(src + (int) 1e5, dis);
            }

            int nbr = edges[src];
            if ((isBuilding && nbr != -1 && !map.containsKey(nbr))
                    || (!isBuilding && nbr != -1 && !map.containsKey(nbr + (int) 1e5)))
                getPath(edges, nbr, map, dis + 1, isBuilding);
        }

    }

    class LC787 {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            int[] arr = new int[n];
            Arrays.fill(arr, (int) 1e9);
            arr[src] = 0;

            for (int f = 0; f <= k; f++) {
                int[] uarr = new int[n];
                for (int i = 0; i < n; i++)
                    uarr[i] = arr[i];

                for (int[] fl : flights) {
                    int u = fl[0];
                    int v = fl[1];
                    int c = fl[2];
                    uarr[v] = Math.min(uarr[v], arr[u] + c);
                }

                arr = uarr;
            }

            return arr[dst] == (int) 1e9 ? -1 : arr[dst];
        }

    }

    class LC472 {
        class TrieNode {
            char ch;
            boolean isFinal;
            TrieNode[] child;

            TrieNode(char ch) {
                this.ch = ch;
                isFinal = false;
                child = new TrieNode[26];
            }

        }

        TrieNode root = new TrieNode('*');

        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            for (String s : words) {
                insert(s);
            }

            List<String> ans = new ArrayList<>();
            for (String s : words) {
                if (check(s, 0, 0)) {
                    ans.add(s);
                }
            }

            return ans;

        }

        private boolean check(String s, int idx, int cnt) {
            if (idx == s.length())
                return cnt >= 2;

            TrieNode curr = root;
            boolean res = false;
            for (int i = idx; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (curr.child[ch - 'a'] != null) {
                    curr = curr.child[ch - 'a'];
                    if (curr.isFinal) {
                        boolean a = check(s, i + 1, cnt + 1);
                        if (a)
                            return true;
                    }
                } else {
                    return false;
                }
            }

            return res;
        }

        private void insert(String s) {
            TrieNode curr = root;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (curr.child[ch - 'a'] == null)
                    curr.child[ch - 'a'] = new TrieNode(ch);
                curr = curr.child[ch - 'a'];
            }
            curr.isFinal = true;
        }
    }

    //352. Data Stream as Disjoint Intervals
    class SummaryRanges {

        HashSet<Integer>numbers;
        int max  = 0;
        int min = (int)1e9;
        public SummaryRanges() {
            numbers = new HashSet<>();
        }
        
        public void addNum(int value) {
            numbers.add(value);
            max=  Math.max(max,value);
            min=  Math.min(min,value);
        }
        
        public int[][] getIntervals() {
            ArrayList<int[]>intervals = new ArrayList<>();
            boolean[]taken = new boolean[(int)1e5];
            for(int i = min;i<=max;i++){
                if(numbers.contains(i) && !taken[i]){
                    int st = i;
                    int en = i;
                    int ptr = st;
                    taken[i] = true;
                    while(numbers.contains(ptr+1)){
                        en = ptr+1;
                        ptr++;
                        taken[ptr] = true;
                    }

                    intervals.add(new int[]{st,en});
                }
            }
        
            int[][]ans = new int[intervals.size()][];
            int idx = 0;
            for(int []intr : intervals){
                ans[idx++] = intr;
            }

            return ans;
        
        
        }
    }
    
}