from typing import List
from collections import defaultdict


class TrieNode():
    def __init__(self):
        self.children = defaultdict(TrieNode)
        self.is_leaf = 0


class Trie():
    def __init__(self, words):
        self.root = TrieNode()
        self.build(words)

    def insert(self, word):
        node = self.root
        for ii, w in enumerate(word):
            node = node.children[w]
            if ii == len(word) - 1:
                node.is_leaf = 1

    def build(self, words):
        for word in words:
            self.insert(word)

    def search(self, word):
        node = self.root
        for w in word:
            node = node.children.get(w, None)
            if node is None:
                return False
        return True

    def search_pref(self, prefix):
        pass


class Solution:
    def __init__(self):
        self.n1, self.n2 = -1, -1
        self.trie = Trie([])
        self.res = []
        self.flag = False
        self.res_set = set()

    # 花里胡哨的  直接dfs看看
    # TLE
    def findWords2(self, board: List[List[str]], words: List[str]) -> List[str]:
        if board is None or len(board) < 1 or len(board[0]) < 1:
            return []
        self.n1 = len(board)
        self.n2 = len(board[0])
        for word in words:
            used = [[0] * self.n2 for i in range(self.n1)]
            self.flag = False
            for i in range(self.n1):
                for j in range(self.n2):
                    if word[0] == board[i][j]:
                        used[i][j] = 1
                        self.helper(board, used, i, j, board[i][j], word, 1)
                        used[i][j] = 0
        return self.res

    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        if board is None or len(board) < 1 or len(board[0]) < 1:
            return []
        if len(words) < 1:
            return []
        self.n1 = len(board)
        self.n2 = len(board[0])
        trie = Trie([])
        for word in words:
            trie.insert(word)
        used = [[0] * self.n2 for i in range(self.n1)]
        for i in range(self.n1):
            for j in range(self.n2):
                if board[i][j] in trie.root.children:
                    used[i][j] = 1
                    self.dfs(board, used, i, j,
                             board[i][j],  trie.root.children[board[i][j]])
                    used[i][j] = 0

        return list(self.res_set)

    def dfs(self, board, used, i, j, cur_word, p):
        if p.is_leaf:
            self.res_set.add(cur_word)
        for t1, t2 in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
            x, y = i+t1, j+t2
            if 0 <= x < self.n1 and 0 <= y < self.n2 and not used[x][y] and board[x][y] in p.children:
                used[x][y] = 1
                self.dfs(board, used, x, y, cur_word +
                         board[x][y], p.children[board[x][y]])
                used[x][y] = 0

    def helper(self, board, used, i, j, cur_word, target_word, k):
        if self.flag:
            return
        if cur_word == target_word:
            self.res.append(target_word)
            self.flag = True
            return
        for t1, t2 in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
            x, y = i+t1, j+t2
            if 0 <= x < self.n1 and 0 <= y < self.n2 and not used[x][y] and target_word[k] == board[x][y]:
                used[x][y] = 1
                self.helper(board, used, x, y, cur_word +
                            board[x][y], target_word, k+1)
                used[x][y] = 0


s = Solution()
board = [["a", "b"], ["a", "a"]]
words = ["aba", "baa", "bab", "aaab", "aaa", "aaaa", "aaba"]
print(s.findWords(board, words))
