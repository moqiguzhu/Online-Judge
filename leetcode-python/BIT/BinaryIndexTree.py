# 注意这里的idx都是从 1 开始算
class BinaryIndexTree:
    def __init__(self, n):
        self.sz = n
        self.vals = [0] * (n + 1)

    def update(self, idx, delta):
        "add c to the value at index idx"
        while idx <= self.sz and idx > 0:
            self.vals[idx] += delta
            idx += idx & (-idx)

    def accumulate(self, idx):
        "get sum from the start to the index of idx"
        ret = 0
        while idx > 0:
            ret += self.vals[idx]
            idx -= idx & (-idx)
        return ret

    def get(self, idx):
        return self.vals[idx+1]

    def range_sum(self, start, end):
        "Calculate a[start], a[start+1], ... a[end]"
        return self.accumulate(end) - self.accumulate(start - 1)
