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
    def runningSum(self, nums: List[int]) -> List[int]:
        res = [0] * (len(nums) + 1)
        for idx, e in enumerate(nums):
            res[idx+1] = res[idx] + e
        return res[1:]


if __name__ == "__main__":
    s = Solution()
    nums = [1, 2, 3, 4]
    print(s.runningSum(nums))
