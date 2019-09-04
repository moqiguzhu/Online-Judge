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

# 这个算法的实现可能是存在问题的


class Solution:
    # buildings already sorted by x
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        res = []
        if buildings is None or len(buildings) < 1:
            return res
        if len(buildings) == 1:
            return[[buildings[0][0], buildings[0][2]], [buildings[0][1], 0]]
        # 相同高度的建筑合并
        t = []
        prev = buildings[0]
        for i in range(1, len(buildings)):
            cur = buildings[i]
            if prev[2] != cur[2] or (prev[2] == cur[2] and prev[1] != cur[0]):
                t.append(prev)
                prev = cur
            else:
                prev = [prev[0], max(cur[1], prev[1]), prev[2]]
        t.append(prev)
        buildings = t
        print(buildings)

        t1, t2 = [], []
        for idx, e in enumerate(buildings):
            t1.append((idx, e[2]))
            t2.append((e[0], idx, e[2], 1))  # begin
            t2.append((e[1], idx, e[2], 0))  # end
            t2.append((e[0], idx, 0, 2))  # left x axis
            t2.append((e[1], idx, e[2], 3))  # right x axis

        t2 = sorted(t2, key=lambda x: x)
        # 预处理 合并相邻的同高度的building
        # 需要一个能够按key删除的pq
        pq = PriorityHeap(key=lambda x: -x[1])

        i = 0
        for m, idx, height, is_begin in t2:
            if is_begin == 1:
                pq.push((idx, height, 1))  # 1 means height
                e = pq.pop()
                res.append([m, e[1]])
                pq.push(e)
            elif is_begin == 0:
                e = pq.pop()
                res.append([m, e[1]])
                pq.push(e)
                pq.remove((idx, height, 1))
            elif is_begin == 2:
                pq.push((idx, 0, 0))  # 0 means x axis  set height to zero
                e = pq.pop()
                res.append([m, e[1]])
                pq.push(e)
            else:
                e = pq.pop()
                res.append([m, e[1]])
                pq.push(e)
                pq.remove((idx, 0, 0))
            i += 1
        print(res)

        # get result from res
        # 只取出上升或者下降的节点
        prev = res[0]
        t = []
        for i in range(1, len(res)):
            cur = res[i]
            if prev[1] != cur[1]:
                t.append(cur)
            prev = cur
        print(t)

        # 处理特殊情况
        final_res = []
        prev = t[0]
        for i in range(1, len(t)):
            cur = t[i]
            if prev[0] == cur[0]:
                if i == len(t) - 1:
                    pass
                else:
                    cur = [prev[0], max(prev[1], cur[1])]
            else:
                final_res.append(prev)
            prev = cur
        final_res.append(prev)
        return final_res


s = Solution()
buildings = [[2, 4, 7], [2, 4, 5], [2, 4, 6]]
print(s.getSkyline(buildings))
