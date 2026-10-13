package Problems.Array;

import java.util.Arrays;

public class MoveZEROS {

    public static void main(String[] args){

        int[] list= {1,0,4,6,0,8,0,10};
        int[] list1= {1,0,4,6,0,8,0,10};

        System.out.println(Arrays.toString(moveZEROSInFront(list)));
        System.out.println(Arrays.toString(moveZEROSInLast(list1)));

    }

    //move zeros in front and make array reverse
    // make {0,0,0,10,8,6,4,1}
    public static int[] moveZEROSInFront(int[] list){
        Arrays.sort(list);
        System.out.println(Arrays.toString(list));
        int left=0;
        int right= list.length-1;
        while(left<right){
            if(list[left]==0){
                left++;
            }else {
                int temp=list[left];
                list[left]=list[right];
                list[right]=temp;
                left++;
                right--;
            }
        }
        return list;
    }

    //move zeros in last
    //      {1,0,4,6,0,8,0,10};
    // make {1,4,6,8,10,0,0,0}
    public static int[] moveZEROSInLast(int[] list){


        int left=0;
        for(int right=0; right< list.length; right++) {
            if(list[right]!=0){
                int temp = list[left];
                list[left] = list[right];
                list[right] = temp;
                left++;
            }
        }
        return list;
    }

}
