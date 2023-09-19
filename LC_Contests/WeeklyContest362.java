import java.util.*;

public class WeeklyContest362 {

    // 2848. Points That Intersect With Cars
    public int numberOfPoints(List<List<Integer>> nums) {
        HashSet<Integer> set = new HashSet<>();
        for (List<Integer> l : nums) {
            for (int i = l.get(0); i <= l.get(1); i++) {
                set.add(i);
            }
        }

        return set.size();
    }

    // 2849. Determine if a Cell Is Reachable at a Given Time
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy)
            return t != 1;
        long xdis = (long) Math.abs((long) sx - (long) fx);
        long ydis = (long) Math.abs((long) sy - (long) fy);
        long totDis = Math.min(xdis, ydis) + Math.abs(xdis - ydis);

        return totDis <= t;

    }
}
