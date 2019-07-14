# 先把函数定义好
class Solution:
	def __init__(self):
		self.num_partition = 0
		self.target = 0
		self.flag = 0

	def makesquare(self, nums):
		return self.partition_Ksubset_equal(nums, 4)


	def partition_Ksubset_equal(self, nums, k):
		total = sum(nums)
		self.num_partition = k
		if total % self.num_partition != 0:
			return False
		self.target = total / self.num_partition
		used = [0] * len(nums)
		return self.help(nums, used, 0, 0, self.num_partition)



	def help(self, nums, used, cur_sum, start, k):
		if self.flag == 1:
			return True
		if k == 1:
			self.flag = 1
			return True
		if cur_sum == self.target:
			self.help(nums, used, 0, 0, k-1)
		for i in range(start, len(nums)):
			if used[i]:
				continue
			else:
				used[i] = True
				if self.help(nums, used, cur_sum + nums[i], i+1, k):
					return True
				used[i] = False
		return False


# dead loop
s = Solution()
nums = [5,5,5,5,4,4,4,4,3,3,3,3]
print(s.makesquare(nums))

