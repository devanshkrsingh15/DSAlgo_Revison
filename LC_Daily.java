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
        int[]indegree = new int[n+1];
        int[]outdegree = new int[n+1];

        for(int[]t :trust ){
            int u = t[0];
            int v = t[1];

            indegree[v]++;
            outdegree[u]++;
        }

        for(int i =1;i<=n;i++){
            if(outdegree[i]==0 &&indegree[i]==n-1) return i;
        }

        return -1;
    }
}
}