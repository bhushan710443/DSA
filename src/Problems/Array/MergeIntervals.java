package Problems.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public static void main(String[] args){

        int[][] intervals = {
                {1, 3},
                {8, 10},
                {2, 6},
                {15, 18}
        };
        System.out.println(Arrays.deepToString(mergeIntervals(intervals)));
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
