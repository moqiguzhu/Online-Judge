class Solution(object):
    def alphabetBoardPath(self, target):
        """
        :type target: str
        :rtype: str
        """
        # 先左 后上 然后 右 下
        board = [
            ['a', 'b', 'c', 'd', 'e'],
            ['f', 'g', 'h', 'i', 'j'],
            ['k', 'l', 'm', 'n', 'o'],
            ['p', 'q', 'r', 's', 't'],
            ['u', 'v', 'w', 'x', 'y'],
            ['z', '#', '#', '#', '#']
        ]
        board_idx = {}
        for i in range(6):
            for j in range(5):
                board_idx[board[i][j]] = (i,j)
        print(board_idx)
        i = 0
        res = ''
        cur_idx = (0,0)
        while i < len(target):
            t = target[i]
            idx = board_idx[t]
            if cur_idx[1] > idx[1]:
                res += 'L' * (cur_idx[1] - idx[1])
                cur_idx = (cur_idx[0], idx[1])
            if cur_idx[0] > idx[0]:
                res += 'U' * (cur_idx[0] - idx[0])
                cur_idx = (idx[0], cur_idx[1])
            if cur_idx[1] < idx[1]:
                res += 'R' * (idx[1] - cur_idx[1])
                cur_idx = (cur_idx[0], idx[1])
            if cur_idx[0] < idx[0]:
                res += 'D' * (idx[0] - cur_idx[0])
                cur_idx = (idx[0], cur_idx[1])
            
            if cur_idx == idx:
                res += '!'
            i += 1
        return res

s = Solution()
target = 'leet'
print(s.alphabetBoardPath(target))