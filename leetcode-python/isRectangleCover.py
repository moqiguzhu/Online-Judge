from typing import List
from PriorityHeap import PriorityHeap


class Solution:
    def isRectangleCover(self, rectangles: List[List[int]]) -> bool:
        if rectangles is None or len(rectangles) < 2:
            return True
        res = 0
        n = len(rectangles)

        for i in range(n):
            res += (rectangles[i][1]-rectangles[i][0]-1) * \
                (rectangles[i][3]-rectangles[i][2]-1)
            if bl[0] <= rectangles[i][0] and bl[1] <= rectangles[i][1]:
                bl = (rectangles[i][0], rectangles[i][1])
            if tr[0] >= rectangles[i][2] and [1] >= rectangles[i][3]:
                tr = (rectangles[i][2], rectangles[i][3])

        if res != (tr[0]-bl[0]-1) * (tr[1]-bl[1]-1):
            return False

        pass
