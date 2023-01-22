package deque;

// invariants:
// 1. size: The number of items in the list should be size.
// 2. addLast: The item will go to position nextLast.
// 3. addFirst: The item will go to position nextFirst.
// 4. resize: Double the size of items array if scaleUp is true. Otherwise, divide the size of items by 2. Copy the and
// paste the items to the start of new items array. Reassign nextFirst to the last position of new array and nextLast to
// the position next to the last non-empty element.
// 5. getIndexOfFirstItem: The first item is at position (nextFirst + 1), except that the first item is at position 0 if
// the nextFirst equals (items.length - 1).
// 6. getIndexOfLastItem: The last item is at position (nextLast - 1), except that the last item is at the end of the
// items array if nextLast equals 0.

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
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
    return nextFirst == items.length - 1 ? 0 : nextFirst + 1;
  }

  private int getIndexOfLastItem() {
    return nextLast == 0 ? items.length - 1 : nextLast - 1;
  }

  @Override
  public void addLast(T item) {
    if (size == items.length) {
      resize(true);
    }
    items[nextLast] = item;
    boolean meetTheEndOfItemsArray = nextLast == items.length - 1;
    if (meetTheEndOfItemsArray) {
      nextLast = 0;
    } else {
      nextLast++;
    }
    size++;
  }

  @Override
  public void addFirst(T item) {
    if (size == items.length) {
      resize(true);
    }
    items[nextFirst] = item;
    boolean meetTheStartOfItemsArray = nextFirst == 0;
    if (meetTheStartOfItemsArray) {
      nextFirst = items.length - 1;
    } else {
      nextFirst--;
    }
    size++;
  }

  @Override
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

  @Override
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

  @Override
  public T get(int index) {
    return items[index];
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
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

  private class ArrayDequeIterator implements Iterator<T> {
    private int pos = 0;

    @Override
    public boolean hasNext() {
      return pos < size;
    }

    @Override
    public T next() {
      T nextItem = get(pos);
      pos++;
      return nextItem;
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new ArrayDequeIterator();
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof ArrayDeque otherAD) {
      if (this.size != otherAD.size) return false;
      for (T item : this) {
        if (!otherAD.contains(item)) return false;
      }
      return true;
    }
    return false;
  }

  private boolean contains(T x) {
    for (T item : this) {
      if (item.equals(x)) return true;
    }
    return false;
  }

}
