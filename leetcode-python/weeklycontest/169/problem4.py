from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import inspect


class Solution:
    def isSolvable(self, words: List[str], result: str) -> bool:
        pass
        # leading_letters = set()
        # letters = set()
        # for word in words + result:
        #     for idx, c in enumerate(word):
        #         letters.add(c)
        #     if idx == 0:
        #         leading_letters.add(c)


if __name__ == '__main__':
    s = Solution()
    print(s.isSolvable.__annotations__)
    print(inspect.getsource(s.isSolvable))
