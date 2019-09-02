# 有些线上环境没有这个库 比如说leetcode
# blist.sorteddict 也是类似的数据结构，同样的问题，不是标准库，线上环境没有
from sortedcollections import SortedDict


class MyCalendarThree:

    def __init__(self):
        # self.d = SortedDict()
        self.d = {}

    def book1(self, start: int, end: int) -> int:
        self.d[start] = self.d.get(start, 0) + 1
        self.d[end] = self.d.get(end, 0) - 1
        ongoing, cur_max = 0, 0
        for k, v in self.d.items():
            cur_max = max(cur_max, ongoing+v)
            ongoing = ongoing + v
        return cur_max

    # 这种写法有问题 没有了解到问题的本质
    def book(self, start: int, end: int) -> int:
        cur_max = 0
        for k, v in self.d.items():
            if start <= k < end:
                self.d[k] = v + 1
        if start not in self.d:
            self.d[start] = 1
        for k, v in self.d.items():
            cur_max = max(cur_max, v)

        return cur_max


s = MyCalendarThree()
print(s.book(10, 20))
print(s.book(50, 60))
print(s.book(10, 40))
print(s.book(5, 15))
print(s.book(5, 10))
print(s.book(25, 55))

# Your MyCalendarThree object will be instantiated and called as such:
# obj = MyCalendarThree()
# param_1 = obj.book(start,end)
