package DP;

public class BuyAndSellStocks {
    /*
     * dp[i][k][x]
     * i -> day number - 0 to n-1
     * k -> number of transactions - 0 to K
     * x -> state ie i have a stock and i have no stock ie 0 or 1
     * 
     * at the end of ith day after kth transaction with no stock what is the maximum
     * profit
     * dp[i][k][0] = MAX(dp[i-1][k][0], dp[i-1][k][1] + cost[i]) // MAX(rest,sell)
     * 
     * at the end of ith day after kth transaction with 1 stock what is the maximum
     * profit
     * dp[i][k][1] = MAX(dp[i-1][k][1], dp[i-1][k-1][0] - cost[i]) // MAX(rest,buy)
     * 
     * each time we buy a stock transactions is increased
     * 
     * Base cases -
     * 1) dp[-1][k][0] = 0 //before opening of stock market
     * 2) dp[-1][k][1] = -(int)1e9 // not possible //before opening of stock market
     * 3) dp[i][0][0] = 0
     * 4) dp[i][0][1] = -(int)1e9 // not possible
     * 
     */

    // 121. Best Time to Buy and Sell Stock I - Single transactions
    public int BuyAndSellStock_I(int[] prices) {

        int max_with_noStock = 0;
        int max_with_oneStock = -(int) 1e9;

        for (int ele : prices) {

            max_with_noStock = Math.max(max_with_noStock, max_with_oneStock + ele);
            max_with_oneStock = Math.max(max_with_oneStock, 0 - ele);
            // dp[i][k][1] = Math.max(dp[i-1][k][1],dp[i-1][k-1][0] -ele)
            // here k == 1 -> therefore dp[i-1][0][0] = 0

        }

        return max_with_noStock;
    }

    // 122. Best Time to Buy and Sell Stock II - Infinite transactions
    // k does not matter -> what matter is previous state
    public int BuyAndSellStock_II(int[] prices) {
        int max_with_noStock = 0;
        int max_with_oneStock = -(int) 1e9;

        for (int ele : prices) {
            int prv_noStockState = max_with_noStock;

            max_with_noStock = Math.max(prv_noStockState, max_with_oneStock + ele);
            max_with_oneStock = Math.max(max_with_oneStock, prv_noStockState - ele);
        }

        return max_with_noStock;
    }

    // 714. Best Time to Buy and Sell Stock with Transaction Fee
    // Infinte trransactions with fee
    public int BuyAndSellStock_Fees(int[] prices, int fee) {
        int max_with_noStock = 0;
        int max_with_oneStock = -(int) 1e9;

        for (int ele : prices) {
            int prv_noStockState = max_with_noStock;

            max_with_noStock = Math.max(prv_noStockState, max_with_oneStock + ele);
            max_with_oneStock = Math.max(max_with_oneStock, prv_noStockState - ele - fee);
        }

        return max_with_noStock;

    }

    // 309. Best Time to Buy and Sell Stock with Cooldown
    // Infinte trransactions with cooldown - we need to rest for one day, for
    // consecutive transactions
    // just maintain previous 2 zero_stock_state value
    public int BuyAndSellStock_CoolDown(int[] prices) {
        int max_with_noStock = 0;
        int max_with_oneStock = -(int) 1e9;
        int prv_Two_with_noStock = 0;

        for (int ele : prices) {
            int prv_noStockState = max_with_noStock;

            max_with_noStock = Math.max(prv_noStockState, max_with_oneStock + ele);
            max_with_oneStock = Math.max(max_with_oneStock, prv_Two_with_noStock - ele);
            prv_Two_with_noStock = prv_noStockState;
        }

        return max_with_noStock;
    }

    // 123. Best Time to Buy and Sell Stock III
    // At most 2 transactions -> K - [0,2]
    public int BuyAndSellStock_TwoTransactions(int[] prices) {
        int ti10 = 0;
        int ti20 = 0; // No stock state
        int ti11 = -(int) 1e9;
        int ti21 = -(int) 1e9; // One stock state

        for (int p : prices) {
            int o_ti10 = ti10;
            int o_ti20 = ti20;
            int o_ti11 = ti11;
            int o_ti21 = ti21;

            ti10 = Math.max(o_ti10, o_ti11 + p);
            ti11 = Math.max(o_ti11, 0 - p);

            ti20 = Math.max(o_ti20, o_ti21 + p);
            ti21 = Math.max(o_ti21, o_ti10 - p);
        }

        return ti20;
    }

}
