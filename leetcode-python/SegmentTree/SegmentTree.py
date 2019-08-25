class SegmentTree:
    def __init__(self, data):
        n = len(data)
        self.data = data
        # self.tree = [0] * (2 * n)  # start from 1
        self.tree = [0] * n * 4     # enough memory
        if n >= 1:  # empty init
            self.__build(1, 0, n-1)

    def __build(self, node, start, end):
        if start == end:
            self.tree[node] = self.data[start]
        else:
            mid = start + ((end - start) >> 1)
            self.__build(2*node, start, mid)
            self.__build(2*node+1, mid+1, end)
            self.tree[node] = self.tree[2*node] + self.tree[2*node+1]

    def __update(self, node, start, end, idx, val):
        if start == end:
            self.data[idx] += val
            self.tree[node] += val
        else:
            mid = start + ((end - start) >> 1)
            if start <= idx <= mid:
                self.__update(2*node, start, mid, idx, val)
            else:
                self.__update(2*node+1, mid+1, end, idx, val)
            self.tree[node] = self.tree[2*node] + self.tree[2*node+1]

    def update(self, idx, val):
        self.__update(1, 0, len(self.data)-1, idx, val)

    def __query(self, node, start, end, l, r):
        if r < start or l > end:
            return 0
        if l <= start and r >= end:
            return self.tree[node]
        mid = start + ((end - start) >> 1)
        p1 = self.__query(2*node, start, mid, l, r)
        p2 = self.__query(2*node+1, mid+1, end, l, r)
        return p1 + p2

    def query(self, l, r):
        return self.__query(1, 0, len(self.data)-1, l, r)


# debug
data = list(range(100000))
st = SegmentTree(data)
# print(st.data)
# print(st.tree)
