from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect


class TreeNode(object):
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None


class Solution:
    def __init__(self):
        self.res = []

    def numOfMinutes(self, n: int, headID: int, manager: List[int], informTime: List[int]) -> int:
        manager_sub = defaultdict(list)
        for idx, e in enumerate(manager):
            manager_sub[e].append(idx)
        self.dfs(0, headID, manager_sub, informTime)
        return max(self.res)

    def dfs(self, cur_res, cur, manager_sub, informTime):
        if cur not in manager_sub:
            self.res.append(cur_res)
        for e in manager_sub[cur]:
            self.dfs(cur_res + informTime[cur], e, manager_sub, informTime)


if __name__ == '__main__':
    s = Solution()
    n = 8
    headID = 0
    manager = [-1, 5, 0, 6, 7, 0, 0, 0]
    informTime = [89, 0, 0, 0, 0, 523, 241, 519]
    print(s.numOfMinutes(n, headID, manager, informTime))
