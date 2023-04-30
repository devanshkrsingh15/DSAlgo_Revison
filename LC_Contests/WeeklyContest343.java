import java.util.*;

public class WeeklyContest343 {
    //2660. Determine the Winner of a Bowling Game
    public int isWinner(int[] player1, int[] player2) {
        if(getSum(player1) == getSum(player2)) return 0;
        else if(getSum(player1) > getSum(player2)) return 1;
        else return 2;
        
    }
    
    public int getSum(int[]arr){
        int n = arr.length;
        int ans = 0;
        for(int i = 0 ;i<n;i++){
            if( (i-1>=0 && arr[i-1]==10) ||  (i-2>=0 && arr[i-2]==10) ){
                ans += 2*arr[i];
            }else{
                ans += arr[i];
            }
        }
        
        return ans;
    }
    //2661. First Completely Painted Row or Column
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        HashMap<Integer,Integer>map =new HashMap<>();

        for(int i = 0 ;i<n; i++){
            for(int j = 0 ;j<m ; j++){
                map.put(mat[i][j],i*m + j);
            }
        }
        int[]rows = new int[n];
        int[]cols = new int[m];

        for(int i = 0;i<arr.length;i++){
            int ele = arr[i];
            int r = map.get(ele)/m;
            int c = map.get(ele)%m;
            rows[r]++;
            cols[c]++;

            if(rows[r]==m || cols[c]==n) return i;

        }

        return -1;

    }

    //2662. Minimum Cost of a Path With Special Roads
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        HashMap<Integer,HashSet<Integer>>nodeHasAlreadyVisited = new HashMap<>();
  
        PriorityQueue<int[]>pq = new PriorityQueue<>((a,b)->{
            return a[2] - b[2];
        }); //x,y,cost
  
        pq.add(new int[]{start[0],start[1],0});
  
  
        while(pq.size()!=0){
            int s = pq.size();
            while(s-->0){
                int[]rem = pq.remove();
                int x = rem[0];
                int y = rem[1];
                int cost = rem[2];
                
                if(x==target[0] && y==target[1]) return cost;
  
                if(nodeHasAlreadyVisited.containsKey(x) && nodeHasAlreadyVisited.get(x).contains(y)) continue;
                 
                 nodeHasAlreadyVisited.putIfAbsent(x,new HashSet<>());
                 nodeHasAlreadyVisited.get(x).add(y);
  
                 //directly jumping to tar
                 pq.add(new int[]{target[0],target[1],cost + Math.abs(target[0]-x) + Math.abs(target[1]-y)});
  
                 //taking roads
                 for(int[]rd:specialRoads ){
                     int x1 = rd[0]; int y1 = rd[1];
                     int x2 = rd[2]; int y2 = rd[3];
  
                     int pathCost = rd[4];
                     int jumpCost = Math.abs(x1-x) + Math.abs(y1-y);
  
                     if(nodeHasAlreadyVisited.containsKey(x2) && nodeHasAlreadyVisited.get(x2).contains(y2))continue;
                     pq.add(new int[]{x2 , y2 , cost + pathCost + jumpCost});
                 }
  
            }
        }
  
        return -1;
      }

}
