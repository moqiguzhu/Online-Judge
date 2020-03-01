from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect


class Solution:
    def rankTeams(self, votes: List[str]) -> str:
        def helper():
            return [0] * len(votes[0])
        candidate_scores = defaultdict(helper)
        for vote in votes:
            for idx, candidate in enumerate(vote):
                candidate_scores[candidate][idx] = candidate_scores[candidate][idx] + 1

        def helper2(e):
            res = [-x for x in e[1]]
            res.append(e[0])
            return res
        t = sorted(candidate_scores.items(), key=lambda e: helper2(e))
        return ''.join([e[0] for e in t])


if __name__ == '__main__':
    s = Solution()
    votes = ["M", "M", "M", "M"]
    print(s.rankTeams(votes))
