import collections

class Solution(object):
    def smallestRange(self, nums):
        """
        :type nums: List[List[int]]
        :rtype: List[int]
        """
        # sliding window
        num_sets = {}
        len1 = len(nums)
        
        for i in range(len1):
            for e in nums[i]:
                if e not in num_sets:
                    num_sets[e] = set([i])
                else:
                    num_sets[e].add(i)
        num_sets = sorted(num_sets.items())
        print(num_sets)

        # sliding window
        i, j = 0, 0
        idx_cnt = {}
        begin, end = 0, 100000000
        flag = False
        while i < len(num_sets) and j < len(num_sets) and i <= j:
            if not flag:
                for e in num_sets[j][1]:
                    idx_cnt[e] = 1 if e not in idx_cnt else idx_cnt[e] + 1
            if len(idx_cnt) == len1 and min(idx_cnt.values()) >= 1:
                t1 = num_sets[i][0]
                t2 = num_sets[j][0]
                if t2 - t1 < end - begin:
                    begin, end = t1, t2
                for e in num_sets[i][1]:
                    idx_cnt[e] = idx_cnt[e] - 1 
                i, j = i + 1, j
                flag = True
            else:
                j = j + 1
                flag = False
        return [begin, end]


s = Solution()
nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
print(s.smallestRange(nums))