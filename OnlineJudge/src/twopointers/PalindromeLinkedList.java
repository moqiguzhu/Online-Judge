package twopointers;

import java.util.ArrayList;
import java.util.List;
import DataStructure.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Follow up: Could you do it in O(n) time and O(1) space?
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-12
 */

public class PalindromeLinkedList {
  public boolean isPalindrome(ListNode head) {
    int len = 0;
    ListNode cur = head;
    while (cur != null) {
      len++;
      cur = cur.next;
    }

    if (len < 2) {
      return true;
    }

    int count = 0;
    cur = head;
    ListNode end = null;
    while (count < len / 2) {
      cur = cur.next;
      count++;
    }

    end = reverseList(cur);

    for (int i = 0; i < len / 2; i++) {
      if (end.val != head.val) {
        return false;
      }
      end = end.next;
      head = head.next;
    }

    return true;
  }

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

    ListNode root3 = new ListNode(2);
    root3.next = new ListNode(2);
    testcases.add(root3);

    ListNode root4 = new ListNode(1);
    root4.next = new ListNode(2);
    root4.next.next = new ListNode(3);
    root4.next.next.next = new ListNode(2);
    root4.next.next.next.next = new ListNode(5);
    testcases.add(root4);

    return testcases;
  }

  public static void main(String[] args) {
    PalindromeLinkedList pll = new PalindromeLinkedList();
    List<ListNode> testcases = pll.createTestcases();

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(pll.isPalindrome(testcases.get(i)));
    }
  }
}
