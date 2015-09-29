package traversal;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import DataStructure.TreeNode;

/**
 * Postorder traversal, which is in Left-Right-Root order. We can observe that the preorder
 * traversal is in Root-Left-Right order, which means if we swap the order of left and right subtree
 * when pushing into stack, we'll get Root-Right-Left, a new traversal.
 * 
 * It is just the opposite way of postorder one. And that's why the following code works.
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-09-29
 */
public class BinaryTreePostorderTraversal {
  public List<Integer> postorderTraversal(TreeNode root) {
    LinkedList<Integer> result = new LinkedList<Integer>();
    if (root == null)
      return result;

    Stack<TreeNode> stack = new Stack<TreeNode>();
    stack.push(root);

    while (!stack.isEmpty()) {
      root = stack.pop();
      result.addFirst(root.val);
      if (root.left != null) {
        stack.push(root.left);
      }
      if (root.right != null) {
        stack.push(root.right);
      }
    }

    return result;
  }

  public TreeNode constructTree() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(4);
    root.right.right = new TreeNode(5);
    return root;
  }

  public static void main(String[] args) {
    BinaryTreePostorderTraversal btpt = new BinaryTreePostorderTraversal();
    TreeNode root = btpt.constructTree();
    System.out.println(btpt.postorderTraversal(root));
  }
}
