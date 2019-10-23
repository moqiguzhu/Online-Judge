from RMQ_BASE import RMQ_BASE
import math


class RMQ_HYBRID(object):
    # 这里只尝试一种hybrid的方法
    # top structure 与 bottom structure 都使用 sparsetable
    # build time:O(nloglog(n)) query time:O(1)
    # block_size logn
    def __init__(self, nums):
        self.block_size = max(2, math.floor(math.log(len(nums), 2)))
        self.M = 10 ** 9 + 1
        # print(self.block_size)

        if len(nums) % self.block_size == 0:
            self.nums = nums
        else:
            self.nums = nums + \
                [self.M] * ((len(nums) // self.block_size + 1)
                            * self.block_size - len(nums))

        self.n = len(self.nums)
        assert(self.n % self.block_size == 0)
        # len(self.block_min)
        self.bottom_structure = []
        self.block_min = [self.M] * (self.n // self.block_size)
        self.blocking()

        self.top_structure = RMQ_BASE(self.block_min, 'sparse_table')

    def blocking(self):
        for i in range(self.n):
            self.block_min[i//self.block_size] = min(
                self.block_min[i//self.block_size], self.nums[i])
            if (i + 1) % self.block_size == 0:
                rmq = RMQ_BASE(
                    self.nums[i//self.block_size*self.block_size:(i//self.block_size+1)*self.block_size], 'sparse_table')
                self.bottom_structure.append(rmq)

    def query(self, i, j):
        if i // self.block_size == j // self.block_size:
            return self.bottom_structure[i//self.block_size].query(i-i//self.block_size*self.block_size, j-j//self.block_size*self.block_size)
        elif i // self.block_size + 1 == j // self.block_size:
            return min(
                self.bottom_structure[i//self.block_size].query(
                    i-i//self.block_size*self.block_size, self.block_size-1),
                self.bottom_structure[j//self.block_size].query(
                    0, j-j//self.block_size*self.block_size)
            )
        else:
            return min(
                self.bottom_structure[i//self.block_size].query(
                    i-i//self.block_size*self.block_size, self.block_size-1),
                self.bottom_structure[j//self.block_size].query(
                    0, j-j//self.block_size*self.block_size),
                self.top_structure.query(
                    i//self.block_size+1, j//self.block_size-1)
            )


nums = [31, 41, 59, 26, 53]
rmq_hybrid = RMQ_HYBRID(nums)

for i in range(len(nums)):
    for j in range(i, len(nums)):
        assert(min(nums[i:j+1]) == rmq_hybrid.query(i, j))
