package list;

import java.util.ArrayList;
import java.util.List;

import DataStructure.ListNode;
import Utils.Utils;

/**
* <p>Title: RotateList.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* @author moqiguzhu
* @date Dec 25, 2015
* @version 1.0
*/
public class RotateList {
  public ListNode rotateRight(ListNode head, int n) { // 边界
    if (n == 0)
      return head;
    if (head == null)
      return head;

    ListNode lastNode = null, newHead, firstNode;
    int size = 0, count;
    newHead = head;
    firstNode = head;

    while (head != null) {
      lastNode = head;
      head = head.next;
      size++;
    }

    head = newHead;
    n = n % size;
    if (n == 0)
      return head;
    
    count = size - n;
    while (--count > 0)
      head = head.next;
    newHead = head.next;
    head.next = null;
    lastNode.next = firstNode;

    return newHead;
  }

  public List<ListNode> createTestCases() {
    List<ListNode> testcases = new ArrayList<ListNode>();

    ListNode head1 = null;
    testcases.add(head1);

    ListNode head2 = new ListNode(1);
    testcases.add(head2);

    ListNode head3 = new ListNode(1);
    head3.next = new ListNode(2);
    head3.next.next = new ListNode(3);
    head3.next.next.next = new ListNode(4);
    testcases.add(head3);

    return testcases;
  }

  public static void main(String[] args) {
    RotateList rl = new RotateList();
    List<ListNode> testcases = rl.createTestCases();
    for (int i = 0; i < testcases.size(); i++)
      Utils.printListNode(rl.rotateRight(testcases.get(i), 5));
  }
}
