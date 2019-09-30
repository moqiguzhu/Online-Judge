# 有了AC自动机的基础之后，手撸KMP算法
# 因为只有一个pattern，这个pattern的suffix link用一个数组就能表示了


class KMP(object):
    def __init__(self, pattern):
        self.suffix_link_arr = [0] * len(pattern)
        self.pattern = pattern
        self.construct_suffix_link_arr()

    def construct_suffix_link_arr(self):
        for idx, w in enumerate(self.pattern):
            if idx == 0:
                self.suffix_link_arr[idx] = -1
            elif idx == 1:
                self.suffix_link_arr[idx] = 0
            else:
                t = self.suffix_link_arr[idx-1]
                while True:
                    if self.pattern[t+1] == self.pattern[idx]:
                        self.suffix_link_arr[idx] = t+1
                        break
                    if t == 0:
                        self.suffix_link_arr[idx] = 0
                        break
                    t = self.suffix_link_arr[t]
        print(self.suffix_link_arr)

    def match(self, s):
        res = []
        if s is None or len(s) < 1:
            return res
        p_idx = 0  # pattern的idx
        for idx, w in enumerate(s):
            if w == self.pattern[p_idx]:
                p_idx += 1
            else:
                while True:
                    if p_idx == 0:
                        break
                    p_idx = self.suffix_link_arr[p_idx-1] + 1
                    if w == self.pattern[p_idx]:
                        p_idx += 1
                        break
            if p_idx == len(self.pattern):
                res.append((idx-len(self.pattern)+1, self.pattern))
                # 找到匹配之后需要回溯 注意p_idx和suffix_link_arr里面存储的东西稍有不同
                p_idx = self.suffix_link_arr[p_idx-1] + 1
        return res


pattern = 'abc'
kmp = KMP(pattern)
s = 'dsadhshashdababc'
print(kmp.match(s))
