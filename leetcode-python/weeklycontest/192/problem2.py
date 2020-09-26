from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest


def cmp(e1, e2):
    if e1[0] > e2[0] or (e1[0] == e2[0] and e1[1] > e2[1]):
        return 1
    elif e1[0] == e2[0] and e1[1] == e2[1]:
        return 0
    else:
        return -1


class Solution:
    def getStrongest(self, arr: List[int], k: int) -> List[int]:
        arr = sorted(arr)
        i, j = 0, len(arr) - 1
        median = arr[j // 2]
        res = []
        while len(res) < k:
            if abs(arr[j] - median) >= abs(median - arr[i]):
                res.append(arr[j])
                j -= 1
            else:
                res.append(arr[i])
                i += 1
        return res

    # 思路不对
    def getStrongest1(self, arr: List[int], k: int) -> List[int]:
        n = len(arr)
        assert(n >= k)
        if k == 0:
            return []
        if k == n:
            return arr

        kth = n - (n-1) // 2
        median = self.findKthMax(arr, kth, cmp=lambda x, y: x - y)

        new_arr = [(abs(e - median), e) for e in arr]

        kmax = self.findKthMax(new_arr, k, cmp)

        res = []
        t = []
        for x, y in new_arr:
            if cmp((x, y), kmax) > 0:
                res.append(y)
            if cmp((x, y), kmax) == 0:
                t.append(y)
        if len(res) == k:
            return res
        else:
            return res + [t[0]] * (k - len(res))

    # all elements are same 算法会退化
    # 3-sets quicksort的实际实现
    # 标准算法尽量用标准库函数
    def findKthMax(self, l, k, cmp):
        if k > len(l):
            return None
        # 随机生成一个下标key,并获取下标对应的数组值keyv
        key = np.random.randint(0, len(l))
        keyv = l[key]

        # 遍历数组（刨除key），sl数组是小于keyv的值，bl数组是大于等于keyv的值
        sl = [i for i in l[:key] + l[key + 1:] if cmp(i, keyv) < 0]
        bl = [i for i in l[:key] + l[key + 1:] if cmp(i, keyv) >= 0]

        # 如果bl的长度恰好是k-1,那么说明keyv就是第k大的数
        if len(bl) == k-1:
            return keyv
        # 如果bl的长度大于等于k,说明第k大的数在bl中，迭代findKthMax函数，找出bl中第k大的数
        elif len(bl) >= k:
            return self.findKthMax(bl, k, cmp)
        # 如果bl的长度小于k-1,说明第k大的数在sl中，因为bl中已经有len(bl)个比目标值大的数，加上keyv本身，所以要找出sl中第（k-len(bl)-1）大的数
        else:
            return self.findKthMax(sl, k-len(bl)-1, cmp)


if __name__ == '__main__':
    s = Solution()
    arr = [6, -3, 7, 2, 11]
    k = 3
    print(s.getStrongest(arr, k))
