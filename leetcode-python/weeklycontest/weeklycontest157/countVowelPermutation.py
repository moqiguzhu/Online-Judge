class Solution:
    def countVowelPermutation(self, n: int) -> int:
        dp = [[0]*5 for i in range(n+1)]
        for i in range(5):
            dp[1][i] = 1
        # a e i o u -> 0, 1, 2, 3, 4
        M = 10 ** 9 + 7
        for i in range(2, n+1, 1):
            dp[i][0] = (dp[i-1][1] + dp[i-1][2] + dp[i-1][4]) % M
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % M
            dp[i][2] = (dp[i-1][1] + dp[i-1][3]) % M
            dp[i][3] = (dp[i-1][2]) % M
            dp[i][4] = (dp[i-1][2] + dp[i-1][3]) % M

        return sum(dp[n]) % M


s = Solution()
n = 1
print(s.countVowelPermutation(n))
