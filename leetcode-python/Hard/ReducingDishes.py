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
    def maxSatisfaction(self, satisfaction: List[int]) -> int:
        positives = [e for e in satisfaction if e >= 0]
        negatives = [e for e in satisfaction if e < 0]
        positives, negatives = sorted(positives, key=lambda x: x), sorted(
            negatives, key=lambda x: -x)

        psum = sum(positives)
        t, cur_sum = [], 0
        for e in negatives:
            cur_sum += e
            if cur_sum + psum > 0:
                t.append(e)
        selected = t[::-1] + positives
        res = 0
        for i in range(1, len(selected)+1, 1):
            res += selected[i-1] * i
        return res


if __name__ == "__main__":
    s = Solution()
    satisfaction = [-2, 5, -1, 0, 3, -3]
    print(s.maxSatisfaction(satisfaction))
