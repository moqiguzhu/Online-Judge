from collections import defaultdict, deque, Counter
from typing import List
import math
import copy

# Definition for a binary tree node.


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class FindElements:
    def __init__(self, root: TreeNode):
        root.val = 0
        self.help(root)
        self.nums = set()

    def help(self, node):
        if node is None:
            return
        if node.left is not None:
            node.left.val = 2 * node.val + 1
            self.nums.add(node.left.val)
        if node.right is not None:
            node.right.val = 2 * node.val + 2
            self.nums.add(node.right.val)
        self.help(node.left)
        self.help(node.right)

    def find(self, target: int) -> bool:
        return target in self.nums


s = FindElements()
s.__init__()


# Your FindElements object will be instantiated and called as such:
# obj = FindElements(root)
# param_1 = obj.find(target)
