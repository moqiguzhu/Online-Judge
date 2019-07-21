import heapq
import numpy as np


class MyHeap(object):
    def __init__(self, initial=None, key=lambda x: x):
        self.key = key
        if initial:
            self._data = [(key(item), item) for item in initial]
            heapq.heapify(self._data)
        else:
            self._data = []

    def push(self, item):
        heapq.heappush(self._data, (self.key(item), item))

    def pop(self):
        return heapq.heappop(self._data)[1]


class Solution(object):
    def mincostToHireWorkers(self, quality, wage, K):
        """
        :type quality: List[int]
        :type wage: List[int]
        :type K: int
        :rtype: float
        """
        if len(quality) < K or len(quality) != len(wage):
            return -1
        
        heap = None

        ratios = []
        for i in range(len(quality)):
            ratios.append(wage[i] / quality[i])
        
        t = np.argsort(ratios)
        quality_init = [quality[i] for i in t[0:K]]
        
        heap = MyHeap(quality_init, key = lambda x : -x) # 大顶堆

        res = sum(quality_init) * ratios[t[K-1]]
        quality_sum = sum(quality_init)

        i = K
        while i < len(quality):
            t1 = t[i]
            x = heap.pop()
            quality_sum = quality_sum - x + quality[t1]
            res = min(quality_sum * ratios[t1], res)
            heap.push(quality[t1])
            i = i + 1
        return res

s = Solution()
quality = [32,43,66,9,94,57,25,44,99,19]
wage = [187,366,117,363,121,494,348,382,385,262]
K = 4
print(s.mincostToHireWorkers(quality, wage, K))
