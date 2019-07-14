class Solution:
	def __init__(self):
		self.res = [[]]
		self.remained = []
		self.num_partition = 0
		self.target = 0
		self.nums_sorted = None
		self.flag = 0

	def makesquare(self, nums):
		return self.partition_Ksubset_equal(nums, 4)


	def partition_Ksubset_equal(self, nums, k):
		#边界
		self.nums_sorted = sorted(nums, reverse = True)
		self.remained = self.nums_sorted
		total = sum(nums)
		self.num_partition = k
		if total % self.num_partition != 0:
			return False
		self.target = total / self.num_partition
		self.help(0)
		return True if self.flag == 1 else False


	def help(self, next_index):
		if self.flag == 1 or self.flag == 2:
			return
		if sum(self.res[len(self.res)-1]) == self.target:
			if len(self.res) == self.num_partition:
				assert(len(self.remained) == 0)
				self.flag = 1
				return 
			else:
				self.res.append([])
				next_index = 0
		if sum(self.res[len(self.res)-1]) < self.target:
			if next_index >= len(self.remained):
				t1 = self.res[-1].pop()
				if len(self.res[-1] == 0):
					self.flag = 2
					return
				t2 = self.remained.pop(0)
				self.remained = t1 + self.remained
				self.res[-1].append(t2)
				next_index = 0
			else:
				self.res[-1].append(self.remained[next_index])
				self.remained.pop(next_index)
		else:
			t1 = self.res[-1].pop()
			t2 = self.remained.pop(0)
			if len(self.res[-1]) == 0 or t2 is None:
				self.flag = 2
				return
			self.remained = [t1] + self.remained
			self.res[-1].append(t2)
			next_index = 0
		self.help(next_index)

# dead loop
s = Solution()
nums = [3,3,3,3,4]
print(s.makesquare(nums))

