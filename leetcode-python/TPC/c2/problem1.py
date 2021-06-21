import fileinput
import sys

def help1(nums):
    n = len(nums)
    if n % 2 == 1:
        return "Yes"
    else:
        a1, a2 = [], []
        for idx, e in enumerate(nums):
            if idx % 2 == 0:
                a1.append(e)
            else:
                a2.append(e)
        a1 = sorted(a1)
        a2 = sorted(a2)
        a = []
        for i in range(len(a1)):
            if a1[i] > a2[i]:
                return "No"
            if i+1 < len(a1) and a2[i] > a1[i+1]:
                return "No"
        return "Yes"

        # for i in range(len(a1)):
        #     a.append(a1[i])
        #     a.append(a2[i])
        # for i in range(len(a)-1):
        #     if a[i] <= a[i+1]:
        #         continue
        #     else:
        #         return "No"
        # return "Yes"

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
