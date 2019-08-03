class Solution(object):
    def maxIncreaseKeepingSkyline(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        if grid is None or len(grid) < 1 or grid[0] is None or len(grid[0]) < 1:
            return 0

        left_skyline = [max(e) for e in grid]
        top_skyline = [0] * len(grid[0])
        for i in range(len(grid[0])):
            for j in range(len(grid)):
                top_skyline[i] = max(top_skyline[i], grid[j][i])
        res = 0
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                res += min(left_skyline[i], top_skyline[j]) - grid[i][j]

        return res


s = Solution()
grid = [[3, 0, 8, 4],
        [2, 4, 5, 7],
        [9, 2, 6, 3],
        [0, 3, 1, 0]]
print(s.maxIncreaseKeepingSkyline(grid))
