package list;

import java.util.ArrayList;
import java.util.List;

import DataStructure.ListNode;

/**
 * 
 * @author moqiguzhu
 * @date 2015-12-07
 * @version 1.0
 */

public class IntersectionofTwoLinkedLists {
  /**
   * 
   * @param headA 第一个链表表头
   * @param headB 第二个链表表头
   * @return 两个链表开始相交的节点，如果两个链表不相交，返回null
   */
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null)
      return null;
    ListNode theadA = headA, theadB = headB;
    ListNode tailA = headA, tailB = headB;
    int countA = 0, countB = 0, count;
    
    while (theadA != null) {        // 求链表A的节点个数    
      tailA = theadA;
      countA++;
      theadA = theadA.next;
    }
    
    while (theadB != null) {        // 求链表B的节点个数
      tailB = theadB;
      countB++;
      theadB = theadB.next;
    }
    
    if (tailA != tailB) {           // 两个链表不相交
      return null;
    }

    theadA = headA;                 // 齐头并进
    theadB = headB;
    if (countA == countB) {
      count = countA;
    } else if (countA > countB) {
      int k = countA - countB;
      count = countB;
      while (k > 0) {
        theadA = theadA.next;
        k--;
      }
    } else {
      int k = countB - countA;
      count = countA;
      while (k > 0) {
        theadB = theadB.next;
        k--;
      }
    }
    
    for (int i = 0; i < count; i++) {       // 查找开始相交的节点
      if (theadA == theadB) {
        return theadA;
      }
      theadA = theadA.next;
      theadB = theadB.next;
    }
    
    return null;                // 永远执行不到，只是为了保证程序语法上的正确性
  }

  public List<ListNode> createTestCases() {
    List<ListNode> testcases = new ArrayList<ListNode>();
    ListNode headA1 = null;
    ListNode headB1 = null;
    testcases.add(headA1);
    testcases.add(headB1);

    ListNode headA2 = null;
    ListNode headB2 = new ListNode(1);
    testcases.add(headA2);
    testcases.add(headB2);

    ListNode headA3 = new ListNode(1);
    ListNode headB3 = headA3;
    testcases.add(headA3);
    testcases.add(headB3);

    ListNode headA4 = new ListNode(1);
    headA4.next = new ListNode(2);
    headA4.next.next = new ListNode(3);
    headA4.next.next.next = new ListNode(4);
    ListNode headB4 = new ListNode(1);
    headB4.next = new ListNode(2);
    headB4.next.next = headA4.next.next;
    testcases.add(headA4);
    testcases.add(headB4);

    ListNode headA5 = new ListNode(1);
    headA5.next = new ListNode(2);
    ListNode headB5 = headA5;
    testcases.add(headA5);
    testcases.add(headB5);

    ListNode headB6 = new ListNode(1);
    headB6.next = new ListNode(2);
    ListNode headA6 = headB6.next;
    testcases.add(headA6);
    testcases.add(headB6);

    return testcases;
  }

  public static void main(String[] args) {
    IntersectionofTwoLinkedLists itll = new IntersectionofTwoLinkedLists();
    List<ListNode> testcases = itll.createTestCases();
    ListNode result;
    for (int i = 0; i < testcases.size() / 2; i++) {
      result = itll.getIntersectionNode(testcases.get(2 * i), testcases.get(2 * i + 1));
      if (result == null)
        System.out.print("null" + " ");
      else
        System.out.print(result.val + " ");
    }
  }
}

