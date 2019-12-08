from collections import defaultdict, deque, Counter
from typing import List
import math
import copy


class Solution:
    def smallestDivisor(self, nums: List[int], threshold: int) -> int:
        start, end = 1, max(nums)
        return self.helper2(nums, threshold, start, end)

    def helper2(self, nums: List[int], threshold: int, start: int, end: int):
        mid = (start + end) >> 1
        if mid == start:
            return start
        if mid == start + 1 and mid + 1 == end:
            t1 = self.helper1(nums, start)
            t2 = self.helper1(nums, mid)
            if t1 <= threshold:
                return start
            else:
                return mid

        t = self.helper1(nums, mid)
        if t > threshold:
            return self.helper2(nums, threshold, mid+1, end)
        else:
            return self.helper2(nums, threshold, start, mid+1)

    def helper1(self, nums, divisor: int):
        res = 0
        for num in nums:
            res += math.ceil(num / divisor)
        return res


if __name__ == '__main__':
    s = Solution()
    nums = [962551, 933661, 905225, 923035, 990560]
    threshold = 10
    print(s.smallestDivisor(nums, threshold))
