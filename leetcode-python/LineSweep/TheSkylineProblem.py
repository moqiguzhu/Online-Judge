from typing import List
import heapq


class PriorityHeap(object):
    def __init__(self, initial=None, key=lambda x: x):
        self.key = key
        self.d = {}
        if initial:
            self.id = 0
            self._data = [(key(item), idx, item)
                          for idx, item in enumerate(initial)]
            heapq.heapify(self._data)
            self.id = len(initial)
            for e in self._data:
                self.d[e[2]] = e
        else:
            self._data = []
            self.id = 0
            self.d = {}

    def push(self, item):
        # 使用方保证不会插入重复item
        heapq.heappush(self._data, (self.key(item), self.id, item))
        self.d[item] = (self.key(item), self.id, item)
        self.id += 1

    def pop(self):
        if len(self.d) < 0:
            return None
        t = heapq.heappop(self._data)
        while t:
            if t[2] in self.d:
                return t[2]
            t = heapq.heappop(self._data)
        return None

    def remove(self, item):
        self.d.pop(item)

    def length(self):
        return len(self.d)


class Solution:
    # buildings already sorted by x
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        res = []
        if buildings is None or len(buildings) < 1:
            return res
        t1, t2 = [], []
        for idx, e in enumerate(buildings):
            t1.append((idx, e[2]))
            t2.append((e[0], idx, e[2], 1))  # begin
            t2.append((e[1], idx, e[2], 0))  # end

        t2 = sorted(t2, key=lambda x: x[0])
        # 预处理 合并相邻的同高度的building
        # 需要一个能够按key删除的pq
        pq = PriorityHeap(key=lambda x: -x[1])

        i = 0
        for m, idx, height, is_begin in t2:
            if is_begin:
                pq.push((idx, height))
                e = pq.pop()
                res.append([m, e[1]])
                pq.push(e)
            else:
                e = pq.pop()
                res.append([m, e[1]])
                pq.push(e)
                pq.remove((idx, height))
            i += 1

        # get result from res

        return res


s = Solution()
buildings = [[2, 9, 10], [3, 7, 15], [5, 12, 12], [15, 20, 10], [19, 24, 8]]
print(s.getSkyline(buildings))
