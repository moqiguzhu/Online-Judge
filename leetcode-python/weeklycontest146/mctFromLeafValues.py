import math
class Solution(object):
    def mctFromLeafValues(self, arr):
        """
        :type arr: List[int]
        :rtype: int
        """
        # return self.help1(arr, 0, len(arr))
        return self.help_dp(arr)

    def help(self, arr, begin, end):
        cur_sum, cur_max = math.inf, -1

        assert(end > begin)

        if end - begin == 1:
            cur_sum, cur_max = 0, arr[begin]
        elif end - begin == 2:
            cur_sum, cur_max = arr[begin] * arr[begin+1], max(arr[begin], arr[begin+1])
        else:
            for fence in range(begin+1, end-1):
                cur_sum1, cur_max1 = self.help(arr, begin, fence)
                cur_sum2, cur_max2 = self.help(arr, fence, end)
                if cur_sum1 + cur_sum2 + cur_max1*cur_max2 < cur_sum:
                    cur_sum = cur_sum1 + cur_sum2 + cur_max1*cur_max2
                    cur_max = max(cur_max1, cur_max2)
        return cur_sum, cur_max

    # tle
    def help1(self, arr, begin, end):
        cur_sum, cur_max = math.inf, -1

        assert(end > begin)

        if end - begin == 1:
            cur_sum = 0
        elif end - begin == 2:
            cur_sum = arr[begin] * arr[begin+1]
        else:
            for fence in range(begin+1, end): #end 不是end-1 下标把自己搞晕了
                cur_sum1 = self.help1(arr, begin, fence)
                cur_sum2 = self.help1(arr, fence, end)
                if cur_sum1 + cur_sum2 + max(arr[begin:fence]) * max(arr[fence:end]) < cur_sum:
                    cur_sum = cur_sum1 + cur_sum2 + max(arr[begin:fence]) * max(arr[fence:end])
        return cur_sum

    def help_dp(self, arr):
        self.memo = {}
        def dp(i,j):
            if j<=i:
                return 0
            if (i,j) not in self.memo:
                res = float('inf')
                for k in range(i+1,j+1):
                    res = min(dp(i,k-1)+dp(k,j)+max(arr[i:k])*max(arr[k:j+1]),res)
                self.memo[(i,j)] = res
            return self.memo[(i,j)]
        return dp(0,len(arr)-1)

        
s = Solution()
arr = [15,13,5,3,15]
print(s.mctFromLeafValues(arr))
# print(s.memo)
