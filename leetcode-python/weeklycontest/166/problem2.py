from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import numpy as np


class Solution:
    def groupThePeople(self, groupSizes: List[int]) -> List[List[int]]:
        sorted_group = np.sort(groupSizes)
        sorted_index = np.argsort(groupSizes)

        res = []
        idx = 0
        while idx < len(groupSizes):
            t = sorted_group[idx]
            res.append(sorted_index[idx:idx+t].tolist())
            idx += t
        return res


if __name__ == '__main__':
    s = Solution()
    groupSizes = [3, 3, 3, 3, 3, 1, 3]
    print(s.groupThePeople(groupSizes))
