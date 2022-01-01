package linkedListSumNumber.src;

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

    public static LinkedList sumNumbers(LinkedList ll1, LinkedList ll2) {
        // start adding numbers from head.
        // If one list is finished then assume the values to be zero and complete the
        // sum
        Node node1 = ll1.head;
        Node node2 = ll2.head;

        int carry = 0;
        LinkedList sum = new LinkedList();
        while (node1 != null && node2 != null) { // Gotcha : node1.next!=null will ignore the last node.
            // sum logic
            int val = node1.data + node2.data + carry;
            // update the carry:
            if (val > 9) {
                carry = val - 9;
            }
            insert(sum, val - carry);
            System.out.println(
                    "Adding " + node1.data + " and " + node2.data + ". sum : " + val + ", carry is : " + carry);
            // update :
            node1 = node1.next;
            node2 = node2.next;
        }
        // after this atleast one list will be empty:
        while (node1 != null) {
            if (node1.data > 9) {
                carry = node1.data - 9;
            }
            insert(sum, node1.data - carry);
            System.out.println("Adding " + node1.data + " and 0, carry is : " + carry);
            node1 = node1.next;
        }
        while (node2 != null) {
            if (node2.data > 9) {
                carry = node2.data - 9;
            }
            insert(sum, node2.data - carry);
            System.out.println("Adding " + node2.data + " and 0, carry is : " + carry);
            node2 = node2.next;
        }
        // adding the carry at the end 
        if(carry>0){
            insert(sum,carry);
        }
        return sum;
    }

    // Driver code
    public static void main(String[] args) {
        /* Start with the empty list. */
        LinkedList list = new LinkedList();
        LinkedList list2 = new LinkedList();

        //
        // ******INSERTION******
        //

        // Insert the values
        list = insert(list, 1);
        list = insert(list, 2);
        list = insert(list, 3);
        list = insert(list, 4);
        list2 = insert(list2, 5);
        list2 = insert(list2, 6);
        list2 = insert(list2, 7);
        list2 = insert(list2, 8);

        // Print the LinkedList
        printList(list);
        printList(list2);

        // sum the numbers
        LinkedList result = sumNumbers(list, list2);
        printList(result);

    }
}