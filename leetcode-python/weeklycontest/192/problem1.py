from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest


class Solution:
    def shuffle(self, nums: List[int], n: int) -> List[int]:
        l = len(nums)
        assert(l % 2 == 0)
        assert(l / 2 == n)

        if l == 0:
            return nums
        res = [0] * l
        for i in range(0, l // 2):
            res[2*i] = nums[i]
            res[2*i+1] = nums[i + n]
        return res


if __name__ == "__main__":
    s = Solution()
    nums = [1, 1, 2, 2]
    n = 2
    print(s.shuffle(nums, n))
