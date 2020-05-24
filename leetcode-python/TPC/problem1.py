import fileinput
import numpy as np


def help1(n, m, num):
    if m == 0:
        return 0
    t = [-1] * 100001
    for e in num:
        t[e] = e
    t = [e for e in t if e != -1]
    t = t[0:m*2]
    res = 0
    for idx in range(m):
        res += t[idx] * t[2*m-idx-1]
    return res


if __name__ == '__main__':
    index = 0
    nms = []
    nums = []
    T = 0
    for line in fileinput.input():
        if index == 0:
            T = int(line.strip())
        elif index % 2 == 1:
            n, m = [int(e) for e in line.strip().split()]
            nms.append((n, m))
        else:
            nums.append(np.fromstring(line.strip(), dtype=int, sep=' '))
        index += 1
        if index == 2 * T + 1:
            break
    for idx in range(len(nms)):
        n, m = nms[idx]
        num = nums[idx]
        print(help1(n, m, num))
