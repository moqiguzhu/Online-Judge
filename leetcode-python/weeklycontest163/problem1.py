from collections import defaultdict, deque, Counter
from typing import List
import math
import copy


class Solution:
    def shiftGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        n, m = len(grid), len(grid[0])
        k = k % (n*m)

        t1, t2 = grid, copy.deepcopy(grid)
        for i in range(n):
            for j in range(m):
                ij = i*m + j
                t2[(ij+k)//m % n][(ij+k) % m] = t1[i][j]
        return t2


s = Solution()
grid = [[1], [2], [3], [4], [7], [6], [5]]
k = 23
print(s.shiftGrid(grid, k))
