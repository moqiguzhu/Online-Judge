from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest

# 先把思路想好再开始
# 多思考


class Solution:
    def maxNumOfSubstrings(self, s: str) -> List[str]:
        c_idx = {}
        for idx, c in enumerate(s):
            if c not in c_idx:
                c_idx[c] = (idx, idx)
            else:
                c_idx[c] = (c_idx[c][0], idx)

        # 经过这一步之后,只会有包含的区间,不会有相交的区间,想清楚这点
        for _ in range(26):
            c_idx_extended = {}
            for c, idx in c_idx.items():
                t1, t2 = idx
                for e in set(s[idx[0]:idx[1]+1]):  # 这个set很关键
                    t1, t2 = min(t1, c_idx[e][0]), max(
                        t2, c_idx[e][1])
                c_idx_extended[c] = (t1, t2)
            c_idx = c_idx_extended

        c_idx = sorted(c_idx.items(), key=lambda x: x[1][1])

        print(c_idx)

        # 去重 -- 有没有不影响结果
        c_idx_copy = []
        idx_before = (-1, -1)
        for c, idx in c_idx:
            if idx == idx_before:
                pass
            else:
                c_idx_copy.append((c, idx))
                idx_before = idx
        c_idx = c_idx_copy

        res = []
        p = -1
        for begin, end in [e[1] for e in c_idx_copy]:
            if begin > p:
                res.append(s[begin:end+1])
                p = end
        return res


if __name__ == "__main__":
    s = Solution()
    st = "cabcccbaa"
    print(s.maxNumOfSubstrings(st))
