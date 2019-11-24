from typing import List


class Solution:
    def movesToMakeZigzag(self, nums: List[int]) -> int:
        if nums is None or len(nums) < 2:
            return 0
        if len(nums) == 2:
            if nums[0] == nums[1]:
                return 1
            else:
                return 0
        res1, res2 = 0, 0
        nums_copy = [e for e in nums]
        for i in range(0, len(nums), 2):
            if i - 1 < 0:
                pass
            else:
                t = min(nums[i-1], nums[i]-1)
                res1 += nums[i-1] - t
                nums[i-1] = t
            if i + 1 >= len(nums):
                pass
            else:
                t = min(nums[i+1], nums[i]-1)
                res1 += nums[i+1] - t
                nums[i+1] = t
        nums = nums_copy
        for i in range(1, len(nums), 2):
            if i - 1 < 0:
                pass
            else:
                t = min(nums[i-1], nums[i]-1)
                res2 += nums[i-1] - t
                nums[i-1] = t
            if i + 1 >= len(nums):
                pass
            else:
                t = min(nums[i+1], nums[i]-1)
                res2 += nums[i+1] - t
                nums[i+1] = t
        return min(res1, res2)


s = Solution()
nums = [2, 7, 10, 9, 8, 9]
print(s.movesToMakeZigzag(nums))
