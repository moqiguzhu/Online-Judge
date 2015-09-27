package Utils;

import DataStructure.ListNode;

public class Utils {
  public static void printListNode(ListNode head) {
    while(head != null) {
      System.out.print(head.val + " ");
      head = head.next;
    }
    
    
    
    
    
    System.out.println();
  }
}
