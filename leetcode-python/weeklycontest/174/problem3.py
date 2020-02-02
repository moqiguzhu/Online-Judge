from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np

# BFS


class Solution:
    def canReach(self, arr: List[int], start: int) -> bool:
        visited = set()
        q = deque()
        q.append(start)
        n = len(arr)
        remained = set([idx for idx, e in enumerate(arr) if e == 0])

        while len(q) > 0:
            t = deque()
            for e in q:
                if n > e + arr[e] >= 0 and e + arr[e] not in visited:
                    t.append(e + arr[e])
                if n > e - arr[e] >= 0 and e - arr[e] not in visited:
                    t.append(e - arr[e])
                visited.add(e)
                if e in remained:
                    return True
            print(visited)
            if len(t) == 0:
                return False
            q = t


if __name__ == '__main__':
    s = Solution()
    arr = [0, 3, 0, 6, 3, 3, 4]
    start = 6
    print(s.canReach(arr, start))
