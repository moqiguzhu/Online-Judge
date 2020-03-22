from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect

import inspect

import heapq

# rolling hash
# nice 好思路


class Solution:
    def longestPrefix(self, s: str) -> str:
        a = 31
        M = 10**9 + 1
        t = [1]
        n = len(s)
        for i in range(1, len(s)+1, 1):
            t.append((t[i-1] * a) % M)

        d1, d2 = defaultdict(lambda: 0), defaultdict(lambda: 0)
        # idx表示长度
        res = ''
        for idx in range(1, n):
            d1[idx] = (d1[idx-1] * a + ord(s[idx-1])) % M
            d2[idx] = (ord(s[n-idx]) * t[idx-1] + d2[idx-1]) % M
            if d1[idx] == d2[idx] and s[0:idx] == s[n-idx:]:
                res = s[0:idx]
        return res


if __name__ == '__main__':
    so = Solution()
    s = 'a'
    print(so.longestPrefix(s))
