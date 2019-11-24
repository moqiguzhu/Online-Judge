from collections import defaultdict, deque, Counter
from typing import List
import math
import copy


class Solution:
    def __init__(self):
        self.M = 10**9 + 7
        self.memo = defaultdict(int)
        self.memo[(0, 0)] = 1

    def numWays(self, steps: int, arrLen: int) -> int:
        self.help(steps, 0, arrLen)

        return self.memo[(steps, 0)]

    def help(self, steps, pos, arrLen):
        if (steps, pos) in self.memo:
            return self.memo[(steps, pos)]
        elif steps <= 0:
            return 0
        else:
            t = 0
            if pos + 1 < arrLen:
                t += (self.help(steps-1, pos+1, arrLen)) % self.M
            if pos < arrLen and pos >= 0:
                t += (self.help(steps-1, pos, arrLen)) % self.M
            if pos - 1 >= 0:
                t += (self.help(steps-1, pos-1, arrLen)) % self.M
            self.memo[(steps, pos)] = t % self.M
            return t % self.M


s = Solution()
steps = 4
arrLen = 2
print(s.numWays(steps, arrLen))
