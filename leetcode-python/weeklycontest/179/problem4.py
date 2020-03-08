from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect

import inspect


class Solution:
    def __init__(self):
        self.paths = []

    def frogPosition(self, n: int, edges: List[List[int]], t: int, target: int) -> float:
        g = defaultdict(list)
        for e1, e2 in edges:
            g[e1].append(e2)
            g[e2].append(e1)
        # w是计算不出来的(不固定) 需要先计算路径, 然后算概率
        # w = [[0] * (n+1) for _ in range(n+1)]
        # for e1, row in g.items():
        #     tt = len(row)
        #     for e2 in row:
        #         if tt == 0:
        #             w[e1][e2] = 0
        #         else:
        #             w[e1][e2] = 1 / tt
        # print(w)
        visited = [0] * (n+1)
        visited[1] = 1
        self.findpath(g, 1, target, t, [], visited)
        # print(self.paths)
        res = max([self.calc_w(path, g) for path in self.paths] + [0])
        return res
    # dfs

    def findpath(self, g, begin, end, t, path, visited):
        if t < 0:
            return
        if t == 0 and begin == end:
            self.paths.append(copy.deepcopy(path))
            return
        flag = True
        for e in g[begin]:
            if visited[e] == 0:
                flag = False
                break
        if flag:
            if begin == end:
                self.paths.append(copy.deepcopy(path))
                return
            else:
                return
        else:
            for e in g[begin]:
                if visited[e] == 0:
                    path.append((begin, e))
                    visited[e] = 1
                    self.findpath(g, e, end, t - 1, path, visited)
                    path.pop(-1)
                    visited[e] = 0

    def calc_w(self, path, g):
        visited = set()
        visited.add(1)
        w = 1
        for e1, e2 in path:
            w *= 1 / len([e for e in g[e1] if e not in visited])
            visited.add(e2)
        return w


if __name__ == '__main__':
    s = Solution()
    n = 9
    edges = [[2, 1], [3, 1], [4, 2], [5, 1], [6, 2], [7, 5], [8, 7], [9, 7]]
    t = 6
    target = 8
    print(s.frogPosition(n, edges, t, target))
