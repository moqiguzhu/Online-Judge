from typing import List


class Solution:
    def checkStraightLine(self, coordinates: List[List[int]]) -> bool:
        if coordinates is None or len(coordinates) < 3:
            return True
        x1, y1, x2, y2 = coordinates[0][0], coordinates[0][1], coordinates[1][0], coordinates[1][1]
        for x3, y3 in coordinates[2:]:
            if (x3-x1)*(y3-y2) != (y3-y1)*(x3-x2):
                return False
        return True


s = Solution()
coordinates = [[1, 1], [2, 2], [3, 4], [4, 5], [5, 6], [7, 7]]
print(s.checkStraightLine(coordinates))
