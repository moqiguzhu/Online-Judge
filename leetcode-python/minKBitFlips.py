from collections import deque
from typing import List

# len(q) maintain how many flips for current index


class Solution:
    def minKBitFlips(self, A: List[int], K: int) -> int:
        if A is None or len(A) < K:
            return -1
        q = deque()
        res = 0
        for i in range(len(A)):
            if len(q) % 2 == 0:
                if A[i] == 0:
                    res += 1
                    q.append(i+K-1)
            else:
                if A[i] == 1:
                    res += 1
                    q.append(i+K-1)
            if q and q[0] == i:
                # maintain how many flips for current index
                q.popleft()
            if q and q[-1] >= len(A):
                return -1
        return res


s = Solution()
A = [0, 0, 0, 1, 0, 1, 1, 0]
K = 3
print(s.minKBitFlips(A, K))
