import math
# """
# This is MountainArray's API interface.
# You should not implement it, or speculate about its implementation
# """

class MountainArray(object):
    def __init__(self, data):
        self._data = data

    def get(self, index):
        """
        :type index: int
        :rtype int
        """
        return self._data[index]

    def length(self):
        """
        :rtype int
        """
        return len(self._data)


class Solution(object):
    def __init__(self):
        self.max = 1000000000 + 1

    def findInMountainArray(self, target, mountain_arr):
        """
        :type target: integer
        :type mountain_arr: MountainArray
        :rtype: integer
        """
        summit_index = self.find_summit(mountain_arr, 0, mountain_arr.length())
        if mountain_arr.get(summit_index) == target:
            return summit_index
        else:
            t = min(self.help(target, mountain_arr, 0, summit_index, True), self.help(target, mountain_arr, summit_index+1, mountain_arr.length(), False))
            return t if t != self.max else -1

    def find_summit(self, mountain_arr, begin, end):
        if begin >= end:
            return -1  # could not be case
        if end - begin == 1:
            assert(mountain_arr.get(begin) > mountain_arr.get(begin-1))
            assert(mountain_arr.get(begin) > mountain_arr.get(begin+1))  # summit
            return begin
        if end - begin == 2:
            if begin -1 >= 0 and mountain_arr.get(begin) > mountain_arr.get(begin-1) and begin+1 < mountain_arr.length() and mountain_arr.get(begin) > mountain_arr.get(begin+1):
                return begin
            else:
                assert(mountain_arr.get(begin+1) > mountain_arr.get(begin))
                assert(mountain_arr.get(begin+1) > mountain_arr.get(begin+2))  # summit
                return begin+1
        mid = int(math.floor((begin + end) / 2)) # ???
        if mountain_arr.get(mid) > mountain_arr.get(mid+1) and mountain_arr.get(mid) > mountain_arr.get(mid-1):
            return mid
        elif mountain_arr.get(mid) > mountain_arr.get(mid+1) and mountain_arr.get(mid) < mountain_arr.get(mid-1):
            return self.find_summit(mountain_arr, begin, mid)
        else:
            return self.find_summit(mountain_arr, mid+1, end)

    # order 从小到大还是从大到小
    def help(self, target, mountain_arr, begin, end, order):
        # 边界
        if begin >= end:
            return self.max
        if end - begin <= 2:
            for i in range(begin, end):
                if target == mountain_arr.get(i):
                    return i
            return self.max
        mid = int(math.floor((begin + end) / 2)) # ???
        if target == mountain_arr.get(mid):
            return mid
        elif target > mountain_arr.get(mid):
            if order:
                return self.help(target, mountain_arr, mid+1, end, order)
            else:
                return self.help(target, mountain_arr, begin, mid, order)
        else:
            if order:
                return self.help(target, mountain_arr, begin, mid, order)
            else:
                return self.help(target, mountain_arr, mid+1, end, order)

s = Solution()
data = [0,5,3,1]
target = 0
mountain_arr = MountainArray(data)
print(s.findInMountainArray(target, mountain_arr))