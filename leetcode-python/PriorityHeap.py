import heapq

class PriorityHeap(object):
    def __init__(self, initial=None, key=lambda x: x):
        self.key = key
        if initial:
            self.id = 0
            self._data = [(key(item), idx, item) for idx, item in enumerate(initial)]
            heapq.heapify(self._data)
            self.id = len(initial)
        else:
            self._data = []
            self.id = 0

    def push(self, item):
        heapq.heappush(self._data, (self.key(item), self.id, item))
        self.id += 1

    def pop(self):
        return heapq.heappop(self._data)[2]

heap = PriorityHeap([1,2,2])
print(heap.pop())
