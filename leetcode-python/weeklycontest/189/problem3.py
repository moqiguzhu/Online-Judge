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
    def peopleIndexes(self, favoriteCompanies: List[List[str]]) -> List[int]:
        if favoriteCompanies is None or len(favoriteCompanies) < 2:
            return favoriteCompanies

        word_idx = defaultdict(set)
        for idx, e1 in enumerate(favoriteCompanies):
            for e2 in e1:
                word_idx[e2].add(idx)

        res = []
        for idx, e1 in enumerate(favoriteCompanies):
            for i, e2 in enumerate(e1):
                if i == 0:
                    t = word_idx[e2]
                else:
                    t = t.intersection(word_idx[e2])
            if len(t) == 1:
                res.append(idx)
        return res


class TestStringMethods(unittest.TestCase):
    def test_peopleIndexes(self):
        s = Solution()

        # case1
        favoriteCompanies1 = [["leetcode", "google", "facebook"], [
            "google", "microsoft"], ["google", "facebook"], ["google"], ["amazon"]]
        self.assertEqual(s.peopleIndexes(favoriteCompanies1), [0, 1, 4])

        # case2
        favoriteCompanies2 = [["leetcode", "google", "facebook"], [
            "leetcode", "amazon"], ["facebook", "google"]]
        self.assertEqual(s.peopleIndexes(favoriteCompanies2), [0, 1])

        # case3
        favoriteCompanies3 = [["leetcode"], [
            "google"], ["facebook"], ["amazon"]]
        self.assertEqual(s.peopleIndexes(favoriteCompanies3), [0, 1, 2, 3])


if __name__ == '__main__':
    unittest.main()
