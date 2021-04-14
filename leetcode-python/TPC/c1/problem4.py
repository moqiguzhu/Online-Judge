import fileinput
import sys

# 二分查找
def help1(nums, d, l, r):
    pass
                


if __name__ == "__main__":
    n, num = 0, 0
    nums = []
    idx = 0
    res = []
    for line in fileinput.input():
        if idx == 0:
            n = int(line.strip())
        elif idx % 2 == 1:
            num, d = [int(e) for e in line.strip().split()]
        else:
            nums = [int(e) for e in line.strip().split()]
            res.append(help1(nums))
        idx += 1

        if idx == 2*n + 1:
            for e in res:
                print(e)
            sys.exit(0)
