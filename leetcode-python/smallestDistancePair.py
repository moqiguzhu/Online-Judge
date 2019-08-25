from typing import List


class Solution:
    def smallestDistancePair(self, nums: List[int], k: int) -> int:
        nums.sort()
        n = len(nums)
        l, r = 0, nums[n-1] - nums[0]

        cnt = 0
        while l < r:
            cnt = 0
            m = l + (r-l) // 2
            j = 0
            for i in range(n):
                while j < n and nums[j] <= nums[i] + m:
                    j += 1
                    cnt += j - i - 1
            if cnt < k:
                l = m + 1
            else:
                r = m
        return l


s = Solution()
nums = [1, 6, 1]
k = 3
print(s.smallestDistancePair(nums, k))
