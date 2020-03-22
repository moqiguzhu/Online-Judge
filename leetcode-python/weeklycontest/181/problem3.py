from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect


class Solution:
    def __init__(self):
        self.flag = False

    def hasValidPath(self, grid: List[List[int]]) -> bool:
        n, m = len(grid), len(grid[0])
        self.dfs(0, 0, n, m, grid, -1, -1)
        return self.flag

    def dfs(self, x, y, n, m, grid, from_x, from_y):
        if self.flag:
            return
        if x == n-1 and y == m-1:
            self.flag = True
            return
        if grid[x][y] == 1:
            if y-1 >= 0 and (grid[x][y-1] == 1 or grid[x][y-1] == 4 or grid[x][y-1] == 6) and not (x == from_x and y-1 == from_y):
                self.dfs(x, y-1, n, m, grid, x, y)
            if y+1 < m and (grid[x][y+1] == 1 or grid[x][y+1] == 3 or grid[x][y+1] == 5) and not (x == from_x and y+1 == from_y):
                self.dfs(x, y+1, n, m, grid, x, y)
        if grid[x][y] == 2:
            if x - 1 >= 0 and (grid[x-1][y] == 2 or grid[x-1][y] == 3 or grid[x-1][y] == 4) and not (x-1 == from_x and y == from_y):
                self.dfs(x-1, y, n, m, grid, x, y)
            if x + 1 < n and (grid[x+1][y] == 2 or grid[x+1][y] == 5 or grid[x+1][y] == 6) and not (x+1 == from_x and y == from_y):
                self.dfs(x+1, y, n, m, grid, x, y)
        if grid[x][y] == 3:
            if y-1 >= 0 and (grid[x][y-1] == 1 or grid[x][y-1] == 4 or grid[x][y-1] == 6) and not (x == from_x and y-1 == from_y):
                self.dfs(x, y-1, n, m, grid, x, y)
            if x + 1 < n and (grid[x+1][y] == 2 or grid[x+1][y] == 5 or grid[x+1][y] == 6) and not (x+1 == from_x and y == from_y):
                self.dfs(x+1, y, n, m, grid, x, y)
        if grid[x][y] == 4:
            if y+1 < m and (grid[x][y+1] == 1 or grid[x][y+1] == 3 or grid[x][y+1] == 5) and not (x == from_x and y+1 == from_y):
                self.dfs(x, y+1, n, m, grid, x, y)
            if x + 1 < n and (grid[x+1][y] == 2 or grid[x+1][y] == 5 or grid[x+1][y] == 6) and not (x+1 == from_x and y == from_y):
                self.dfs(x+1, y, n, m, grid, x, y)
        if grid[x][y] == 5:
            if y-1 >= 0 and (grid[x][y-1] == 1 or grid[x][y-1] == 4 or grid[x][y-1] == 6) and not (x == from_x and y-1 == from_y):
                self.dfs(x, y-1, n, m, grid, x, y)
            if x - 1 >= 0 and (grid[x-1][y] == 2 or grid[x-1][y] == 3 or grid[x-1][y] == 4) and not (x-1 == from_x and y == from_y):
                self.dfs(x-1, y, n, m, grid, x, y)
        if grid[x][y] == 6:
            if x - 1 >= 0 and (grid[x-1][y] == 2 or grid[x-1][y] == 3 or grid[x-1][y] == 4) and not (x-1 == from_x and y == from_y):
                self.dfs(x-1, y, n, m, grid, x, y)
            if y+1 < m and (grid[x][y+1] == 1 or grid[x][y+1] == 3 or grid[x][y+1] == 5) and not (x == from_x and y+1 == from_y):
                self.dfs(x, y+1, n, m, grid, x, y)


if __name__ == '__main__':
    s = Solution()
    grid = [[1, 1, 1, 1, 1, 1, 3]]
    print(s.hasValidPath(grid))
