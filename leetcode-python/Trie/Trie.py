# 似乎没有多少使用后缀树的场景
from collections import defaultdict


class TrieNode():
    def __init__(self):
        self.children = defaultdict(TrieNode)


class Trie():
    def __init__(self, words):
        self.root = TrieNode()
        self.build(words)

    def insert(self, word):
        node = self.root
        for w in word:
            node = node.children[w]

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
