# 后缀树的实现都在这里
import sys

# !!! 难受
try:
    from RMQ import Fischer_Heun
except:
    sys.path.append("..")
    from RMQ import Fischer_Heun


class SuffixArray():
    def __init__(self):
        self.rmq = Fischer_Heun([1, 2, 3])

    def dc3(self): pass

    def radix_sort(self): pass

    def merge(self): pass


sa = SuffixArray()
