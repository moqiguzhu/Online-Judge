from collections import defaultdict


class RMQ_BASE(object):
    def __init__(self, nums, method='sparse_table', block_size=50):
        assert(nums is not None)
        assert(len(nums) > 1)

        self.nums = nums
        self.n = len(nums)
        self.M = 10 ** 9 + 1
        self.method = method
        self.block_size = block_size

        if self.method == 'precompute_all':
            self.pair_min = [[self.M] * (self.n+1) for _ in range(self.n+1)]
            self.precompute_all()
        elif self.method == 'precompute_none':
            pass
        elif self.method == 'blocking':
            # if block_size = 'O(n^0.5)' O(n) build time, O(n^0.5) query time
            self.block_min = [self.M] * (self.n // self.block_size + 1)
            self.blocking()
        elif self.method == 'sparse_table':
            # most significant bit
            self.k = self.n.bit_length() - 1
            self.sparse_table = [[self.M] * (self.k+1) for _ in range(self.n)]
            self.precompute_sparse_table()
        else:
            pass

    def precompute_all(self):
        for i in range(1, self.n+1, 1):
            for j in range(i, self.n+1, 1):
                self.pair_min[i][j] = min(
                    self.pair_min[i][j-1], self.nums[j-1])

    def blocking(self):
        for i in range(self.n):
            self.block_min[i//self.block_size] = min(
                self.block_min[i//self.block_size], self.nums[i])

    def precompute_sparse_table(self):
        # k = 0
        # k is O(log(self.n))
        for i in range(self.n):
            self.sparse_table[i][0] = self.nums[i]
        for k in range(1, self.k+1, 1):
            gap = 1 << k
            for i in range(self.n):
                if i + gap <= self.n:
                    self.sparse_table[i][k] = min(
                        self.sparse_table[i][k-1], self.sparse_table[i+(gap >> 1)][k-1])

    def hybrid(self):
        pass

    def query(self, i, j):
        if self.method == 'precompute_all':
            return self.pair_min[i+1][j+1]
        elif self.method == 'sparse_table':
            k = (j - i + 1).bit_length() - 1
            return min(self.sparse_table[i][k], self.sparse_table[j-(1 << k)+1][k])
        elif self.method == 'precompute_none':
            res = self.M
            for idx in range(i, j+1, 1):
                res = res if res < self.nums[idx] else self.nums[idx]
            return res
        elif self.method == 'blocking':
            if i//self.block_size == j//self.block_size:
                return min(self.nums[i:j+1])
            else:
                return min(self.block_min[i//self.block_size+1:j//self.block_size] +
                           self.nums[i:self.block_size*(i//self.block_size+1)] +
                           self.nums[self.block_size*(j//self.block_size):j+1])


# 测试程序
nums = [31, 41, 59, 26, 53, 58, 97, 93]
rmq1 = RMQ_BASE(nums, 'blocking', 2)
rmq2 = RMQ_BASE(nums, 'precompute_none')
rmq3 = RMQ_BASE(nums, 'precompute_all')
rmq4 = RMQ_BASE(nums, 'sparse_table')
rmqs = [rmq1, rmq2, rmq3, rmq4]
for rmq in rmqs:
    for i in range(len(nums)):
        for j in range(i, len(nums), 1):
            assert(min(nums[i:j+1]) == rmq.query(i, j))
