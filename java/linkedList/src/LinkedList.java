package linkedList.src;

import java.io.*;

// Java program to implement
// a Singly Linked List
public class LinkedList {

    Node head; // head of list

    // Linked list Node.
    // This inner class is made static
    // so that main() can access it
    static class Node {

        int data;
        Node next;

        // Constructor
        Node(int d) {
            data = d;
            next = null;
        }
    }

    // Method to insert a new node
    public static LinkedList insert(LinkedList list, int data) {
        // Create a new node with given data
        Node new_node = new Node(data);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        } else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }

        // Return the list by head
        return list;
    }

    // insert into sorted linked list:
    public static LinkedList insertSorted(LinkedList ll, int val) {
        Node node = ll.head;
        if (node == null) {
            ll.head = new Node(val);// new head
        } else if (node.next == null) {
            // ie its the head node
            node.next = new Node(val);
        } else {
            while (node.next != null) {
                if (node.next.data > val) {
                    Node tmp = node.next;
                    node.next = new Node(val);
                    node.next.next = tmp;
                    break;
                }
                node = node.next; // dont miss the while loop update
            }
        }
        return ll;
    }

    // reverse linked list :
    public static void reverseLinkedList(LinkedList ll) {
        // reversing the links :
        if (ll.head == null) {
            System.out.println("nothing there to reverse");
        } else {
            Node tmp = ll.head;
            Node fast = ll.head;
            Node slow = null;
            while (tmp.next != null) {
                // 1->2->3->4
                // null<-1 2->3->4
                // 1<-2 3->4
                // 1<-2<-3 4
                // 1<-3<-3<-4

                fast = tmp.next;
                tmp.next = slow;
                slow = tmp;
                tmp = fast;
            }
            // processing the last element:
            tmp.next = slow;
            // new head
            ll.head = fast;
            printList(ll);

        }

    }

    // reverse linked list recursive :
    public static Node reverseLinkedListRecrusive(Node node) {
        Node head;
        if (node.next == null) {
            head = node;
            // we cannot return head directly since we need to have a
            // return statement at the end and it will change the return value that
            // we wanted to capture after recursion finishes.
        } else {
            head = reverseLinkedListRecrusive(node.next);
            // 1 > 2 >3 >4
            // 1 2 n <3 <4t
            // 1 n 2 <3t <4
            // n 1< 2t <3 <4
            Node tmp = node.next;
            node.next = null;
            tmp.next = node;
        }
        return head;
    }

    // Method to print the LinkedList.
    public static void printList(LinkedList list) {
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
        LinkedList list = new LinkedList();

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

        // Print the LinkedList
        printList(list);

        // will print after reversing the list:
        reverseLinkedList(list);
        // new head's data :
        System.out.println(list.head.data);

        list.head = reverseLinkedListRecrusive(list.head);
        printList(list);

        // insert element into sorted sigly linked list:
        list = insertSorted(list, 3);
        printList(list);
    }
}