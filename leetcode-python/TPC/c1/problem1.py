import fileinput
import sys

def help1(nums):
    a, b, c = 0, 0, 0
    for e in nums:
        if e == 0:
            a += 1
        elif e > 0:
            b += 1
        else:
            c += 1
    if abs(b-c) - a < 2:
        return "Yes"
    else:
        return "No"

if __name__ == "__main__":
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
