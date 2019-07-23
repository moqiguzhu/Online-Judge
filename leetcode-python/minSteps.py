import math

class Solution(object):
    def __init__(self):
        self.memo = {}
        self.memo[1] = 0
        self.memo[2] = 2
        self.memo[3] = 3
        self.memo[4] = 4
        self.memo[5] = 5
        self.memo[6] = 5

    def minSteps(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n in self.memo:
            return self.memo[n]
        else: 
            for i in range(2,int(math.floor(math.sqrt(n)))+1,1):
                if n % i == 0:
                    t2 = self.minSteps(n / i)
                    self.memo[n] = t2 + i
                    return self.memo[n]
            self.memo[n] = n
            return n

s = Solution()
n = 8
print(s.minSteps(n))