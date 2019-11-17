from collections import defaultdict, deque, Counter
from typing import List
import math
import copy


class Solution:
    def maxSumDivThree(self, nums: List[int]) -> int:
        if nums is None or len(nums) == 0:
            return 0
        n = len(nums)
        t1, t2 = [-10**9] * 3, [-10**9] * 3
        for i in range(n):
            tt = nums[i] % 3
            if tt == 0:
                t2[1] = t1[1] + nums[i]
                t2[2] = t1[2] + nums[i]
                t2[0] = t1[0] + nums[i]
            elif tt == 1:
                t2[0] = t1[2] + nums[i]
                t2[1] = t1[0] + nums[i]
                t2[2] = t1[1] + nums[i]
            else:
                t2[0] = t1[1] + nums[i]
                t2[1] = t1[2] + nums[i]
                t2[2] = t1[0] + nums[i]
            t1 = [max(t1[i], t2[i]) for i in range(3)]
            t1[tt] = max(t1[tt], nums[i])
        return max(t1[0], 0)


s = Solution()
nums = [3, 6, 5, 1, 8]
print(s.maxSumDivThree(nums))
