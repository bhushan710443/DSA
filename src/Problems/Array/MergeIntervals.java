package Problems.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    public static void main(String[] args){

        int[][] intervals = {
                {1, 3},
                {8, 10},
                {2, 6},
                {15, 18}
        };
        int[][] intervals1 = {
                {1, 4},
                {8, 13},
                {2, 6},
                {15, 18}
        };
        System.out.println(Arrays.deepToString(mergeIntervals(intervals)));

        System.out.println(Arrays.deepToString(mergeIntervalssssss(intervals1)));

        //Insert Interval
        int[][] intervals2 = {
                {1, 3},
                {8, 12},
                {5, 6},
                {15, 18}
        };
        int[] array={11,13};
        System.out.println(Arrays.deepToString(insertinterval(intervals2, array)));

    }



    public static int[][] insertinterval(int[][] intervals, int[] arr){

        List<int[]> res= new ArrayList<>();
        Arrays.sort(intervals,(a,b)-> Integer.compare(a[0],b[0]));
        int i=0;
        int n=intervals.length;

        //add starting intervals
        while(i<n && intervals[i][1] > arr[0]){
           res.add(intervals[i]);
           i++;
        }

        //add overlapping intervals
        while(i<n && intervals[i][0] < arr[1]){
            arr[0]=Math.min(arr[0], intervals[i][0]);
            arr[1]=Math.min(arr[1], intervals[i][1]);
            i++;
        }
        res.add(arr);

        while(i<n){
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }




    public static int[][] mergeIntervalssssss(int[][] arr){

        Arrays.sort(arr, (a,b)-> Integer.compare(a[0], b[0]) );
        List<int[]> result = new ArrayList<>();

        for(int[] a : arr){
            if(result.isEmpty() || result.get(result.size()-1)[1] < a[0]){
                 result.add(a);
            }
            else {
                result.get(result.size()-1)[1] =    Math.max( result.get(result.size()-1)[1], a[1]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }



    public static int[][] mergeIntervals(int[][] intervals){

        // Step 1: Sort intervals by starting value
        Arrays.sort(intervals, (a,b)-> Integer.compare(a[0],b[0]));
        List<int[]> result = new ArrayList<>();

        // Step 2: Process each interval
        for(int[] inter : intervals){

            // If result is empty OR no overlap exists
         if(result.isEmpty() || result.get(result.size()-1)[1] < inter[0]){
           result.add(inter);
         }
         else {
             // Overlap exists, so merge intervals
             result.get(result.size() - 1)[1] = Math.max(result.get(result.size()-1)[1], inter[1]);
         }
        }
        return result.toArray(new int[result.size()][]);
    }

}
