from collections import deque
from collections import defaultdict


class LFUCache:

    def __init__(self, capacity: int):
        self.l = []
        self.d = defaultdict(lambda: -1)
        self.k_f = defaultdict(lambda: 0)
        self.k_idx = defaultdict(lambda: 0)
        self.c = capacity

    def get(self, key: int) -> int:
        if self.c == 0:
            return -1
        if key in self.k_f:
            self.k_f[key] += 1
            idx = self.k_idx[key]
            t = idx-1
            while t >= 0 and self.k_f[self.l[t+1]] >= self.k_f[self.l[t]]:
                tt = self.l[t]
                self.l[t] = self.l[t+1]
                self.l[t+1] = tt
                self.k_idx[self.l[t]] = t
                self.k_idx[self.l[t+1]] = t+1
                t -= 1
        return self.d[key]

    def put(self, key: int, value: int) -> None:
        if self.c == 0:
            return
        if key not in self.d:
            if len(self.l) == self.c:
                # print(self.l)
                # print(self.d)
                k = self.l.pop(self.c-1)
                self.l.insert(self.c-1, key)
                self.d.pop(k)
                self.d[key] = value
                self.k_f.pop(k)
                self.k_f[key] = 0
                self.k_idx.pop(k)
                self.k_idx[key] = self.c-1
            else:
                self.l.append(key)
                self.d[key] = value
                self.k_f[key] = 0
                self.k_idx[key] = len(self.l) - 1
                t = len(self.l) - 2
                while t >= 0 and self.k_f[self.l[t+1]] >= self.k_f[self.l[t]]:
                    tt = self.l[t]
                    self.l[t] = self.l[t+1]
                    self.l[t+1] = tt
                    self.k_idx[self.l[t]] = t
                    self.k_idx[self.l[t+1]] = t+1
                    t -= 1
        else:
            self.d[key] = value
            self.k_f[key] += 1
            idx = self.k_idx[key]
            t = idx-1
            while t >= 0 and self.k_f[self.l[t+1]] >= self.k_f[self.l[t]]:
                tt = self.l[t]
                self.l[t] = self.l[t+1]
                self.l[t+1] = tt
                self.k_idx[self.l[t]] = t
                self.k_idx[self.l[t+1]] = t+1
                t -= 1


# Your LFUCache object will be instantiated and called as such:
cache = LFUCache(3)
# cache.put(1, 1)
# cache.put(2, 2)
# print(cache.get(1))
# cache.put(3, 3)
# print(cache.get(2))
# print(cache.get(3))
# cache.put(4, 4)
# print(cache.get(1))
# print(cache.get(3))
# print(cache.get(4))
cache.put(1, 1)
cache.put(2, 2)
cache.put(3, 3)
cache.put(4, 4)
print(cache.get(4))
print(cache.get(3))
