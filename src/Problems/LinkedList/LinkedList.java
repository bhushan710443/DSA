package Problems.LinkedList;

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






}

