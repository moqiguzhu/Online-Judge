import heapq


# class PriorityHeap(object):
#     def __init__(self, initial=None, key=lambda x: x):
#         self.key = key
#         if initial:
#             self.id = 0
#             self._data = [(key(item), idx, item)
#                           for idx, item in enumerate(initial)]
#             heapq.heapify(self._data)
#             self.id = len(initial)
#         else:
#             self._data = []
#             self.id = 0

#     def push(self, item):
#         heapq.heappush(self._data, (self.key(item), self.id, item))
#         self.id += 1

#     def pop(self):
#         return heapq.heappop(self._data)[2]

#     def length(self):
#         return len(self._data)


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
                self.d.pop(t[2])
                return t[2]
            t = heapq.heappop(self._data)
        return None

    def remove(self, item):
        self.d.pop(item)

    def length(self):
        return len(self.d)
