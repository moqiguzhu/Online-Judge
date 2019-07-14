package DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import DataStructure.TreeNode;

public class LowestCommonAncestorBinarySearchTree {
  LinkedList<TreeNode> stack = new LinkedList<>();
  List<TreeNode> path = new ArrayList<>();
  boolean flag = false;

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    findPath(root, p);
    List<TreeNode> path1 = new ArrayList<>();
    path1.addAll(path);
    
    // reset variables
    path.clear();
    stack.clear();
    flag = false;
    
    findPath(root, q);
    List<TreeNode> path2 = new ArrayList<>();
    path2.addAll(path);

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

  public void findPath(TreeNode root, TreeNode node) {
    if (flag) {
      return;
    }
    
    stack.addLast(root);

    if (root == node) {
      flag = true;
      path.addAll(stack);
    }
    
    if(node.val < root.val) {
      findPath(root.left, node);
    } else {
      findPath(root.right, node);
    }

    stack.removeLast();
  }
  
  public List<TreeNode> createTestCases() {
    List<TreeNode> testcases = new ArrayList<>();

    TreeNode root1 = new TreeNode(6);
    root1.left = new TreeNode(2);
    root1.right = new TreeNode(8);
    root1.left.left = new TreeNode(0);
    root1.left.right = new TreeNode(4);
    root1.left.right.left = new TreeNode(3);
    root1.left.right.right = new TreeNode(5);
    root1.right.left = new TreeNode(7);
    root1.right.right = new TreeNode(9);
    testcases.add(root1);

    return testcases;
  }

  public static void main(String[] args) {
    LowestCommonAncestorBinarySearchTree lca = new LowestCommonAncestorBinarySearchTree();
    List<TreeNode> testcases = lca.createTestCases();

    TreeNode root = testcases.get(0);
    TreeNode p = root.left.right;
    TreeNode q = root.left.right.right;

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(lca.lowestCommonAncestor(root, p, q));
    }
  }
}
