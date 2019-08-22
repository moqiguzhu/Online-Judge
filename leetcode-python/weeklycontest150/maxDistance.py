from typing import List
import math
from collections import deque


class Solution:
    # time limit
    def maxDistance1(self, grid: List[List[int]]) -> int:
        res_max = 0
        n1, n2 = len(grid), len(grid[0])
        t = sum([sum(e) for e in grid])
        if t == (n1) * (n2) or t == 0:
            return -1
        for i in range(n1):
            for j in range(n2):
                e1 = grid[i][j]
                if e1 == 1:
                    continue
                else:
                    flag = True
                    cur_min = math.inf
                    k, p = 0, 0
                    while k < n1 and p < n2 and flag:
                        e2 = grid[k][p]
                        kc, pc = k, p
                        p += 1
                        k += (p // n2)
                        p = p % n2
                        if e2 == 0:
                            continue
                        cur_d = abs(i-pc) + abs(j-kc)
                        cur_min = min(cur_min, cur_d)
                        if cur_min <= res_max:
                            flag = False
                    res_max = max(res_max, cur_min)
        return res_max

    # time limit
    def maxDistance2(self, grid: List[List[int]]) -> int:
        n1, n2 = len(grid), len(grid[0])
        t = sum([sum(e) for e in grid])
        if t == (n1) * (n2) or t == 0:
            return -1

        q = deque()
        n1 = len(grid)
        n2 = len(grid[0])

        cur_d = 1
        for i in range(n1):
            for j in range(n2):
                if grid[i][j] == 0:
                    q.append((i, j))
        while len(q) > 0:
            t = deque()
            for e in q:
                tt = []
                for l in range(0, cur_d+1, 1):
                    tt.append((e[0]-l, e[1]-(cur_d-l)))
                    tt.append((e[0]-l, e[1]+(cur_d-l)))
                    tt.append((e[0]+l, e[1]-(cur_d-l)))
                    tt.append((e[0]+l, e[1]+(cur_d-l)))
                flag = True
                for idx1, idx2 in tt:
                    if idx1 >= 0 and idx1 < n1 and idx2 >= 0 and idx2 < n2 and grid[idx1][idx2] == 1:
                        flag = False
                        break
                if flag:
                    t.append(e)
            q = t
            cur_d += 1

        return cur_d - 1

    # answer from wangyou
    def maxDistance(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        q = deque([(i, j) for i in range(m)
                   for j in range(n) if grid[i][j] == 1])
        if len(q) == m * n or len(q) == 0:
            return -1
        level = 0
        while q:
            size = len(q)
            for _ in range(size):
                i, j = q.popleft()
                for x, y in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
                    xi, yj = x+i, y+j
                    if 0 <= xi < m and 0 <= yj < n and grid[xi][yj] == 0:
                        q.append((xi, yj))
                        grid[xi][yj] = 1
            level += 1
        return level-1


s = Solution()
grid = [[1, 0, 0], [0, 0, 0], [0, 0, 0]]
print(s.maxDistance(grid))
