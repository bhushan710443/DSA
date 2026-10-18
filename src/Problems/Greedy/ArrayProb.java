package Problems.Greedy;


public class ArrayProb {

    public static void main(String[] args){
//      remove k digits so that it will become the smallest number.
//      Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
        String num = "1432219";
        int k = 3;  // Output: "1219"
        System.out.println(removeKDigits(num, k));

        //number decode dp
        System.out.println( numDecodings("226"));

    }

    public static int numDecodings(String s) {

        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();

        // dp[i] = number of ways to decode the first i characters
        int[] dp = new int[n + 1];

        // Empty string has one way to decode: choose nothing.
        dp[0] = 1;

        // A valid first digit gives one decoding.
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {

            // Consider the last one digit.
            char oneDigit = s.charAt(i - 1);

            if (oneDigit >= '1' && oneDigit <= '9') {
                dp[i] = dp[i] + dp[i - 1];
            }

            // Consider the last two digits.
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] = dp[i] + dp[i - 2];
            }
        }

        return dp[n];
    }

    public static String removeKDigits(String num, int k) {

        StringBuilder stack= new StringBuilder();
        char[] chars = num.toCharArray();

        for(char digit : chars){

            while(k>0 && stack.length()>0 && stack.charAt(stack.length()-1)> digit){
                stack.deleteCharAt(stack.length() - 1);
                k--;
            }

            stack.append(digit);
        }

        // If removals remain, remove digits from the end.
        stack.setLength(stack.length() - k);

        // Remove leading zeros.
        int index = 0;
        while (index < stack.length() && stack.charAt(index) == '0') {
            index++;
        }

        String result = stack.substring(index);

        return result.isEmpty() ? "0" : result;
    }


}
