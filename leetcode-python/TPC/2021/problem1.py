import fileinput
import sys

def help1(num):
    if num >= 1:
        return "You are the future of Tencent!"
    else:
        return "Good luck and Enjoy TPC!"

if __name__ == "__main__":
    n, num = 0, 0
    idx = 0
    for line in fileinput.input():
        if idx == 0:
            n = int(line.strip())
        else:
            num = int(line.strip())
            print(help1(num))
        idx += 1
        if idx == n + 1:
            sys.exit(0)
