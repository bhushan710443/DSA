package Problems.DynamicProgramming;

import java.util.*;

public class AdvanceDP {

    public static void main(String[] args) {

        // 11. Matrix Chain Multiplication
        System.out.println("=== Matrix Chain Multiplication ===");
        int[] dims = {10, 30, 5, 60};   // A(10×30), B(30×5), C(5×60)
        System.out.println("Min multiplications: " + matrixChain(dims)); // 4500

        // 12. Palindrome Partitioning
        System.out.println("\n=== Palindrome Partitioning ===");
        System.out.println("Min cuts 'aab':   " + minCutPalindrome("aab"));    // 1 → "aa|b"
        System.out.println("Min cuts 'abcba': " + minCutPalindrome("abcba"));  // 0 → whole is palindrome
        System.out.println("Min cuts 'abcde': " + minCutPalindrome("abcde"));  // 4

        // 13. Burst Balloons
        System.out.println("\n=== Burst Balloons ===");
        System.out.println("Max coins: " + maxCoins(new int[]{3, 1, 5, 8})); // 167

        // 14. Regular Expression Matching
        System.out.println("\n=== Regular Expression Matching ===");
        System.out.println(regexMatch("aa",          "a*"));        // true
        System.out.println(regexMatch("ab",          ".*"));        // true
        System.out.println(regexMatch("aab",         "c*a*b"));     // true
        System.out.println(regexMatch("mississippi", "mis*is*p*.")); // false

        // 15. Word Break
        System.out.println("\n=== Word Break ===");
        System.out.println(wordBreak("leetcode",      new HashSet<>(Arrays.asList("leet", "code"))));          // true
        System.out.println(wordBreak("applepenapple", new HashSet<>(Arrays.asList("apple", "pen"))));          // true
        System.out.println(wordBreak("catsandog",     new HashSet<>(Arrays.asList("cats","dog","sand","and","cat")))); // false
    }

    // ─────────────────────────────────────────────
    // 11. MATRIX CHAIN MULTIPLICATION
    // dims[i-1] × dims[i] = dimensions of matrix i (1-indexed)
    // dp[i][j] = min scalar multiplications to compute matrices i..j
    // For each split k:  dp[i][j] = min(dp[i][k] + dp[k+1][j] + dims[i-1]*dims[k]*dims[j])
    // O(n³) time  |  O(n²) space
    // ─────────────────────────────────────────────
    public static int matrixChain(int[] dims) {
        int n = dims.length - 1;        // number of matrices
        int[][] dp = new int[n + 1][n + 1];

        for (int len = 2; len <= n; len++) {            // chain length
            for (int i = 1; i <= n - len + 1; i++) {   // start of chain
                int j = i + len - 1;                    // end of chain
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {           // split point
                    int cost = dp[i][k] + dp[k + 1][j]
                             + dims[i - 1] * dims[k] * dims[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[1][n];
    }

    // ─────────────────────────────────────────────
    // 12. PALINDROME PARTITIONING — Minimum Cuts
    // isPalin[i][j] = true if s[i..j] is a palindrome
    // dp[i]         = min cuts for s[0..i]
    // dp[i] = 0                if s[0..i] is already a palindrome
    //       = min(dp[j-1] + 1) for all j where s[j..i] is a palindrome
    // O(n²) time  |  O(n²) space
    // ─────────────────────────────────────────────
    public static int minCutPalindrome(String s) {
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];

        // Precompute palindrome table bottom-up by length
        for (int i = 0; i < n; i++) isPalin[i][i] = true;  // single chars
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j))
                    isPalin[i][j] = (len == 2) || isPalin[i + 1][j - 1];
            }
        }

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (isPalin[0][i]) {
                dp[i] = 0;                   // entire prefix is a palindrome
            } else {
                dp[i] = Integer.MAX_VALUE;
                for (int j = 1; j <= i; j++) {
                    if (isPalin[j][i])       // s[j..i] is palindrome → cut before j
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    // ─────────────────────────────────────────────
    // 13. BURST BALLOONS
    // Pad array with 1s at both ends as virtual boundaries.
    // Key insight: treat k as the LAST balloon burst in open interval (i, j)
    // dp[i][j] = max coins from bursting all balloons strictly between i and j
    // dp[i][j] = max over k of: balloons[i]*balloons[k]*balloons[j] + dp[i][k] + dp[k][j]
    // O(n³) time  |  O(n²) space
    // ─────────────────────────────────────────────
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] b = new int[n + 2];          // padded array
        b[0] = b[n + 1] = 1;
        for (int i = 0; i < n; i++) b[i + 1] = nums[i];
        int N = n + 2;

        int[][] dp = new int[N][N];

        for (int len = 2; len < N; len++) {            // window size (exclusive endpoints)
            for (int i = 0; i < N - len; i++) {
                int j = i + len;
                for (int k = i + 1; k < j; k++) {     // k = last to burst
                    int coins = b[i] * b[k] * b[j] + dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], coins);
                }
            }
        }
        return dp[0][N - 1];
    }

    // ─────────────────────────────────────────────
    // 14. REGULAR EXPRESSION MATCHING
    // '.' → matches any single character
    // '*' → zero or more of the preceding element
    // dp[i][j] = s[0..i-1] matches p[0..j-1]
    // '*' case:  zero occurrences → dp[j] = dp[j-2]
    //            one or more      → dp[j] |= prev_dp[j]  (if chars match)
    // O(m×n) time  |  O(n) space (two-row rolling)
    // ─────────────────────────────────────────────
    public static boolean regexMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        // Empty string can match patterns like a*, a*b*, a*b*c*
        for (int j = 2; j <= n; j++)
            if (p.charAt(j - 1) == '*') dp[j] = dp[j - 2];

        for (int i = 1; i <= m; i++) {
            boolean[] curr = new boolean[n + 1];
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                if (pc == '*') {
                    curr[j] = curr[j - 2];   // zero occurrences of preceding char
                    char pre = p.charAt(j - 2);
                    if (pre == '.' || pre == s.charAt(i - 1))
                        curr[j] = curr[j] || dp[j]; // one or more occurrences
                } else if (pc == '.' || pc == s.charAt(i - 1)) {
                    curr[j] = dp[j - 1];     // single char match
                }
            }
            dp = curr;
        }
        return dp[n];
    }

    // ────────────────���────────────────────────────
    // 15. WORD BREAK
    // dp[i] = true if s[0..i-1] can be fully segmented using dict
    // Only look back at most maxWordLen characters (avoids O(n²) inner loop)
    // O(n × maxWordLen) time  |  O(n) space
    // ─────────────────────────────────────────────
    public static boolean wordBreak(String s, Set<String> dict) {
        int n = s.length();
        int maxLen = dict.stream().mapToInt(String::length).max().orElse(0);
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;                         // empty prefix always valid

        for (int i = 1; i <= n; i++) {
            for (int j = Math.max(0, i - maxLen); j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
