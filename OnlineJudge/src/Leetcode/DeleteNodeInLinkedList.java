package Leetcode;

import DataStructure.ListNode;
import Utils.Utils;

/**
 * 描述：Write a function to delete a node (except the tail) in a singly linked list, given only access to
 * that node.
 * 
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the
 * linked list should become 1 -> 2 -> 4 after calling your function.
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-09-30
 */

public class DeleteNodeInLinkedList {
  //Java不能直接访问内存
  //node = null node指向的内存并没有释放
  public void deleteNode(ListNode node) {
    ListNode p = node;
    while (node.next != null) {
      node.val = node.next.val;
      p = node;
      node = node.next;
    }
    p.next = null;
  }
  
  public void delete(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(0);
    head.next = new ListNode(1);

    DeleteNodeInLinkedList dnll = new DeleteNodeInLinkedList();
    
    dnll.deleteNode(head);

    Utils.printListNode(head);
  }
}
