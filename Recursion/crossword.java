
package Recursion;

public class crossword {

    public static void main(String[] args) {
       char[][] board  = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

        String[] words = { "AGRA", "NORWAY", "ENGLAND", "GWALIOR" };

        solveCrossword(board, words, 0);
    }

    public static int solveCrossword(char[][] board, String[] words, int idx) {
        if (idx == words.length) {
            print(board);
            System.out.println();
            return 1;
        }

        int cnt = 0;
        int n = board.length;
        int m = board[0].length;
        String word= words[idx];
        for(int r = 0 ; r<n;r++){
            for(int c = 0 ;c<m;c++){
                if(isPossibleToPlaceH(board, word, r, c)){
                    boolean[]arr = placeWordH(board, word, r, c);
                    cnt+=solveCrossword(board,words,idx+1);
                    unplaceWordH(board, word, r, c, arr);
                }

                if(isPossibleToPlaceV(board, word, r, c)){
                    boolean[]arr = placeWordV(board, word, r, c);
                    cnt+=solveCrossword(board,words,idx+1);
                    unplaceWordV(board, word, r, c, arr);
                }
            }

        }

        return cnt;
    }

    public static boolean isPossibleToPlaceH(char[][] board, String word, int r, int c) {
        int n = board.length;
        int m = board[0].length;

        int len = word.length();
        if (r - 1 >= 0 && board[r - 1][c] == '-')
            return false;
        if (r + len < n && board[r + len][c] == '-')
            return false;

        for (int i = 0; i < len; i++) {
            char ch = word.charAt(i);
            if (r + i >= n)
                return false;
            if (board[i + r][c] != '-' && board[i + r][c] != ch)
                return false;
        }

        return true;

    }

    public static boolean[] placeWordH(char[][] board, String word, int r, int c) {
        int n = board.length;
        int m = board[0].length;
        int len = word.length();
        boolean[]arr = new boolean[len];

        for (int i = 0; i < len; i++) {
            char ch = word.charAt(i);
            if(board[i + r][c] == '-'){
                board[i + r][c] = ch;
                arr[i] =  true;
            }
        }

        return arr;
    }

    public static void unplaceWordH(char[][] board, String word, int r, int c, boolean[]arr) {
        int n = board.length;
        int m = board[0].length;
        int len = word.length();
      

        for (int i = 0; i < len; i++) {
            char ch = word.charAt(i);
            if(arr[i]){
                board[i + r][c] = '-';
                arr[i] =  false;
            }
        }

    }

    public static boolean isPossibleToPlaceV(char[][] board,String word,int r,int c){
        int n = board.length;
        int m = board[0].length;

        int len = word.length();
        if (c - 1 >= 0 && board[r][c-1] == '-')
            return false;
        if (c + len < m && board[r][c+len] == '-')
            return false;

        for (int i = 0; i < len; i++) {
            char ch = word.charAt(i);
            if (c+i >= m)
                return false;
            if (board[r][c+i] != '-' && board[r][c+i] != ch)
                return false;
        }

        return true;

    }

    public static boolean[] placeWordV(char[][] board, String word, int r, int c) {
        int n = board.length;
        int m = board[0].length;
        int len = word.length();
        boolean[]arr = new boolean[len];

        for (int i = 0; i < len; i++) {
            char ch = word.charAt(i);
            if(board[r][c+i] == '-'){
                board[r][c+i] = ch;
                arr[i] =  true;
            }
        }

        return arr;
    }

    public static void unplaceWordV(char[][] board, String word, int r, int c,boolean[]arr) {
        int n = board.length;
        int m = board[0].length;
        int len = word.length();
      

        for (int i = 0; i < len; i++) {
            char ch = word.charAt(i);
            if(arr[i]){
                board[r][c+i] = '-';
                arr[i] =  false;
            }
        }

    }

    private static void print(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

        return;
    }

}