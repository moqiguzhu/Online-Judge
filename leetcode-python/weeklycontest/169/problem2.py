from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def getAllElements(self, root1: TreeNode, root2: TreeNode) -> List[int]:
        res1 = self.help(root1)
        res2 = self.help(root2)

        return self.merge(res1, res2)

    def help(self, root):
        if root is None:
            return []
        t1, t2 = [], []
        if root.left is not None:
            t1 = self.help(root.left)
        if root.right is not None:
            t2 = self.help(root.right)
        return t1 + [root.val] + t2

    def merge(self, l1, l2):
        i, j = 0, 0
        n1, n2 = len(l1), len(l2)
        res = []
        while i < n1 or j < n2:
            if i == n1:
                res.append(l2[j])
                j += 1
            elif j == n2:
                res.append(l1[i])
                i += 1
            elif l1[i] <= l2[j]:
                res.append(l1[i])
                i += 1
            else:
                res.append(l2[j])
                j += 1
        return res


if __name__ == '__main__':
    s = Solution()
    root1 = TreeNode(2)
    root1.left = TreeNode(1)
    root1.right = TreeNode(4)

    root2 = TreeNode(1)
    root2.left = TreeNode(0)
    root2.right = TreeNode(3)

    print(s.getAllElements(root1, root2))
