from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np


class Solution:
    def minSetSize(self, arr: List[int]) -> int:
        c = Counter(arr)
        n = len(arr)
        t = c.most_common()
        cur, res = 0, 0
        for e1, e2 in t:
            cur += e2
            res += 1
            if cur >= n / 2:
                return res


if __name__ == '__main__':
    s = Solution()
    arr = [7, 7, 7, 7, 7, 7]
    print(s.minSetSize(arr))
