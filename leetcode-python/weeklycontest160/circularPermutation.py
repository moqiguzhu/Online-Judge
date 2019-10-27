
# 题都没审清楚

from typing import List


class Solution:
    def circularPermutation(self, n: int, start: int) -> List[int]:
        # gray code
        a = [i ^ (i >> 1) for i in range(2**n)]
        idx = a.index(start)
        return a[idx:] + a[:idx]
