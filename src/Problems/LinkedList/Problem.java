package Problems.LinkedList;

public class Problem {

    public static void main(String[] args) {

        // First number: 2 -> 4 -> 3 (represents 342)
        ListNode newNode1 = new ListNode(3);
        ListNode newNode2 = new ListNode(4, newNode1);
        ListNode newNode3 = new ListNode(2, newNode2);

        // Second number: 5 -> 6 -> 4 (represents 465)
        ListNode newNode4 = new ListNode(4);
        ListNode newNode5 = new ListNode(6, newNode4);
        ListNode newNode6 = new ListNode(5, newNode5);

        System.out.print("First number: ");
        printList(newNode3);

        System.out.print("Second number: ");
        printList(newNode6);

        ListNode result = addNumbers(newNode3, newNode6);

        System.out.print("Result: ");
        printList(result);
    }

    public static ListNode addNumbers(ListNode node1, ListNode node2) {

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        int carry = 0;

        while (node1 != null || node2 != null || carry != 0) {

            int a = (node1 != null) ? node1.value : 0;
            int b = (node2 != null) ? node2.value : 0;

            int sum = a + b + carry;

            int digit = sum % 10;
            carry = sum / 10;

            current.next = new ListNode(digit);
            current = current.next;

            if (node1 != null) {
                node1 = node1.next;
            }

            if (node2 != null) {
                node2 = node2.next;
            }
        }

        return dummy.next;
    }


    static class ListNode {

        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void printList(ListNode node) {

        while (node != null) {
            System.out.print(node.value);

            if (node.next != null) {
                System.out.print(" -> ");
            }

            node = node.next;
        }

        System.out.println();
    }

}