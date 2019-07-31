import math

class Solution(object):
    def __init__(self):
        self.memo = {}
        self.sums = []

    def stoneGameII(self, piles):
        """
        :type piles: List[int]
        :rtype: int
        """
        if piles is None or len(piles) < 1:
            return 0
        n = len(piles)
        self.sums = [0] * n
        self.sums[n-1] = piles[n-1]
        for i in range(n-2,-1,-1):
            self.sums[i] = piles[i] + self.sums[i+1]

        return self.helper(piles, 0, 1)

    def helper(self, piles, i, M):
        if i == len(piles):
            return 0
        if len(piles) - i <= 2 * M:
            return self.sums[i]
        if (i,M) in self.memo:
            return self.memo[(i,M)]
        t_min = math.inf
        for x in range(1,2*M+1,1):
            t_min = min(t_min, self.helper(piles, i+x, max(x, M)))
        self.memo[(i,M)] = self.sums[i] - t_min
        return self.memo[(i,M)]
s = Solution()
piles = [2,7,9,4,4]
print(s.stoneGameII(piles))

        