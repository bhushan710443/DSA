package Problems.LinkedList;
import java.util.PriorityQueue;

public class LinkedList {

    public static void main(String[] args) {
        // Reverse
        Node head = buildList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(head);
        Node reversed = reverseLinkedList(head);
        System.out.print("Reversed: ");
        printList(reversed);

        // Middle node (use a fresh list — reverseLinkedList mutates the original)
        Node list2 = buildList(new int[]{1, 2, 3, 4, 5});
        Node middleNode = findMiddleNode(list2);
        System.out.print("Middle: ");
        System.out.println(middleNode.val);

        // Merge two sorted lists (use fresh lists)
        Node sortedA = buildList(new int[]{1, 3, 5});
        Node sortedB = buildList(new int[]{2, 4, 6, 7, 8});
        Node merged = mergeTwoSortedLinkedList(sortedA, sortedB);
        System.out.print("Merged: ");
        printList(merged);

        // Merge two sorted lists (use fresh lists)
        Node sortedC = buildList(new int[]{1, 3, 6, 5, 6, 3, 1});
        boolean A = checkIfLinkedListIsPalindrome(sortedC);
        System.out.print("Is palindrome: ");
        System.out.println(A);

        // Remove the Nth Node from the End of a Linked List
        Node nodes = buildList(new int[]{1, 3, 6, 5, 6, 3, 1});
        Node s=RemoveNthNodeFromEndOfLinkedList(nodes,4);
        System.out.println("Remove the Nth Node from the End of a Linked List");
        printList(s);

        // Remove the Nth Node from the End of a Linked List
        Node num1 = buildList(new int[]{2, 4, 3});
        Node num2 = buildList(new int[]{5, 6, 4});
        Node answer=addTwoNumbers(num1 , num2);
        System.out.println("Add two numbers");
        printList(answer);

        // Find the Intersection of Two Linked Lists
        Node first = buildList(new int[]{4, 1, 8, 4, 5});
        Node sec = buildList(new int[]{5, 6, 1, 8, 4, 5});
        Node intersection = getIntersectionNode(first, sec);
        System.out.println("Find the Intersection");
        printList(intersection);

        // reverse Nodes In KGroup
        Node reverse = buildList(new int[]{1,2,3,4,5,6,7,8});
        Node reversedByK = reverseNodesInKGroup(reverse, 3);
        System.out.println("Reverse Nodes In K Group");
        printList(reversedByK);


        // Merge K Sorted Linked Lists
        Node[] lists = new Node[3];
        lists[0] = buildList(new int[]{1, 4, 5});
        lists[1] = buildList(new int[]{1, 3, 4});
        lists[2] = buildList(new int[]{2, 6});
        Node finalNode = mergeKSortedLinkedLists(lists);
        System.out.println("Merge K Sorted Linked Lists");
        printList(finalNode);
    }

    // Node definition
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
        Node(){}
    }

    // Helper: build list from array
    public static Node buildList(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new Node(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    // Helper: print list
    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? " -> " : ""));
            curr = curr.next;
        }
        System.out.println();
    }


    // Reverse a linked list iteratively (Two Pointers)
    public static Node reverseLinkedList(Node head) {
        // Example: 1 -> 2 -> 3 -> 4 -> 5
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next; // save next
            curr.next = prev;      // reverse link
            prev = curr;           // move prev forward
            curr = next;           // move curr forward
        }

        return prev; // new head
    }

    // find a middle node
    public static Node findMiddleNode(Node head) {
        // Example: 1 -> 2 -> 3 -> 4 -> 5
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null && fast.next.next!=null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }


    public static Node mergeTwoSortedLinkedList(Node head, Node head2) {
        // Example: 1->3->5  +  2->4->6->7->8  =>  1->2->3->4->5->6->7->8
        Node dummy = new Node(); // placeholder head
        Node curr = dummy;

        while (head != null && head2 != null) {
            if (head.val <= head2.val) {  // <= handles equal values too
                curr.next = head;
                head = head.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next; // advance the builder pointer
        }

        // Append whichever list still has remaining nodes.
        // No loop needed! Each node already has its .next pointer intact,
        // so assigning curr.next = head (or head2) links the ENTIRE remaining
        // chain at once. e.g. if head = 5->null, curr.next = head attaches 5->null in one step.
        if (head != null) curr.next = head;
        if (head2 != null) curr.next = head2;

        return dummy.next; // skip the empty dummy node
    }


    public static boolean checkIfLinkedListIsPalindrome(Node head) {
        if (head == null || head.next == null) return true;
        Node middle = findMiddleNode(head);
        Node secondHalfStart = reverseLinkedList(middle.next);
        Node firstHalf = head;
        Node secondHalf = secondHalfStart;
        boolean isPalindrome = true;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                isPalindrome = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return isPalindrome;
    }


//    Remove the Nth Node from the End of a Linked List
    public static Node RemoveNthNodeFromEndOfLinkedList(Node head, int n) {

        Node fast=head;
        Node prev=head;

        // Create a gap of n + 1 nodes
        for(int i=0; i <=n; i++){
            fast=fast.next;
        }

        // Move both pointers together
        while(fast!=null){
            fast=fast.next;
            prev=prev.next;
        }

        // Remove the target node
          prev.next=prev.next.next;

        return head;
    }

    //    Add two numbers
    public static Node addTwoNumbers(Node head, Node head2) {

        Node dummy= new Node();
        Node answer= dummy;
        int carry=0;

        while(head!=null ||  head2!=null){

            int digit = carry ;

            if(head!=null){
                digit = digit + head.val;
                head=head.next;
            }
            if(head2!=null){
                digit = digit + head2.val;
                head2=head2.next;
            }
               carry = digit/10;
               digit = digit%10;

            answer.next=new Node(digit);
            answer=answer.next;
        }

        return dummy.next;
    }


    public static Node getIntersectionNode(Node headA, Node headB) {

        Node a = headA;
        Node b = headB;

        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }

        return a;
    }

//    Reverse Nodes in K-Group
//    {1,2,3,4,5,6,7,8});
    public static Node reverseNodesInKGroup(Node head, int k){

        Node dummy = new Node();
        dummy.next = head;
        Node groupPrev = dummy;

        while(head!=null){

            // Find the kth node in the current group
            Node kth = groupPrev;

            // find kth group
            for(int i=0; i<k && kth != null; i++){
                kth = kth.next;
            }

            // Fewer than k nodes remain
            if (kth == null) {
                break;
            }

            Node groupNext = kth.next;
            Node prev = groupNext;
            Node curr = groupPrev.next;
            // reverse the group
            while(curr != groupNext){
                Node next= curr.next;
                curr.next= prev;
                prev=curr;
                curr=next;
            }
            // Connect the reversed group
            Node temp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = temp;
        }
        return dummy.next;
    }



//   Merge K Sorted Linked Lists
    public static Node mergeKSortedLinkedLists(Node[] nodes) {

        PriorityQueue<Node> pq= new PriorityQueue<>(
                (a,b)-> Integer.compare(a.val, b.val)
        );

        // Add the head of every non-empty list
         for(Node node : nodes){
                  if(node!=null){
                      pq.offer(node);
                  }
         }

        Node dummy = new Node();
        Node tail = dummy;

        while(!pq.isEmpty()){
            Node smallest = pq.poll();
            // Save the next node before linking
             Node next = smallest.next;

             tail.next = smallest;
             tail = tail.next;

            if (next != null) {
                pq.offer(next);
            }
         }
        tail.next = null;

        return dummy.next;
    }
}

