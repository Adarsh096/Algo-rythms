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

            // adding remaining nodes :
            node.next.next = tmp;

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

    // remove Kth element from the end:
    public static LinkedList removeKth(LinkedList ll, int k) {
        Node slow = ll.head;
        Node fast = ll.head;

        // moving the fast pointer ahead:
        while (--k > 0) {
            fast = fast.next;
        }
        // checking if we are deleting the head node:
        if (fast.next == null) {
            ll.head = slow.next;// head will be the next element from the start
        } else {
            // slow will point to the node before the node to be deleted:
            while (fast.next.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        // removing the node, altering slow pointer will also alter the original linked
        // list:
        slow.next = slow.next.next;
        return ll;
    }

    // merge two sorted linked list :
    public static LinkedList mergeSorted(LinkedList l1, LinkedList l2) {
        LinkedList result = new LinkedList();
        Node node1 = l1.head;
        Node node2 = l2.head;
        // looping till one list is finished:
        while (node1 != null && node2 != null) {
            if (node1.data > node2.data) {
                insert(result, node2.data);
                node2 = node2.next;
            } else {
                insert(result, node1.data);
                node1 = node1.next;
            }
        }
        // now at least one of the list is over:
        if (node1 == null) {
            while (node2 != null) {
                insert(result, node2.data);
                node2 = node2.next;
            }
        } else {
            while (node1 != null) {
                insert(result, node1.data);
                node1 = node1.next;
            }
        }
        return result;
    }

    // to swap pair of nodes in linked list :
    /*
    // NOTE : DOES NOT WORK AS EXPECTED
    public static LinkedList swapPair(LinkedList ll) {
        Node node = ll.head;
        // Gotcha : after swapping the head will also get altered
        ll.head = node.next;
        LinkedList result = new LinkedList();
        result.head = node.next;
        // swap the values :
        // n1-> 2-> 3t-> 4
        // n1<->2 3t->4
        // 2->n1->3t->4
        // 2n -> 1 ->3t->4

        while (node != null && node.next != null && node.next.next != null) {
            Node tmp = node.next.next;
            Node newHead = node.next;
            node.next.next = node;
            node.next = tmp;
            //Reset what we call as node to later move to next of next
            node = newHead;
            node = newHead.next.next;
            System.out.println("swapping the values : ");
            printList(ll);
        }
        // swapping the last two nodes:
        if (node!=null && node.next != null) {
            node.next.next = node;
            node.next = null;
            System.out.println("swapping the last two values : ");
            printList(ll);
        }
        
        return ll;
    }
    */


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

        // insert element into sorted singly linked list:
        list = insertSorted(list, 3);
        list = insertSorted(list, 99);
        printList(list);
        removeKth(list, 10);
        printList(list);

        // create and merge two list :
        LinkedList list2 = new LinkedList();

        // Insert the values
        list2 = insert(list2, 1);
        list2 = insert(list2, 2);
        list2 = insert(list2, 3);
        list2 = insert(list2, 4);
        list2 = insert(list2, 5);
        list2 = insert(list2, 6);
        list2 = insert(list2, 7);
        list2 = insert(list2, 8);

        LinkedList res = mergeSorted(list, list2);
        printList(res);

        // System.out.println("Before swapping : ");
        // printList(list2);
        // swapPair(list2);
        // System.out.println("After pair swap : ");
        // printList(list2);

    }
}