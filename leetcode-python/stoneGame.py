class Solution(object):
    def stoneGame(self, piles):
        """
        :type piles: List[int]
        :rtype: bool
        """
        n = len(piles)
        dp = [[0 for j in range(n+1)] for i in range(n+1)]
        self.memo = {}

        length = 1
        idx = 0
        while length <= n:
            while idx + length <= n:
                i, j = idx, idx + length
                if i + 1 == j:
                    dp[i][j] = piles[i]
                else:
                    if (i+1, j) not in self.memo:
                        self.memo[(i+1,j)] = sum(piles[i+1:j])
                    if (i, j-1) not in self.memo:
                        self.memo[(i,j-1)] = sum(piles[i:j-1])
                    dp[i][j] = max(piles[i] + self.memo[(i+1,j)] - dp[i+1][j], piles[j-1] + self.memo[(i,j-1)] - dp[i][j-1])
                idx += 1
            idx = 0
            length += 1
        if sum(piles[0:n]) - dp[0][n] < dp[0][n]:
            return True
        else:
            return False

s = Solution()
piles = [5,3,4,5]
print(s.stoneGame(piles))