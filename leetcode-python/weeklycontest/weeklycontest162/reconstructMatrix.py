from typing import List


class Solution:
    def reconstructMatrix(self, upper: int, lower: int, colsum: List[int]) -> List[List[int]]:
        res = [[], []]
        t1, t2 = 0, 0
        for cols in colsum:
            if cols == 2:
                res[0].append(1)
                res[1].append(1)
                t1 += 1
                t2 += 1
            elif cols == 0:
                res[0].append(0)
                res[1].append(0)
            else:
                if abs(t1-upper) >= abs(t2-lower):
                    res[0].append(1)
                    res[1].append(0)
                    t1 += 1
                else:
                    res[0].append(0)
                    res[1].append(1)
                    t2 += 1
        if (t1 != upper) or (t2 != lower):
            return []
        else:
            return res


s = Solution()
upper = 3
lower = 6
colsum = [1, 1, 1, 1, 0, 1, 1, 0, 2, 1, 0, 0]
print(s.reconstructMatrix(upper, lower, colsum))
