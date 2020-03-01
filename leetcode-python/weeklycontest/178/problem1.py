from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect


class Solution:
    def smallerNumbersThanCurrent(self, nums: List[int]) -> List[int]:
        res = []
        sorted_nums = sorted(nums)
        for num in nums:
            res.append(bisect.bisect_left(sorted_nums, num))
        return res


if __name__ == '__main__':
    s = Solution()
    nums = [7, 7, 7, 7]
    print(s.smallerNumbersThanCurrent(nums))
