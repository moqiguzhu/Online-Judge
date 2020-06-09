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
    # tle
    def minReorder1(self, n: int, connections: List[List[int]]) -> int:
        g = np.zeros(n, n, dtype=int)
        g1 = defaultdict(set)
        for e in connections:
            begin, end = e
            g[begin][end] = 1
            g1[begin].add(end)
            g1[end].add(begin)
        res = 0
        target = [0]
        candidates = g1[0]
        visited = set()
        while len(visited) < n:
            t = set()
            for begin in candidates:
                t.update(g1[begin])
                for end in target:
                    if g[begin][end] == 1:
                        continue
                    elif g[end][begin] == 1:
                        g[begin][end] = 1
                        g[end][begin] = 0
                        res = res + 1
                    else:
                        continue
            visited.update(target)
            target = candidates
            candidates = set([e for e in t if e not in visited])
        return res

    # accept
    def minReorder2(self, n: int, connections: List[List[int]]) -> int:
        g = set()
        for e in connections:
            g.add((e[0], e[1]))
        g1 = defaultdict(set)
        for e in connections:
            begin, end = e
            g1[begin].add(end)
            g1[end].add(begin)
        res = 0
        candidates = [(e, 0) for e in g1[0]]
        visited = set([0])
        while len(candidates) > 0:
            t = set()
            for begin, end in candidates:
                if (begin, end) not in g:
                    res += 1
                visited.add(begin)
                t.add(begin)
            candidates = []
            for end in t:
                for begin in g1[end]:
                    if begin not in visited:
                        candidates.append((begin, end))
        return res
    # memory error

    def minReorder(self, n: int, connections: List[List[int]]) -> int:
        # 分配内存速度快很多
        g = np.zeros((n, n), dtype=int)
        g1 = defaultdict(set)
        for e in connections:
            begin, end = e
            g[begin][end] = 1
            g1[begin].add(end)
            g1[end].add(begin)
        res = 0
        candidates = [(e, 0) for e in g1[0]]
        visited = set([0])
        while len(candidates) > 0:
            t = set()
            for begin, end in candidates:
                if g[begin][end] != 1:
                    res += 1
                visited.add(begin)
                t.add(begin)
            candidates = []
            for end in t:
                for begin in g1[end]:
                    if begin not in visited:
                        candidates.append((begin, end))
        return res


if __name__ == "__main__":
    s = Solution()
    n = 6
    connections = [[0, 1], [1, 3], [2, 3], [4, 0], [4, 5]]
    print(s.minReorder(n, connections))
