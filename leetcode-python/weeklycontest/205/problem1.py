from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest
import string


class Solution:
    def modifyString(self, s: str) -> str:
        candidates = string.ascii_lowercase
        l, res = len(s), ''
        for idx, e in enumerate(s):
            cur_candidates = set(candidates)
            if e == '?':
                if idx - 1 >= 0:
                    if idx + 1 < l and s[idx+1] != '?':
                        cur_candidates.remove(s[idx+1])
                    if res[idx-1] in cur_candidates:
                        cur_candidates.remove(res[idx-1])
                else:
                    if idx + 1 < l and s[idx+1] != '?':
                        cur_candidates.remove(s[idx+1])
                for t in cur_candidates:
                    res += t
                    break
            else:
                res += e
        return res


if __name__ == "__main__":
    s = Solution()
    ss = "?ob?b???"
    print(s.modifyString(ss))
