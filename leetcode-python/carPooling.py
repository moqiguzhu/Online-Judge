from operator import itemgetter, attrgetter

class Solution(object):
    def carPooling(self, trips, capacity):
        """
        :type trips: List[List[int]]
        :type capacity: int
        :rtype: bool
        """
        if capacity is None or len(trips) == 0:
            return True
        points = []
        for x in trips:
            # 上车是1 下车是0
            # 先下后上
            points.append((x[0], x[1], 1))
            points.append((x[0], x[2], 0))

        points.sort(key = itemgetter(1,2))
        cur_cap = capacity
        for x in points:
            if x[2] == 1:
                cur_cap = cur_cap - x[0]
            else:
                cur_cap = cur_cap + x[0]
            if cur_cap < 0:
                return False
        
        return True


s = Solution()
trips = [[2,1,5],[3,5,7]]
capacity = 3
print(s.carPooling(trips, capacity))
