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
    def minDays(self, bloomDay: List[int], m: int, k: int) -> int:
        mi, ma = min(bloomDay), max(bloomDay)
        return self.help(bloomDay, m, k, mi, ma)

    def help(self, bloomDay: List[int], m, k, begin: int, end: int):
        if begin == end:
            if self.test(bloomDay, m, k, begin):
                return begin
            else:
                return -1
        if end == begin + 1:
            t1, t2 = self.test(bloomDay, m, k, begin), self.test(
                bloomDay, m, k, end)
            if t1:
                return begin
            elif t2:
                return end
            else:
                return -1

        mid = (begin + end) >> 1
        t = self.test(bloomDay, m, k, mid)
        if t:
            return self.help(bloomDay, m, k, begin, mid)
        else:
            return self.help(bloomDay, m, k, mid+1, end)

    def test(self, bloomDay: List[int], m, k, threshold: int) -> bool:
        t = [1 if e <= threshold else 0 for e in bloomDay]
        res = []
        for idx, e in enumerate(t):
            if idx == 0:
                if e == 0:
                    begin, end = 0, -1
                else:
                    begin, end = 0, 0
                continue

            if e == 0:
                if t[idx-1] == 1:
                    res.append(end - begin + 1)
            else:
                if t[idx-1] == 1:
                    end = idx
                else:
                    begin, end = idx, idx
        if t[-1] == 1:
            res.append(end - begin + 1)

        if sum([e // k for e in res]) >= m:
            return True
        else:
            return False


if __name__ == "__main__":
    s = Solution()
    bloomDay = [1, 10, 2, 9, 3, 8, 4, 7, 5, 6]
    m = 4
    k = 2
    print(s.minDays(bloomDay, m, k))
