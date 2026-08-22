package Problems.ProductBaseProbs;

public class SlidingWindow {

    public static void main(String[] args) {

        String s="AABCDSAACF";
        int[] nums={2,3,1,2,4,2};
        int[] nums1={1,3,-1,-3,5,3,6,7};
        System.out.println("Longest repeating character replacement: " + longestRepeatingCharacterReplacement(s, 2));
        System.out.println("Minimum size subarray sum: " + minimumSizeSubarraySum(nums, 7));
        System.out.println("Sliding window maximum sum " + slidingWindowMaximum(nums1, 3));
    }

    public static int  longestRepeatingCharacterReplacement(String s, int k) {

                int[] count = new int[26];

                int left = 0;
                int maxFreq = 0;
                int result = 0;

                for (int right = 0; right < s.length(); right++) {
                    // Add current character to the window
                    count[s.charAt(right) - 'A']++;
                    // Find the highest frequency character
                    maxFreq = Math.max(maxFreq, count[s.charAt(right) - 'A']);

                    // If replacements needed > k,
                    // shrink the window
                    while ((right - left + 1) - maxFreq > k) {
                        count[s.charAt(left) - 'A']--;
                        left++;
                    }

                    // Store the maximum valid window length
                    result = Math.max(result, right - left + 1);
                }

                return result;
            }

//          int[] nums=[1,3,-1,-3,5,3,6,7];

            public static int slidingWindowMaximum(int[] nums, int k) {

                int left = 0;
                int sum = 0;
                int maxSum = Integer.MIN_VALUE;

                for (int right = 0; right < nums.length; right++) {
                    sum += nums[right];

                    if (right - left + 1 == k) {
                        maxSum = Math.max(maxSum, sum);
                        sum -= nums[left];
                        left++;
                    }
                }

                return maxSum;
            }



            //          ;
            public static int minimumSizeSubarraySum(int[] nums, int target) {

                int left = 0;
                int minSize = Integer.MAX_VALUE;
                int sum = 0;

                for (int right = 0; right < nums.length; right++) {
                    sum += nums[right];

                    while (sum >= target) {
                        minSize = Math.min(minSize, right - left + 1);
                        sum -= nums[left];
                        left++;
                    }
                }

                return minSize == Integer.MAX_VALUE ? 0 : minSize;
            }

}
