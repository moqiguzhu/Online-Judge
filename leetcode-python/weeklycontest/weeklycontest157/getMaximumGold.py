from typing import List


class Solution:
    def getMaximumGold(self, grid: List[List[int]]) -> int:
        if grid is None or len(grid) < 1 or len(grid[0]) < 1:
            return 0
        n1, n2 = len(grid), len(grid[0])
        visited = [[0] * n2 for i in range(n1)]
        m = 0
        for i in range(n1):
            for j in range(n2):
                m = max(self.dfs(visited, i, j, 0, grid, n1, n2), m)
        return m

    def dfs(self, visited, i, j, res, grid, n1, n2):
        if visited[i][j] == 1 or grid[i][j] == 0:
            return res
        else:
            visited[i][j] = 1
            res = res + grid[i][j]
            x, y = [i+0, i+0, i+1, i-1], [j+1, j-1, j+0, j+0]
            t = []
            for p1, p2 in zip(x, y):
                if p1 >= 0 and p1 < n1 and p2 >= 0 and p2 < n2:
                    t.append(self.dfs(visited, p1, p2,
                                      res, grid, n1, n2))
            visited[i][j] = 0
            return max(t)


s = Solution()
grid = [[1, 0, 7], [2, 0, 6], [3, 4, 5], [0, 3, 0], [9, 0, 20]]
print(s.getMaximumGold(grid))
