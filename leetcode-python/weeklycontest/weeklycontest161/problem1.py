class Solution:
    def minimumSwap(self, s1: str, s2: str) -> int:
        if s1 is None or s2 is None:
            return -1
        if len(s1) != len(s2):
            return -1

        n = len(s1)

        new_s1, new_s2 = list(s1), list(s2)
        res = 0
        for i in range(n):
            if new_s1[i] == new_s2[i]:
                continue
            flag = False
            for j in range(n):
                if new_s1[i] == new_s1[j] and new_s2[i] == new_s2[j]:
                    t = new_s1[i]
                    new_s1[i] = new_s2[j]
                    new_s2[j] = t
                    res += 1
                    flag = True
                    break
        for i in range(n):
            if new_s1[i] == new_s2[i]:
                continue
            flag = False
            if new_s1[i] == new_s2[j] and new_s1[j] == new_s2[i]:
                t = new_s2[i]
                new_s2[i] = new_s2[j]
                new_s2[j] = t
                res += 2
                flag = True
                break
            if not flag:
                return -1
        return res


s = Solution()
s1 = "xxyyxyxyxx"
s2 = "xyyxyxxxyx"
print(s.minimumSwap(s1, s2))
