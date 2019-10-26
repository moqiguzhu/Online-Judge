from collections import deque
from RMQ_BASE import RMQ_BASE
import math
from collections import defaultdict


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
        self.block_size = max(2, math.floor(math.log(len(nums), 2)) // 4 + 1)
        self.M = 10 ** 9 + 1
        print(self.block_size)

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


if __name__ == "__main__":
    print('开始测试RMQ_Fischer_Heun:')
    nums = [32, 45, 16, 18, 9, 33, 100, -1]
    fh = Fischer_Heun(nums)

    # 测试cartesian number计算是否准确
    root = fh.cartesian_tree(nums)
    t = fh.inorder_traversal(root)
    for idx, e in enumerate(t):
        assert(e == nums[idx])

    print(fh.cartesian_tree_number(nums))

    for i in range(len(nums)):
        for j in range(i, len(nums)):
            assert(min(nums[i:j+1]) == fh.query(i, j))
