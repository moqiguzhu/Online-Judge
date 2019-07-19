# 熟悉debug的过程
class Solution(object):
    # 与找最小公倍数的思路一致
    # 先找到最大公约数
    def shortestCommonSupersequence(self, str1, str2):
        """
        :type str1: str
        :type str2: str
        :rtype: str
        """
        comm_str = self.lcs(str1, str2)

        idx = 0
        idx1 = []
        for i in range(len(str1)):
            if idx >= len(comm_str):
                break
            if str1[i] == comm_str[idx]:
                idx = idx + 1
                idx1.append(i)
        idx = 0
        idx2 = []
        for i in range(len(str2)):
            if idx >= len(comm_str):
                break
            if str2[i] == comm_str[idx]:
                idx = idx + 1
                idx2.append(i)
        assert(len(idx1) == len(idx2))
        res = ''
        for i in range(len(idx1)):
            if i == 0:
                res = str1[0:idx1[i]] + str2[0:idx2[i]] + comm_str[i]
            else:
                res = res + str1[idx1[i-1]+1:idx1[i]] + str2[idx2[i-1]+1:idx2[i]] + comm_str[i]
        res = res + str1[idx1[-1]+1:len(str1)] + str2[idx2[-1]+1:len(str2)]
        return res
            
    def lcs(self, A, B):
        n, m = len(A), len(B)
        dp = [["" for _ in range(m + 1)] for _ in range(n + 1)]
        for i in range(n):
            for j in range(m):
                if A[i] == B[j]:
                    dp[i + 1][j + 1] = dp[i][j] + A[i]
                else:
                    dp[i + 1][j + 1] = max(dp[i + 1][j], dp[i][j + 1], key=len)
        return dp[-1][-1]
    
    # bugged
    def longestCommonSubsequence(self, str1, str2):
        dp_str = [['' for j in range(len(str2))] for i in range(len(str1))]
        dp = [[0 for j in range(len(str2))] for i in range(len(str1))]
        str2_idx = self.help(str1, str2)
        for i in range(len(str1)):
            for j in range(len(str2)):
                if i == 0 and j == 0:
                    dp[i][j] = 0 if str1[i] != str2[j] else 1
                    dp_str[i][j] = '' if str1[i] != str2[j] else str1[i]
                    continue
                t = str2_idx[j]

                if len(t) == 0:
                    if j == 0:
                        dp[i][j] = 0
                        dp_str[i][j] = ''
                    else:
                        dp[i][j] = dp[i][j-1]
                        dp_str[i][j] = dp_str[i][j-1]
                else:
                    k = 0
                    for x in reversed(t):
                        if x <= i:
                            k = x
                            break
                    if j == 0:
                        if k-1 < 0:
                            dp[i][j] = 0
                            dp_str[i][j] = ''
                        else:
                            dp[i][j] = 1
                            dp_str[i][j] = str2[j]
                        continue
                    if k-1 < 0:
                        if 1 > dp[i][j-1]: # python的下标没有溢出的说法 特别注意
                            dp[i][j] = 1
                            dp_str[i][j] = str2[j]
                        else:
                            dp[i][j] = dp[i][j-1]
                            dp_str[i][j] = dp_str[i][j-1]
                    elif dp[k-1][j-1] + 1 > dp[i][j-1]:
                        dp[i][j] = dp[k-1][j-1] + 1
                        dp_str[i][j] = dp_str[k-1][j-1] + str2[j]
                    else:
                        dp[i][j] = dp[i][j-1]
                        dp_str[i][j] = dp_str[i][j-1]
        return dp_str[len(str1)-1][len(str2)-1]

        
                

    def help(self, str1, str2):
        str2_idx = [[] for i in range(len(str2))]
        # 这种写法要小心 * 实际上是浅拷贝
        # str2_idx = [[]] * len(str2)  
        for i in range(len(str2)):
            for j in range(0, len(str1)):
                if str1[j] == str2[i]:
                    # str2_idx[i].append(j)
                    str2_idx[i] = str2_idx[i] + [j]
        return str2_idx
                
# 先测试lcs
# 无底洞  lcs实现逻辑太复杂  不知道还有多少漏洞在里面
s = Solution()
# 这个case通不过
str1 = "bbabacaa"
str2 = "cccababab"
print(s.help(str1, str2))
print(s.lcs(str1, str2))
print(s.shortestCommonSupersequence(str1, str2))