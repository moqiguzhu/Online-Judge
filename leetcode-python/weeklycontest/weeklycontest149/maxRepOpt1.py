class Solution:
    def maxRepOpt1(self, text: str) -> int:
        if text is None:
            return 0
        if len(text) < 2:
            return len(text)
        t = []
        i = 1
        begin, end = 0, 0
        while end < len(text):
            if text[end] == text[begin]:
                end += 1
                continue
            else:
                t.append((text[begin], end-begin, end-1))
                begin = end
                end = end
        t.append((text[begin], end-begin, end-1))
        if len(t) == 1:
            return t[0][1]
        # print(t)

        d = {}
        for e in t:
            if e[0] not in d:
                d[e[0]] = 1
            else:
                d[e[0]] += 1
        # print(d)

        i = 1
        res = 0
        while i < len(t):
            if i == len(t) - 1:
                res = max(res, t[i][1])
                i += 1
                continue
            if t[i-1][0] == t[i+1][0] and t[i][1] == 1:
                if d[t[i-1][0]] > 2:
                    res = max(res, t[i-1][1] + t[i+1][1] + 1)
                else:
                    res = max(res, t[i-1][1] + t[i+1][1])
            else:
                if d[t[i-1][0]] < 2:
                    res = max(res, t[i-1][1])
                else:
                    res = max(res, t[i-1][1] + 1)
            if d[t[i][0]] > 1:
                res = max(res, t[i][1] + 1)
            else:
                res = max(res, t[i][1])
            i += 1
        return res


s = Solution()
text = "babbaaabbbbbaa"
print(s.maxRepOpt1(text))
