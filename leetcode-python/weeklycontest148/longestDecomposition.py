class Solution:
    def __init__(self):
        self.memo = {}

    def longestDecomposition(self, text: str) -> int:
        self.help(0, len(text)-1, text)
        return self.memo[(0, len(text)-1)]

    def help(self, i, j, text):
        if i > j:
            return 0
        if i == j:
            self.memo[(i, j)] = 1
            return 1
        k = 1
        t = -1
        while i+k <= j-k+1:
            if text[i:i+k] == text[j-k+1:j+1]:
                t1 = self.help(i+k, j-k, text)
                t = max(t, t1)
            k += 1

        self.memo[(i, j)] = 2 + t

        return 2+t


s = Solution()
text = "antaprezatepzapreanta"
print(s.longestDecomposition(text))
