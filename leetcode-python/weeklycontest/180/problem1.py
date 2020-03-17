from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect

import inspect

import heapq


class Solution:
    def luckyNumbers(self, matrix: List[List[int]]) -> List[int]:
        t = np.array(matrix)
        t1, t2 = t.max(axis=0), t.min(axis=1)
        res = []
        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if matrix[i][j] == t1[j] and matrix[i][j] == t2[i]:
                    res.append(matrix[i][j])
        return res


if __name__ == '__main__':
    s = Solution()
    matrix = [[3, 7, 8], [9, 11, 13], [15, 16, 17]]
    print(s.luckyNumbers(matrix))
