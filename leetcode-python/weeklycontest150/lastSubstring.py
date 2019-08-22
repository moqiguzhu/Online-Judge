class Solution:
    # memory limit
    def __init__(self):
        self.res = -1

    def lastSubstring1(self, s: str) -> str:
        t = []
        for i in range(len(s)):
            t.append(s[i:])
        return max(t)
    # time limit

    def lastSubstring2(self, s: str) -> str:
        t = []
        tt = []
        for i in range(len(s)):
            tt.append(s[i:])
            if i % 1000 == 0:
                t.append(max(tt))
                tt = []
        t.append(max(tt))
        return max(t)

    def lastSubstring(self, s: str) -> str:
        t = set(s)
        if len(t) == 1:
            return s
        t = [(i, 0) for i in range(len(s))]
        self.helper(t, s)
        return s[self.res:]

    def helper(self, candidates, s):
        if len(candidates) == 1:
            self.res = candidates[0][0]
            return
        t = []
        for e in candidates:
            idx, l = e[0], e[1]
            t.append(s[idx+l])
        m = max(t)
        tt = []
        for e in candidates:
            if s[e[0]+e[1]] == m:
                tt.append(e[0])
        if len(tt) == 1:
            self.res = tt[0]
            return

        tc = []
        for e in candidates:
            idx, l = e[0], e[1]
            if s[idx+l] == m and idx + l + 1 < len(s):
                tc.append((idx, l+1))
        self.helper(tc, s)


s = Solution()
ss = "babcbd"
print(s.lastSubstring2(ss))
