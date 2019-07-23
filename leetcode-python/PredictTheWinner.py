class Solution(object):
    def PredictTheWinner(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        n = len(nums)
        if n <= 1:
            return True
        dp = [[0 for j in range(n+1)] for i in range(n+1)]
        self.memo = {}

        length = 1
        idx = 0
        while length <= n:
            while idx + length <= n:
                i, j = idx, idx + length
                if i + 1 == j:
                    dp[i][j] = nums[i]
                else:
                    if (i+1, j) not in self.memo:
                        self.memo[(i+1,j)] = sum(nums[i+1:j])
                    if (i, j-1) not in self.memo:
                        self.memo[(i,j-1)] = sum(nums[i:j-1])
                    dp[i][j] = max(nums[i] + self.memo[(i+1,j)] - dp[i+1][j], nums[j-1] + self.memo[(i,j-1)] - dp[i][j-1])
                idx += 1
            idx = 0
            length += 1
        if sum(nums[0:n]) - dp[0][n] <= dp[0][n]:
            return True
        else:
            return False
s = Solution()
nums = [1, 5, 2]
print(s.PredictTheWinner(nums))