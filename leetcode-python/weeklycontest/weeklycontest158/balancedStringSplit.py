class Solution:
    def balancedStringSplit(self, s: str) -> int:
        if s is None or len(s) < 1:
            return 0
        if len(s) % 2 != 0:
            return -1
        lc, rc = 0, 0
        res = 0
        for c in s:
            if c == 'L':
                lc += 1
            if c == 'R':
                rc += 1
            if lc == rc:
                res += 1
                lc, rc = 0, 0
        return res


s = Solution()
st = 'RLLLLRRRLR'
print(s.balancedStringSplit(st))
