from typing import List


class Solution:
    def jobScheduling(self, startTime: List[int], endTime: List[int], profit: List[int]) -> int:
        # 背包问题，典型的dp
        jobs = zip(startTime, endTime, profit)
        jobs = sorted(jobs, lambda x: x[1])
        max_end_time = jobs[-1][1] + 1
        dp = [0] * max_end_time

        idx, cur_end_time = 0, jobs[0][1]
        for i in range(jobs[-1][1]):
            if i == cur_end_time:
                dp[i] = max(dp[i-endTime]+jobs[idx][2], dp[i-1])
                idx += 1
                cur_end_time = jobs[idx][1]
            else:
                dp[i] = dp[i-1]
        return dp[max_end_time]
