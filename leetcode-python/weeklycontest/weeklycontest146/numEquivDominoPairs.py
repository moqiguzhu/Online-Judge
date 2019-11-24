class Solution(object):
    def numEquivDominoPairs(self, dominoes):
        """
        :type dominoes: List[List[int]]
        :rtype: int
        """
        if len(dominoes) <= 2:
            return 0

        pair_cnt = {}
        res = 0
        for i in range(len(dominoes)):
            num1, num2 = dominoes[i][0], dominoes[i][1]
            t1, t2 = (num1, num2), (num2, num1)
            if t1 not in pair_cnt:
                pair_cnt[t1] = 1
            else:
                res += pair_cnt[t1]
                pair_cnt[t1] += 1
            if t2 in pair_cnt and num1 != num2:
                res += pair_cnt[t2]

        return res

s = Solution()
dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
print(s.numEquivDominoPairs(dominoes))
