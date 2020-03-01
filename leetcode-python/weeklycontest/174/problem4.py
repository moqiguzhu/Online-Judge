from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import inspect

# 题目没读懂
# bad code base 影响太大了

# TLE 实现不优雅


class Solution:
    def maxJumps(self, arr: List[int], d: int) -> int:
        dd = {}
        n = len(arr)
        for i in range(n-1):
            for j in range(i+1, n):
                if i + 1 == j:
                    dd[(i, j)] = min(arr[i], arr[j])
                    cur = 0
                else:
                    cur = max(min(arr[i], arr[j]), max(cur, arr[j-1]))
                    dd[(i, j)] = cur

        t = [(idx, e) for idx, e in enumerate(arr)]
        t.sort(key=lambda x: x[1])

        res = {}
        for i in range(len(t)):
            res[t[i][0]] = 1
            for j in range(0, i):
                t1, t2 = min(t[j][0], t[i][0]), max(t[j][0], t[i][0])
                if t2 - t1 <= d and t[i][1] > dd[(t1, t2)]:
                    if 1+res[t[j][0]] > res[t[i][0]]:
                        res[t[i][0]] = 1+res[t[j][0]]
        return max([v for k, v in res.items()])


if __name__ == '__main__':
    s = Solution()
    arr = [6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12]
    d = 2
    print(s.maxJumps(arr, d))
