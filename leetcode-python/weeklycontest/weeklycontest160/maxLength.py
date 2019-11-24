from typing import List
from collections import defaultdict
from collections import Counter


class Solution:
    # 递归 + memo
    def maxLength(self, arr: List[str]) -> int:
        t = []
        for e in arr:
            tt = Counter(e)
            if tt.most_common(1)[0][1] == 1:
                t.append(e)
        arr = t
        n1 = len(arr)
        self.dup_info = [[0] * n1 for _ in range(n1)]
        self.len_info = [0] * n1

        arr_set = [set(e) for e in arr]

        for i in range(n1):
            self.len_info[i] = len(arr[i])
            for j in range(n1):
                self.dup_info[i][j] = 0 if len(
                    arr_set[i].intersection(arr_set[j])) == 0 else 1

        self.memo = defaultdict(int)
        self.helper(tuple(range(n1)))

        return self.memo[tuple(range(n1))]

    def helper(self, idxs):
        if len(idxs) == 1:
            self.memo[idxs] = self.len_info[idxs[0]]
        if idxs in self.memo:
            pass
        else:
            cur_max = 0
            for idx in idxs:
                t_idx = [e for e in idxs if self.dup_info[idx][e] == 0]
                cur_max = max(cur_max, self.helper(
                    tuple(t_idx)) + self.len_info[idx])
            self.memo[idxs] = cur_max
        return self.memo[idxs]


s = Solution()
arr = ["abcdefghijklmnopqrstuvwxyz"]
print(s.maxLength(arr))
