from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect
import inspect
import unittest


class Solution:
    def __init__(self):
        self.memo = {}
        # debug的话, states可以改成list
        self.states = set()
    # bfs + recursion + memo

    def catMouseGame(self, graph: List[List[int]]) -> int:
        t = self.bfs(graph, 2, 1, 1)
        return t

    # turn = 1 mouse turn
    # turn = 2 cat turn
    def bfs(self, graph, cat, mouse, turn):
        if (cat, mouse, turn) in self.memo:
            return self.memo[(cat, mouse, turn)]
        else:
            self.states.add((cat, mouse, turn))
        if mouse == 0:
            res = 1
        elif cat == mouse:
            res = 2
        elif turn == 1:
            t = []
            for m in graph[mouse]:
                if (cat, m, 2) in self.states:
                    t.append(0)  # 直接给0有问题 !!! discussion里面也有提到
                else:
                    t.append(self.bfs(graph, cat, m, 2))
            tt = [1 if e == 1 else 0 for e in t]
            if max(tt) == 1:
                res = 1
            else:
                res = min(t)
        elif turn == 2:
            t = []
            for c in graph[cat]:
                if c != 0:
                    if (c, mouse, 1) in self.states:
                        t.append(0)
                    else:
                        t.append(self.bfs(graph, c, mouse, 1))
            # if (cat, mouse, turn) == (7, 6, 2):
            #     print(self.states)
            #     print(self.memo)
            #     print(t)
            if max(t) == 2:
                res = 2
            else:
                res = min(t)
        else:
            pass
        self.memo[(cat, mouse, turn)] = res
        self.states.remove((cat, mouse, turn))
        return res


if __name__ == "__main__":
    s = Solution()
    # 所有dfs + memo的方法都过不去这个case
    # 画图出来就明白了
    graph = [[6], [4], [9], [5], [1, 5], [3, 4, 6], [0, 5, 10], [8, 9, 10], [7], [2, 7], [6, 7]]                                                                                                                                                                                                                                              16, 17, 18], [4, 5, 6, 7, 9, 18], [3, 6, 9, 12, 19], [4, 6, 11, 15, 17, 19], [6, 9, 15, 17, 18, 19], [4, 6, 8, 15, 19], [0, 3, 5, 6, 8, 9, 12, 13, 14, 16, 19], [0, 4, 7, 8, 9, 15, 17, 18, 19], [5, 6, 7, 9, 2, 12, 13, 16], [0, 10, 5, 9, 2, 13, 16], [1, 6, 8, 11, 12, 13, 14, 15, 16]]
    print(s.catMouseGame(graph))
