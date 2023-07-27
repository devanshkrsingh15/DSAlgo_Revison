import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class BiweeklyContest107 {

    //2744. Find Maximum Number of String Pairs
    public int maximumNumberOfStringPairs(String[] words) {
        HashSet<Integer>set = new HashSet<>();
        int n= words.length;
        int ans  = 0;
        for(int i = 0 ;i<n;i++){
            for(int j = i+1;j<n;j++){
                if(!set.contains(j) && !set.contains(i)){
                    if(isPossible(words,i,j)) ans++;
                }
            }
        }

        return ans;
    }

    public boolean isPossible(String[] words,int i,int j){
        return words[i].charAt(1)==words[j].charAt(0) &&  words[i].charAt(0)==words[j].charAt(1);
    }


    //2745. Construct the Longest New String
    public int longestString(int x, int y, int z) {
        int ans = 2*z + 4*Math.min(x,y);
        if(x!=y) ans+=2;
        return ans;
    }


    //2746. Decremental String Concatenation
    int[][][]dp;
    public int minimizeConcatenatedLength(String[] words) {
        int n = words.length;
        dp = new int[n+1][28][28];
        
        for(int[][]d2:dp){
            for(int[]d:d2) Arrays.fill(d,-1);
        }

        String str = words[0];
        int len = str.length();
        char fc = str.charAt(0);
        char lc = str.charAt(len-1);
        return minimizeConcatenatedLength_(words,fc-'a',lc-'a',1) + len ;
    }
    
    public int minimizeConcatenatedLength_(String[]words,int firstChar,int lastChar,int idx){
        if(idx==words.length) return 0;
        if(dp[idx][firstChar][lastChar]!=-1) return dp[idx][firstChar][lastChar];
        
        
        int ans = (int)1e9;

        String str = words[idx];
        int len = str.length();
        char fc = str.charAt(0);
        char lc = str.charAt(len-1);

        //str_so_far JOIN strIdx
        if(lastChar==(fc-'a') ){
            ans = Math.min(ans,minimizeConcatenatedLength_(words,firstChar,lc-'a',idx+1)+len-1);
        }else{
            ans = Math.min(ans,minimizeConcatenatedLength_(words,firstChar,lc-'a',idx+1)+len);
        }
        
        //strIdx JOIN str_so_far
        if(firstChar==(lc-'a')){
            ans = Math.min(ans,minimizeConcatenatedLength_(words,fc-'a',lastChar,idx+1)+len-1);
        }else{
            ans = Math.min(ans,minimizeConcatenatedLength_(words,fc-'a',lastChar,idx+1)+len);
        }
        return dp[idx][firstChar][lastChar] =  ans;

    }


    public int[] countServers(int tot, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs,(a,b)->{
            return a[1] - b[1];
        });
        int n = queries.length;
        int[]ans= new int[n];

        ArrayList<int[]>list = new ArrayList<>(); //idx,time
        for(int i= 0 ; i<n;i++){
            list.add(new int[]{i,queries[i]});
        }

        Collections.sort(list,(a,b)->{
            return a[1] - b[1];
        });
        int st = 0;
        int en = 0;

        HashMap<Integer,Integer>map = new HashMap<>();

        int idx = 0;

        while(idx < list.size()){
            int ptr= list.get(idx)[0];
            int hi = list.get(idx)[1];
            int lo = list.get(idx)[1] - x;

            while(en<logs.length && logs[en][1]<=hi){
                int sid = logs[en][0];
                map.put(sid,map.getOrDefault(sid,0)+1);
                en++;
            }

            while(st<logs.length && logs[st][1]<lo){
                int sid = logs[st][0];
                map.put(sid,map.getOrDefault(sid,0)-1);
                if(map.get(sid)==0) map.remove(sid);
                st++;
            }

            ans[ptr] = tot - map.keySet().size();
            idx++;
        }

        return ans;


    }

}
