package DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import DataStructure.TreeNode;

/**
 * 
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
 * two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a
 * node to be a descendant of itself).”
 * 
 * DFS a binary tree
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-12
 */
public class LowestCommonAncestorBinaryTree {
  LinkedList<TreeNode> stack = new LinkedList<>();
  List<TreeNode> path1 = new ArrayList<>();
  List<TreeNode> path2 = new ArrayList<>();
  int label = 0;

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    findPath(root, p, q);

    int index = 0;
    TreeNode result = null;
    while (index < Math.min(path1.size(), path2.size())) {
      if (path1.get(index) == path2.get(index)) {
        result = path1.get(index);
      } else {
        break;
      }
      index++;
    }

    return result;
  }

  public void findPath(TreeNode root, TreeNode p, TreeNode q) {
    if (label == 2) {
      return;
    }
    stack.addLast(root);

    if (root == p) {
      label++;
      path1.addAll(stack);
    }
    if (root == q) {
      label++;
      path2.addAll(stack);
    }

    if (root.left != null) {
      findPath(root.left, p, q);
    }
    if (root.right != null) {
      findPath(root.right, p, q);
    }

    stack.removeLast();
  }

  public List<TreeNode> createTestCases() {
    List<TreeNode> testcases = new ArrayList<>();

    TreeNode root1 = new TreeNode(3);
    root1.left = new TreeNode(5);
    root1.right = new TreeNode(1);
    root1.left.left = new TreeNode(6);
    root1.left.right = new TreeNode(2);
    root1.left.right.left = new TreeNode(7);
    root1.left.right.right = new TreeNode(4);
    root1.right.left = new TreeNode(0);
    root1.right.right = new TreeNode(8);
    testcases.add(root1);

    return testcases;
  }

  public static void main(String[] args) {
    LowestCommonAncestorBinaryTree lca = new LowestCommonAncestorBinaryTree();
    List<TreeNode> testcases = lca.createTestCases();

    TreeNode root = testcases.get(0);
    TreeNode p = root.left.right;
    TreeNode q = root.left.right.right;

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(lca.lowestCommonAncestor(root, p, q));
    }
  }
}
