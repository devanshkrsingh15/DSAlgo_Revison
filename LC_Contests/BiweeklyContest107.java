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


}
