from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect

# Definition for singly-linked list.


class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

# Definition for a binary tree node.


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def __init__(self):
        self.head = None

    def isSubPath(self, head: ListNode, root: TreeNode) -> bool:
        self.head = head

        return self.helper(head, root)

    # 思路有问题
    # 需要回溯
    # !!! 有一个办法是记住回溯点 回溯点就是==时的left、right
    # 但是这样效率可能会成为一个问题
    # 一旦找到答案, 马上退出
    def helper(self, curhead, curroot):
        if curhead is None:
            return True
        if curroot is None:
            return False
        if curhead.val == curroot.val:
            return self.helper(curhead.next, curroot.left) or self.helper(curhead.next, curroot.right)
        else:
            if self.head.val == curroot.val:
                return (self.helper(self.head.next, curroot.left)) or (self.helper(self.head.next, curroot.right))
            else:
                return (self.helper(self.head, curroot.left)) or (self.helper(self.head, curroot.right))


if __name__ == '__main__':
    s = Solution()
    head = ListNode(4)
    head.next = ListNode(2)
    head.next.next = ListNode(8)

    root = TreeNode(1)
    node1 = TreeNode(4)
    node2 = TreeNode(4)
    root.left, root.right = node1, node2

    node3 = TreeNode(2)
    node4 = TreeNode(1)
    node3.left = node4
    node1.right = node3

    node5, node6, node7, node8, node9 = TreeNode(
        2), TreeNode(6), TreeNode(8), TreeNode(1), TreeNode(3)
    node2.left, node5.left, node5.right, node7.left, node7.right = node5, node6, node7, node8, node9

    print(s.isSubPath(head, root))
