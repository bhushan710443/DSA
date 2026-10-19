package Problems.Queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueQuestions {

    public static void main(String[] args) {

        int[] arr= {1,2,4,5,55,3,5,7};
        System.out.println(findTheKthLargest(arr, 5));
        System.out.println(findTheKthSmallest(arr, 3));
    }

    //        find the kth largest
    public static int findTheKthLargest(int[] arr, int k){

        PriorityQueue<Integer> queue= new PriorityQueue<>();
        for(int a: arr){
            if(a!=0){
                queue.add(a);
            }
            if(queue.size()>k){
                queue.poll();
            }
        }
     return queue.peek();
    }

    //        find the kth smallest
    public static int findTheKthSmallest(int[] arr, int k){
        PriorityQueue<Integer> queue= new PriorityQueue<>(Comparator.reverseOrder());
        for (int a : arr){
            if(a!=0){
               queue.add(a);
            }
            if(queue.size()>k){
                queue.poll();
            }
        }
     return queue.peek();
    }


}
