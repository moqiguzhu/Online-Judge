import fileinput
import sys
import bisect

# 答案错误
# def help1(nums):
#     c, a1, a2 = nums
#     a = [a1, a2]
#     for i in range(2, c, 1):
#         a.append(a[i-1] + a[i-2])
#     k = 63
#     res = 0
#     for i in range(k, -1, -1):
#         if a[c-1] >= (1<<i):
#             # print(1<<i, 1<<(i+1))
#             res += help2(a, 0, len(a)-1, 1<<i, 1<<(i+1))
#     return res

# def help2(a, begin, end, x1, x2):
#     if end < begin:
#         return 1
#     if end == begin:
#         if a[begin] >= x1 and a[begin] < x2:
#             return 0
#         else:
#             return 1
#     mid = (begin + end) >> 1
#     if a[mid] >= x1 and a[mid] < x2:
#         return 0
#     elif a[mid] < x1:
#         return help2(a, mid+1, end, x1, x2)
#     else:
#         return help2(a, begin, mid-1, x1, x2)


def help3(nums):
    c, a1, a2 = nums
    res = 0
    s = set()
    for i in range(0, 12, 1):
        t = 1 << i
        if t >= a1+a2:
            break
        else:
            if a1 & t == 0 and a2 & t == 0:
                s.add(t) 

    for i in range(2, c, 1):
        a1, a2 = a2, a1 + a2
        for e in list(s):
            if a2 & e > 0:
                s.remove(e)
                if len(s) == 0:
                    return 0
                if 1 in s and len(s) == 1 and a1 % 2 == 0 and a2 % 2 == 0:
                    return 1
    return len(s)


if __name__ == "__main__":
    n = 0
    nums = []
    idx = 0
    res = []
    for line in fileinput.input():
        if idx == 0:
            n = int(line.strip())
        else:
            nums = [int(e) for e in line.strip().split()]
            res.append(help3(nums))
        idx += 1

        if idx == n + 1:
            for e in res:
                print(e)
            sys.exit(0)
