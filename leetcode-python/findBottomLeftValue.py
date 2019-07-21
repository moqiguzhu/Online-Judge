# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def __init__(self):
        self.height = 0
        self.value = -1

    def findBottomLeftValue(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        self.help(root, 1)
        return self.value
    def help(self, node, height):
        if node.left is None and node.right is None:
            if height > self.height:
                self.value = node.val
                self.height = height
        if node.left is not None:
            self.help(node.left, height + 1)
        if node.right is not None:
            self.help(node.right, height + 1)
