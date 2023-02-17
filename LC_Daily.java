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

    // 352. Data Stream as Disjoint Intervals
    class SummaryRanges {

        HashSet<Integer> numbers;
        int max = 0;
        int min = (int) 1e9;

        public SummaryRanges() {
            numbers = new HashSet<>();
        }

        public void addNum(int value) {
            numbers.add(value);
            max = Math.max(max, value);
            min = Math.min(min, value);
        }

        public int[][] getIntervals() {
            ArrayList<int[]> intervals = new ArrayList<>();
            boolean[] taken = new boolean[(int) 1e5];
            for (int i = min; i <= max; i++) {
                if (numbers.contains(i) && !taken[i]) {
                    int st = i;
                    int en = i;
                    int ptr = st;
                    taken[i] = true;
                    while (numbers.contains(ptr + 1)) {
                        en = ptr + 1;
                        ptr++;
                        taken[ptr] = true;
                    }

                    intervals.add(new int[] { st, en });
                }
            }

            int[][] ans = new int[intervals.size()][];
            int idx = 0;
            for (int[] intr : intervals) {
                ans[idx++] = intr;
            }

            return ans;

        }
    }

    class LFUCache {
        public class Node {
            int key, val, freq;
            Node next = null;
            Node prev = null;

            Node(int key, int val, int freq) {
                this.key = key;
                this.val = val;
                this.freq = freq;
            }
        }

        // (first) Head <-> ...... <-> Tail (last)
        public class DoubleLinkedList {
            Node head = null;
            Node tail = null;
            int len = 0;

            public void AddFist(Node node) {
                if (this.head == null) {
                    this.head = node;
                    this.tail = node;
                } else {
                    this.head.prev = node;
                    node.next = this.head;
                    this.head = node;
                }
                size++;
                len++;
            }

            public void AddLast(Node node) {
                if (this.head == null) {
                    this.head = node;
                    this.tail = node;
                } else {
                    this.tail.next = node;
                    node.prev = this.tail;
                    this.tail = node;
                }
                size++;
                len++;
            }

            public void remove(Node node) {
                if (this.head == node) {
                    RemoveFirst();
                } else if (this.tail == node) {
                    RemoveLast();
                } else {
                    Node pnode = node.prev;
                    Node nnode = node.next;

                    pnode.next = nnode;
                    nnode.prev = pnode;

                    node.prev = null;
                    node.next = null;

                    size--;
                    len--;
                }
            }

            public void RemoveFirst() {
                if (this.head == this.tail) {
                    this.head = null;
                    this.tail = null;
                } else {
                    Node nhead = this.head.next;
                    nhead.prev = null;
                    this.head.next = null;
                    this.head = nhead;
                }
                size--;
                len--;

            }

            public void RemoveLast() {
                if (this.head == this.tail) {
                    this.head = null;
                    this.tail = null;
                } else {
                    Node ntail = this.tail.prev;
                    ntail.next = null;
                    this.tail.prev = null;
                    this.tail = ntail;
                }
                size--;
                len--;
            }

        }

        int size = 0;
        int cap = 0;
        int maxF = 0;
        int minF = 0;

        HashMap<Integer, Node> nodeMappping;
        HashMap<Integer, DoubleLinkedList> freqMapping;

        public LFUCache(int capacity) {
            initialize(capacity);
        }

        private void initialize(int capacity) {
            this.nodeMappping = new HashMap<>();
            this.freqMapping = new HashMap<>();
            this.size = 0;
            this.cap = capacity;
        }

        public int get(int key) {
            if (!nodeMappping.containsKey(key))
                return -1;

            Node node = nodeMappping.get(key);
            makeRecent(node);
            return node.val;

        }

        private void makeRecent(Node node) {
            int of = node.freq;
            int nf = of + 1;

            DoubleLinkedList olist = freqMapping.get(of);
            olist.remove(node);

            if (olist.len == 0) {
                freqMapping.remove(of);
                if (minF == of)
                    minF++;
            }

            node.freq = nf;
            nodeMappping.put(node.key, node);

            freqMapping.putIfAbsent(nf, new DoubleLinkedList());
            DoubleLinkedList nlist = freqMapping.get(nf);

            maxF = Math.max(maxF, nf);
            nlist.AddLast(node);
        }

        public void put(int key, int value) {
            if (this.cap == 0)
                return;

            if (!nodeMappping.containsKey(key)) {
                if (this.size == this.cap) {
                    removeLeastFrequent();
                }

                Node node = new Node(key, value, 1);
                nodeMappping.put(key, node);

                freqMapping.putIfAbsent(1, new DoubleLinkedList());
                DoubleLinkedList list = freqMapping.get(1);
                list.AddLast(node);

                minF = 1;
            } else {
                Node node = nodeMappping.get(key);
                node.val = value;
                makeRecent(node);
            }
        }

        private void removeLeastFrequent() {
            DoubleLinkedList list = freqMapping.get(minF);
            Node n = list.head;
            nodeMappping.remove(n.key);
            list.RemoveFirst();
            if (list.len == 0) {
                freqMapping.remove(minF);
            }
        }
    }

    class LC1137 {
        public int tribonacci(int n) {
            int[] dp = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                if (i <= 1)
                    dp[i] = i;
                else if (i == 2)
                    dp[i] = 1;
                else
                    dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }

            return dp[n];
        }
    }

    class LC1626 {
        int[][] dp;

        public int bestTeamScore(int[] scores, int[] ages) {
            int n = scores.length;
            ArrayList<int[]> list = new ArrayList<>();
            dp = new int[n + 1][(int) 1e3 + 15];
            for (int[] d : dp)
                Arrays.fill(d, -1);

            for (int i = 0; i < n; i++) {
                int ag = ages[i];
                int sc = scores[i];
                list.add(new int[] { ag, sc });
            }

            Collections.sort(list, (a, b) -> {
                if (a[0] == b[0])
                    return a[1] - b[1];
                return a[0] - b[0];
            });
            return bestTeamScore_(list, 0, (int) 1e3 + 10);

        }

        public int bestTeamScore_(ArrayList<int[]> list, int idx, int prvIdx) {
            if (idx == list.size())
                return 0;
            if (dp[idx][prvIdx] != -1)
                return dp[idx][prvIdx];

            int max = 0;
            int myScore = list.get(idx)[1];
            int myAge = list.get(idx)[0];

            if (prvIdx == (int) 1e3 + 10 || list.get(prvIdx)[1] <= myScore)
                max = Math.max(max, bestTeamScore_(list, idx + 1, idx) + myScore);
            max = Math.max(max, bestTeamScore_(list, idx + 1, prvIdx));

            return dp[idx][prvIdx] = max;
        }
    }

    class LC1071 {
        public String gcdOfStrings(String str1, String str2) {
            HashSet<String> hs1 = getDivisors(str1);
            HashSet<String> hs2 = getDivisors(str2);

            if (hs1.size() == 0 || hs2.size() == 0)
                return "";

            int maxLen = 0;
            String maxStr = "";

            for (String s1 : hs1) {
                if (hs2.contains(s1)) {
                    if (maxLen < s1.length()) {
                        maxLen = s1.length();
                        maxStr = s1;
                    }
                }
            }

            return maxStr;
        }

        public HashSet<String> getDivisors(String s) {
            int len = s.length();
            HashSet<String> hs = new HashSet<>();
            for (int i = 0; i < len; i++) {
                String cs = s.substring(0, i + 1);

                if (check(cs, s, i + 1)) {
                    hs.add(cs);
                }

            }

            return hs;
        }

        public boolean check(String cs, String s, int st) {
            int n = s.length();
            int m = cs.length();
            if (n % m != 0)
                return false;

            for (int i = st; i < n; i += m) {
                if (i + m > n)
                    return false;
                if (!cs.equals(s.substring(i, i + m)))
                    return false;
            }

            return true;
        }

        public String gcdOfStringsOpti(String str1, String str2) {
            if (!(str1 + str2).equals((str2 + str1)))
                return "";

            int gcd = findGcd(str1.length(), str2.length());
            return str1.substring(0, gcd);
        }

        private int findGcd(int a, int b) {
            if (b == 0)
                return a;
            return findGcd(b, a % b);
        }
    }

    class LC953 {
        public boolean isAlienSorted(String[] words, String o) {
            int[] order = new int[26];
            for (int i = 0; i < o.length(); i++) {
                char ch = o.charAt(i);
                order[ch - 'a'] = i;
            }

            String prv = words[0];
            for (int i = 1; i < words.length; i++) {
                String curr = words[i];
                int lim = Math.max(prv.length(), curr.length());
                int idx = 0;
                while (idx < lim) {
                    char a = (idx < curr.length()) ? curr.charAt(idx) : ' ';
                    char b = (idx < prv.length()) ? prv.charAt(idx) : ' ';
                    int u = (a == ' ') ? -1 : order[a - 'a'];
                    int v = (b == ' ') ? -1 : order[b - 'a'];
                    idx++;
                    if (u == v) {
                        continue;
                    } else if (u > v) {
                        break;
                    } else {
                        return false;
                    }
                }
                prv = curr;
            }

            return true;
        }

    }

    class LC6 {
        public String convert(String s, int numRows) {
            int n = s.length();
            char arr[][] = new char[numRows][n];
            for (char[] a : arr)
                Arrays.fill(a, '*');
            int r = 0;
            int c = 0;
            int idx = 0;
            int maxC = 0;
            boolean down = true;
            while (idx < n) {
                if (down) {
                    arr[r][c] = s.charAt(idx++);
                    r++;
                    if (r == numRows) {
                        down = false;
                        c++;
                        r -= 2;
                    }
                } else {
                    if (r <= 0) {
                        r = Math.max(0, r);
                        down = true;
                        continue;
                    }
                    arr[r][c] = s.charAt(idx++);
                    r--;
                    c++;
                }
            }

            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < maxC; j++) {
                    if (arr[i][j] != '*') {
                        sb.append(arr[i][j]);
                        cnt++;
                    }
                    if (cnt == n)
                        return sb.toString();
                }
            }

            return sb.toString();
        }
    }

    class LC567 {
        public boolean checkInclusion(String s1, String s2) {
            int[] farr = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                char ch = s1.charAt(i);
                farr[ch - 'a']++;
            }

            int[][] arr = new int[s2.length()][26];
            for (int i = 0; i < s2.length(); i++) {
                char ch = s2.charAt(i);
                arr[i][ch - 'a']++;
                for (int j = 0; j < 26; j++) {
                    if (i - 1 >= 0)
                        arr[i][j] += arr[i - 1][j];
                }
            }

            for (int i = 0; i < s2.length(); i++) {
                if (i + s1.length() - 1 < s2.length()) {
                    int cnt = 0;

                    for (int j = 0; j < 26; j++) {

                        if (farr[j] == ((i == 0) ? arr[i + s1.length() - 1][j]
                                : arr[i + s1.length() - 1][j] - arr[i - 1][j])) {
                            cnt++;
                        }
                    }
                    if (cnt == 26)
                        return true;
                }

            }

            return false;

        }
    }

    class LC438 {
        public List<Integer> findAnagrams(String s, String p) {
            ArrayList<Integer> ans = new ArrayList<>();
            int[] arr = new int[26];
            int cnt = 0;
            for (int i = 0; i < p.length(); i++) {
                if (arr[p.charAt(i) - 'a'] == 0)
                    cnt++;
                arr[p.charAt(i) - 'a']++;
            }

            int ei = 0;
            int si = 0;

            while (ei < s.length()) {
                arr[s.charAt(ei) - 'a']--;
                if (arr[s.charAt(ei) - 'a'] == 0)
                    cnt--;
                ei++;
                while (cnt == 0) {
                    if (ei - si == p.length())
                        ans.add(si);
                    arr[s.charAt(si) - 'a']++;
                    if (arr[s.charAt(si) - 'a'] == 1)
                        cnt++;
                    si++;
                }
            }

            return ans;
        }
    }

    class LC1470 {
        public int[] shuffle(int[] nums, int n) {
            int[] arr = new int[2 * n];
            int len = nums.length;
            int idx = 0;
            for (int i = 0; i < len / 2; i++) {
                arr[idx] = nums[i];
                idx += 2;
            }
            idx = 1;

            for (int i = len / 2; i < len; i++) {
                arr[idx] = nums[i];
                idx += 2;
            }

            return arr;
        }
    }

    class LC904 {
        public int totalFruit(int[] fruits) {
            int[] farr = new int[(int) 1e5 + 7];
            int cnt = 0;
            int ei = 0;
            int si = 0;
            int n = fruits.length;
            int max = 0;
            while (ei < n) {
                if (farr[fruits[ei++]]++ == 0)
                    cnt++;
                while (cnt > 2) {
                    if (farr[fruits[si++]]-- == 1)
                        cnt--;
                }

                max = Math.max(max, ei - si);
            }

            return max;
        }
    }

    class LC2306 {
        public long distinctNames(String[] ideas) {
            HashSet<String> sets[] = new HashSet[26];
            for (int i = 0; i < 26; i++) {
                sets[i] = new HashSet<>();
            }

            for (String id : ideas) {
                char ch = id.charAt(0);
                sets[ch - 'a'].add(id.substring(1));
            }

            long ans = 0l;

            for (int i = 0; i < 26; i++) {
                for (int j = i + 1; j < 26; j++) {
                    HashSet<String> setA = sets[i];
                    HashSet<String> setB = sets[j];

                    long sameSuff = 0l;

                    for (String s : setA) {
                        if (setB.contains(s))
                            sameSuff++;
                    }

                    ans += ((long) setA.size() - sameSuff) * ((long) setB.size() - sameSuff) * 2;

                }
            }

            return ans;

        }

    }

    public int maxDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int max = -1;

        int ones = 0;
        int zeros = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0)
                    zeros++;
                if (grid[i][j] == 1)
                    ones++;
            }
        }

        if (ones == 0 || zeros == 0)
            return -1;

        if (ones == 1) {
            int x1 = -1;
            int y1 = -1;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        x1 = i;
                        y1 = j;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 0) {
                        max = Math.max(max, Math.abs(i - x1) + Math.abs(j - y1));
                    }
                }
            }

            return max;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    int res = bfs(grid, i, j);
                    if (res != -1) {
                        int x = res / m;
                        int y = res % m;

                        max = Math.max(max, Math.abs(i - x) + Math.abs(j - y));
                    }
                }

            }
        }

        return max;
    }

    public int bfs(int[][] grid, int i, int j) {
        int n = grid.length;
        int m = grid[0].length;

        boolean vis[][] = new boolean[n][m];
        int[][] direcs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(i * m + j);

        while (q.size() != 0) {
            int s = q.size();
            while (s-- > 0) {
                int ridx = q.remove();
                int r = ridx / m;
                int c = ridx % m;

                if (grid[r][c] == 1)
                    return r * m + c;
                if (vis[r][c])
                    continue;

                vis[r][c] = true;

                for (int k = 0; k < direcs.length; k++) {
                    int x = r + direcs[k][0];
                    int y = c + direcs[k][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && !vis[x][y]) {
                        q.add(x * m + y);
                    }
                }
            }

        }

        return -1;
    }

}

