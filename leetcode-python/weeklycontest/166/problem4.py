from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import numpy as np
import random

# BFS会更简单 效率也高的多


class Solution:
    # 花式调参解决问题
    # line_profiler帮了大忙
    def minFlips(self, mat: List[List[int]]) -> int:
        if self.checker(mat) == 0:
            return 0
        m, n = len(mat), len(mat[0])
        repeat = 2000
        res = -1
        for _ in range(repeat):
            t = self.helper1(mat, m, n, res)
            if t != -1:
                if res == -1:
                    res = t
                else:
                    res = min(t, res)
            else:
                pass
        return res

    def helper1(self, mat, m, n, res):
        mat_copy = copy.deepcopy(mat)
        t_sum = self.checker(mat_copy)
        times = 10
        idx = [(0, 1), (0, -1), (1, 0), (-1, 0), (0, 0)]
        for i in range(times):
            if i + 1 >= res and res != -1:
                return -1
            # 比np.random.randint快
            x, y = random.randint(0, m-1), random.randint(0, n-1)
            for e1, e2 in idx:
                if 0 <= x+e1 < m and 0 <= y+e2 < n:
                    mat_copy[x+e1][y+e2] = 1 - mat_copy[x+e1][y+e2]
                    if mat_copy[x+e1][y+e2] == 1:
                        t_sum += 1
                    else:
                        t_sum -= 1
            if t_sum == 0:
                return i+1
        return -1

    def checker(self, mat):
        return np.sum((np.sum(mat)))


if __name__ == '__main__':
    s = Solution()
    mat = [[1], [0]]
    print(s.minFlips(mat))
