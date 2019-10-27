from collections import defaultdict
from typing import List


from collections import deque
import math
from collections import defaultdict
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
        elif self.method == 'precompute_all_index':
            self.pair_min_index = [[self.M] *
                                   (self.n+1) for _ in range(self.n+1)]
            self.precompute_all_index()
        else:
            pass

    def precompute_all(self):
        for i in range(1, self.n+1, 1):
            for j in range(i, self.n+1, 1):
                self.pair_min[i][j] = min(
                    self.pair_min[i][j-1], self.nums[j-1])
    # 存储的全部是index
    # 计算的是一类数组的RMQ信息

    def precompute_all_index(self):
        for i in range(1, self.n+1, 1):
            curmin_idx = i-1
            for j in range(i, self.n+1, 1):
                if i != j and self.nums[self.pair_min_index[i][j-1]] > self.nums[j-1]:
                    curmin_idx = j-1
                self.pair_min_index[i][j] = curmin_idx

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

    def query(self, i, j, nums=[]):
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
        # 因为存储的是index信息
        # 所以在查询的时候需要把当前实际数组带进来
        # 调用方保证传进来的nums与发起调用的RMQ结构能够对应上
        elif self.method == 'precompute_all_index':
            return nums[self.pair_min_index[i+1][j+1]]
        else:
            pass


class TreeNode(object):
    def __init__(self, num, left=None, right=None, parent=None):
        self.num = num
        self.left = left
        self.right = right
        self.parent = parent


# RMQ 问题的终极目标
# O(n) 预处理时间复杂度 O(1)查询复杂度


class Fischer_Heun(object):
    # 一种特殊的hybrid方法
    # top structure采用sparse table
    # bottom structure采用precompute all
    # block_size = 1/4*logn
    # 这里面的trick是对每一个block，首先用一个O(b)的算法检测当前block的笛卡尔树与之前block的笛卡尔树有没有重叠，如果有，就不用precompute all
    def __init__(self, nums):
        if len(nums) <= 2:
            self.nums = nums
        else:
            self.block_size = max(2, math.floor(
                math.log(len(nums), 2)) // 4 + 1)
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

            self.cartesiannum_rmq = defaultdict(lambda x: -1)

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
                t = self.cartesian_tree_number(
                    self.nums[i+1-self.block_size:i+1])
                if t in self.cartesiannum_rmq:
                    rmq = self.cartesiannum_rmq[t]
                else:
                    # 花里胡哨
                    # !!! self.nums[i+1-self.block_size:i+1
                    rmq = RMQ_BASE(
                        self.nums[i+1-self.block_size:i+1], 'precompute_all_index')
                    self.cartesiannum_rmq[t] = rmq
                self.bottom_structure.append(rmq)

    def query(self, i, j):
        if len(self.nums) <= 2:
            return min(self.nums[i:j+1])
        else:
            i_begin, i_end = i // self.block_size * \
                self.block_size, (i//self.block_size+1)*self.block_size
            j_begin, j_end = j // self.block_size * \
                self.block_size, (j//self.block_size+1)*self.block_size
            i_nums, j_nums = self.nums[i_begin:i_end], self.nums[j_begin:j_end]

            relative_i, relative_j = i-i//self.block_size * \
                self.block_size, j-j//self.block_size*self.block_size

            if i // self.block_size == j // self.block_size:
                return self.bottom_structure[i//self.block_size].query(relative_i, relative_j, i_nums)
            elif i // self.block_size + 1 == j // self.block_size:
                return min(
                    self.bottom_structure[i//self.block_size].query(
                        relative_i, self.block_size-1, i_nums),
                    self.bottom_structure[j//self.block_size].query(
                        0, relative_j, j_nums)
                )
            else:
                return min(
                    self.bottom_structure[i//self.block_size].query(
                        relative_i, self.block_size-1, i_nums),
                    self.bottom_structure[j//self.block_size].query(
                        0, relative_j, j_nums),
                    self.top_structure.query(
                        i//self.block_size+1, j//self.block_size-1)
                )

    # cartesian tree是一个min heap，但是是一个不规则的min heap，不适合排序
    def cartesian_tree(self, block=[]):
        stack = deque()
        for num in block:
            new_node = TreeNode(num)
            t = None
            while len(stack) != 0 and stack[-1].num > num:
                t = stack.pop()
            tt = None if len(stack) == 0 else stack[-1]

            new_node.left = t
            new_node.parent = tt
            # 关键
            if tt is not None:
                tt.right = new_node
            stack.append(new_node)
        root = stack[0]
        return root

    def cartesian_tree_number(self, block):
        stack = deque()
        operations = []
        for num in block:
            new_node = TreeNode(num)
            t = None
            while len(stack) != 0 and stack[-1].num > num:
                t = stack.pop()
                operations.append(0)
            tt = None if len(stack) == 0 else stack[-1]

            new_node.left = t
            new_node.parent = tt
            # 关键
            if tt is not None:
                tt.right = new_node
            stack.append(new_node)
            operations.append(1)
        while len(stack) > 0:
            stack.pop()
            operations.append(0)
        return ''.join([str(e) for e in operations])

    def inorder_traversal(self, root):
        t = []
        if root.left is not None:
            t.extend(self.inorder_traversal(root.left))
        t.append(root.num)
        if root.right is not None:
            t.extend(self.inorder_traversal(root.right))

        return t


class Solution:
    def sumSubarrayMins(self, A: List[int]) -> int:
        if A is None:
            return 0
        if len(A) == 1:
            return A[0]

        # tle
        # rmq = RMQ_BASE(A, 'precompute_all')
        # tle
        # rmq = RMQ_BASE(A, 'sparse_table')

        rmq = Fischer_Heun(A)
        sum = 0
        M = 10**9 + 7
        for i in range(len(A)):
            for j in range(i, len(A), 1):
                sum = (sum + rmq.query(i, j)) % M
        return sum

    # 是个O(n)的问题
    # def sumSubarrayMins(self, A: List[int]) -> int:
    #     pass


s = Solution()
A = [59, 91]
print(s.sumSubarrayMins(A))
