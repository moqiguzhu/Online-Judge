package Utils;

import DataStructure.ListNode;

public class Utils {
  public static void printListNode(ListNode head) {
    while (head != null) {
      System.out.print(head.val + " ");
      head = head.next;
    }

    System.out.println();
  }

  public static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + "  ");
    }
    System.out.println();
  }

  public static void printArray(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      printArray(arr[i]);
    }
  }
}
