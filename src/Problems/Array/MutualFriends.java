package Problems.Array;

import java.util.*;

public class MutualFriends {

    public static void main(String[] args){
        int[] arr = {1,1,1,3,2,3,4,5,6,7};
        int[] arr1 = {1,2,3,9,8};
        mutualFriend(arr, arr1);
    }

    public static void mutualFriend(int[]arr, int[] arr1 ){

        Set<Integer> set=new HashSet<>();
        List<Integer> mutual=new ArrayList<>();
        for(int a : arr){
            set.add(a);
        }

        for(int b: arr1) {
            if (set.contains(b)){
                mutual.add(b);
                System.out.print( b + ",");
            }

        }

    }

}
