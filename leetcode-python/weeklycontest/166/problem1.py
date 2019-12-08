from collections import defaultdict, deque, Counter
from typing import List
import math
import copy


class Solution:
    def subtractProductAndSum(self, n: int) -> int:
        n_str = str(n)

        t1, t2 = 1, 0
        for e in n_str:
            t1 *= int(e)
            t2 += int(e)

        return t1 - t2


if __name__ == '__main__':
    s = Solution()
    n = 234
    print(s.subtractProductAndSum(n))
