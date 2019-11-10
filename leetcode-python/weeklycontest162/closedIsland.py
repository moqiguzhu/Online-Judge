from typing import List
from collections import deque


class Solution:
    def closedIsland(self, grid: List[List[int]]) -> int:
        n, m = len(grid), len(grid[0])
        visited = [[0] * m for _ in range(n)]
        res = 0
        for i in range(n):
            for j in range(m):
                res += self.help(grid, visited, i, j, n, m)
        return res

    def help(self, grid, visited, i, j, n, m):
        tt = [[0, 1], [0, -1], [1, 0], [-1, 0]]
        if grid[i][j] == 1:
            return 0
        else:
            if visited[i][j] == 1:
                return 0
            else:
                visited[i][j] = 1
                q = deque()
                q.append((i, j))
                t = deque()
                flag = True
                while len(q) > 0:
                    for ii, jj in q:
                        if ii + 1 >= n or ii - 1 < 0 or jj+1 >= m or jj-1 < 0:
                            flag = False
                        for x, y in tt:
                            if n > ii + x >= 0 and m > jj + y >= 0:
                                if grid[ii+x][jj+y] == 0 and visited[ii+x][jj+y] == 0:
                                    t.append((ii+x, jj+y))

                    for ii, jj in t:
                        visited[ii][jj] = 1
                    q = t
                    t = deque()
                if flag:
                    return 1
                else:
                    return 0


s = Solution()
grid = [[1, 1, 1, 1, 1, 1, 1, 0], [1, 0, 0, 0, 0, 1, 1, 0], [
    1, 0, 1, 0, 1, 1, 1, 0], [1, 0, 0, 0, 0, 1, 0, 1], [1, 1, 1, 1, 1, 1, 1, 0]]
print(s.closedIsland(grid))
