package Problems.LinkedList;

public class DoublyLinkedList {
    Node head;
    Node tail;

    static class Node {
        int value;
        Node next;
        Node prev;

        public Node(int value) {
            this.value = value;
        }
    }

    // Add node at the end
    public void addLast(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = tail = node;
        } else {

            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    // Add node at the beginning
    public void addFirst(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    // Remove first occurrence of value
    public void remove(int value) {
        Node curr = head;
        while (curr != null) {
            if (curr.value == value) {
                if (curr.prev != null) curr.prev.next = curr.next;
                else head = curr.next;
                if (curr.next != null) curr.next.prev = curr.prev;
                else tail = curr.prev;
                return;
            }
            curr = curr.next;
        }
    }

    // Print list forward
    public void printForward() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.value + (curr.next != null ? " <-> " : "\n"));
            curr = curr.next;
        }
    }

    // Print list backward
    public void printBackward() {
        Node curr = tail;
        while (curr != null) {
            System.out.print(curr.value + (curr.prev != null ? " <-> " : "\n"));
            curr = curr.prev;
        }
    }

    // Reverse the doubly linked list in place
    public void reverse() {
        Node curr = head;
        Node temp = null;
        while (curr != null) {
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev;
        }
        // Swap head and tail
        if (temp != null) {
            head = temp.prev;
            tail = temp.next == null ? temp : temp.next;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.addLast(1);
        dll.addLast(2);
        dll.addLast(3);
        dll.addFirst(0);
        System.out.println("Forward:");
        dll.printForward();
        System.out.println("Backward:");
        dll.printBackward();
        dll.remove(2);
        System.out.println("After removing 2:");
        dll.printForward();
        System.out.println("Reversed:");
        dll.reverse();
        dll.printForward();
    }
}
