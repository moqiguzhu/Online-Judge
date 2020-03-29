from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect

import inspect

import heapq

# 漏洞百出的代码
# !!! 没有测试


def char_range(c1, c2):
    """Generates the characters from `c1` to `c2`, inclusive."""
    for c in range(ord(c1), ord(c2)+1):
        yield chr(c)


def char_range2(c1, c2):
    for c in range(ord(c1)+1, ord(c2)):
        yield chr(c)


class Solution:
    def __init__(self):
        self.d = defaultdict(lambda: -1)
        self.M = 10 ** 9 + 1

    def findGoodStrings(self, n: int, s1: str, s2: str, evil: str) -> int:
        for e1, e2 in zip(range(n), s1, s2):
            if e1 > e2:
                return 0
            elif e1 == e2:
                if e1 == evil[0]:
                    res = self.findGoodStrings(n-1, s1[1:], s2[1:], evil[1:])
                else:
                    res = self.findGoodStrings(n-1, s1[1:], s2[1:], evil)
            else:
                res = 0
                for e in char_range2(e1, e2):
                    if e == evil[0]:
                        res += self.help1(n-1, len(evil)-1)
                    else:
                        res += self.help1(n-1, len(evil))
                    # 每一次调用之前, 都要检查第一个字母和evil[0]是不是相等
                    res += self.help2(n-1, s1[1:], 'z' * (n-1), evil, 1)
                    res += self.help2(n-1, 'a' * (n-1), s2[1:], evil, 2)
            return res

    def help1(self, m, n):
        if self.d[(m, n)] != -1:
            pass
        else:
            if n == 0:
                t = 0
            elif m == 0:
                t = 1
            elif m == n:
                t = 26 ** m - 1
            else:
                t = 25 * self.help1(m-1, n) + self.help1(m-1, n-1)
        self.d[(m, n)] = t % self.M
        return self.d[(m, n)]

    def help2(self, n, s1, s2, evil, flag):
        if n == 0:
            return
        if flag == 1:
            res = 0
            for e in char_range(chr(ord(s1[0])+1), 'z'):
                if e == evil[0]:
                    res += self.help1(n-1, len(evil)-1)
                else:
                    res += self.help1(n-1, len(evil))
            res += self.help2(n-1, s1[1:], 'z' * (n-1), evil, 1)
        if flag == 2:
            res = 0
            for e in char_range('a', chr(ord(s1[0])-1)):
                if e == evil[0]:
                    res += self.help1(n-1, len(evil)-1)
                else:
                    res += self.help1(n-1, len(evil))
            res += self.help2(n-1, 'a' * (n-1), s2[1:], evil, 2)
        return res % self.M


if __name__ == '__main__':
    so = Solution()
