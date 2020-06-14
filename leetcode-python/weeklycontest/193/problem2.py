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
    def findLeastNumOfUniqueInts(self, arr: List[int], k: int) -> int:
        num_cnt = defaultdict(int)
        for e in arr:
            num_cnt[e] += 1

        num_cnt = sorted(num_cnt.items(), key=lambda x: x[1])
        res = len(num_cnt)
        for num, cnt in num_cnt:
            if k >= cnt:
                res -= 1
                k -= cnt
            else:
                break
        return res


if __name__ == "__main__":
    s = Solution()
    arr = [4, 3, 1, 1, 3, 3, 2]
    k = 3
    print(s.findLeastNumOfUniqueInts(arr, k))
