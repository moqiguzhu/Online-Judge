from typing import List
from collections import defaultdict
import copy
from collections import Counter


class Solution:
    # dp 有点问题 不适合用dp解决问题
    # 代码写的也有点问题
    # 确定解题思路之前,把example带入思路
    def maxScoreWords1(self, words: List[str], letters: List[str], score: List[int]) -> int:
        pass

    # brute force
    # tle
    def maxScoreWords(self, words: List[str], letters: List[str], score: List[int]) -> int:
        c_cnt = defaultdict(int)
        for c in letters:
            c_cnt[c] += 1
        word_val = defaultdict(int)
        for idx, word in enumerate(words):
            for c in word:
                word_val[word+'_'+str(idx)] += score[ord(c)-97]
        words = [word+'_'+str(idx) for idx, word in enumerate(words)]
        print(words)
        print(word_val)

        return self.help(c_cnt, words, word_val)

    def help(self, remain, words, word_val):
        res = 0
        for idx, word in enumerate(words):
            t = self.check(remain, word)
            if t:
                res = max(res, self.help(
                    t, words[:idx] + words[idx+1:], word_val) + word_val[word])
        return res

    def check(self, remain, word):
        t = Counter(word[:word.find('_')])
        tt = copy.deepcopy(remain)
        for k, v in t.items():
            if remain[k] < v:
                return False
            else:
                tt[k] -= v
        return tt


s = Solution()
words = ["add", "dda", "bb", "ba", "add"]
letters = ["a", "a", "a", "a", "b", "b", "b",
           "b", "c", "c", "c", "c", "c", "d", "d", "d"]
score = [3, 9, 8, 9, 0, 0, 0, 0, 0, 0, 0, 0,
         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
print(s.maxScoreWords(words, letters, score))
