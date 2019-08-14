from typing import List
import math
# recursive bfs + early stop


class Solution:
    def __init__(self):
        self.res = 0

    def numSquarefulPerms(self, A: List[int]) -> int:
        if len(A) < 2:
            return 1
        self.helper(A, -1)
        return self.res

    def helper(self, remain, last):
        if len(remain) <= 1:
            if int(math.sqrt(remain[0] + last)) ** 2 == (remain[0] + last):
                self.res += 1
            return
        s = set()
        for i in range(len(remain)):
            if remain[i] not in s:
                s.add(remain[i])
                if last != -1 and int(math.sqrt(remain[i] + last)) ** 2 != (remain[i] + last):
                    continue
                else:
                    t = remain[0:i] + remain[i+1:]
                    self.helper(t, remain[i])
            else:
                pass


s = Solution()
A = [0, 0, 0, 1, 1, 1]
print(s.numSquarefulPerms(A))
