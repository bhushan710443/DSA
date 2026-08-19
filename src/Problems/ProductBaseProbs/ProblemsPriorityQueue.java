package Problems.ProductBaseProbs;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ProblemsPriorityQueue {


    public static void main(String[] args){
        int[] arr = {2, 2, 3, 4, 2, 1, 2, 3, 5};
        System.out.println(findKthLargest(arr, 3));
        findKthLargest(arr);
        topKFrequentElements(arr, 2);
        int[][] points = {{1, 2}, {3, 4}, {2, -1}};
        int[][] closestPoints = kClosestPointsToOrigin(points, 3);
        for (int[] point : closestPoints) {
            System.out.println("[" + point[0] + ", " + point[1] + "]");
        }
    }

    public static int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int num : nums) {
            pq.offer(num);

            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek() != null ? pq.peek() : -1;
    }

    public static void findKthLargest(int[] nums) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(50);
        maxHeap.add(10);
        maxHeap.add(30);
        maxHeap.add(20);

        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }

    public static void topKFrequentElements(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }


        PriorityQueue<Integer> pq = new PriorityQueue<>(java.util.Comparator.comparingInt(map::get));

        for(int num : map.keySet()) {
            pq.offer(num);

            if(pq.size() > k) {
                pq.poll();
            }
        }

        while (!pq.isEmpty()) {
            System.out.println("Hi"+pq.poll());
        }

    }

    public static int[][] kClosestPointsToOrigin(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> 
            (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
        );

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] result = new int[k][2];
        int index = 0;
        while (!maxHeap.isEmpty()) {
            result[index++] = maxHeap.poll();
        }
        return result;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            current.next = minNode;
            current = current.next;

            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }

        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }



}
