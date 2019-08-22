from collections import defaultdict
from typing import List
import copy


class Solution:
    def countCharacters(self, words: List[str], chars: str) -> int:
        d = defaultdict(int)
        res = 0
        for e in chars:
            d[e] = d[e] + 1
        for i in range(len(words)):
            word = words[i]
            dc = copy.deepcopy(d)
            for j in range(len(word)):
                if dc[word[j]] <= 0:
                    break
                else:
                    dc[word[j]] -= 1
                if j == len(word) - 1:
                    res += len(word)
        return res


s = Solution()
words = ["hello", "world", "leetcode"]
chars = "welldonehoneyr"
print(s.countCharacters(words, chars))
