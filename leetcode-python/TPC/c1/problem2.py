import fileinput
import sys
from collections import Counter

def help1(nums):
    cnt = 0
    memo = {}
    t = set(nums)
    c = Counter(nums)
    for e in nums:
        if c[e] > 2:
            return 0
        elif e in memo:
            pass
        else:
            flag = False
            for i in range(1, e // 2+1):
                if (i not in t) and ((e - i) not in t) and (i != e-i):
                    memo[e] = 1
                    flag = True
                    break
            if not flag:
                memo[e] = 0
        cnt += memo[e]
    return cnt
                


if __name__ == "__main__":
    # print(" ".join([str(e) for e in range(1, 10**5+1, 1)]))
    n, num = 0, 0
    nums = []
    idx = 0
    res = []
    for line in fileinput.input():
        if idx == 0:
            n = int(line.strip())
        elif idx % 2 == 1:
            num = int(line.strip())
        else:
            nums = [int(e) for e in line.strip().split()]
            res.append(help1(nums))
        idx += 1
        if idx == 2*n + 1:
            for e in res:
                print(e)
            sys.exit(0)
