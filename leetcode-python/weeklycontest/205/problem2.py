from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest
import string


class Solution:
    def numTriplets(self, nums1: List[int], nums2: List[int]) -> int:
        n1, n2 = len(nums1), len(nums2)
        d1, d2 = defaultdict(int), defaultdict(int)
        for j in range(n1):
            for k in range(j+1, n1):
                d1[nums1[j] * nums1[k]] += 1

        for j in range(n2):
            for k in range(j+1, n2):
                d2[nums2[j] * nums2[k]] += 1

        res = 0
        for e in nums1:
            res += d2[e*e]
        for e in nums2:
            res += d1[e*e]
        return res


if __name__ == "__main__":
    s = Solution()
    nums1 = [1, 1]
    nums2 = [1, 1, 1]
    print(s.numTriplets(nums1, nums2))
