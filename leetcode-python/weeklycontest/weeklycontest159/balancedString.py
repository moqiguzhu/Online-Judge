from collections import defaultdict


class Solution:
    def balancedString(self, s: str) -> int:
        d = defaultdict(int)
        for c in s:
            d[c] += 1
        print(d.items())
        ma, mi, su = max(list(d.values())), min(
            list(d.values())), sum(list(d.values()))

        return int(sum([0 if e < su / 4 else e - su / 4 for e in d.values()]))


so = Solution()
s = "WWEQERQWQWWRWWERQWEQ"
print(so.balancedString(s))
