import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleinHistogram {
    public int largestRectangleArea(int[] heights) {
        int[] leftLim = NSOL(heights);
        int[] rightLim = NSOR(heights);

        int max = 0;
        int n = heights.length;

        for (int i = 0; i < n; i++) {
            int width = rightLim[i] - leftLim[i] - 1;
            int ht = heights[i];

            max = Math.max(max, width * ht);
        }

        return max;
    }

    public int[] NSOR(int[] arr) {
        int n = arr.length;
        int[] nsor = new int[n];
        Arrays.fill(nsor, n);

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (st.size() != 0 && arr[i] < arr[st.peek()]) {
                nsor[st.pop()] = i;
            }

            st.push(i);
        }

        return nsor;
    }

    public int[] NSOL(int[] arr) {
        int n = arr.length;
        int[] nsol = new int[n];
        Arrays.fill(nsol, -1);

        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[i] < arr[st.peek()]) {
                nsol[st.pop()] = i;
            }

            st.push(i);
        }

        return nsol;
    }

    public int largestRectangleArea_opti(int[] hts) {
        int n = hts.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int max = 0;

        for (int i = 0; i < n; i++) {

            while (st.peek() != -1 && hts[st.peek()] > hts[i]) {
                int idx = st.pop();
                int ht = hts[idx];
                int wd = i - st.peek() - 1;
                max = Math.max(max, wd * ht);
            }
            st.push(i);
        }

        while (st.peek() != -1) {
            int idx = st.pop();
            int ht = hts[idx];
            int wd = n - st.peek() - 1;
            max = Math.max(max, wd * ht);
        }

        return max;
    }

    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int max = 0;

        int[] arr = new int[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                arr[j] = (matrix[i][j] == '0') ? 0 : arr[j] + 1;

            }
            max = Math.max(max, largestRectangleArea_opti(arr));

        }

        return max;
    }

    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int max = 0;

        int[] arr = new int[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                arr[j] = (matrix[i][j] == '0') ? 0 : arr[j] + 1;

            }
            max = Math.max(max, largestRectangleArea_optiForSqaure(arr));

        }

        return max;
    }

    private int largestRectangleArea_optiForSqaure(int[] hts) {
        int n = hts.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int max = 0;

        for (int i = 0; i < n; i++) {

            while (st.peek() != -1 && hts[st.peek()] > hts[i]) {
                int idx = st.pop();
                int ht = hts[idx];
                int wd = i - st.peek() - 1;
                int area = Math.min(ht, wd) * Math.min(ht, wd);
                max = Math.max(max, area);
            }
            st.push(i);
        }

        while (st.peek() != -1) {
            int idx = st.pop();
            int ht = hts[idx];
            int wd = n - st.peek() - 1;
            int area = Math.min(ht, wd) * Math.min(ht, wd);
            max = Math.max(max, area);
        }

        return max;

    }

    public void PRINT(int[][] arr) {
        for (int[] d : arr) {
            for (int e : d)
                System.out.print(e + " ");
            System.out.println();
        }
    }

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] grid = new int[n][n];
        for (int[] g : grid)
            Arrays.fill(g, 1);

        if (mines.length == n * n)
            return 0;

        for (int[] m : mines) {
            int x = m[0];
            int y = m[1];
            grid[x][y] = 0;
        }

        int[][] leftSum = generateLeftSum(grid);
        // PRINT(leftSum);
        // System.out.println();

        int[][] rightSum = generateRightSum(grid);
        // PRINT(rightSum);
        // System.out.println();
        int[][] UpSum = generateUpSum(grid);

        // PRINT(UpSum);
        // System.out.println();
        int[][] DownSum = generateDownSum(grid);

        // PRINT(DownSum);
        // System.out.println();
        int max = 1;

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {

                int min = getMin(UpSum[i - 1][j], DownSum[i + 1][j], leftSum[i][j - 1], rightSum[i][j + 1]);

                if (min > 0 && grid[i][j] == 1) {
                    max = Math.max(max, min + 1);
                }
            }
        }

        return max;

    }

    private int getMin(int... arr) {
        int min = (int) 1e9;
        for (int e : arr)
            min = Math.min(min, e);

        return min;
    }

    private int[][] generateUpSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] sum = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    sum[i][j] = grid[i][j];
                } else {

                    if (grid[i][j] == 0) {
                        sum[i][j] = 0;
                    } else {
                        sum[i][j] += sum[i - 1][j] + grid[i][j];
                    }
                }
            }
        }

        return sum;
    }

    private int[][] generateDownSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] sum = new int[n][m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (i == n - 1) {
                    sum[i][j] = grid[i][j];
                } else {

                    if (grid[i][j] == 0) {
                        sum[i][j] = 0;
                    } else {
                        sum[i][j] += sum[i + 1][j] + grid[i][j];
                    }
                }
            }
        }

        return sum;
    }

    private int[][] generateLeftSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] sum = new int[n][m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (j == 0) {
                    sum[i][j] = grid[i][j];
                } else {

                    if (grid[i][j] == 0) {
                        sum[i][j] = 0;
                    } else {
                        sum[i][j] += sum[i][j - 1] + grid[i][j];
                    }
                }
            }
        }

        return sum;
    }

    private int[][] generateRightSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] sum = new int[n][m];
        for (int j = m - 1; j >= 0; j--) {
            for (int i = 0; i < n; i++) {
                if (j == m - 1) {
                    sum[i][j] = grid[i][j];
                } else {

                    if (grid[i][j] == 0) {
                        sum[i][j] = 0;
                    } else {
                        sum[i][j] += sum[i][j + 1] + grid[i][j];
                    }
                }
            }
        }

        return sum;
    }

    // 402. Remove K Digits
    public String removeKdigits(String num, int k) {
        int n = num.length();

        Stack<Character> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);

            while (k > 0 && st.size() != 0 && st.peek() > ch) {
                k--;
                st.pop();
            }
            st.push(ch);
        }
        while (k-- > 0)
            st.pop();
        StringBuilder sb = new StringBuilder();
        while (st.size() != 0) {
            sb.append(st.pop());
        }

        sb.reverse();
        String ans = sb.toString();
        int i = 0;
        while (i < ans.length() && ans.charAt(i) == '0')
            i++;

        if (i == ans.length())
            return "0";
        return ans.substring(i);
    }
}
