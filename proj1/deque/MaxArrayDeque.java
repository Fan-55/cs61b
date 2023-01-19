package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
  Comparator<T> defaultComparator;

  public MaxArrayDeque(Comparator<T> c) {
    this.defaultComparator = c;
  }

  public T max() {
    if (this.isEmpty()) {
      return null;
    }
    int maxIndex = 0;
    for (int pos = 0; pos < this.size() - 1; pos++) {
      T currentItem = get(pos);
      T nextItem = get(pos + 1);
      int comparison = defaultComparator.compare(currentItem, nextItem);
      if (comparison < 0) {
        maxIndex = pos + 1;
      }
    }
    return get(maxIndex);
  }

  public T max(Comparator<T> c) {
    if (this.isEmpty()) {
      return null;
    }
    int maxIndex = 0;
    for (int pos = 0; pos < this.size() - 1; pos++) {
      T currentItem = get(pos);
      T nextItem = get(pos + 1);
      int comparison = c.compare(currentItem, nextItem);
      if (comparison < 0) {
        maxIndex = pos + 1;
      }
    }
    return get(maxIndex);
  }
}
