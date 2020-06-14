from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest


class TreeNode(object):
    def __init__(self, parent, label, level):
        self.parent = parent
        self.label = label
        self.level = level

# TLE


class TreeAncestor:
    def __init__(self, n: int, parent: List[int]):
        self.label_node = {}
        for label, p in enumerate(parent):
            if p == -1:
                parent = None
                level = 0
            else:
                parent = self.label_node[p]
                level = self.label_node[p].level + 1
            node = TreeNode(parent, label, level)
            self.label_node[label] = node
        self.memo = {}

    def getKthAncestor(self, node: int, k: int) -> int:
        treenode = self.label_node[node]
        if k > treenode.label:
            return -1
        treek = k
        while treenode is not None and treek > 0:
            treenode = treenode.parent
            treek -= 1
            if treenode is not None and (treenode.label, treek) in self.memo:
                self.memo[(node, k)] = self.memo[(treenode.label, treek)]
                return self.memo[(treenode.label, treek)]
        res = 0
        if treek == 0 and treenode is not None:
            res = treenode.label
        else:
            res = -1
        self.memo[(node, k)] = res
        return res


if __name__ == "__main__":
    n = 50000
    parent = [-1] + list(range(49999))
    treeAncestor = TreeAncestor(n, parent)

    print(treeAncestor.getKthAncestor(41998, 40146))
