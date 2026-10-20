package Problems.Strings;

public class PalindromeProb {

    public static void main(String[] args) {
        String str = "afbcbfd";
        System.out.println(findSubStringPalindrom(str));

        String str1 = "AACECAAAA";
        System.out.println(minimumCharactersToAddToMakeStringPalindrome(str1));
    }

    private static int minimumCharactersToAddToMakeStringPalindrome(String str) {


        char[] ch =str.toCharArray();

        for(int i=str.length();i>=0;i--){

            boolean isPalindrome=true;
            int left=0;
            int right=i-1;

            while (left<right) {
                if (ch[left] != ch[right]) {
                    isPalindrome = false;
                    break;
                }
                left++;
                right--;
            }

            if(isPalindrome){
                return str.length() - i;
            }
        }
        return str.length()-1;
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
