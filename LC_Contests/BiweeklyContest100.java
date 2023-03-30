import java.util.*;

public class BiweeklyContest100 {

    //2591. Distribute Money to Maximum Children
    public int distMoney(int money, int children) {
        if(money<children) return -1;
        money-=children;
        int ans = 0;

        while(money>=7 && children>0){
            money-=7;
            children--;
            ans++;
        }

        if(money==3 && children==1) ans--;
        if(money!=0 && children==0) ans--;

        return ans;
    }

    public int distMoney_(int money, int children) {
        if(money<children) return -1;
        int[]arr = new int[children];
        Arrays.fill(arr,1);
        money -= children;
        int cnt = 0;
        int lastIdx = -1;
        for(int i = 0;i<children;i++){
            if(money>=7){
                money-=7;
                arr[i] += 7;
            }else if(money==0){
                break;
            }
            else{
                if(money==3){
                    arr[i]+= 2;
                    money -= 2;
                }else{
                    arr[i] += money;
                    money = 0;
                }
            }
        }
        
        int ans = 0;
        for(int ele : arr){
            ans += ele==8 ? 1 : 0;
        }
        
        if(money!=0) ans--;
        return ans;
    }


    //2592. Maximize Greatness of an Array
}
