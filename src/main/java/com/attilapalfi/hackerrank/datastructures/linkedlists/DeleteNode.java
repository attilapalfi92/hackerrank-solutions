package com.attilapalfi.hackerrank.datastructures.linkedlists;

import java.io.BufferedWriter;
import java.io.IOException;

public class DeleteNode {

    class Result {

        /*
         * Complete the 'deleteNode' function below.
         *
         * The function is expected to return an INTEGER_SINGLY_LINKED_LIST.
         * The function accepts following parameters:
         *  1. INTEGER_SINGLY_LINKED_LIST llist
         *  2. INTEGER position
         */

        // write your code here
        public static SinglyLinkedListNode deleteNode(SinglyLinkedListNode llist, int position) {
            SinglyLinkedListNode head = llist;
            SinglyLinkedListNode slowIterator = llist;
            SinglyLinkedListNode fastIterator = llist.next;

            if (position == 0) {
                return llist.next;
            }

            for (int i = 0; i < position; i++) {

                if (i == position - 1) {
                    slowIterator.next = fastIterator.next;

                } else if (fastIterator.next != null) {
                    slowIterator = fastIterator;
                    fastIterator = fastIterator.next;
                }
            }
            return head;
        }

    }

    class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

    class SinglyLinkedListPrintHelper {
        public static void printList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
            while (node != null) {
                bufferedWriter.write(String.valueOf(node.data));

                node = node.next;

                if (node != null) {
                    bufferedWriter.write(sep);
                }
            }
        }
    }
}