class LC1129 {
    class Edge {
        int u;
        int v;
        int col;

        Edge(int u, int v, int col) {
            this.u = u;
            this.v = v;
            this.col = col;
        }
    }

    // red wt = 1
    // blue wt = 0;
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        ArrayList<Edge> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] ed : redEdges) {
            int u = ed[0];
            int v = ed[1];
            graph[u].add(new Edge(u, v, 1));

        }

        for (int[] ed : blueEdges) {
            int u = ed[0];
            int v = ed[1];
            graph[u].add(new Edge(u, v, 0));
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        boolean[][] vis = new boolean[n][2];

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[] { 0, -1 }); // src,color
        vis[0][0] = vis[0][1] = true;
        int level = 0;
        while (q.size() != 0) {
            int s = q.size();

            while (s-- > 0) {
                int[] ridx = q.remove();
                int idx = ridx[0];
                int color = ridx[1];
                if (ans[idx] == -1)
                    ans[idx] = level;

                for (Edge ed : graph[idx]) {
                    if (!vis[ed.v][ed.col] && color != ed.col) {
                        q.add(new int[] { ed.v, ed.col });
                        vis[ed.v][ed.col] = true;
                    }
                }
            }

            level++;
        }

        return ans;
    }
}

class LC2477 {
    long ans = 0l;
    int S = 0;

