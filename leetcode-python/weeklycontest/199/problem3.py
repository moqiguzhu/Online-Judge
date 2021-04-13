from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest

# Definition for a binary tree node.


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class newTreeNode:
    def __init__(self, val=0, left=None, right=None, parent=None, id=0, h=0):
        self.val = val
        self.left = left
        self.right = right
        self.parent = None
        self.id = id
        self.h = h


class Solution:
    def __init__(self):
        self.memo = defaultdict(int)
        self.id = 0
        self.leaf = []

    def countPairs(self, root: TreeNode, distance: int) -> int:
        new_root = self.construct_parent(root, 0)
        self.construct_parent1(new_root)

        self.iterate_tree(new_root)

        res = 0
        n = len(self.leaf)

        for i in range(n):
            for j in range(i+1, n, 1):
                # print(self.solve(self.leaf[i], self.leaf[j]),
                #       self.leaf[i].val, self.leaf[j].val)
                if self.solve(self.leaf[i], self.leaf[j]) <= distance:
                    res += 1
        return res

    def iterate_tree(self, root):
        if root is None:
            return
        elif root.left is None and root.right is None:
            self.leaf.append(root)
            return
        else:
            self.iterate_tree(root.left)
            self.iterate_tree(root.right)

    def solve(self, left_node, right_node):
        if (left_node.id, right_node.id) in self.memo:
            pass
        elif left_node.id == right_node.id:
            self.memo[(left_node.id, right_node.id)] = 0
        elif (left_node.parent is not None and left_node.parent.id == right_node.id) or (right_node.parent is not None and right_node.parent.id == left_node.id):
            self.memo[(left_node.id, right_node.id)] = 1
        else:
            if left_node.parent is None:
                t = 1 + self.solve(left_node, right_node.parent)
            elif right_node.parent is None:
                t = 1 + self.solve(left_node.parent, right_node)
            else:
                if left_node.h > right_node.h:
                    t = self.solve(left_node.parent, right_node) + 1
                else:
                    t = self.solve(left_node, right_node.parent) + 1
            self.memo[(left_node.id, right_node.id)] = t
        return self.memo[(left_node.id, right_node.id)]

    def construct_parent(self, root, h):
        if root is None:
            return
        if root.left is not None:
            new_left = self.construct_parent(root.left, h+1)
        else:
            new_left = None
        if root.right is not None:
            new_right = self.construct_parent(root.right, h+1)
        else:
            new_right = None

        new_root = newTreeNode(
            root.val, new_left, new_right, None, self.id, h)
        self.id += 1

        return new_root

    def construct_parent1(self, new_root):
        if new_root is None:
            return
        if new_root.left is not None:
            new_root.left.parent = new_root
            self.construct_parent1(new_root.left)
        if new_root.right is not None:
            new_root.right.parent = new_root
            self.construct_parent1(new_root.right)


if __name__ == "__main__":
    s = Solution()

    root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left.right = TreeNode(4)

    # root = TreeNode(100)

    # root = TreeNode(1)
    # root.left, root.right = TreeNode(1), TreeNode(1)

    root = TreeNode(1)
    root.left, root.right = TreeNode(2), TreeNode(3)
    root.left.left, root.left.right, root.right.left, root.right.right = TreeNode(
        4), TreeNode(5), TreeNode(6), TreeNode(7)
    root.right.right.left, root.right.right.right = TreeNode(8), TreeNode(9)
    root.right.right.left.left = TreeNode(10)

    # root = TreeNode(43)
    # root.left, root.right = TreeNode(32), TreeNode(22)
    # root.left.left, root.left.right, root.right.right = TreeNode(
    #     72), TreeNode(34), TreeNode(28)
    # root.left.right.right = TreeNode(70)

    print(s.countPairs(root, 5))
