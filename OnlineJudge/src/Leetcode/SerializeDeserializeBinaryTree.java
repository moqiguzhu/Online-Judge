package Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import DataStructure.TreeNode;

public class SerializeDeserializeBinaryTree {
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if (root == null) {
      return null;
    }
    
    Stack<TreeNode> stack = new Stack<TreeNode>();
    StringBuffer result = new StringBuffer();
    
    stack.push(root);
    TreeNode temp;

    while (!stack.isEmpty()) {
      temp = stack.pop();
      if (temp == null) {
        result.append("/" + "|");
      } else {
        result.append(temp.val + "|");
        if (temp.right != null) {
          stack.add(temp.right);
        } else {
          stack.add(null);
        }
        if (temp.left != null) {
          stack.add(temp.left);
        } else {
          stack.add(null);
        }
      }
    }
    result.deleteCharAt(result.length() - 1);

    return result.toString();
  }

  // Decodes your encoded data to tree.
  // Stack改写
  public TreeNode deserialize(String data) {
    if(data == null) {
      return null;
    }
    
    String regex = "\\|";
    String[] nodes = data.split(regex);
    
    TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
    LinkedList<TreeNode> ancestors = new LinkedList<>();
    LinkedList<Integer> states = new LinkedList<>();
    ancestors.add(root);
    states.add(1);
    TreeNode temp = null;
    
    for(int i = 1; i < nodes.length;i++) {
      if(states.getLast() == 1) {
        if(nodes[i].equals("/")) {
          ancestors.getLast().left = null;
          
          states.removeLast();
          states.addLast(2);
        } else {
          temp = new TreeNode(Integer.parseInt(nodes[i]));
          ancestors.getLast().left = temp;
          
          states.removeLast();
          states.addLast(2);
          
          ancestors.addLast(temp);
          states.addLast(1);
        }
      } else {
        if(nodes[i].equals("/")) {
          ancestors.getLast().right = null;
          
          ancestors.removeLast();
          states.removeLast();
        } else {
          temp = new TreeNode(Integer.parseInt(nodes[i]));
          ancestors.getLast().right = temp;
          
          ancestors.removeLast();
          states.removeLast();
          
          ancestors.addLast(temp);
          states.addLast(1);
        }
      }
    }
    
    return root;
  }

  public String preorder_serialize(TreeNode root) {
    if (root == null) {
      return "$";
    }
    return root.val + " " + serialize(root.left) + " " + serialize(root.right);
  }

  public TreeNode preorder_deserialize(String data) {
    Scanner sc = new Scanner(data);
    TreeNode root = build(sc);
    sc.close();
    
    return root;
  }

  public TreeNode build(Scanner sc) {
    if(!sc.hasNext()) return null;
    String token = sc.next();
    if(token.equals("$")) return null;
    TreeNode root = new TreeNode(Integer.parseInt(token));
    root.left = build(sc);
    root.right = build(sc);
    
    return root;
  }
  
  public List<TreeNode> createTestCases() {
    List<TreeNode> testcases = new ArrayList<TreeNode>();

    TreeNode root1 = null;
    testcases.add(root1);

    TreeNode root2 = new TreeNode(1);
    root2.right = new TreeNode(2);
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
    SerializeDeserializeBinaryTree sdbt = new SerializeDeserializeBinaryTree();
    List<TreeNode> testcases = sdbt.createTestCases();
    
    for(TreeNode node : testcases) {
      TreeNode root = sdbt.deserialize(sdbt.serialize(node));
      System.out.println(root);
      System.out.println(root.left);
      System.out.println(root.right);
//      Utils.printTreeNode(sdbt.deserialize(sdbt.serialize(node)));
    }
  }
}
