import java.util.*;

public class WeeklyContest317 {
    // 2455. Average Value of Even Numbers That Are Divisible by Three
    public int averageValue(int[] nums) {
        double tot = 0;
        double cnt = 0;

        for (int ele : nums) {
            if (ele % 6 == 0) {
                tot += ele;
                cnt++;
            }
        }

        return (int) Math.floor(tot / cnt);
    }

    // 2456. Most Popular Video Creator
    class Pair {
        long v;
        String i;

        Pair(String i, long v) {
            this.i = i;
            this.v = v;
        }
    }

    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        List<List<String>> ans = new ArrayList<>();
        int n = creators.length;
        HashMap<String, ArrayList<Pair>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.putIfAbsent(creators[i], new ArrayList<>());
            map.get(creators[i]).add(new Pair(ids[i], views[i]));
        }

        long omax = 0;
        for (String c : map.keySet()) {
            long mySum = 0;
            ArrayList<Pair> list = map.get(c);
            Collections.sort(list, (a, b) -> {
                if (a.v == b.v)
                    return a.i.compareTo(b.i);
                return (int)b.v - (int)a.v;
            });
            String highest = list.get(0).i;
            for (Pair rp : list) {
                mySum += rp.v;
            }

            omax=Math.max(omax, mySum);
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(c);
            tmp.add(highest);
            tmp.add(mySum + "");
            ans.add(tmp);

        }
        List<List<String>> fans = new ArrayList<>();
        for(List<String>t :ans){
            long sum = Long.parseLong(t.get(2));
            if(sum==omax){
                t.remove(t.size()-1);
                fans.add(t);
            }
        }

        return fans;

    }

    //2457. Minimum Addition to Make Integer Beautiful
    
    public long[] countSumOfDig(long n){
        long cnt = 0;
        long sum =0;

        while(n!=0){
            cnt++;
            sum = n%10;
            n = n/10;
        }

        return new long[]{cnt,sum};
    }

    public long makeIntegerBeautiful(long n, int target) {
        long[]arr = countSumOfDig(n);
        if(arr[1]<=target) return 0l;

        
    }
}

