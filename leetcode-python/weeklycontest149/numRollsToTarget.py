class Solution:
    def __init__(self):
        self.memo = {}

    def numRollsToTarget(self, d: int, f: int, target: int) -> int:
        if d < 1 or target < 1:
            return 0
        if d == 1:
            if target <= f:
                self.memo[(d, f, target)] = 1
                return 1
        if (d, f, target) in self.memo:
            return self.memo[(d, f, target)]

        res = 0
        for i in range(1, 1+f):
            t = self.numRollsToTarget(d-1, f, target-i)
            self.memo[(d-1, f, target-i)] = t
            res += t
            res %= 1000000007
        return res


s = Solution()
d = 30
f = 30
target = 500
print(s.numRollsToTarget(d, f, target))
