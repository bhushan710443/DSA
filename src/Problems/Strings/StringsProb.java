package Problems.Strings;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringsProb {

    public static void main(String[] args) {
        String str2 = "aaaaabbbbbccdddeffffffff";
        String str1 = "abcabcdef";
//        System.out.println(longestCommonSubsequence(str2));
        System.out.println(longestSubstringWithoutRepeatingCharacters(str1));
        System.out.println(countFrequencyOfChar(str2));
        System.out.println(countFrequencyOfCharByMap(str2));
        System.out.println(maximumOccurringCharacter(str2));
    }

    public static int longestSubstringWithoutRepeatingCharacters(String str){
        int maxCount = 0;
        int left = 0;
        Set<Character> ch = new HashSet<>();

        for (int right = 0; right < str.length(); right++){
            while (ch.contains(str.charAt(right))){
                ch.remove(str.charAt(left));
                left++;
            }
            ch.add(str.charAt(right));
            maxCount = Math.max(right - left + 1, maxCount);
        }

        return maxCount;
    }

//    public static StringBuilder longestCommonSubsequence(String str){
//        int count =0;
//        StringBuilder result = new StringBuilder();
//
//        for (int i = 0; i < str.length(); i++) {
//            // check next character
//            if (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
//                count++;
//            } else {
//                result.append(count).append(str.charAt(i));
//                count = 1;
//            }
//        }
//        return result;
//    }


    //count Frequency of character in a string
    public static String countFrequencyOfChar(String str){
        int count =0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            // check next character
            if (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                result.append(count).append(str.charAt(i));
                count = 1;
            }
        }
        return result.toString();
    }


    //count Frequency of character in a string
    public static String countFrequencyOfCharByMap(String str){

        Map<Character, Long> count =
                str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()));

        return count.toString();
    }

    // Maximum Occurring Character
    public static Character maximumOccurringCharacter(String str){

        Map<Character, Integer> map= new HashMap<>();

        for(Character ch : str.toCharArray()){
            map.put(ch, map.getOrDefault(ch,0)+1);
        }
        
        int count=0;
        Character cha = null;
        
        for (Character ch : map.keySet()){
            if(map.get(ch) > count){
                cha=ch;
                count=map.get(ch);
            }
        }
        return cha;
    }



}
