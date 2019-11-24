from typing import List
import numpy as np


class Solution:
    def oddCells(self, n: int, m: int, indices: List[List[int]]) -> int:
        t = [[0] * m for _ in range(n)]
        t = np.asarray(t)

        for ri, ci in indices:
            t[ri] += 1
            t[:, ci] += 1
        return len(t[t % 2 == 1])


s = Solution()
n = 48
m = 37
indices = [[40, 5]]
print(s.oddCells(n, m, indices))
