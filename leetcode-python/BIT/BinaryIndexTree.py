# 外部看到的idx从0开始  保证体验一致
# https://gist.githubusercontent.com/m00nlight/cf89e14d93ed69c204f8/raw/4ad5dc94d05a2538679a5cbed3cb98ebbe27aa84/gistfile1.py
# also called Fenwick Tree


class BinaryIndexTree:
    def __init__(self, n):
        self.sz = n
        self.vals = [0] * (n + 1)

    def update(self, idx, delta):
        "add c to the value at index idx"
        idx += 1
        while idx <= self.sz and idx > 0:
            self.vals[idx] += delta
            idx += idx & (-idx)

    def accumulate(self, idx):
        "get sum from the start to the index of idx"
        idx += 1
        ret = 0
        while idx > 0:
            ret += self.vals[idx]
            idx -= idx & (-idx)
        return ret

    def range_sum(self, start, end):
        "Calculate a[start], a[start+1], ... a[end]"
        return self.accumulate(end) - self.accumulate(start - 1)