    public long minimumFuelCost(int[][] roads, int seats) {
        S = seats;
        ArrayList<Integer> graph[] = buildGraph(roads);
        if (graph.length == 1)
            return 0l;

        int n = graph.length;
        boolean[] vis = new boolean[n];
        dfs(0, graph, vis);
        return ans;
    }

    public int dfs(int src, ArrayList<Integer> graph[], boolean[] vis) {
        int size = 0;
        vis[src] = true;

        for (int nbr : graph[src]) {
            if (!vis[nbr]) {
                size += dfs(nbr, graph, vis);
            }
        }
        size++;

        if (src != 0)
            ans += size / S + (size % S > 0 ? 1 : 0);

        return size;
    }

    public ArrayList<Integer>[] buildGraph(int[][] roads) {
        int n = roads.length;
        ArrayList<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] rd : roads) {
            int u = rd[0];
            int v = rd[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        return graph;
    }
}

class LC1523 {
    public int countOdds(int low, int high) {
        if (low % 2 == 0)
            low++;
        if (high % 2 == 0)
            high--;

        long n = (high - low) / 2 + 1;

        return (int) n;
    }
}


class LC989 {
    public List<Integer> addToArrayForm(int[] num, int k) {
        int i = num.length-1;
        ArrayList<Integer>ans = new ArrayList<>();
        int carry = 0;

        while(i>=0 || k!=0){
            int a = (i>=0) ? num[i] : 0;
            int b =(k>0)  ? k%10 : 0 ;
            int sum = a + b + carry;

            if(sum<10){
                ans.add(sum);
                carry = 0;
            }else{
                ans.add(sum%10);
                carry = sum/10;
            }

            i--;
            k/=10;
        }
        if(carry!=0){
            ans.add(carry);
        }

        Collections.reverse(ans);
        return ans;
    }
}


