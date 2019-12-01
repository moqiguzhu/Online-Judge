from collections import defaultdict, deque, Counter
from typing import List
import math
import copy


class Solution:
    def countSquares(self, matrix: List[List[int]]) -> int:
        m, n = len(matrix), len(matrix[0])
        res = 0
        for i in range(m):
            for j in range(n):
                res += self.help(matrix, i, j, m, n)
        return res

    def help(self, martrix, i, j, m, n):
        for l in range(0, min(m-i-1, n-j-1)+1, 1):  # slice begin end浪费不少时间  思维敏锐debug
            for k in range(i, i+l+1, 1):
                if sum(martrix[k][j:j+l+1]) != l+1:
                    return l
        return l+1


s = Solution()
matrix = [
    [1, 0, 1],
    [1, 1, 0],
    [1, 1, 0]
]
print(s.countSquares(matrix))
