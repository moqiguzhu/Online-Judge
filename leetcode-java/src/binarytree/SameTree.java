package binarytree;

import DataStructure.TreeNode;

/**
 * @author moqiguzhu
 * @date 2016-01-10
 * @version 1.0
 */
public class SameTree {
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if(p == null && q == null) 
      return true;
    else if(p == null || q == null) 
      return false;
    else 
      return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }
}
