# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def balanceBST(self, root: TreeNode) -> TreeNode:
        nums = self.traverse(root)
        print(nums)
        nums = [e for e in nums if e is not None]
        new_root = self.construct(nums)
        t = self.traverse(new_root)
        return t

    def traverse(self, node):
        if node is None:
            return [None]
        else:
            return [node.val] + self.traverse(node.left) + self.traverse(node.right)

    def construct(self, nums):
        if len(nums) == 0:
            return None
        elif len(nums) == 1:
            return TreeNode(nums[0])
        else:
            mid = len(nums) // 2
            node = TreeNode(nums[mid])
            node.left = self.construct(nums[0:mid])
            node.right = self.construct(nums[mid+1:])
            return node


if __name__ == '__main__':
    s = Solution()
    node1, node2, node3, node4 = TreeNode(
        1), TreeNode(2), TreeNode(3), TreeNode(4)
    node3.right = node4
    node2.right = node3
    node1.right = node2
    root = node1
    print(s.balanceBST(root))
