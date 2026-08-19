package Problems.Array;

public class Ladder {
    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        System.out.println(basicLadderProb(10));
    }

//    Basic Ladder Problem
//
//Problem:
//You are at step 0 and can jump either 1 or 2 steps at a time. Find the number of ways to reach step n.

    public  static  int basicLadderProb(int n){

        int prev=1;
        int curr=2;
        for(int i=2; i<n; i++){
            int temp=curr;
            curr=prev+curr;
            prev=temp;
        }
        return curr;
    }

}
