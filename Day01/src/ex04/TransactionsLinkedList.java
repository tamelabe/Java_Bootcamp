package src.ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Node first;
    private Node last;
    private int size;

    TransactionsLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public void printTransactions() {
        System.out.println("------Transactions-------");
        if (size != 0) {
            for (Node iter = first; iter != null; iter = iter.next) {
                iter.data.printInfo();
            }
        }
        System.out.println("----Transactions END-----");
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new TransactionNotFoundException("Null pointer detected");
        }
        Node newNode = new Node(null, null, transaction);
        if (size == 0) {
            first = last = newNode;
        } else {
            newNode.prev = last;
            last = last.next = newNode;
        }
        ++size;
    }

    @Override
    public void removeById(UUID id) {
        if (id == null) {
            throw new TransactionNotFoundException("Null pointer detected");
        }
        Node prevNode = null;
        for (Node iter = first; iter != null; iter = iter.next) {
            if (iter.data == null) {
                throw new TransactionNotFoundException("Null pointer detected");
            }
            if  (iter.data.getIdentifier() == id) {
                removeNode(iter);
                return;
            }
            prevNode = iter;
        }
        throw new TransactionNotFoundException("Transaction not found");
    }

    private void removeNode(Node current) {
        if (current == null) { return; }
        if (size == 1) {
            first = last = null;
        } else if (current.prev == null) {
            first = first.next;
        } else {
            current.prev.next = current.next;
        }
        --size;
    }

    @Override
    public Transaction[] toArray() {
        if (size == 0) { return null; }
        Transaction[] arrayTransaction = new Transaction[size];
        int i = 0;
        for (Node iter = first; iter != null; iter = iter.next, ++i) {
            arrayTransaction[i] = iter.data;
        }
        return arrayTransaction;
    }

    public int getSize() { return size; }

    class Node {
        Node(Node otherPrev, Node otherNext, Transaction otherData) {
            data = otherData;
            next = otherNext;
            prev = otherPrev;
        }
        public Transaction data;
        public Node prev;
        public Node next;
    }
}