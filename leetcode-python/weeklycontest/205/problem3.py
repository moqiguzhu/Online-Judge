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
    def minCost(self, s: str, cost: List[int]) -> int:
        rep_weights, max_weight = [], []
        is_new = True
        for idx, e in enumerate(s):
            if idx == 0:
                continue
            if e == s[idx-1]:
                if is_new:
                    rep_weights.append(sum(cost[idx-1:idx+1]))
                    max_weight.append(max(cost[idx-1:idx+1]))
                    is_new = False
                else:
                    rep_weights[-1] += cost[idx]
                    max_weight[-1] = max(max_weight[-1], cost[idx])
            else:
                is_new = True
        return sum(rep_weights) - sum(max_weight)


if __name__ == "__main__":
    s = Solution()
    ss = "aabaa"
    cost = [1, 2, 3, 4, 1]
    print(s.minCost(ss, cost))
