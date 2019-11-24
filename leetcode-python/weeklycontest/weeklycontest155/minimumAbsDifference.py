from typing import List


class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        if arr is None or len(arr) < 2:
            return []
        arr = sorted(arr)
        min_dist = 10**7
        res = []
        for i in range(len(arr)-1):
            min_dist = min(min_dist, arr[i+1]-arr[i])
        for i in range(len(arr)-1):
            if arr[i+1]-arr[i] == min_dist:
                res.append([arr[i], arr[i+1]])
        return res


s = Solution()
arr = [3, 8, -10, 23, 19, -4, -14, 27]
print(s.minimumAbsDifference(arr))
