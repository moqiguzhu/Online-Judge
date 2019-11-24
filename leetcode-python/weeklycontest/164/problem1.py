from collections import defaultdict, deque, Counter
from typing import List
import math
import copy


class Solution:
    def minTimeToVisitAllPoints(self, points: List[List[int]]) -> int:
        if points is None or len(points) < 2:
            return 0

        current = points[0]

        res = 0
        for i in range(1, len(points)):
            res += max(abs(points[i][0] - current[0]),
                       abs(points[i][1] - current[1]))
            current = points[i]

        return res


s = Solution()
points = [[3, 2], [-2, 2]]
print(s.minTimeToVisitAllPoints(points))
