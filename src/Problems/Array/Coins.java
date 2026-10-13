package Problems.Array;

import java.util.Arrays;

public class Coins {

    public static void main(String[] args){
        int[] coins = {1, 2, 5};
        System.out.println(findCoinsOutput(coins, 11));
        System.out.println(findCoinsOutputDP(coins, 11));

    }


    public static int findCoinsOutput(int[] coins, int amount) {
        int count=0;
        for(int i=coins.length-1;i>=0;i--){
            while(amount>=coins[i]){
                amount=amount-coins[i];
                  count++;
            }
        }
        return count;
    }

    public static int findCoinsOutputDP(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // First, initialize all amounts to an impossible value
        Arrays.fill(dp, amount + 1);
        // Then set the base case
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1
                    );
                }
            }
        }
        // Return -1 if the amount cannot be made
        return dp[amount] > amount ? -1 : dp[amount];
    }





}