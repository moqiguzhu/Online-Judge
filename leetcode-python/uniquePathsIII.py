from typing import List

# DFS


class Solution:
    def __init__(self):
        self.res = []
        self.n1 = 0
        self.n2 = 0

    def uniquePathsIII(self, grid: List[List[int]]) -> int:
        if grid is None or len(grid) < 1 or grid[0] is None or len(grid[0]) < 1:
            return 0
        self.n1 = len(grid)
        self.n2 = len(grid[0])

        # used = [[0] * self.n2] * self.n1
        used = []
        for i in range(self.n1):
            used.append([0] * self.n2)
        total = 0
        init_pos = ()
        for i in range(self.n1):
            for j in range(self.n2):
                if grid[i][j] != -1:
                    total += 1
                if grid[i][j] == 1:
                    init_pos = (i, j)

        self.help(used, grid, 1, [init_pos], total, init_pos)

        return len(self.res)

    def help(self, used, grid, num_cur, cur_path, total, cur_pos):
        if num_cur == total:
            self.res.append(cur_path)
            return
        i, j = cur_pos[0]-1, cur_pos[1]
        if i >= 0 and used[i][j] != 1 and grid[i][j] != -1 and (grid[i][j] != 2 or (grid[i][j] == 2 and num_cur == total - 1)) and grid[i][j] != 1:
            used[i][j] = 1
            cur_path.append((i, j))
            self.help(used, grid, num_cur+1, cur_path, total, (i, j))
            cur_path.pop()
            used[i][j] = 0

        i, j = cur_pos[0]+1, cur_pos[1]
        if i < self.n1 and used[i][j] != 1 and grid[i][j] != -1 and (grid[i][j] != 2 or (grid[i][j] == 2 and num_cur == total - 1)) and grid[i][j] != 1:
            used[i][j] = 1
            cur_path.append((i, j))
            self.help(used, grid, num_cur+1, cur_path, total, (i, j))
            cur_path.pop()
            used[i][j] = 0

        i, j = cur_pos[0], cur_pos[1]-1
        if j >= 0 and used[i][j] != 1 and grid[i][j] != -1 and (grid[i][j] != 2 or (grid[i][j] == 2 and num_cur == total - 1)) and grid[i][j] != 1:
            used[i][j] = 1
            cur_path.append((i, j))
            self.help(used, grid, num_cur+1, cur_path, total, (i, j))
            cur_path.pop()
            used[i][j] = 0

        i, j = cur_pos[0], cur_pos[1]+1
        if j < self.n2 and used[i][j] != 1 and grid[i][j] != -1 and (grid[i][j] != 2 or (grid[i][j] == 2 and num_cur == total - 1)) and grid[i][j] != 1:
            used[i][j] = 1
            cur_path.append((i, j))
            self.help(used, grid, num_cur+1, cur_path, total, (i, j))
            cur_path.pop()
            used[i][j] = 0


s = Solution()
grid = [[1, 0, 0, 0], [0, 0, 0, 0], [0, 0, 2, -1]]
print(s.uniquePathsIII(grid))
