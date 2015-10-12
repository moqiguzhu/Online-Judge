package Leetcode;

import java.util.ArrayList;
import java.util.List;

import DataStructure.ListNode;
import Utils.Utils;

/**
 * Reverse a singly linked list.
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-12
 */
public class ReverseLinkedList {
  public ListNode reverseList(ListNode head) {
    if (head == null) {
      return head;
    }
    ListNode cur = head, next = head.next, nnext = null;
    if (next != null) {
      nnext = next.next;
    }
    head.next = null;
    while (next != null) {
      next.next = cur;
      cur = next;
      next = nnext;
      if (nnext != null) {
        nnext = nnext.next;
      }
    }

    return cur;
  }

  public List<ListNode> createTestcases() {
    List<ListNode> testcases = new ArrayList<>();

    ListNode root1 = null;
    testcases.add(root1);

    ListNode root2 = new ListNode(1);
    testcases.add(root2);

    ListNode root3 = new ListNode(1);
    root3.next = new ListNode(2);
    testcases.add(root3);

    ListNode root4 = new ListNode(1);
    root4.next = new ListNode(2);
    root4.next.next = new ListNode(3);
    root4.next.next.next = new ListNode(4);
    root4.next.next.next.next = new ListNode(5);
    testcases.add(root4);

    return testcases;
  }

  public static void main(String[] args) {
    ReverseLinkedList rll = new ReverseLinkedList();
    List<ListNode> testcases = rll.createTestcases();

    for (int i = 0; i < testcases.size(); i++) {
      Utils.printListNode(rll.reverseList(testcases.get(i)));
    }
  }
}
