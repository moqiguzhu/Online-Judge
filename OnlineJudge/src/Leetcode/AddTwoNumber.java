package Leetcode;

import java.util.ArrayList;
import java.util.List;

import Utils.Utils;
import DataStructure.ListNode;

public class AddTwoNumber {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode l3 = new ListNode(0);
    ListNode tempNode = l3;
    int sum = 0;
    while (l1 != null || l2 != null) {
      if (l1 != null) {
        sum += l1.val;
        l1 = l1.next;
      }
      if (l2 != null) {
        sum += l2.val;
        l2 = l2.next;
      }
      tempNode.next = new ListNode(sum % 10);
      sum /= 10;
      tempNode = tempNode.next;
    }
    if (sum != 0)
      tempNode.next = new ListNode(sum);
    return l3.next;
  }

  public List<ListNode> createTestCases() {
    List<ListNode> testcases = new ArrayList<>();

    ListNode l1 = new ListNode(2);
    l1.next = new ListNode(8);
    l1.next.next = new ListNode(3);

    ListNode l2 = new ListNode(9);
    l2.next = new ListNode(6);
    l2.next.next = new ListNode(4);

    testcases.add(l1);
    testcases.add(l2);

    return testcases;
  }

  public static void main(String[] args) {
    AddTwoNumber atn = new AddTwoNumber();
    List<ListNode> testcases = atn.createTestCases();

    for (int i = 0; i < testcases.size() / 2; i++) {
      ListNode result = atn.addTwoNumbers(testcases.get(2 * i), testcases.get(2 * i + 1));
      Utils.printListNode(result);
    }
  }
}
