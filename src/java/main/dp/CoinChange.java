package src.java.main.dp;

import java.util.Arrays;

public class CoinChange {
    int[][] result;

    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 1;
        result = new int[amount + 1][coins.length];
        Arrays.stream(result).forEach(a -> Arrays.fill(a, Integer.MAX_VALUE));
        return memoCoinChange(coins, amount, 0);
    }

    public int coinChange1D(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
        }

        for (int coin = 0; coin < coins.length; coin++) {
            for (int amt = 1; amt <= amount; amt++) {
                dp[amt] = (amt - coins[coin]) >= 0 ? Math.min(dp[amt], dp[amt - coins[coin]] + 1) : dp[amt];
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int dpCoinChange2D(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (coins.length == 0)
            return -1;
        Arrays.sort(coins);
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= amount; i++) {
            dp[0][i] = amount + 1;
        }
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 0;
        }
        for (int row = 1; row <= coins.length; row++) {
            for (int col = 1; col <= amount; col++) {
                if (col < coins[row - 1])
                    dp[row][col] = dp[row - 1][col];
                else {
                    dp[row][col] = Math.min(dp[row - 1][col], (1 + dp[row][col - coins[row - 1]]));
                }
            }
        }
        return dp[coins.length][amount] > amount ? -1 : dp[coins.length][amount];
    }

    public int recurCoinChange(int[] coins, int amount, int currentIndex) {
        if (amount == 0)
            return 0;
        if (currentIndex == coins.length && amount > 0)
            return -1;
        // else try all quantities of currentIndex coins
        int currentAmount = amount;
        int quantity = 0;
        int minCoinsNeeded = -1;
        while (currentAmount >= 0) {
            // find the total coins with each quantity
            currentAmount = amount - (quantity * (coins[currentIndex]));
            int otherCoins = recurCoinChange(coins, currentAmount, currentIndex + 1);
            if (otherCoins >= 0) {
                if (minCoinsNeeded == -1) {
                    minCoinsNeeded = (otherCoins + quantity);
                }
                minCoinsNeeded = Math.min(minCoinsNeeded, (otherCoins + quantity));
            }
            quantity++;
        }
        return minCoinsNeeded;
    }

    public int memoCoinChange(int[] coins, int amount, int currentIndex) {
        if (amount == 0)
            return 0;
        if (currentIndex == coins.length && amount > 0 || amount < 0)
            return -1;
        if (result[amount][currentIndex] != 0) {
            return result[amount][currentIndex];
        }
        // else try all quantities of currentIndex coins
        int currentAmount = amount;
        int quantity = 0;
        int minCoinsNeeded = -1;
        while (currentAmount >= 0) {
            // find the total coins with each quantity
            currentAmount = amount - (quantity * (coins[currentIndex]));
            int otherCoins = memoCoinChange(coins, currentAmount, currentIndex + 1);
            if (otherCoins >= 0) {
                if (minCoinsNeeded == -1) {
                    minCoinsNeeded = (otherCoins + quantity);
                }
                minCoinsNeeded = Math.min(minCoinsNeeded, (otherCoins + quantity));
            }
            quantity++;
        }
        result[amount][currentIndex] = minCoinsNeeded;
        return result[amount][currentIndex];
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int[] coins = {411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422};
        System.out.println(cc.coinChange(coins, 9864));
        //  coins = new int[]{2};
        // System.out.println(cc.coinChange(coins,3));
        // coins = new int[]{1};
        // System.out.println(cc.coinChange(coins,0));
    }
}
