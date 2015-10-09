package Leetcode;

import java.util.ArrayList;
import java.util.List;

import DataStructure.TreeNode;

/**
 * 
 * Given a binary tree, return all root-to-leaf paths.
 * 
 * recursion
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-09
 */
public class BinaryTreePaths {
  private List<String> result = new ArrayList<>();

  public List<String> binaryTreePaths(TreeNode root) {
    if (root == null) {
      return result;
    }
    help(root, "");

    return result;
  }

  public void help(TreeNode node, String str) {
    if (node.left == null && node.right == null) {
      str += node.val;
      result.add(str);
    } else {
      str += node.val + "->";
    }

    if (node.left != null) {
      help(node.left, str);
    }
    if (node.right != null) {
      help(node.right, str);
    }
  }

  public List<TreeNode> createTestcases() {
    List<TreeNode> testcases = new ArrayList<>();

    TreeNode root1 = new TreeNode(1);
    root1.left = new TreeNode(2);
    root1.right = new TreeNode(3);
    root1.left.right = new TreeNode(5);

    testcases.add(root1);

    return testcases;
  }

  public static void main(String[] args) {
    BinaryTreePaths btp = new BinaryTreePaths();
    List<TreeNode> testcases = btp.createTestcases();

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(btp.binaryTreePaths(testcases.get(i)));
    }
  }
}
