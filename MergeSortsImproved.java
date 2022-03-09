/**
 * Improved MergeSort class.
 *
 * @author Samuel Page
 * @version 11/3/2021
 */

public class MergeSortsImproved {

  /**
   * Merge sort the provided array using an improved merge operation.
   */
  public static <T extends Comparable<T>> void mergeSort1(T[] items) {
    T[] temp;
    temp = (T[]) new Comparable[(items.length / 2) + 1];

    mergeSort1(items, temp, 0, items.length - 1);
  }

  /**
   * Recursive helper method for improved merge sort algorithm.
   *
   * @param items the array to be sorted
   * @param temp the temporary array to use for merge operation
   * @param left the index of the left end of the given region
   * @param right the index of the right end of the given region
   * @param <T> generic element
   */
  public static <T extends Comparable<T>> void mergeSort1(T[] items, T[] temp,
      int left, int right) {

    if (left >= right) {
      return; // region width is 0
    }
    int mid = (left + right) / 2;

    mergeSort1(items, temp, left, mid); // recurse left
    mergeSort1(items, temp, mid + 1, right); // recurse right

    // improved merge
    merge(items, temp, left, mid, right);
  }

  /**
   * Improved merge method.
   * @param items to sort
   * @param temp half length array to merge with sorted inplace array
   * @param left bound
   * @param mid value
   * @param right bound
   * @param <T> element
   */
  public static <T extends Comparable<T>> void merge(T[] items, T[] temp, int left, int mid,
      int right) {

    // copy array elements from the first merge sorted half into temp
    for (int i = 0; i < temp.length && i + left <= mid; i++) {
      temp[i] = items[i + left];
    }

    int i1 = 0;
    int i2 = mid + 1;

    // merge the values from temp and the second sorted half of items into items
    for (int cur = left; cur <= right; cur++) {
      // temp array exhausted
      if (i1 == temp.length || i1 + left >= mid + 1) {
        items[cur] = items[i2++];
      } else if (i2 > right) { // items sub array exhausted
        items[cur] = temp[i1++];
      } else if (temp[i1].compareTo(items[i2]) <= 0) { // get smaller
        items[cur] = temp[i1++];
      } else {
        items[cur] = items[i2++];
      }
    }

  }


  /**
   * Merge sort the provided array by using an improved merge operation and
   * switching to insertion sort for small sub-arrays.
   */
  public static <T extends Comparable<T>> void mergeSort2(T[] items) {
    T[] temp = (T[]) new Comparable[(items.length)];

    mergeSort2(items, temp, 0, items.length - 1);
  }

  /**
   * Updated mergesort recursive method includes check for size of items.
   * @param items to sort
   * @param temp array
   * @param left bound
   * @param right bound
   * @param <T> element
   */
  public static <T extends Comparable<T>> void mergeSort2(T[] items, T[] temp, int left,
      int right) {
    int mergeThreshold = 80;
    if (right - left <= mergeThreshold) { // insertionSort threshold
      BasicSorts.insertionSort(items, left, right);
    } else {
      if (left >= right) {
        return;
      }
      int mid = (left + right) / 2;

      mergeSort2(items, temp, left, mid); //recurse left
      mergeSort2(items, temp, mid + 1, right); //recurse right
      merge2(items, temp, left, mid, right);
    }
  }

  /**
   * Merge sort the provided sub-array using our improved merge sort. This is the
   * fallback method used by introspective sort.
   */
  public static <T extends Comparable<T>> void mergeSort2(T[] items, int start, int end) {
    mergeSort2(items, (T[]) new Comparable[(items.length)], start, end);
  }

  /**
   * Updated merge method from MergeSort.
   * @param items to sort
   * @param temp array
   * @param left bound
   * @param mid value
   * @param right bound
   * @param <T> element
   */
  private static <T extends Comparable<T>> void merge2(T[] items, T[] temp, int left, int mid,
      int right) {

    for (int i = left; i <= right; i++) {
      temp[i] = items[i]; // Copy subarray to temp
    }

    int i1 = left;
    int i2 = mid + 1;
    for (int curr = left; curr <= right; curr++) {
      if (i1 == mid + 1) { // Left subarray exhausted
        items[curr] = temp[i2++];
      } else if (i2 > right) { // Right subarray exhausted
        items[curr] = temp[i1++];
      } else if (temp[i1].compareTo(temp[i2]) <= 0) { // Get smaller value
        items[curr] = temp[i1++];
      } else {
        items[curr] = temp[i2++];
      }
    }
  }

}
/*
 * This work complies with the JMU Honor Code.
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 */
