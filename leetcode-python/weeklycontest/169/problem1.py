from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np


class Solution:
    def sumZero(self, n: int) -> List[int]:
        res = []
        for i in range(1, n // 2+1, 1):
            res.append(i)
            res.append(-i)
        if n % 2 != 0:
            res.append(0)
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.sumZero(5))
