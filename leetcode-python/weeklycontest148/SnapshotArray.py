class SnapshotArray:

    def __init__(self, length: int):
        self.arr = [0 for i in range(length)]
        self.changes = [{}]
        self.id = -1
        # self.records = [{i:j for i, j in enumerate(arr)}]

    def set(self, index: int, val: int) -> None:
        self.changes[-1][index] = val
        return None

    def snap(self) -> int:
        t = {}
        t.update(self.changes[-1])
        self.changes.append(t)
        self.id += 1
        return self.id

    def get(self, index: int, snap_id: int) -> int:
        if index not in self.changes[snap_id]:
            return self.arr[index]
        else:
            return self.changes[snap_id][index]


# Your SnapshotArray object will be instantiated and called as such:
length = 10
index = 5
val = 10
snap_id = 0
obj = SnapshotArray(length)
obj.set(index, val)
param_2 = obj.snap()
param_3 = obj.get(index, snap_id)
print(param_3)
