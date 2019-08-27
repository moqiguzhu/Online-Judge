from collections import defaultdict
from typing import List


class TrieNode():
    def __init__(self):
        self.children = defaultdict(TrieNode)
        self.idx = -1


class Trie():
    def __init__(self, words):
        self.root = TrieNode()
        self.build(words)

    def insert(self, word, i):
        node = self.root
        for ii, w in enumerate(word):
            node = node.children[w]
            if ii == len(word) - 1:
                node.children[w].idx = i

    def build(self, words):
        for i, word in enumerate(words):
            self.insert(word, i)

    def search(self, word):
        node = self.root
        for w in word:
            node = node.children.get(w, None)
            if node is None:
                return False
        return True

    def prefix_subset(self, prefix):
        node = self.root
        for i, w in enumerate(prefix):
            node = node.children.get(w, None)
            if node is None:
                return []
        return self.get_idx_subset(node)

    def get_idx_subset(self, node):
        if node.idx != -1:
            return [node.idx]
        else:
            t = []
            for k, v in node.children.items():
                t = t + self.get_idx_subset(v)
            return t


words = ['pop', 'happy']
trie = Trie(words)
print(trie.prefix_subset('x'))

# 后缀树不是把字符串反过来建字典树
# 后缀树存的是所有的后缀以及后缀对应的begin_index 关键是，后缀树是一个字符串一棵树


class WordFilter1:

    def __init__(self, words: List[str]):
        self.prefix_trie = Trie(words)
        words_reversed = []
        for word in words:
            words_reversed.append(word[::-1])
        self.suffix_trie = Trie(words_reversed)

    def f(self, prefix: str, suffix: str) -> int:
        prefix_subset = set(self.prefix_trie.prefix_subset(prefix))
        suffix_subset = set(self.suffix_trie.prefix_subset(suffix))
        t = prefix_subset.intersection(suffix_subset)
        return -1 if len(t) == 0 else max(t)

        # Your WordFilter object will be instantiated and called as such:
        # obj = WordFilter(words)
        # param_1 = obj.f(prefix,suffix)


class WordFilter(object):

    def __init__(self, words):
        from collections import defaultdict
        self.prefixes = defaultdict(set)
        self.suffixes = defaultdict(set)
        self.weights = {}
        for index, word in enumerate(words):
            prefix, suffix = '', ''
            for char in [''] + list(word):
                prefix += char
                self.prefixes[prefix].add(word)
            for char in [''] + list(word[::-1]):
                suffix += char
                self.suffixes[suffix[::-1]].add(word)
            self.weights[word] = index

    def f(self, prefix, suffix):
        weight = -1
        # x & y = x.intersection(y)
        for word in self.prefixes[prefix].intersection(self.suffixes[suffix]):
            if self.weights[word] > weight:
                weight = self.weights[word]
        return weight


words = ["pop"]
s = WordFilter(words)
print(s.f('op', ''))
