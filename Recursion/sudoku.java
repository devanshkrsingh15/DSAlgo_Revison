package Recursion;

import java.util.*;

public class sudoku {
    int N = 9;
    boolean[][] row = new boolean[N][10];
    boolean[][] col = new boolean[N][10];
    boolean[][][] mat = new boolean[N][N][10];

    int ROW[] = new int[N];
    int COL[] = new int[N];
    int MAT[][] = new int[3][3];

    public void set(char[][] board, int val, int i, int j) {
        board[i][j] = (char) (val + '0');
        row[i][val] = true;
        col[j][val] = true;

        int r = (i / 3) * 3;
        int c = (j / 3) * 3;
        mat[r][c][val] = true;
    }

    public void unset(char[][] board, int val, int i, int j) {
        board[i][j] = '.';
        row[i][val] = false;
        col[j][val] = false;
        int r = (i / 3) * 3;
        int c = (j / 3) * 3;
        mat[r][c][val] = false;

    }

    public boolean isSafe(char[][] board, int n, int r, int c, int val) {
        if (row[r][val] || col[c][val])
            return false;

        int rsi = (r / 3) * 3;
        int rci = (c / 3) * 3;
        if (mat[rsi][rci][val])
            return false;

        return true;
    }

    public void set_bits(char[][] board, int val, int i, int j) {
        board[i][j] = (char) (val + '0');
        ROW[i] ^= (1 << val);
        COL[j] ^= (1 << val);
        MAT[i / 3][j / 3] ^= (1 << val);
    }

    public void unset_bits(char[][] board, int val, int i, int j) {
        board[i][j] = '.';
        ROW[i] ^= (1 << val);
        COL[j] ^= (1 << val);
        MAT[i / 3][j / 3] ^= (1 << val);
    }

    public boolean isSafe_bits(char[][] board, int n, int r, int c, int val) {
        int mask = (1 << val);
        return ((ROW[r] & mask) == 0) && ((COL[c] & mask) == 0) && ((MAT[r / 3][c / 3] & mask) == 0);
    }

    public void solveSudoku(char[][] board) {

        ArrayList<Integer> emptyIndices = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    int idx = i * 9 + j;
                    emptyIndices.add(idx);
                } else {
                    set(board, board[i][j] - '0', i, j);
                }
            }
        }
        solveSudokuHelper(board, 0, emptyIndices);

    }

    private boolean solveSudokuHelper(char[][] board, int idx, ArrayList<Integer> emptyIndices) {

        if (idx == emptyIndices.size()) {
            return true;
        }

        int ei = emptyIndices.get(idx);
        int r = ei / 9;
        int c = ei % 9;

        for (int val = 1; val <= 9; val++) {
            if (isSafe(board, 9, r, c, val)) {
                set(board, val, r, c);
                boolean res = solveSudokuHelper(board, idx + 1, emptyIndices);
                if (res)
                    return true;
                unset(board, val, r, c);
            }

        }

        return false;
    }

    public boolean isValidSudoku(char[][] board) {
        int ROW[] = new int[N];
        int COL[] = new int[N];
        int MAT[][] = new int[3][3];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = board[i][j] - '0';
                if(val>=0  && val<=9){
                    if(isSafe_bits(board,9,i,j,val)){
                        ROW[i] ^= (1 << val);
                        COL[j] ^= (1 << val);
                        MAT[i / 3][j / 3] ^= (1 << val);
                    }else{
                        return false;
                    }
                   
                }
               

            }
        }

        return true;
        

    }

}
