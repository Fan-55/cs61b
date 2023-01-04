package deque;

// invariants:
// 1. size: The number of items in the list should be size.
// 2. addLast: The item will go to position nextLast.
// 3. addFirst: The item will go to position nextFirst.
// 3. resize: double the size of items array and copy from the first item which is at position (nextFirst + 1) if the
// nextFirst is not the last item in the array. Otherwise, first item is at position 0.

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextLast;
    private int nextFirst;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(boolean scaleUp) {
        int newSize = scaleUp ? items.length * 2 : items.length / 2;
        T[] resizedItems = (T[]) new Object[newSize];
        int indexForCopying = getIndexOfFirstItem();
        for (int i = 0; i < size; i++) {
            resizedItems[i] = items[indexForCopying];
            if (indexForCopying + 1 == items.length) {
                indexForCopying = 0;
            } else {
                indexForCopying++;
            }
        }
        nextFirst = resizedItems.length - 1;
        nextLast = scaleUp ? items.length : size;
        items = resizedItems;
    }

    private int getIndexOfFirstItem() {
        int indexOfFirstItem;
        if (nextFirst == items.length - 1) {
            indexOfFirstItem = 0;
        } else {
            indexOfFirstItem = nextFirst + 1;
        }
        return indexOfFirstItem;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize();
        }
        items[nextLast] = item;
        if (nextLast + 1 == items.length) {
            nextLast = 0;
        } else {
            nextLast++;
        }
        size++;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize();
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst--;
        }
        size++;
    }

    public T removeLast() {
        if (size > 0) {
            double usageFactor = (double) (size - 1) / items.length;
            if (usageFactor < 0.25) {
                resize(false);
            }
            int indexOfLastItem = getIndexOfLastItem();
            T lastItem = items[indexOfLastItem];
            items[indexOfLastItem] = null;
            nextLast = indexOfLastItem;
            size--;
            return lastItem;
        }
        return null;
    }

    public T removeFirst() {
        if (size > 0) {
            double usageFactor = (double) (size - 1) / items.length;
            if (usageFactor < 0.25) {
                resize(false);
            }
            int indexOfFirstItem = getIndexOfFirstItem();
            T firstItem = items[indexOfFirstItem];
            nextFirst = indexOfFirstItem;
            size--;
            return firstItem;
        }
        return null;
    }

    public T get(int index) {
        return items[index];
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        int indexForPrinting = getIndexOfFirstItem();
        for (int i = 0; i < size; i++) {
            System.out.print(items[indexForPrinting] + " ");
            if (indexForPrinting + 1 == items.length) {
                indexForPrinting = 0;
            } else {
                indexForPrinting++;
            }
        }
        System.out.println();
    }
}
