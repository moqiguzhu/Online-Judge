package traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import DataStructure.TreeNode;

public class BinaryTreePreorderTraversal {
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    
    if(root == null) {
      return result;
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

    return result;
  }

  public List<TreeNode> createTestCases() {
    List<TreeNode> testcases = new ArrayList<TreeNode>();

    TreeNode root1 = null;
    testcases.add(root1);

    TreeNode root2 = new TreeNode(1);
    testcases.add(root2);

    TreeNode root3 = new TreeNode(1);
    root3.left = new TreeNode(2);
    root3.left.left = new TreeNode(3);
    testcases.add(root3);

    TreeNode root4 = new TreeNode(1);
    root4.left = new TreeNode(2);
    root4.right = new TreeNode(3);
    testcases.add(root4);

    TreeNode root5 = new TreeNode(1);
    root5.left = new TreeNode(2);
    root5.right = new TreeNode(3);
    root5.right.left = new TreeNode(4);
    root5.right.right = new TreeNode(5);
    testcases.add(root5);

    return testcases;
  }

  public static void main(String[] args) {
    BinaryTreePreorderTraversal btpt = new BinaryTreePreorderTraversal();
    List<TreeNode> testcases = btpt.createTestCases();
    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(btpt.preorderTraversal(testcases.get(i)).toString());
    }
  }
}
