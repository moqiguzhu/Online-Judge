import copy
from typing import List
import collections
import bisect
import random


class MajorityChecker:
    def __init__(self, arr: List[int]):
        self.data = [{}]
        for i in range(1, len(arr)+1, 1):
            d = copy.deepcopy(self.data[i-1])
            t = 1 if arr[i-1] not in d else d[arr[i-1]] + 1
            d[arr[i-1]] = t
            self.data.append(d)
        print(self.data)

    def query(self, left: int, right: int, threshold: int) -> int:
        t = [(e, self.data[right+1][e] - self.data[left].get(e, 0))
             for e in self.data[right+1]]
        print(t)
        m = max(t, key=lambda x: x[1])
        if m[1] >= threshold:
            return m[0]
        else:
            return -1

# random pick - elegant


class MajorityChecker1(object):

    def __init__(self, A):
        a2i = collections.defaultdict(list)
        for i, x in enumerate(A):
            a2i[x].append(i)
        self.A, self.a2i = A, a2i

    def query(self, left, right, threshold):
        for _ in range(10):
            a = self.A[random.randint(left, right)]
            l = bisect.bisect_left(self.a2i[a], left)
            r = bisect.bisect_right(self.a2i[a], right)
            if r - l >= threshold:
                return a
        return -1


# Your MajorityChecker object will be instantiated and called as such:
arr = [1, 1, 2, 2, 1, 1]
obj = MajorityChecker(arr)
print(obj.query(2, 3, 2))
