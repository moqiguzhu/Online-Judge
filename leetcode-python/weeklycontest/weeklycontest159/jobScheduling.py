from typing import List
import bisect
from collections import defaultdict


class Solution:
    def jobScheduling(self, startTime: List[int], endTime: List[int], profit: List[int]) -> int:
        # 背包问题，典型的dp
        jobs = zip(startTime, endTime, profit)
        jobs = sorted(jobs, key=lambda x: x[1])
        dp = defaultdict(int)

        print(jobs)
        # 我有一个更好的idea nlogn复杂度
        i = 0
        cur_max = 0
        t = []
        for s, e, p in jobs:
            if i == 0:
                dp[e] = p
                cur_max = dp[e]
            else:
                idx = bisect.bisect_right(t, s)
                if idx < 1:
                    tt = 0
                else:
                    tt = dp[t[idx-1]]
                dp[e] = max(cur_max, tt + p)
                cur_max = dp[e]
            t.append(e)
            i += 1
        return cur_max


s = Solution()
startTime = [1, 1, 1]
endTime = [2, 3, 4]
profit = [5, 6, 4]
print(s.jobScheduling(startTime, endTime, profit))
