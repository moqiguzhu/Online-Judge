from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np


class Solution:
    def kWeakestRows(self, mat: List[List[int]], k: int) -> List[int]:
        n, m = len(mat), len(mat[0])
        t = []
        for i in range(n):
            t.append((i, sum(mat[i])))
        t.sort(key=lambda x: (x[1], x[0]))
        return [x[0] for x in t][:k]


if __name__ == '__main__':
    s = Solution()
    mat = [[1, 0, 0, 0],
           [1, 1, 1, 1],
           [1, 0, 0, 0],
           [1, 0, 0, 0]]
    k = 2
    print(s.kWeakestRows(mat, k))
