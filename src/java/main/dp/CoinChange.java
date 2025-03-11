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

    public int dpCoinChange(int[] coins, int amount) {
        int minCoins = -1;
        if (amount == 0)
            return 0;
        result = new int[coins.length][amount + 1];
        for (int coinIndex = 0; coinIndex < coins.length; coinIndex++) {
            System.out.println("coin\n");
            for (int amountIndex = 0; amountIndex <= amount; amountIndex++) {
                if (amountIndex == 0) {
                    result[coinIndex][amountIndex] = 0;
                } else if (amountIndex - coins[coinIndex] < 0) {
                    result[coinIndex][amountIndex] = coinIndex == 0 ? -1 : result[coinIndex - 1][amountIndex];
                } else {
                    if (result[coinIndex][amountIndex - coins[coinIndex]] == -1) {
                        result[coinIndex][amountIndex] = coinIndex == 0 ? -1 : result[coinIndex - 1][amountIndex];
                    } else {
                        result[coinIndex][amountIndex] = 1 + result[coinIndex][amountIndex - coins[coinIndex]];
                    }
                }
            }
            System.out.println(result[coinIndex][amount] + "\t");
            if (result[coinIndex][amount] > 0)
                minCoins = minCoins < 0 ? result[coinIndex][amount] : Math.min(minCoins, result[coinIndex][amount]);
        }
        return minCoins;
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
