package traversal;

import DataStructure.TreeNode;

public class MorrisInOrderTraversal {
  public static void morrisInOrder(TreeNode root) {
    TreeNode cur = root;
    TreeNode pre;
    while (cur != null) {
      if (cur.left == null) {
        System.out.println(cur.val);
        cur = cur.right; // move to next right node
      } else { // has a left subtree
        pre = cur.left;
        while (pre.right != null) { // find rightmost
          pre = pre.right;
        }
        pre.right = cur; // put cur after the pre node
        TreeNode temp = cur; // store cur node
        cur = cur.left; // move cur to the top of the new tree
        temp.left = null; // original cur left be null, avoid infinite loops
      }
    }
  }
}
