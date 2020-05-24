from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest

# tle


class Solution:
    def maxDotProduct1(self, nums1: List[int], nums2: List[int]) -> int:
        # 处理特殊情况
        t1, t2 = min(nums1), max(nums1)
        t3, t4 = min(nums2), max(nums2)
        if t1 > 0 and t4 < 0:
            return t1 * t4
        if t2 < 0 and t3 > 0:
            return t2 * t3
        n1, n2 = len(nums1), len(nums2)

        dp = [[0] * (n2+1) for _ in range(n1+1)]
        for i in range(1, n1+1):
            for j in range(1, n2+1):
                t = -10**9 - 7
                for k in range(1, i+1):
                    t = max(t, nums2[j-1] * nums1[k-1] + dp[k-1][j-1])
                dp[k][j] = max(dp[k][j-1], t)

        return dp[n1][n2]

    def maxDotProduct(self, nums1: List[int], nums2: List[int]) -> int:
        # 处理特殊情况
        t1, t2 = min(nums1), max(nums1)
        t3, t4 = min(nums2), max(nums2)
        if t1 > 0 and t4 < 0:
            return t1 * t4
        if t2 < 0 and t3 > 0:
            return t2 * t3
        n1, n2 = len(nums1), len(nums2)

        dp = [[0] * (n2+1) for _ in range(n1+1)]
        for i in range(1, n1+1):
            for j in range(1, n2+1):
                dp[i][j] = max([dp[i-1][j], dp[i][j-1], dp[i-1]
                                [j-1], dp[i-1][j-1] + nums1[i-1]*nums2[j-1]])

        return dp[n1][n2]


if __name__ == "__main__":
    s = Solution()
    nums1 = [2, 1, -2, 5]
    nums2 = [3, 0, -6]
    print(s.maxDotProduct(nums1, nums2))
