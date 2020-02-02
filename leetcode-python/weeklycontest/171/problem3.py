from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import inspect


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
    def makeConnected(self, n: int, connections: List[List[int]]) -> int:
        uf = UnionFind(range(n))
        cable_remain = 0
        for e1, e2 in connections:
            if uf.connected(e1, e2):
                cable_remain += 1
            else:
                uf.union(e1, e2)

        if cable_remain+1 >= uf.count:
            return uf.count - 1
        else:
            return -1


if __name__ == '__main__':
    s = Solution()
    n = 5
    connections = [[0, 1], [0, 2], [3, 4], [2, 3]]
    print(s.makeConnected(n, connections))
