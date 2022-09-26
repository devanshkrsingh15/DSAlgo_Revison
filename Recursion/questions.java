package Recursion;

import java.util.*;

public class questions {

    // 17. Letter Combinations of a Phone Number
    HashMap<Integer, String> map;
    List<String> ans = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return ans;
        map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        letterCombinations_(digits, 0, "");
        return ans;
    }

    public void letterCombinations_(String dig, int idx, String psf) {
        if (idx == dig.length()) {
            ans.add(psf);
            return;
        }

        char ch = dig.charAt(idx);
        String s = map.get(ch - '0');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            letterCombinations_(dig, idx + 1, psf + "" + c);
        }
    }

    // MAZE PATH with one step
    public ArrayList<String> MazePathHVD(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            return new ArrayList<>();
        }

        ArrayList<String> ans = new ArrayList<>();
        if (sr + 1 <= er) {
            ArrayList<String> fans = MazePathHVD(sr + 1, sc, er, ec);
            for (String s : fans) {
                ans.add("H" + s);
            }
        }

        if (sc + 1 <= ec) {
            ArrayList<String> fans = MazePathHVD(sr, sc + 1, er, ec);
            for (String s : fans) {
                ans.add("V" + s);
            }
        }

        if (sc + 1 <= ec && sr + 1 <= er) {
            ArrayList<String> fans = MazePathHVD(sr + 1, sc + 1, er, ec);
            for (String s : fans) {
                ans.add("D" + s);
            }
        }

        return ans;

    }

    // MAZE PATH with multi steps
    public ArrayList<String> MazePathHVD_multiJump(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            return new ArrayList<>();
        }

        ArrayList<String> ans = new ArrayList<>();
        for (int i = 1; sr + i <= er; i++) {
            ArrayList<String> fans = MazePathHVD(sr + i, sc, er, ec);
            for (String s : fans) {
                ans.add("H" + i + s);
            }
        }

        for (int i = 1; sc + i <= ec; i++) {
            ArrayList<String> fans = MazePathHVD(sr, sc + i, er, ec);
            for (String s : fans) {
                ans.add("V" + i + s);
            }
        }

        for (int i = 1; sc + i <= ec && sr + i <= er; i++) {
            ArrayList<String> fans = MazePathHVD(sr + i, sc + i, er, ec);
            for (String s : fans) {
                ans.add("D" + i + s);
            }
        }

        return ans;

    }

    //46. Permutations
    List<List<Integer>> pans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        permute_(nums, new ArrayList<>(), 0);
        return pans;
    }

    public void permute_(int[] nums, ArrayList<Integer> tmp, int vis) {
        if (tmp.size() == nums.length) {
            List<Integer> base = new ArrayList<>((tmp));
            pans.add(base);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if ((vis & (1 << i)) == 0) {
                vis ^= (1 << i);
                tmp.add(nums[i]);
                permute_(nums, tmp, vis);
                tmp.remove(tmp.size() - 1);
                vis ^= (1 << i);
            }
        }
    }



    //47. Permutations II
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>>ans = new ArrayList<>();
        permuteUnique_helper(ans,nums,new ArrayList<>(),0);
        return ans;
    }

    private void permuteUnique_helper(List<List<Integer>>ans,int[] nums, List<Integer>tmp,int vis) {
        if(tmp.size()==nums.length){
            List<Integer> base = new ArrayList<>((tmp));
            ans.add(base);
            return;
        }

        HashSet<Integer>set = new HashSet<>();
        for(int i = 0; i <nums.length;i++){
            if ((vis & (1 << i)) == 0   && !set.contains(nums[i]))  {
                vis ^= (1 << i);
                set.add(nums[i]);
                tmp.add(nums[i]);
                permuteUnique_helper(ans,nums, tmp, vis);
                tmp.remove(tmp.size() - 1);
                vis ^= (1 << i);
            }
        }


    }

   

}
