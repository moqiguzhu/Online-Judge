from SegmentTree import SegmentTree
from typing import List


class NumArray:

    def __init__(self, nums: List[int]):
        self.st = SegmentTree(nums)

    def update(self, i: int, val: int) -> None:
        self.st.update(i, val - self.st.data[i])

    def sumRange(self, i: int, j: int) -> int:
        return self.st.query(i, j)


# Your NumArray object will be instantiated and called as such:
nums = [9, -8]
obj = NumArray(nums)
obj.update(0, 3)
print(obj.sumRange(1, 1))
print(obj.sumRange(0, 1))
obj.update(1, -3)
print(obj.sumRange(0, 1))
