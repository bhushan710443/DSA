package Problems.Strings;

public class PalindromeProb {

    public static void main(String[] args) {
        String str = "afbcbfd";
        System.out.println(findSubStringPalindrom(str));
    }

        public static String findSubStringPalindrom(String s) {


            int start = 0;
            int maxLength = 1;

            for (int i = 0; i < s.length(); i++) {

                // Check palindrome with an odd number of characters
                int len1 = expand(s, i, i);

                // Check palindrome with an even number of characters
                int len2 = expand(s, i, i + 1);

                int len = Math.max(len1, len2);

                // Update the longest palindrome found so far
                if (len > maxLength) {
                    maxLength = len;
                    start = i - (len - 1) / 2;
                }
            }

            return s.substring(start, start + maxLength);
        }

        public static int expand(String s, int left, int right) {

            while (left >= 0 && right < s.length() &&
                    s.charAt(left) == s.charAt(right)) {

                left--;
                right++;
            }

            // Return the length of the palindrome
            return right - left - 1;
        }
}
