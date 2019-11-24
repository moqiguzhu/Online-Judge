from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import numpy as np


class Solution:
    def countServers(self, grid: List[List[int]]) -> int:
        res = 0

        m, n = len(grid), len(grid[0])
        grid = np.asarray(grid, dtype=int)
        used = copy.deepcopy(grid)

        for i in range(m):
            t = sum(grid[i])
            if t > 1:
                res += t
                used[i] = 0
        for j in range(n):
            t = sum(grid[:, j])
            if t > 1:
                res += sum(used[:, j])
        return res


s = Solution()
grid = [[1, 1, 0, 0], [0, 0, 1, 0], [0, 0, 1, 0], [0, 0, 0, 1]]
print(s.countServers(grid))
