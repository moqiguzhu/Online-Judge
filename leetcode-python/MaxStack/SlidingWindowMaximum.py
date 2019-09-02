from typing import List
from MaxQueue import MaxQueue


class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        res = []
        mq = MaxQueue(nums[0:k])
        for i in range(k, len(nums)):
            res.append(mq.get_max())
            mq.pop()
            mq.push(nums[i])
        res.append(mq.get_max())
        return res


s = Solution()
nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3
print(s.maxSlidingWindow(nums, k))
