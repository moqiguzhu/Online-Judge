package binarytree;

import java.util.ArrayList;
import java.util.List;

import DataStructure.TreeNode;

/**
 * 
 * @author moqiguzhu
 * @date 2015-12-07
 * @version 1.0
 */

public class MaximumDepthofBinaryTree {
  // one-line solution
  public int maxDepth(TreeNode root) {
    return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
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

    return testcases;
  }

  public static void main(String[] args) {
    MaximumDepthofBinaryTree mdbt = new MaximumDepthofBinaryTree();
    List<TreeNode> testcases = mdbt.createTestCases();
    for (int i = 0; i < testcases.size(); i++) {
      System.out.print(mdbt.maxDepth(testcases.get(i)) + " ");
    }
  }
}
