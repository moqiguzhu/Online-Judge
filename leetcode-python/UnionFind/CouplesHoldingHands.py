from typing import List

try:
    profile  # throws an exception when profile isn't defined
except NameError:
    # if it's not defined simply ignore the decorator.
    def profile(x): return x


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

# 没 UnionFind 什么事儿～


class Solution:
    @profile
    def minSwapsCouples(self, row: List[int]) -> int:
        if row is None or len(row) < 2 or len(row) % 2 != 0:
            return -1
        a2i = {}
        for idx, e in enumerate(row):
            a2i[e] = idx

        # uf = UnionFind(row)

        res = 0
        for i in range(0, len(row), 2):
            if (row[i] % 2 == 1 and row[i+1] == row[i]-1) or (row[i] % 2 == 0 and row[i+1] == row[i]+1):
                # uf.union(row[i], row[i+1])
                continue
            t = row[i]-1 if row[i] % 2 == 1 else row[i] + 1
            idx = a2i[t]

            a2i[row[i+1]] = idx
            a2i[row[idx]] = i+1

            tt = row[idx]
            row[idx] = row[i+1]
            row[i+1] == tt

            res += 1
        return res


s = Solution()
row = [3, 2, 0, 1]
print(s.minSwapsCouples(row))
