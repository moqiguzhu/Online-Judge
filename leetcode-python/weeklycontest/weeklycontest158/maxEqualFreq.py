from typing import List
from collections import defaultdict


class Solution:
    # 败给细节 需要考虑的情况太多 解题框架没有搭建好
    # 还是要采取从简单到难的策略
    def maxEqualFreq1(self, nums: List[int]) -> int:
        ma, mi = -1, 10**9
        num_cnt = defaultdict(int)
        one_set = set()
        ma_set = set()
        for idx, num in enumerate(nums):
            num_cnt[num] = num_cnt[num] + 1
            if num_cnt[num] > ma:
                ma_set = set([num_cnt[num]])
            if num_cnt[num] == ma:
                ma_set.add(num)
            ma = max(ma, num_cnt[num])
            # !!! to-do
            mi = min(mi, num_cnt[num])
            if num_cnt[num] == 1:
                one_set.add(num)
            if num_cnt[num] == 2:
                one_set.remove(num)
            if len(one_set) == len(num_cnt):
                res = idx + 1
                continue
            if len(num_cnt) == 1:
                res = idx + 1
                continue
            if (ma == mi + 1 and len(ma_set) == 1):
                res = idx + 1
                continue
            if (len(one_set) == 1):
                if self.second_min(list(num_cnt.values())) == ma:
                    res = idx + 1

        return res

    def second_min(self, l):
        # print(l)
        if len(l) < 2:
            return None
        mi = min(l[0], l[1])
        second_min = max(l[0], l[1])

        for e in l[2:]:
            if e <= mi:
                second_min = mi
                mi = e
        return second_min

    # all elements appear exact once.
    # all elements appear max_F times, except one appears once.
    # all elements appear max_F-1 times, except one appears max_F

    # 逻辑思维 总结归纳能力太强了
    # https://leetcode.com/problems/maximum-equal-frequency/discuss/403628/Easy-Python-Solution-Concise-10-lines-Explained-O(N)
    def maxEqualFreq(self, nums: List[int]) -> int:
        cnt, freq, maxF, res = defaultdict(int), defaultdict(int), 0, 0
        for i, num in enumerate(nums):
            cnt[num] += 1
            freq[cnt[num]-1] -= 1
            freq[cnt[num]] += 1
            maxF = max(maxF, cnt[num])
            if maxF*freq[maxF] == i or (maxF-1)*(freq[maxF-1]+1) == i or maxF == 1:
                res = i + 1
        return res


s = Solution()
nums = [1, 1, 1, 2, 2, 2]
print(s.maxEqualFreq(nums))
