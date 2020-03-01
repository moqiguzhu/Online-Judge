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
    def __init__(self):
        self.candidates = []
        self.M = 10**9 + 7

    def maxProduct(self, root: TreeNode) -> int:
        self.cursum(root)
        s = self.candidates[-1]
        res = 0
        for i in range(0, len(self.candidates)-1):
            res = max((s-self.candidates[i]) * self.candidates[i], res)
        return res % self.M

    def cursum(self, root):
        if root.left is not None:
            t1 = self.cursum(root.left)
        else:
            t1 = 0
        if root.right is not None:
            t2 = self.cursum(root.right)
        else:
            t2 = 0
        t = t1 + t2 + root.val
        self.candidates.append(t)
        return t


if __name__ == '__main__':
    s = Solution()
    t1 = TreeNode(4)
    t2 = TreeNode(5)
    t3 = TreeNode(2)
    t3.left = t1
    t3.right = t2

    t4 = TreeNode(6)
    t5 = TreeNode(3)
    t5.left = t4

    t6 = TreeNode(1)
    t6.left = t3
    t6.right = t5

    print(s.maxProduct(t6))
