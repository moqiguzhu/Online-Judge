import numpy as np
from typing import List
from collections import defaultdict
import datetime
from line_profiler import LineProfiler

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
        self.cluster_size = [1] * n

    def union(self, e1, e2):
        if self.connected(e1, e2):
            return
        p1 = self.find(e1)
        p2 = self.find(e2)

        if self.rank[p1] > self.rank[p2]:
            self.parent[p2] = p1
            self.cluster_size[p1] += self.cluster_size[p2]
        elif self.rank[p1] < self.rank[p2]:
            self.parent[p1] = p2
            self.cluster_size[p2] += self.cluster_size[p1]
        else:
            self.parent[p2] = p1
            self.rank[p1] += 1
            self.cluster_size[p1] += self.cluster_size[p2]
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
    def has_edge(self, n1, n2):
        t1, t2 = min(n1, n2), max(n1, n2)
        if t1 == 1:
            return False
        if t2 % t1 == 0:
            return True
        for e in range(2, t1+1, 1):
            if n1 % e == 0 and n2 % e == 0:
                # print(t1, t2)
                return True
        return False

    def get_primes(self, n):
        primes = []
        is_prime = np.ones(n+1, dtype=np.bool)
        for i in range(2, int(n**0.5)+1):
            if is_prime[i]:
                is_prime[i*i::i] = 0
        primes = set(np.nonzero(is_prime)[0][2:])
        return primes

    # n^2时间复杂度

    def largestComponentSize1(self, A: List[int]) -> int:
        if A is None or len(A) < 1:
            return 0
        uf = UnionFind(A)
        for i in range(0, len(A)):
            for j in range(i+1, len(A)):
                if uf.get_count() == 1:
                    return len(A)
                if self.has_edge(A[i], A[j]):
                    uf.union(A[i], A[j])
        return max(uf.cluster_size)

    # still tle
    def largestComponentSize2(self, A: List[int]) -> int:
        if A is None or len(A) < 1:
            return 0
        uf = UnionFind(A)

        visited = set()
        unvisited = set(A)
        while len(unvisited) > 0:
            t = next(iter(unvisited))
            visited.add(t)
            unvisited.remove(t)
            for e in visited:
                if e != t and self.has_edge(e, t):
                    # print(e, t)
                    uf.union(e, t)
                    visited.add(e)
                    if e in unvisited:
                        unvisited.remove(e)
        return max(uf.cluster_size)

    # still tle???
    # profile看看性能瓶颈在哪里
    # 先搞个虚拟环境，python=3.6 安装line_profiler
    @profile
    def largestComponentSize(self, A: List[int]) -> int:
        print(datetime.datetime.now())
        if A is None or len(A) < 1:
            return 0
        uf = UnionFind(A)

        primes = self.get_primes(100000)
        prime_l = defaultdict(list)
        for e in A:
            if e in primes:
                prime_l[e].append(e)
                continue
            # [prime_l[p].append(e) if e % p == 0 else None for p in primes]
            for p in primes:
                if e % p == 0:
                    prime_l[p].append(e)
        print(datetime.datetime.now())
        for k, v in prime_l.items():
            for i in range(0, len(v)-1):
                uf.union(v[i], v[i+1])

        return max(uf.cluster_size)


