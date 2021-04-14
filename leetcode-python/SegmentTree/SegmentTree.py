# 支持求和、求最大值
class SegmentTree:
    def __init__(self, data):
        n = len(data)
        self.data = data
        # self.tree = [0] * (2 * n)  # start from 1
        self.tree = [0] * n * 4     # enough memory 竟然还有证明 https://www.desgard.com/algo/docs/part3/ch02/2-segment-tree-combat/
        self.add_tree = [0] * n * 4
        if n >= 1:  # empty init
            self.__build(1, 0, n-1)

    def __build(self, node, start, end):
        if start == end:
            self.tree[node] = self.data[start]
        else:
            mid = start + ((end - start) >> 1)
            self.__build(2*node, start, mid)
            self.__build(2*node+1, mid+1, end)
            self.__push_up(node)
    
    def __push_up(self, node):
        self.tree[node] = self.tree[2*node] + self.tree[2*node+1]

    # m是区间长度
    def __push_down(self, node, m):
        if self.add_tree[node] > 0:
            self.add_tree[node << 1] = self.add_tree[node]
            self.add_tree[node << 1 + 1] = self.add_tree[node]
            self.tree[node << 1] += (m - (m >> 1)) * self.add_tree[node]
            self.tree[node << 1 + 1] += (m >> 1) * self.add_tree[node]
            self.add_tree[node] = 0

    # 单点更新
    def __update_dot(self, node, start, end, idx, val):
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
    
    def update_dot(self, idx, val):
        self.__update_dot(1, 0, len(self.data)-1, idx, val)

    def __update_range(self, node, start, end, l, r, val):
        if start >= l and end <= r:
            self.add_tree[node] += val
            self.tree[node] += (end - start + 1) * val
            # 所以在query的时候需要pushdown lazy思想
            return
        
        # r - l + 1 是 2的次方
        self.__push_down(node, end - start + 1)
        
        mid = start + ((end - start) >> 1)

        if mid >= l:
            self.__update_range(2*node, start, mid, l, r, val)
        if mid < r:
            self.__update_range(2*node+1, mid + 1, end, l, r, val)
        
        self.__push_up(node)
    
    def update_range(self, l, r, val):
        return self.__update_range(1, 0, len(self.data)-1, l, r, val)

    def __query(self, node, start, end, l, r):
        if r < start or l > end:
            return 0
        if l <= start and r >= end:
            return self.tree[node]
        self.__push_down(node, end-start+1)
        mid = start + ((end - start) >> 1)
        p1, p2 = 0, 0
        if mid >= l:
            p1 = self.__query(2*node, start, mid, l, r)
        if mid < r:
            p2 = self.__query(2*node+1, mid+1, end, l, r)
        return p1 + p2  # 改成max就是求最大值

    def query(self, l, r):
        return self.__query(1, 0, len(self.data)-1, l, r)


# debug
data = [1, 8, 3, 4, 7, 1, 6, 2]
st = SegmentTree(data)
print(st.query(0,5))
st.update_range(0, 5, 4)
print(st.query(0,5))
print(st.query(0,1))
print(st.query(0,7))
