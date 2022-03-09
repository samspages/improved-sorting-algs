/**
 * Introsort class.
 *
 * @author Samuel Page
 * @version 11/3/2021
 */
public class IntroSort {

  /**
   * Sort the provided items using the introsort algorithm.
   */
  public static <T extends Comparable<T>> void introSort(T[] items) {
    if (items.length <= 0) {
      return;
    }
    double maxDepth = 2 * (Math.log(items.length) / Math.log(2));
    quickSort(items, 0, items.length - 1, maxDepth);
  }

  /**
   * Updated quicksort to track stack depth and shift to mergeSort2 if input is pathological.
   * @param items to sort
   * @param left bound
   * @param right bound
   * @param maxDepth max stack depth
   * @param <T> element
   */
  public static <T extends Comparable<T>> void quickSort(T[] items, int left, int right,
      double maxDepth) {

    if (items.length <= 1) {
      return;
    }

    if (maxDepth <= 1) {
      MergeSortsImproved.mergeSort2(items, left, right);
    } else {
      int pivotindex = (left + right) / 2;
      // curr will be the final position of the pivot item.
      int curr = QuickSort.partition(items, left, right, pivotindex);

      if ((curr - left) > 1) {
        quickSort(items, left, curr - 1, maxDepth - 1); // Sort left partition
      }
      if ((right - curr) > 1) {
        quickSort(items, curr + 1, right, maxDepth - 1); // Sort right partition
      }
    }
  }

}
/*
 * This work complies with the JMU Honor Code.
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 */
