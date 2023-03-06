import java.util.*;

public class WeeklyContest335 {
    //2582. Pass the Pillow
    public int passThePillow(int n, int time) {
        if(n>time) return time+1;
        int pos = 1;
        int mul = 1;
        
        while(time-->0){
            pos = pos + 1*mul;
            if(pos==n+1){
                pos = n-1;
                mul = -1;
            }
            if(pos==0){
                pos = 2;
                mul = 1;
            }
        }

        return pos;

        
    }
}
