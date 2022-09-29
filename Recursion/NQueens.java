package Recursion;
import java.util.*;

public class NQueens {
    //51. N-Queens
        //52. N-Queens II
    int row  = 0;
    int col = 0;
    int dia=  0;
    int rdia=  0;
    public List<List<String>> solveNQueens(int n) {
        List<List<String>>ans = new ArrayList<>();
        char[][]board = new char[n][n];
        for(char[]arr:board)Arrays.fill(arr,'.');
        solveNQueens_(board,0,n,ans);
        return ans;

    }

    private int solveNQueens_(char[][] board, int r,int n, List<List<String>> ans) {
        if(n==0){
            buildAns(board,ans);
            return 1;
        }

        int cnt = 0;

        for(int c = 0;c<board.length;c++){
            if(isSafe(board,r,c)){
                toggle(board,r,c);
                board[r][c]='Q';
                cnt += solveNQueens_(board,r+1,n-1,ans);
                board[r][c]='.';
                toggle(board,r,c);
            }
        }


        return cnt;



    }

    private void toggle(char[][] board, int r, int c) {
        int n = board.length;
        row^=(1<<r);
        col^=(1<<c);
        dia^=(1<<(r+c));
        rdia^=(1<<(r-c+n));
    }

    private boolean isSafe(char[][] board, int r, int c) {
        int n = board.length;
        return ((row &(1<<r))==0) && ((col &(1<<c))==0)  && ((dia &(1<<(r+c)))==0)  && ((rdia &(1<<(r-c+n)))==0) ;
    }

    private void buildAns(char[][] board, List<List<String>> ans) {
        List<String>tmp = new ArrayList<>();
        for(int i = 0; i<board.length;i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0;j<board[0].length;j++){
                sb.append(board[i][j]);
            }
            tmp.add(sb.toString());
        }
        ans.add(tmp);
    }

    

}
