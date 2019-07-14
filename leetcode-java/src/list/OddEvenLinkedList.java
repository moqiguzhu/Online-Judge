package list;

import java.util.ArrayList;
import java.util.List;

import DataStructure.ListNode;
import Utils.Utils;

public class OddEvenLinkedList {
  public ListNode oddEvenList(ListNode head) {
    if(head == null || head.next == null) {
      return head;
    }
    
    ListNode evenHead = new ListNode(-1);
    ListNode evenTmp = evenHead;
    ListNode oddTmp = new ListNode(-1);
    boolean flag = false;
    ListNode tmp = head;
    
    while(tmp != null) {
      if(flag) {
        evenTmp.next = tmp;
        evenTmp = evenTmp.next;
      } else {
        oddTmp.next = tmp;
        oddTmp = oddTmp.next;
      }
      flag = !flag;
      tmp = tmp.next;
    }
    
    //
    oddTmp.next = evenHead.next;
    evenTmp.next = null;
    
    return head;
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
    
    ListNode head4 = new ListNode(1);
    head4.next = new ListNode(2);
    head4.next.next = new ListNode(3);
    head4.next.next.next = new ListNode(4);
    head4.next.next.next.next = new ListNode(5);
    testcases.add(head4);

    return testcases;
  }
  
  public static void main(String[] args) {
    OddEvenLinkedList OddEvenLinkedList = new OddEvenLinkedList();
    List<ListNode> testcases = OddEvenLinkedList.createTestCases();
    for (int i = 0; i < testcases.size(); i++)
      Utils.printListNode(OddEvenLinkedList.oddEvenList(testcases.get(i)));
  }
}
