
class Solution:
    def dayOfYear(self, date: str) -> int:
        if date is None or len(date) != 10:
            return 0
        year = int(date[0:4])
        month = int(date[5:7])
        day = int(date[8:10])

        if (year % 4 == 0 and year % 100 != 0) or (year % 4 == 0 and year % 400 == 0):
            t = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        else:
            t = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        days = 0
        for i in range(month-1):
            days += t[i]
        days += day

        return days


s = Solution()
date = '2019-02-10'
print(s.dayOfYear(date))
