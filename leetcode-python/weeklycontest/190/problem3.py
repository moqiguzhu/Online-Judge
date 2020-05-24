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
        self.res = 0

    def pseudoPalindromicPaths(self, root: TreeNode) -> int:
        if root is None:
            return 0
        else:
            return self.findpath(root, [root.val])

    def findpath(self, cur, cur_path=[]):
        if cur.left is None and cur.right is None:
            self.res += self.is_palindrome(cur_path)

        if cur.left is not None:
            self.findpath(cur.left, cur_path + cur.left.val)
        if cur.right is not None:
            self.findpath(cur.right, cur_path + cur.right.val)

    def is_palindrome(self, path):
        c = Counter(path)
        odd_cnt = 0
        for num, freq in c:
            odd_cnt += freq % 2
        return odd_cnt < 2
