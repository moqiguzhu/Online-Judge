import fileinput
import sys

def help1(nums, n, k):
    flag = True
    for i in range(1, len(nums)):
        if nums[i] != nums[i-1]:
            flag = False
            break
    if flag:
        return (k, nums[0])
    nums = nums * 3
    cnt = 0
    for i, e in enumerate(nums):
        if i == 0:
            cnt += 1
            continue
        if e == nums[i-1]:
            cnt += 1
            if cnt == k:
                return (i+1, e)
        else:
            cnt = 1
    return "INF"


if __name__ == "__main__":
    num_cases = 0
    k, n = 0, 0
    nums = []
    idx = 0
    res = []
    for line in fileinput.input():
        if idx == 0:
            num_cases = int(line.strip())
        elif idx % 2 == 1:
            n, k = [int(e) for e in line.strip().split()]
        else:
            nums = [int(e) for e in line.strip().split()]
            res.append(help1(nums, n, k))
        idx += 1
        if idx == num_cases * 2 + 1:
            print(res)
            for e in res:
                print(e)
            sys.exit(0)