class LC67 {
    public String addBinary(String a, String b) {
        if(a.length()<b.length()){
            return addBinary(b,a);
        }

        int n = a.length();
        int m = b.length();
        int i = n-1;
        int j = m-1;
        int carry = 0;

        StringBuilder sb  =  new StringBuilder();

        while(j>=0){
            char cha = a.charAt(i);
            char chb = b.charAt(j);

            int an = cha-'0';
            int am = chb-'0';

            if(an==0 && am==0){
                sb.append(carry);
                carry = 0;
            }else if(an==1 && am==1){
                int app = (carry==1) ? 1 : 0;
                sb.append(app);
                carry = 1;
            }else {
                int app = (carry==1) ? 0 : 1;
                sb.append(app);
                carry = (carry==1) ? 1 : 0;
            }

            i--;
            j--;

        }

        while(i>=0){
            char cha = a.charAt(i);
            int an = cha-'0';
            int am = carry;

            if(an==0 && am==0){
                sb.append(carry);
                carry = 0;
            }else if(an==1 && am==1){
                int app = 0;
                sb.append(app);
                carry = 1;
            }else {
                int app = 1;
                sb.append(app);
                carry = 0;
            }

            i--;
        }

        if(carry!=0)sb.append(carry);
      
        

        sb.reverse();
        return sb.toString();
    }
}

class LC104 {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        return Math.max( maxDepth(root.left) , maxDepth(root.right) )+ 1;
    }
}