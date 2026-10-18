package Problems.DynamicProgramming;

import java.util.Arrays;

public class MediumDP {

    public static void main(String[] args) {

        // 5. Coin Change
        System.out.println("=== Coin Change ===");
        System.out.println(coinChange(new int[]{1, 5, 6, 9}, 11)); // 2 → (5+6)

        // 6. LIS
        System.out.println("\n=== Longest Increasing Subsequence ===");
        int[] lis = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("O(n²):     " + lisN2(lis));       // 4 → [2,3,7,101]
        System.out.println("O(n logn): " + lisNLogN(lis));    // 4

        // 7. LCS
        System.out.println("\n=== Longest Common Subsequence ===");
        System.out.println(lcs("abcde", "ace"));  // 3 → "ace"

        // 8. 0/1 Knapsack
        System.out.println("\n=== 0/1 Knapsack ===");
        int[] weights = {1, 3, 4, 5};
        int[] values  = {1, 4, 5, 7};
        System.out.println(knapsack(weights, values, 7)); // 9 → items (3+4)

        // 9. Partition Equal Subset Sum
        System.out.println("\n=== Partition Equal Subset Sum ===");
        System.out.println(canPartition(new int[]{1, 5, 11, 5})); // true  → {1,5,5} & {11}
        System.out.println(canPartition(new int[]{1, 2, 3, 5}));  // false

        // 10. Edit Distance
        System.out.println("\n=== Edit Distance ===");
        System.out.println(editDistance("horse", "ros")); // 3
    }

    // ─────────────────────────────────────────────
    // 5. COIN CHANGE
    // find minimum numbers of coin to make this value
    // dp[i] = min(dp[i], dp[i - coin] + 1)
    // O(amount × coins) time, O(amount) space
    // ─────────────────────────────────────────────
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);   // fill with "infinity"
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }


        return dp[amount] > amount ? -1 : dp[amount];
    }

    // ─────────────────────────────────────────────
    // 6a. LONGEST INCREASING SUBSEQUENCE — O(n²)
    // dp[i] = longest LIS ending at index i
    // ─────────────────────────────────────────────
    public static int lisN2(int[] nums) {
        int n = nums.length, max = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);             // every element is LIS of length 1

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // ─────────────────────────────────────────────
    // 6b. LONGEST INCREASING SUBSEQUENCE — O(n log n)
    // Uses patience sorting with binary search (tails array)
    // ─────────────────────────────────────────────
    public static int lisNLogN(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;

        for (int num : nums) {
            // Binary search: find leftmost position where tails[pos] >= num
            int lo = 0, hi = size;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (tails[mid] < num) lo = mid + 1;
                else hi = mid;
            }
            tails[lo] = num;           // replace or extend
            if (lo == size) size++;    // new longest
        }
        return size;
    }

    // ─────────────────────────────────────────────
    // 7. LONGEST COMMON SUBSEQUENCE (LCS)
    // dp[i][j] = LCS length of s1[0..i-1] and s2[0..j-1]
    // O(m×n) time, O(min(m,n)) space (single-row optimized)
    // ─────────────────────────────────────────────
    public static int lcs(String s1, String s2) {
        // Always iterate over the shorter string as columns
        if (s1.length() < s2.length()) { String tmp = s1; s1 = s2; s2 = tmp; }
        int m = s1.length(), n = s2.length();
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int prev = 0;                          // holds dp[i-1][j-1]
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[j] = prev + 1;              // match
                else
                    dp[j] = Math.max(dp[j], dp[j - 1]); // no match
                prev = temp;
            }
        }
        return dp[n];
    }

    // ────────��────────────────────────────────────
    // 8. 0/1 KNAPSACK
    // dp[w] = max value with weight capacity w
    // dp[w] = max(dp[w], value[i] + dp[w - weight[i]])
    // O(n×W) time, O(W) space (1D optimized)
    // ─────────────────────────────────────────────
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < weights.length; i++) {
            // Traverse RIGHT→LEFT to avoid using item i twice
            for (int w = capacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], values[i] + dp[w - weights[i]]);
            }
        }
        return dp[capacity];
    }

    // ─────────────────────────────────────────────
    // 9. PARTITION EQUAL SUBSET SUM
    // Can we pick a subset that sums to total/2?
    // Reduces to 0/1 knapsack subset sum
    // O(n × sum/2) time, O(sum/2) space
    // ─────────────────────────────────────────────
    public static boolean canPartition(int[] nums) {
        int total = Arrays.stream(nums).sum();
        if (total % 2 != 0) return false;   // odd total → impossible
        int target = total / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;                        // sum 0 always achievable

        for (int num : nums) {
            // RIGHT→LEFT to avoid reusing same element
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[target];
    }

    // ─────────────────────────────────────────────
    // 10. EDIT DISTANCE (Levenshtein)
    // Operations: insert, delete, replace (each costs 1)
    // dp[i][j] = min operations to convert s1[0..i] → s2[0..j]
    // O(m×n) time, O(min(m,n)) space (single-row optimized)
    // ─────────────────────────────────────────────
    public static int editDistance(String s1, String s2) {
        if (s1.length() < s2.length()) { String tmp = s1; s1 = s2; s2 = tmp; }
        int m = s1.length(), n = s2.length();
        int[] dp = new int[n + 1];

        // Base case: convert empty string → s2 prefix needs j insertions
        for (int j = 0; j <= n; j++) dp[j] = j;

        for (int i = 1; i <= m; i++) {
            int prev = dp[0];           // holds dp[i-1][j-1]
            dp[0] = i;                  // converting s1[0..i] → "" needs i deletions
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[j] = prev;                                        // no operation needed
                else
                    dp[j] = 1 + Math.min(prev, Math.min(dp[j], dp[j-1])); // replace, delete, insert
                prev = temp;
            }
        }
        return dp[n];
    }
}
