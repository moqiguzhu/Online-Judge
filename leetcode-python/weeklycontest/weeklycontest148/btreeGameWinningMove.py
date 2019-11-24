# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def __init__(self):
        self.c = [0, 0]

    def count(self, root, x):
        if root is None:
            return 0
        else:
            t1, t2 = self.count(root.left, x), self.count(root.right, x)
            if root.val == x:
                self.c[0], self.c[1] = t1, t2
            return t1 + t2 + 1

    def btreeGameWinningMove(self, root: TreeNode, n: int, x: int) -> bool:
        return self.count(root, x) / 2 < max(max(self.c), n-sum(self.c)-1)
