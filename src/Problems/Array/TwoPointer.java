package Problems.Array;

public class TwoPointer {

    public static void main(String[] args){

        char[] strs= {'a','a','a','b','b','c','c','c'};
        System.out.println(findCount(strs));
    }

    public static char[] findCount(char[] strs) {

        int read=0;
        int write=0;
        char[] res = new char[strs.length];

        while(read < strs.length) {
            char ch = strs[read];
            int count = 0;

            while (read < strs.length && ch == strs[read]) {
                count++;
                read++;
            }

            res[write++] = ch;

            if (count > 1) {
             for(char digit : String.valueOf(count).toCharArray()){
                 res[write++]=digit;
             }
            }
        }
        return res;
    }

}
