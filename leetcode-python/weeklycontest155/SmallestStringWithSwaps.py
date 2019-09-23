from typing import List
from collections import defaultdict


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

    def cluster(self):
        res = []
        for e in self.e_idx.keys():
            res.append(self.find(e))
        return res


class Solution:
    def smallestStringWithSwaps(self, s: str, pairs: List[List[int]]) -> str:
        if s is None or len(s) < 2:
            return s
        if pairs is None or len(pairs) < 1:
            return s

        uf = UnionFind([(idx, e) for idx, e in enumerate(s)])
        for idx1, idx2 in pairs:
            uf.union((idx1, s[idx1]), (idx2, s[idx2]))

        res = [''] * len(s)
        p_idx = defaultdict(list)
        print(uf.cluster())
        for idx, e in enumerate(uf.cluster()):
            p_idx[e].append(idx)
        print(p_idx)
        for k,  v in p_idx.items():
            if len(v) == 1:
                res[v[0]] = s[v[0]]
            else:
                t_s = [s[idx] for idx in v]
                t_s = sorted(t_s)
                for i, idx in enumerate(v):
                    res[idx] = t_s[i]
        return ''.join(res)


so = Solution()
s = "udyyek"
pairs = [[3, 3], [3, 0], [5, 1], [3, 1], [3, 4], [3, 5]]
print(so.smallestStringWithSwaps(s, pairs))
