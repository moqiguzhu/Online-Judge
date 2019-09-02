from collections import deque


class MaxStack(object):
    def __init__(self, eles):
        self.stack1 = deque()
        self.stack2 = deque()
        self.__build(eles)

    def __build(self, eles):
        cur_max = None
        for e in eles:
            self.stack1.append(e)
            cur_max = e if cur_max is None else max(e, cur_max)
            self.stack2.append(cur_max)

    def push(self, e):
        self.stack1.append(e)
        if len(self.stack2) == 0:
            self.stack2.append(e)
        else:
            self.stack2.append(max(self.stack2[-1], e))

    def pop(self):
        if len(self.stack1) == 0:
            return None
        else:
            self.stack2.pop()
            return self.stack1.pop()

    def get_max(self):
        return self.stack2[-1]

    def size(self):
        return len(self.stack1)


if __name__ == "__main__":
    eles = [3, 1, 2]
    ms = MaxStack(eles)
    print(ms.get_max())
    ms.push(4)
    print(ms.get_max())
    ms.pop()
    print(ms.get_max())
