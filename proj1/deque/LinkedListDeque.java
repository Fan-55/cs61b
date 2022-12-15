package deque;

// invariants:
// 1. Sentinel.next points to the first node in the list
// 2. Sentinel.prev points to the last node in the list
// 3. last node.next points to the sentinel

public class LinkedListDeque<T> {
    private static class Node<T> {
        Node<T> prev;
        T item;
        Node<T> next;

        public Node(Node<T> prev, T item, Node<T> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private int size = 0;
    private Node<T> sentinel;

    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
    }


    public void addFirst(T item) {
        if (size <= 0) {
            Node<T> newNode = new Node<>(sentinel, item, sentinel);
            sentinel.next = newNode;
            sentinel.prev = newNode;
        } else {
            Node<T> newNode = new Node<>(sentinel, item, sentinel.next);
            sentinel.next.prev = newNode;
            sentinel.next = newNode;
        }
        size++;
    }

    public void addLast(T item) {
        if (size <= 0) {
            Node<T> newNode = new Node<>(sentinel, item, sentinel);
            sentinel.next = newNode;
            sentinel.prev = newNode;
        } else {
            Node<T> newNode = new Node<>(sentinel.prev, item, sentinel);
            sentinel.prev.next = newNode;
            sentinel.prev = newNode;
        }
        size++;
    }

    public T removeLast() {
        if (size > 0) {
            Node<T> lastNode = sentinel.prev;
            lastNode.prev.next = sentinel;
            sentinel.prev = lastNode.prev;
            lastNode.prev = null;
            lastNode.next = null;
            size--;
            return lastNode.item;
        }
        return null;
    }

    public T removeFirst() {
        if (size > 0) {
            Node<T> firstNode = sentinel.next;
            firstNode.next.prev = sentinel;
            sentinel.next = firstNode.next;
            firstNode.prev = null;
            firstNode.next = null;
            size--;
            return firstNode.item;
        }
        return null;
    }

    public T get(int index) {
        if (index >= 0 && index < size) {
            int currentIndex = 0;
            Node<T> p = sentinel.next;
            while (currentIndex < index) {
                p = p.next;
                currentIndex++;
            }
            return p.item;
        }
        return null;
    }

    public T getRecursive(int index) {
        if (index >= 0 && index < size) {
            return getRecursiveHelper(sentinel, index).item;
        }
        return null;
    }

    private Node<T> getRecursiveHelper(Node<T> startNode, int index) {
        if (index == 0) {
            return startNode.next;
        }
        Node<T> newStartNode = startNode.next;
        index--;
        return getRecursiveHelper(newStartNode, index);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public void printDeque() {
        Node<T> p = sentinel.next;
        while (p != null && p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }
}
