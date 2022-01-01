package doublyLinkedList.src;

import java.io.*;

// Java program to implement
// a Singly Linked List
public class DoubleLinkedList {

    Node head; // head of list
    Node tail; // tail of doubly linked list
    // Linked list Node.
    // This inner class is made static
    // so that main() can access it

    static class Node {

        int data;
        Node next;
        Node prev;

        // Constructor
        Node(int d) {
            data = d;
            next = null;
            prev = null;
        }
    }

    // Method to insert a new node
    public static DoubleLinkedList insert(DoubleLinkedList list, int data) {
        // Create a new node with given data
        Node new_node = new Node(data);

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
            list.tail = new_node;
        } else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
            new_node.prev = last;
            list.tail = new_node;
        }

        // Return the list by head
        return list;
    }

    // insert into sorted linked list:
    public static DoubleLinkedList insertSorted(DoubleLinkedList ll, int val) {
        Node node = ll.head;
        if (node == null) {
            ll.head = ll.tail = new Node(val);// new head
        } else if (node.next == null) {
            // ie its the head node
            node.next = new Node(val);
            ll.tail = node.next;
        } else {
            while (node.next != null) {
                if (node.next.data > val) {
                    break;
                }
                node = node.next; // dont miss the while loop update
            }
            // insert val in between node and node.next:
            // GOTCHA : If the node is the last node of the linkedlist then node.next.next
            // does not exist...
            Node tmp = node.next;
            // adding the new node with double links:
            node.next = new Node(val);
            node.next.prev = node;
            // adding remaining nodes :
            node.next.next = tmp;
            if (node.next.next != null) {
                node.next.next.prev = node.next;
            } else {
                // we just added the element at the end so new tail:
                ll.tail = node.next;
            }
        }
        return ll;
    }

    // reverse linked list :
    public static void reverseLinkedList(DoubleLinkedList ll) {
        // reversing the links :
        if (ll.head == null) {
            System.out.println("nothing there to reverse");
        } else {
            Node tmp = ll.head;
            Node fast = ll.head;
            Node slow = null;
            while (tmp.next != null) {
                // 1<->2<->3<->4->null
                // null<-1<-2<->3<->4->null
                // null<-1<->2<-3<->4->null
                // null<-1<->2<->3<-4
                // null<-1<->3<->3<->4

                fast = tmp.next;
                tmp.prev = tmp.next;
                tmp.next = slow;
                slow = tmp;
                tmp = fast;
            }
            // processing the last element:
            tmp.next = slow;
            slow.prev = tmp;
            // new head
            ll.head = fast;
            printList(ll);

        }

    }

    // delete node
    public static DoubleLinkedList deleteNode(DoubleLinkedList dll, int val) {
        if (dll.head == null) {
            System.out.println("List was already empty");

        } else if (dll.head.data == val) {
            Node tmp = dll.head.next;
            dll.head.next = null;
            dll.head.prev = null;
            // new head :
            dll.head = tmp;
            dll.head.prev = null;
        } else {
            Node node = dll.head;
            while (node.next != null) {
                if (node.data == val) {
                    break;
                }
                node = node.next;
            }
            // node will now have the address of the node which we want to delete:
            if (node.next != null) {
                Node prevNode = node.prev;
                prevNode.next = prevNode.next.next;
                prevNode.next.prev = prevNode;
                node.prev = null;
                node.next = null;
            } else {
                // we are at the end node:
                Node prevNode = node.prev;
                prevNode.next = null;
                node.prev = null;
            }
        }
        return dll;
    }

    // reverse linked list recursive :
    // static class Pair {
    //     Node head;
    //     Node tail;
    // }

    private static Node reverseNodeLinks(Node node) {
        // DoubleLinkedList.Pair newHT = new DoubleLinkedList.Pair();

        Node head = null;
        if(node.next == null){
            // it is the last node which will become the new head:
            head = node;
        }
        // dll's old head is stored in head variable as there is no conidition to identify the head.

        if (node != null && node.next != null) {
            head = reverseNodeLinks(node.next);
            // reversing the links of the two nodes:
            Node tmpNode = node.next;
            node.next = null;
            tmpNode.next = node;
            tmpNode.prev = null;
            node.prev = tmpNode;
        }
        return head;
    }

    public static DoubleLinkedList reverseRecursive(DoubleLinkedList dll) {

        // we expect the recursive call will give us the reversed sub list:
        Node newHead = reverseNodeLinks(dll.head);
        dll.tail = dll.head;
        dll.head = newHead;
        return dll;
    }

    // Method to print the LinkedList.
    public static void printList(DoubleLinkedList list) {
        Node currNode = list.head;

        System.out.print("LinkedList: ");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.data + " ");

            // Go to next node
            currNode = currNode.next;
        }
        System.out.println();
    }

    // Driver code
    public static void main(String[] args) {
        /* Start with the empty list. */
        DoubleLinkedList list = new DoubleLinkedList();

        //
        // ******INSERTION******
        //

        // Insert the values
        list = insertSorted(list, 1);
        list = insert(list, 2);
        list = insert(list, 3);
        list = insert(list, 4);
        list = insert(list, 5);
        list = insert(list, 6);
        list = insert(list, 7);
        list = insert(list, 8);
        list = insertSorted(list, 9);

        // Print the DoublyLinkedList
        printList(list);

        // insert element into sorted doubly linked list:
        list = insertSorted(list, 3);
        printList(list);

        reverseLinkedList(list);

        list = deleteNode(list, 1);
        printList(list);
        list = deleteNode(list, 7);
        printList(list);
        reverseLinkedList(list);
        list = deleteNode(list, 8);
        printList(list);
        // will print after reversing the list:

        // new head's data :
        System.out.println(list.head.data);

        list = reverseRecursive(list);
        printList(list);

    }
}