s = Solution()
A = [
    4096,
    8195,
    14,
    24,
    4122,
    36,
    3761,
    6548,
    350,
    54,
    8249,
    4155,
    8252,
    70,
    9004,
    2120,
    4169,
    6224,
    4110,
    87,
    6233,
    4186,
    6238,
    4192,
    1382,
    4199,
    104,
    2153,
    1845,
    8310,
    4231,
    2185,
    4245,
    2212,
    4261,
    8359,
    2222,
    483,
    1506,
    8371,
    180,
    2230,
    6333,
    2238,
    2244,
    197,
    8391,
    6353,
    210,
    215,
    216,
    2265,
    4315,
    5872,
    222,
    224,
    8418,
    6371,
    4326,
    234,
    4331,
    4332,
    4334,
    6384,
    6387,
    4342,
    248,
    2298,
    4350,
    260,
    8454,
    2316,
    270,
    6419,
    277,
    6430,
    6431,
    8483,
    2341,
    310,
    311,
    8521,
    8525,
    4432,
    6483,
    6487,
    8541,
    8542,
    365,
    370,
    4473,
    396,
    8596,
    4501,
    8598,
    6551,
    408,
    2458,
    4510,
    4513,
    4515,
    6571,
    2476,
    8622,
    4530,
    8628,
    2486,
    8265,
    8632,
    8642,
    458,
    2512,
    4563,
    4569,
    4577,
    763,
    6629,
    6642,
    8700,
    512,
    4609,
    6659,
    5206,
    8961,
    6665,
    2571,
    4623,
    8721,
    4630,
    6679,
    8731,
    6685,
    8740,
    4650,
    557,
    8752,
    8884,
    4660,
    4665,
    4674,
    2629,
    4678,
    2632,
    4793,
    2635,
    6737,
    4690,
    2652,
    2656,
    2659,
    8807,
    6764,
    628,
    629,
    2679,
    6779,
    2685,
    2688,
    6786,
    4740,
    6795,
    2700,
    654,
    4751,
    5682,
    6807,
    678,
    5575,
    4785,
    2738,
    2739,
    2740,
    2743,
    1140,
    6844,
    4800,
    2754,
    9675,
    6853,
    712,
    4809,
    717,
    8925,
    8930,
    6883,
    4837,
    8936,
    8944,
    4849,
    754,
    3162,
    4853,
    6906,
    4859,
    766,
    769,
    812,
    2832,
    2834,
    796,
    799,
    8994,
    6947,
    4908,
    813,
    5619,
    817,
    6962,
    8329,
    4924,
    6975,
    2881,
    4930,
    9507,
    9034,
    9036,
    6989,
    9038,
    9041,
    7651,
    2904,
    9055,
    9069,
    7025,
    2931,
    2933,
    892,
    7038,
    7040,
    4994,
    2953,
    2955,
    7069,
    9119,
    5031,
    9129,
    5035,
    7086,
    1523,
    9143,
    9144,
    7099,
    5062,
    3018,
    9163,
    7121,
    9173,
    7130,
    3035,
    6309,
    3050,
    5102,
    3055,
    1010,
    1015,
    7160,
    7162,
    5115,
    5118,
    8704,
    7171,
    9220,
    9225,
    7179,
    7183,
    1040,
    7189,
    9240,
    9252,
    3112,
    7215,
    5171,
    5173,
    7226,
    9280,
    5186,
    7240,
    9295,
    5200,
    5201,
    5202,
    3158,
    9306,
    7268,
    1131,
    7276,
    5232,
    9332,
    1151,
    9345,
    1154,
    7303,
    7309,
    1182,
    7327,
    1184,
    1190,
    7337,
    7341,
    1199,
    9396,
    7353,
    9404,
    3275,
    7370,
    1227,
    7374,
    3284,
    3289,
    3299,
    5348,
    5349,
    1255,
    1266,
    5371,
    5376,
    9481,
    7438,
    8408,
    7444,
    7726,
    3355,
    1313,
    6363,
    3371,
    3373,
    5425,
    9527,
    9531,
    7486,
    3394,
    5443,
    5444,
    7494,
    9547,
    5454,
    9569,
    3429,
    5478,
    7533,
    3445,
    9591,
    4330,
    1406,
    1600,
    3468,
    3471,
    5527,
    7581,
    7591,
    9642,
    5557,
    9657,
    7071,
    7614,
    7618,
    3523,
    9668,
    1479,
    5576,
    7627,
    9676,
    9680,
    7639,
    9698,
    5603,
    1513,
    2642,
    9712,
    7763,
    5621,
    7081,
    9722,
    9728,
    9803,
    9733,
    3592,
    1546,
    5038,
    9753,
    1582,
    7730,
    3637,
    5696,
    9794,
    1609,
    951,
    7756,
    7758,
    5711,
    1617,
    9811,
    7764,
    1632,
    5738,
    9836,
    1647,
    3703,
    5754,
    9851,
    1661,
    5397,
    2668,
    9867,
    3728,
    9874,
    7827,
    1684,
    7834,
    1693,
    3747,
    3748,
    7856,
    9905,
    3766,
    1728,
    5828,
    2308,
    1735,
    7881,
    5834,
    7887,
    1745,
    6435,
    7896,
    1754,
    9950,
    8485,
    1760,
    9954,
    3822,
    3824,
    1777,
    1782,
    981,
    5891,
    7942,
    7946,
    8168,
    7958,
    5912,
    4834,
    5915,
    7973,
    5930,
    5941,
    5943,
    3899,
    5951,
    8005,
    8006,
    8161,
    1864,
    8013,
    3919,
    3926,
    8024,
    8038,
    6120,
    6004,
    6013,
    6015,
    6026,
    1933,
    6031,
    8081,
    3990,
    1944,
    1947,
    1952,
    6055,
    1963,
    8111,
    4024,
    6074,
    6075,
    5451,
    1997,
    2002,
    4053,
    8152,
    4057,
    4059,
    4063,
    2016,
    6113,
    4072,
    8179,
    4089
]
print(s.largestComponentSize(A))
