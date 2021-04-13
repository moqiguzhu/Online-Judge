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


class Solution:
    def __init__(self):
        self.distance = 0

    def countPairs(self, root: TreeNode, distance: int) -> int:
        self.distance = distance
        t = self.help(root)

        return t[1]

    def help(self, root):
        if root.left is None and root.right is None:
            return (1, 0, np.array([0]))
        else:
            if root.left is not None:
                t1 = self.help(root.left)
            else:
                t1 = (0, 0, np.array([]))
            if root.right is not None:
                t2 = self.help(root.right)
            else:
                t2 = (0, 0, np.array([]))

            tt1 = (t1[2] + 1)[t1[2] < self.distance-1]
            tt2 = (t2[2]+1)[t2[2] < self.distance-1]

            t = 0
            for e1 in tt1:
                for e2 in tt2:
                    if e1 + e2 <= self.distance:
                        t += 1

            t = (t1[0] + t2[0], t1[1] + t2[1] + t, np.concatenate([tt1, tt2]))
            return t


if __name__ == "__main__":
    s = Solution()

    root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left.right = TreeNode(4)

    # root = TreeNode(100)

    # root = TreeNode(1)
    # root.left, root.right = TreeNode(1), TreeNode(1)

    # root = TreeNode(1)
    # root.left, root.right = TreeNode(2), TreeNode(3)
    # root.left.left, root.left.right, root.right.left, root.right.right = TreeNode(
    #     4), TreeNode(5), TreeNode(6), TreeNode(7)
    # root.right.right.left, root.right.right.right = TreeNode(8), TreeNode(9)
    # root.right.right.left.left = TreeNode(10)

    root = TreeNode(43)
    root.left, root.right = TreeNode(32), TreeNode(22)
    root.left.left, root.left.right, root.right.right = TreeNode(
        72), TreeNode(34), TreeNode(28)
    root.left.right.right = TreeNode(70)

    print(s.countPairs(root, 5))
