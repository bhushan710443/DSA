package Problems.ProductBaseProbs;

import java.util.Map;

public class Stri {

    public static void main(String[] args) {

        System.out.println(longestSubstringWithoutRepeatingCharacters("abcabcabcdcc"));
        System.out.println(minimumWindowSubstring("ADOBECODEBANC", "ABC"));

    }



    // "abcabcabcdcc"
    public static String longestSubstringWithoutRepeatingCharacters(String s) {

        if (s == null || s.isEmpty()) {
            return "";
        }

        Map<Character, Integer> map = new java.util.HashMap<>();
        char[] str = s.toCharArray();
        int left = 0;
        int maxLen = 0;
        int start = 0;

        for (int right = 0; right < str.length; right++) {
            char c = str[right];
            if (map.containsKey(c) && map.get(c) >= left) {
                left = map.get(c) + 1;
            }
            map.put(c, right);

            int currentLen = right - left + 1;
            if (currentLen > maxLen) {
                maxLen = currentLen;
                start = left;
            }
        }

        return s.substring(start, start + maxLen);
    }


//    s = "ADOBECODEBANC"
//    t = "ABC"
//    Output:"BANC"

    public static String minimumWindowSubstring(String s, String t) {

        char[] chars = t.toCharArray();
        Map<Character, Integer> map = new java.util.HashMap<>();

        for(char c:chars){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }


        int requiredCount = t.length();
        int left=0;
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;

        for(int right=0; right<s.length();right++) {

            char a = s.charAt(right);

            if (map.containsKey(a)) {
                if (map.get(a) > 0) {
                    requiredCount--;
                }
                map.put(a, map.get(a) - 1);
            }

            while(requiredCount == 0) {
                // Update the minimum window if needed
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minStart = left;
                }
                // Move the left pointer to try and shrink the window
                char leftChar = s.charAt(left);

                if (map.containsKey(leftChar)) {

                    map.put(leftChar, map.get(leftChar) + 1);

                    // Window is no longer valid
                    if (map.get(leftChar) > 0) {
                        requiredCount++;
                    }
                }

                left++;

            }

        }


        return s.substring(minStart, minStart + minLength);
    }



}
