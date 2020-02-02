from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import inspect


class Solution:
    def minFlips(self, a: int, b: int, c: int) -> int:
        a, b, c = bin(a)[2:], bin(b)[2:], bin(c)[2:]

        res = 0
        for idx in range(-1, -len(c)-1, -1):
            if c[idx] == '1':
                if abs(idx) <= len(a) and a[idx] == '1':
                    continue
                elif abs(idx) <= len(b) and b[idx] == '1':
                    continue
                else:
                    res += 1
            if c[idx] == '0':
                if abs(idx) <= len(a) and a[idx] == '1':
                    res += 1
                if abs(idx) <= len(b) and b[idx] == '1':
                    res += 1
        if len(a) > len(c):
            for idx in range(-len(c)-1, -len(a)-1, -1):
                if a[idx] == '1':
                    res += 1
        if len(b) > len(c):
            for idx in range(-len(c)-1, -len(b)-1, -1):
                if b[idx] == '1':
                    res += 1
        return res


if __name__ == '__main__':
    s = Solution()
    a = 4
    b = 2
    c = 7
    print(s.minFlips(a, b, c))
