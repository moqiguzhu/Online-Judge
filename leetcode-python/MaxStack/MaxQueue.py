from MaxStack import MaxStack


class MaxQueue(object):
    def __init__(self, eles):
        self.push_ms = MaxStack(eles)
        self.pop_ms = MaxStack([])

    def push(self, e):
        self.push_ms.push(e)

    def pop(self):
        if self.pop_ms.size() > 0:
            return self.pop_ms.pop()
        else:
            while self.push_ms.size() > 0:
                t = self.push_ms.pop()
                self.pop_ms.push(t)
            if self.pop_ms.size() > 0:
                return self.pop_ms.pop()
            else:
                return None

    def get_max(self):
        if self.pop_ms.size() < 1:
            t = self.push_ms.get_max()
        elif self.push_ms.size() < 1:
            t = self.pop_ms.get_max()
        else:
            t = max(self.pop_ms.get_max(), self.push_ms.get_max())
        return t


if __name__ == "__main__":
    eles = [3, 1, 2]
    mq = MaxQueue(eles)
    print(mq.get_max())
    mq.push(4)
    print(mq.get_max())
    mq.pop()
    print(mq.get_max())
