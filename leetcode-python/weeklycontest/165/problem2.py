from collections import defaultdict, deque, Counter
from typing import List
import math
import copy


class Solution:
    def numOfBurgers(self, tomatoSlices: int, cheeseSlices: int) -> List[int]:
        t = tomatoSlices - cheeseSlices * 2
        if t >= 0 and t % 2 == 0 and tomatoSlices <= cheeseSlices*4:
            return [t//2, cheeseSlices - t // 2]
        else:
            return []


s = Solution()
tomatoSlices = 2385088
cheeseSlices = 164763
print(s.numOfBurgers(tomatoSlices, cheeseSlices))
