package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of
 * the two subtrees of every node never differ by more than 1.
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-09-25
 */

public class BalancedBinaryTree {
  boolean flag = true;

  // 有点backtracking的味道
  // 还有点DFS的味道
  public boolean isBalanced(TreeNode root) {
    maxHeight(root, 0);
    return flag;
  }

  public int maxHeight(TreeNode node, int height) {
    if (!flag) {
      return 0;
    }
    if (node == null) {
      return height;
    }
    int left_height = maxHeight(node.left, height + 1);
    int right_height = maxHeight(node.right, height + 1);
    if (Math.abs(left_height - right_height) > 1) {
      flag = false;
    }
    return Math.max(left_height, right_height);
  }

  public boolean naive_isBalanced(TreeNode root) {
    if (root == null)
      return true;
    if (Math.abs(naive_maxheight(root.left) - naive_maxheight(root.right)) > 1)
      return false;
    else
      return isBalanced(root.left) && isBalanced(root.right);
  }

  public int naive_maxheight(TreeNode root) {
    if (root == null)
      return 0;
    int h = 0;
    int temp1 = 1;
    int temp2 = 1;
    if (root.left != null) {
      temp1 = 1 + naive_maxheight(root.left);
    } else {
    }
    if (root.right != null) {
      temp2 = 1 + naive_maxheight(root.right);
    } else {
    }
    h = Math.max(temp1, temp2);
    return h;
  }

  public List<TreeNode> createTestCases() {
    List<TreeNode> testcases = new ArrayList<TreeNode>();

    TreeNode root1 = null;
    testcases.add(root1);

    TreeNode root2 = new TreeNode(1);
    testcases.add(root2);

    TreeNode root3 = new TreeNode(1);
    root3.left = new TreeNode(2);
    root3.right = new TreeNode(3);
    testcases.add(root3);

    TreeNode root4 = new TreeNode(1);
    root4.left = new TreeNode(2);
    root4.left.left = new TreeNode(3);
    testcases.add(root4);

    TreeNode root5 = new TreeNode(1);
    root5.left = new TreeNode(2);
    root5.right = new TreeNode(3);
    root5.left.left = new TreeNode(4);
    root5.left.left.right = new TreeNode(5);
    root5.left.left.right.left = new TreeNode(6);
    testcases.add(root5);

    TreeNode root6 = new TreeNode(1);
    root6.left = new TreeNode(2);
    root6.right = new TreeNode(2);
    root6.left.left = new TreeNode(3);
    root6.right.right = new TreeNode(3);
    root6.left.left.left = new TreeNode(4);
    root6.right.right.right = new TreeNode(4);
    testcases.add(root6);


    TreeNode root7 = new TreeNode(1);
    root7.left = new TreeNode(2);
    root7.right = new TreeNode(2);
    root7.left.left = new TreeNode(3);
    root7.left.right = new TreeNode(3);
    root7.right.left = new TreeNode(3);
    root7.right.right = new TreeNode(3);
    root7.left.left.left = new TreeNode(4);
    root7.left.left.right = new TreeNode(4);
    root7.left.right.left = new TreeNode(4);
    root7.left.right.right = new TreeNode(4);
    root7.right.left.left = new TreeNode(4);
    root7.right.left.right = new TreeNode(4);
    root7.left.left.left.left = new TreeNode(5);
    root7.left.left.left.right = new TreeNode(5);
    testcases.add(root7);

    TreeNode root8 = new TreeNode(1);
    root8.left = new TreeNode(2);
    root8.right = new TreeNode(2);
    root8.left.left = new TreeNode(3);
    root8.left.right = new TreeNode(3);
    root8.left.left.left = new TreeNode(4);
    root8.left.left.right = new TreeNode(4);
    testcases.add(root8);

    return testcases;
  }

  public static void main(String[] args) {
    BalancedBinaryTree bbt = new BalancedBinaryTree();
    List<TreeNode> testcases = bbt.createTestCases();
    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(bbt.isBalanced(testcases.get(i)));
    }
  }
}
