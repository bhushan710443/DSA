package Problems.Strings;

public class SlidingWindowInString {

    public static void main(String[] args) {

        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(minimumWindowSubstring(s,t));
    }

     public static String minimumWindowSubstring(String s, String t) {

            int[] freq = new int[128];

            for (char c : t.toCharArray()) {
                freq[c]++;
            }

            int left = 0;
            int required = t.length();
            int minLength = Integer.MAX_VALUE;
            int start = 0;

            for (int right = 0; right < s.length(); right++) {
                char c = s.charAt(right);

                if (freq[c] > 0) {
                    required--;
                }
                freq[c]--;

                while (required == 0) {
                    int length = right - left + 1;

                    if (length < minLength) {
                        minLength = length;
                        start = left;
                    }

                    char leftChar = s.charAt(left);
                    freq[leftChar]++;

                    if (freq[leftChar] > 0) {
                        required++;
                    }

                    left++;
                }
            }

            return minLength == Integer.MAX_VALUE
                    ? ""
                    : s.substring(start, start + minLength);
        }
}
