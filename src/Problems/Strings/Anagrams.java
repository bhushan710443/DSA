package Problems.Strings;

import java.util.*;

class Anagrams {

    public static void main(String[] args) {
        String inputs = "cbaebabacd";
        String p = "abc";

        findAnagrams(inputs, p);
        String[] arr2 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(arr2));

        String s = "anagram", t = "nagaram";
        System.out.println(validAnagram(s,t));

    }


    public static boolean validAnagram(String s, String t) {

        if(s.length() != t.length()){
            return false;
        }

        int[] count = new int[26];

        for(int i=0; i<s.length(); i++){
            count[s.charAt(i)-'a']++;
            count[t.charAt(i)-'a']--;
        }
        for(int val: count){
            if(val!=0){
                return false;
            }
        }
        return true;
    }

    public static ArrayList<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map=new HashMap<>();

        for(String str: strs){
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key= new String(charArray);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());

    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) {
            return result;
        }

        int[] freq = new int[26];

        for (char c : p.toCharArray()) {
            freq[c - 'a']++;
        }

        int left = 0;
        int windowSize = p.length();

        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'a']--;


            // Keep the window size equal to p.length()
            if (right - left + 1 > windowSize) {
                freq[s.charAt(left) - 'a']++;
                left++;
            }

            // A zero frequency difference means an anagram
            if (right - left + 1 == windowSize && allZero(freq)) {
                result.add(left);
            }
        }

        return result;
    }

    private static boolean allZero(int[] freq) {
        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
