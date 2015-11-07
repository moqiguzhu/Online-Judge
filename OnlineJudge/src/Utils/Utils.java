package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import DataStructure.ListNode;
import DataStructure.TreeNode;

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
  
  public static void printArray(Object[] arr) {
    for(int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + "  ");
    }
    System.out.println();
  }
  
  // 先序遍历打印二叉树
  public static void printTreeNode(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    
    if(root == null) {
      System.out.println(result);
    }
    
    stack.push(root);
    TreeNode temp;
    
    while (!stack.isEmpty()) {
      temp = stack.pop();
      result.add(temp.val);
      if (temp.right != null) {
        stack.add(temp.right);
      }
      if (temp.left != null) {
        stack.add(temp.left);
      }
    }

    System.out.println(result);
  }
}
