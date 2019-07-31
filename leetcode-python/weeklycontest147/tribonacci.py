class Solution(object):
    def __init__(self):
        self.memo = {}
        self.memo[0] = 0
        self.memo[1] = 1
        self.memo[2] = 1

    def tribonacci(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n in self.memo:
            return self.memo[n]
        else:
            t1 = self.tribonacci(n-3)
            t2 = self.tribonacci(n-2)
            t3 = self.tribonacci(n-1)
            self.memo[n-3] = t1
            self.memo[n-2] = t2
            self.memo[n-1] = t3
            return t1 + t2 + t3