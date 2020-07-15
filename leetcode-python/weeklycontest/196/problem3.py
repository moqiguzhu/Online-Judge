from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest


class Solution:
    # 这个方法考虑不全面, 很多case不能通过
    def numSubmat(self, mat: List[List[int]]) -> int:
        n, m = len(mat), len(mat[0])

        dp_left = [[0] * (m+1) for _ in range(n+1)]
        dp_up = [[0] * (m+1) for _ in range(n+1)]
        dp_left_up = [[0] * (m+1) for _ in range(n+1)]
        res = 0
        for i in range(1, n+1, 1):
            for j in range(1, m+1, 1):
                if mat[i-1][j-1] == 0:
                    dp_left[i][j], dp_up[i][j], dp_left_up[i][j] = 0, 0, 0
                else:
                    dp_left[i][j], dp_up[i][j], dp_left_up[i][j] = dp_left[i][j -
                                                                              1] + 1, dp_up[i-1][j] + 1, min([dp_left_up[i-1][j-1], dp_left[i][j - 1], dp_up[i-1][j]]) + 1

                    res += (dp_left[i][j] + dp_up[i][j] + dp_left_up[i][j] - 2)
        return res


if __name__ == "__main__":
    s = Solution()
    mat = [[1, 0, 1], [0, 1, 0], [1, 0, 1]]
    print(s.numSubmat(mat))
