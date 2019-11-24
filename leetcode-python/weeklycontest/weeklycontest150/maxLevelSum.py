# Definition for a binary tree node.
from collections import deque
import math


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def maxLevelSum(self, root: TreeNode) -> int:
        if root is None:
            return 0
        q = deque()
        q.append(root)
        max_sum = -math.inf
        max_level = 0

        level = 0
        while len(q) > 0:
            level += 1
            cur_sum = 0
            t = deque()
            for e in q:
                cur_sum += e.val
                if e.left is not None:
                    t.append(e.left)
                if e.right is not None:
                    t.append(e.right)
            if cur_sum > max_sum:
                max_sum = cur_sum
                max_level = level
            q = t
        return max_level


s = Solution()
root = TreeNode(1)
root.left = TreeNode(7)
root.left.left = TreeNode(7)
root.left.right_str = TreeNode(-8)
root.right = TreeNode(0)
print(s.maxLevelSum(root))
