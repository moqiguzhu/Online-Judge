class Solution(object):
	def __init__(self):
		self.res = 0
		self.part_sum = []
		self.mid_res = {}

	def findTargetSumWays(self, nums, S):
		for idx, e in enumerate(nums):
			if len(self.part_sum) == 0:
				self.part_sum.append(sum(nums))
			self.part_sum.append(self.part_sum[-1] - e)
		# print(self.part_sum)

		# self.help(nums, 0, 0, S)
		self.help_memo1(nums, 0, 0, S)
		print(self.mid_res)
		return self.mid_res[len(nums)][S]

	# tle
	def help(self, nums, cur_sum, idx, target):
		if idx == len(nums):
			if cur_sum == target:
				self.res = self.res + 1 # acc
			else:
				pass # reject
			return
		if cur_sum + self.part_sum[idx] >= target and cur_sum - self.part_sum[idx] <= target:
			self.help(nums, cur_sum + nums[idx], idx + 1, target)
			self.help(nums, cur_sum - nums[idx], idx + 1, target)

	# wrong answer
	def help_memo(self, nums, cur_sum, idx, target):
		if idx == len(nums):
			if cur_sum == target:
				self.res = self.res + 1 # acc
				if idx - 1 not in self.mid_res:
					self.mid_res[idx-1] = {}
				self.mid_res[idx-1][cur_sum-nums[-1]] = 1
				self.mid_res[idx-1][cur_sum+nums[-1]] = 1
			else:
				pass # reject
			return
		if idx in self.mid_res and target - cur_sum in self.mid_res[idx]:
			self.res = self.res + self.mid_res[idx][target-cur_sum]
			if idx - 1 not in self.mid_res:
				self.mid_res[idx-1] = {}
			self.mid_res[idx-1][target - cur_sum - nums[idx-1]] = self.mid_res[idx][target-cur_sum]
			self.mid_res[idx-1][target - cur_sum + nums[idx-1]] = self.mid_res[idx][target-cur_sum]
			return
		else:
			self.help_memo(nums, cur_sum + nums[idx], idx + 1, target)
			self.help_memo(nums, cur_sum - nums[idx], idx + 1, target)

	def help_memo1(self, nums, cur_sum, idx, target):
		if idx == len(nums):
			return 1 if cur_sum == target else 0
		if idx in self.mid_res and cur_sum in self.mid_res[idx]:
			return self.mid_res[idx][cur_sum]
		if idx not in self.mid_res:
			self.mid_res[idx] = {}
		self.mid_res[idx][cur_sum] = self.help_memo1(nums, cur_sum-nums[idx], idx+1, target) + self.help_memo1(nums, cur_sum+nums[idx], idx+1, target)
		return self.mid_res[idx][cur_sum]

	def dp_help(self, nums, S):
		pass



s = Solution()
nums = [9,7,0,3,9,8,6,5,7,6]
print(s.findTargetSumWays(nums, 2))