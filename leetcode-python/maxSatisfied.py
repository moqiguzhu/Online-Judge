class Solution(object):
	def __init__(self):
		self.cur_sum = [0]
		self.cur_satisfied_sum = [0]

	def maxSatisfied(self, customers, grumpy, X):
		"""
		:type customers: List[int]
		:type grumpy: List[int]
		:type X: int
		:rtype: int
		"""
		if len(customers) < 1:
			return 0
		if X >= len(grumpy):
			return sum(customers)

		for idx, e in enumerate(customers):
			self.cur_sum.append(self.cur_sum[-1] + e)
			if grumpy[idx] == 0:
				self.cur_satisfied_sum.append(self.cur_satisfied_sum[-1] + e)
			else:
				self.cur_satisfied_sum.append(self.cur_satisfied_sum[-1] + 0)

		# sliding window
		res_max = 0
		for idx in range(0, len(customers)-X+1):
			cur_res = self.cur_sum[idx+X] - self.cur_sum[idx] + self.cur_satisfied_sum[idx] - self.cur_satisfied_sum[idx+X] + self.cur_satisfied_sum[len(customers)]
			res_max = cur_res if cur_res > res_max else res_max

		return res_max

s = Solution()
customers = [1,0,1,2,1,1,7,5]
grumpy = [0,1,0,1,0,1,0,1]
X = 3

print(s.maxSatisfied(customers, grumpy, X))