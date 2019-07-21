# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

def printTreeNode(root):
    print(root.val,flush = True, end = ',')
    if root.left is not None:
        printTreeNode(root.left)
    else:
        print('null', flush = True, end = ',')
    if root.right is not None:
        printTreeNode(root.right)
    else:
        print('null', flush = True, end = ',')
# 递归来做
class Solution(object):
    def recoverFromPreorder(self, S):
        """
        :type S: str
        :rtype: TreeNode
        """
        return self.help(S, 0)
    
    def help(self, s, height):
        begin, end = -1, -1
        idx1, idx2 = -1, -1
        flag = True

        i = 0
        while i < len(s):
            e = s[i]
            if flag:
                t = height
                while t < len(s) and s[t] != '-':
                    t = t + 1
                root_val = int(s[height:t])
                begin, end = -1, -1
                flag = False
                i = t
                continue
            if e == '-':
                if begin == -1:
                    begin, end = i, i
                else:
                    end = i
            else:
                if end - begin + 1 == height+1 and begin != -1 and end != -1:
                    if idx1 == -1:
                        idx1 = end
                    else:
                        idx2 = end
                begin, end = -1, -1
            i = i + 1

        assert(flag is False)

        if idx1 == -1 and idx2 == -1:
            return TreeNode(root_val)
        else:
            if idx1 != -1:
                if idx2 != -1:
                    left_str = s[idx1-height:idx2-height]
                else:
                    left_str = s[idx1-height:len(s)]
                left = self.help(left_str, height + 1)
                assert(left is not None)
            if idx2 != -1:
                right_str = s[idx2-height:len(s)]
                right = self.help(right_str, height + 1)
            else:
                right = None
    
        root = TreeNode(root_val)
        root.left = left
        root.right = right
        
        return root

s = Solution()
S = "5-5--1---6-10"
root = s.recoverFromPreorder(S)
printTreeNode(root)
print()