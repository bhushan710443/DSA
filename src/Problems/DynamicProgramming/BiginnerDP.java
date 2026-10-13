package Problems.DynamicProgramming;

import java.util.HashMap;

public class BiginnerDP {

    public static void main(String[] args) {
        // 1. Fibonacci
        System.out.println("=== Fibonacci ===");
        System.out.println("Opt   F(10): " + fibOptimized(10));     // 55

        // 2. Climbing Stairs
        System.out.println("\n=== Climbing Stairs ===");
        System.out.println("Ways to climb 5 stairs: " + climbStairs(5)); // 8

        // 3. House Robber
        System.out.println("\n=== House Robber ===");
        int[] houses = {2, 7, 9, 3, 1, 5};
        System.out.println("Max rob: " + houseRobber(houses)); // 12 (2+9+1)

        // 4. Maximum Subarray (Kadane's)
        System.out.println("\n=== Maximum Subarray ===");
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Max subarray sum: " + maxSubarray(nums)); // 6 → [4,-1,2,1]
    }

    // ─────────────────────────────────────────────
    // 1. FIBONACCI NUMBER
    // ─────────────────────────────────────────────

//    Space Optimized — O(n) time, O(1) space
    public static long fibOptimized(int n) {
        if (n <= 1) return n;
        long prev2 = 0, prev1 = 1;
        long curr = 0;
        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;  // F(n) = F(n-1) + F(n-2)
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
    }

    // ─────────────────────────────────────────────
    // 2. CLIMBING STAIRS
    // dp[n] = dp[n-1] + dp[n-2]  — same recurrence as Fibonacci
    // O(n) time, O(1) space
    // ─────────────────────────────────────────────
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;   // ways(n) = ways(n-1) + ways(n-2)
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    // ─────────────────────────────────────────────
    // 3. HOUSE ROBBER
    // dp[i] = max(dp[i-1], dp[i-2] + nums[i])
    // O(n) time, O(1) space
    // int[] houses = {2, 7, 9, 3, 1};
    // ─────────────────────────────────────────────
    public static int houseRobber(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int curr = Math.max(prev1, prev2 + nums[i]);  // rob or skip
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    // ─────────────────────────────────────────────
    // 4. MAXIMUM SUBARRAY — Kadane's Algorithm
    // dp[i] = max(nums[i], dp[i-1] + nums[i])
    // O(n) time, O(1) space
    // ─────────────────────────────────────────────
    public static int maxSubarray(int[] nums) {
        int maxSum = nums[0];
        int curr   = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr   = Math.max(nums[i], curr + nums[i]); // extend or restart
            maxSum = Math.max(maxSum, curr);
        }
        return maxSum;
    }
}
