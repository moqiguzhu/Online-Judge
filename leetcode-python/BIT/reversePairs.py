from BinaryIndexTree import BinaryIndexTree
from typing import List
import bisect


class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        n = len(nums)
        bit = BinaryIndexTree(n)

        res = 0
        nums_sorted = sorted(nums)
        for e in nums:
            idx = bisect.bisect_left(nums_sorted, 2*e + 1)
            res += bit.range_sum(idx, n-1)
            idx1 = bisect.bisect_left(nums_sorted, e)
            bit.update(idx1, 1)
        return res


s = Solution()
nums = [2, 4, 3, 5, 1]
print(s.reversePairs(nums))
