# 字符串快速匹配技术
class Solution:
    def longestDecomposition(self, text: str) -> int:
        if text is None:
            return 0
        if len(text) == 1:
            return 1
        base = 30
        modolo = 10 ** 9 + 1
        i, j = 0, len(text)-1
        l = 1
        t1, t2 = 0, 0

        res = 0
        while i <= j:
            t1 = ord(text[i]) if t1 == 0 else t1 + \
                (ord(text[i+l-1]) * (base ** (l-1))) % modolo
            t2 = ord(text[j]) if t2 == 0 else (
                t2 * base) % modolo + ord(text[j-l+1])
            t1, t2 = t1 % modolo, t2 % modolo
            if t1 == t2:
                res = res + 2 if i != j-l+1 else res + 1
                i, j = i+l, j-l
                l = 1
                t1, t2 = 0, 0
            else:
                l += 1
        return res


s = Solution()
text = 'ghiabcdefhelloadamhelloabcdefghi'
print(s.longestDecomposition(text))
