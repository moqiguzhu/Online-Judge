# so close to right answer
class Solution(object):
    def largest1BorderedSquare(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        if len(grid) < 1:
            return 0
        n1 = len(grid)
        if len(grid[0]) < 1:
            return 0
        n2 = len(grid[0])

        dp = [[0 for j in range(n2)] for i in range(n1)]
        for i in range(n2):
            dp[0][i] = grid[0][i]
        for i in range(n1):
            dp[i][0] = grid[i][0]
        
        for i in range(1,n1,1):
            for j in range(1,n2,1):
                if grid[i][j] == 0:
                    dp[i][j] == 0
                else:
                    t = min(i,j)
                    while t >= 0: # 注意这种写法  np.array才能实现想要的功能
                        flag = True
                        for k in range(i-t,i):
                            if grid[k][j] != 1:
                                flag = False
                                break
                        for k in range(j-t+1,j+1):
                            if not flag or grid[i][k] != 1:
                                flag = False
                                break
                        for k in range(i-t+1,i+1):
                            if not flag or grid[k][j-t] != 1:
                                flag = False
                                break
                        for k in range(j-t,j):
                            if not flag or grid[i-t][k] != 1:
                                flag = False
                                break
                        if flag:
                            dp[i][j] = t + 1
                            break
                        else:
                            t = t - 1
                        # t1 = sum(grid[i-t:i][j])
                        # t2 = sum(grid[i][j-t+1:j+1])
                        # t3 = sum(grid[i-t+1:i+1][j-t])
                        # t4 = sum(grid[i-t][j-t:j])
                        # if t1 + t2 + t3 + t4 == 4 * t:
                        #     dp[i][j] = t + 1
                        #     break
                        # else:
                        #     t = t - 1
        t = max([max(e) for e in dp])
        return t * t

s = Solution()
grid = [[1,1,1],[1,1,0],[1,1,1],[0,1,1],[1,1,1]]
print(s.largest1BorderedSquare(grid))   

