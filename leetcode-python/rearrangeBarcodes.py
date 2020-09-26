import heapq
from collections import Counter
from functools import total_ordering


@total_ordering
class Barcode(object):
    def __init__(self, cnt, num):
        self.cnt = cnt
        self.num = num

    def _is_valid_operand(self, other):
        return (hasattr(other, "cnt") and
                hasattr(other, "num"))

    def __eq__(self, other):
        if not self._is_valid_operand(other):
            return NotImplemented
        return ((self.cnt, self.num) == (other.cnt, other.num))

    def __gt__(self, other):
        if not self._is_valid_operand(other):
            return NotImplemented
        return ((self.cnt, self.num) > (other.cnt, other.num))


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
    def rearrangeBarcodes1(self, barcodes):
        """
        :type barcodes: List[int]
        :rtype: List[int]
        """
        num_cnt = Counter(barcodes)
        heap_items = [Barcode(cnt, num) for num, cnt in num_cnt.items()]

        myheap = MyHeap(initial=heap_items, key=lambda x: -x.cnt)

        res = []

        while len(myheap._data) > 0:
            t = myheap.pop()
            if len(res) == 0 or t.num != res[-1]:
                res.append(t.num)
                t.cnt = t.cnt - 1
                if t.cnt > 0:
                    myheap.push(t)
            else:
                t1 = myheap.pop()  # must have one solution
                res.append(t1.num)
                myheap.push(t)
                t1.cnt = t1.cnt - 1
                if t1.cnt > 0:
                    myheap.push(t1)

        return res
    # https://leetcode.com/problems/distant-barcodes/discuss/299225/JavaPython-Set-Odd-Position-and-Even-Position

    def rearrangeBarcodes(self, packages):
        i, n = 0, len(packages)
        res = [0] * n
        for k, v in Counter(packages).most_common():
            for _ in range(v):
                res[i] = k
                i += 2
                if i >= n:
                    i = 1
        return res
