from collections import defaultdict, deque, Counter
from typing import List
import math
import copy


class TrieNode():
    def __init__(self):
        self.children = defaultdict(TrieNode)
        self.idx = None


class Trie():
    def __init__(self, words):
        self.root = TrieNode()
        self.build(words)

    def insert(self, word, idx):
        node = self.root
        for w in word:
            if node.idx is None:
                node.idx = idx
            node = node.children[w]
        if node.idx is None:
            node.idx = idx

    def build(self, words):
        for idx, word in enumerate(words):
            self.insert(word, idx)

    def search(self, word):
        node = self.root
        for w in word:
            node = node.children.get(w, None)
            if node is None:
                return False
        return True

    def search_pref(self, prefix):
        node = self.root
        for w in prefix:
            node = node.children.get(w, None)
            if node is None:
                return None
        return node.idx


class Solution:
    def suggestedProducts(self, products: List[str], searchWord: str) -> List[List[str]]:
        products = sorted(products)
        trie = Trie(products)

        res = []
        for i in range(1, len(searchWord)+1):
            idx = trie.search_pref(searchWord[0:i])
            if idx is None:
                res.append([])
            else:
                t = []
                for j in range(idx, min(idx+3, len(products)), 1):
                    if products[j][0:i] == searchWord[0:i]:
                        t.append(products[j])
                res.append(t)
        return res


s = Solution()
products = ["bags", "baggage", "banner", "box", "cloths"]
searchWord = "bags"
print(s.suggestedProducts(products, searchWord))
