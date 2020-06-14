from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest

# egg dropping problem
# https://brilliant.org/wiki/egg-dropping/
# 三种解法
# 还有一种解法,需要先推出来公式,超出能力范围了,详情可以看上面的链接


class Solution:
    def __init__(self):
        self.memo = {}

    def eggdrop_recursive(self, n, k):
        if n == 0:
            return 0
        if k < 1:
            return 10**9
        if n == 1:
            return 1
        if k == 1:
            return n

        t = []
        for i in range(1, n+1, 1):
            t.append(1 + max(self.eggdrop_recursive(i-1, k-1),
                             self.eggdrop_recursive(n-i, k)))
        return min(t)

    def eggdrop_recursive_with_memo(self, n, k):
        if (n, k) in self.memo:
            return self.memo[(n, k)]
        if n == 0:
            res = 0
        elif k < 1:
            res = 10**9
        elif n == 1:
            res = 1
        elif k == 1:
            res = n
        else:
            t = []
            for i in range(1, n+1, 1):
                t.append(1 + max(self.eggdrop_recursive_with_memo(i-1, k-1),
                                 self.eggdrop_recursive_with_memo(n-i, k)))
            res = min(t)
        self.memo[(n, k)] = res
        return res

    def eggdrop_dp(self, n, k):
        if n == 0:
            return 0
        if k < 1:
            return 10**9
        if n == 1:
            return 1
        if k == 1:
            return n

        dp = np.zeros((n+1, k+1), dtype=np.int)
        dp[:, 0] = 10**9  # 顺序不能反
        dp[0, :] = 0
        for i in range(1, n+1):
            for j in range(1, k+1):
                t = [(1 + max(dp[p-1][j-1], dp[i-p][j]))
                     for p in range(1, i+1)]
                dp[i][j] = min(t)
        return dp[n][k]


if __name__ == "__main__":
    s = Solution()
    n, k = 100, 40
    print(s.eggdrop_dp(n, k))
