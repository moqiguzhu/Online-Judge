from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest
import string


class UnionFind(object):
    def __init__(self, eles):
        n = len(eles)
        self.e_idx = dict([(e, idx) for idx, e in enumerate(eles)])
        self.parent = list(range(n))
        self.rank = [0] * n
        self.count = n

    def union(self, e1, e2):
        if self.connected(e1, e2):
            return
        p1 = self.find(e1)
        p2 = self.find(e2)

        if self.rank[p1] > self.rank[p2]:
            self.parent[p2] = p1
        elif self.rank[p1] < self.rank[p2]:
            self.parent[p1] = p2
        else:
            self.parent[p2] = p1
            self.rank[p1] += 1
        self.count -= 1

    # 路径压缩
    def find(self, e):
        idx = self.e_idx[e]
        while self.parent[idx] != self.parent[self.parent[idx]]:
            self.parent[idx] = self.parent[self.parent[idx]]
        return self.parent[idx]

    def get_count(self):
        return self.count

    def connected(self, e1, e2):
        return self.find(e1) == self.find(e2)

    def get_index(self, e):
        return self.e_idx[e]


class Solution:
    def maxNumEdgesToRemove(self, n: int, edges: List[List[int]]) -> int:
        eles = range(1, n+1)
        uf3 = UnionFind(eles)
        res = 0
        for idx, e in enumerate(edges):
            type_, start, end = e
            if type_ == 3:
                if uf3.connected(start, end):
                    res += 1
                else:
                    uf3.union(start, end)
        uf1, uf2 = copy.deepcopy(uf3), copy.deepcopy(uf3)

        for idx, e in enumerate(edges):
            type_, start, end = e
            if type_ == 1:
                if uf1.connected(start, end):
                    res += 1
                else:
                    uf1.union(start, end)
            if type_ == 2:
                if uf2.connected(start, end):
                    res += 1
                else:
                    uf2.union(start, end)
        if (uf1.get_count() > 1 or uf2.get_count() > 1):
            return -1
        else:
            return res


if __name__ == "__main__":
    s = Solution()
    n = 13
    edges = [[1, 1, 2], [2, 2, 3], [2, 3, 4], [1, 3, 5], [3, 2, 6], [2, 3, 7], [3, 7, 8], [3, 2, 9], [2, 4, 10], [2, 9, 11], [1, 2, 12], [3, 4, 13], [2, 2, 7], [1, 1, 9], [1, 2, 13], [2, 7, 13], [3, 2, 3], [1, 7, 10], [2, 8, 11], [1, 2, 7], [2, 1, 9], [
        2, 2, 9], [1, 5, 6], [2, 4, 9], [1, 7, 8], [1, 4, 6], [1, 4, 9], [3, 7, 13], [2, 2, 8], [2, 2, 6], [1, 1, 10], [1, 1, 11], [2, 5, 10], [1, 2, 9], [2, 1, 2], [1, 3, 4], [3, 6, 8], [3, 6, 13], [1, 3, 8], [1, 1, 6], [3, 3, 9], [1, 2, 3], [1, 11, 13]]
    print(s.maxNumEdgesToRemove(n, edges))
