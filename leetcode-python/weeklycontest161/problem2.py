from typing import List


class Solution:
    def numberOfSubarrays(self, nums: List[int], k: int) -> int:
        odd_flag = [0 if e % 2 == 0 else 1 for e in nums]
        n = len(nums)
        t = 0
        for j in range(n):
            t += odd_flag[j]
            if t == k:
                break
        i, j = 0, t
        res = 0
        while True:
            # 实现太混乱
            pass

        return res


s = Solution()
nums = [2, 2, 2, 1, 2, 2, 1, 2, 2, 2]
print(s.numberOfSubarrays(nums, 2))
