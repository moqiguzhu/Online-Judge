class Solution(object):
	def prevPermOpt1(self, A):
		"""
		:type A: List[int]
		:rtype: List[int]
		"""
		if A is None or len(A) < 2:
			return A

		cur_min_reverse = [0] * len(A) + [100001]

		for idx in range(len(A)-1, -1, -1):
			cur_min_reverse[idx] = min(A[idx], cur_min_reverse[idx+1])

		can_swap = [0] * len(A)
		for idx in range(len(A)):
			if A[idx] > cur_min_reverse[idx+1]:
				can_swap[idx] = 1
			else:
				can_swap[idx] = 0
		can_swap[len(A)-1] = 0

		if sum(can_swap) == 0:
			return A
		else:
			can_swap_idx = [idx for idx, e in enumerate(can_swap) if e == 1]
			can_swap_idx_max = can_swap_idx[-1]

			cur_max = 0
			ano_idx = -1
			for idx in range(can_swap_idx_max+1, len(A)):
				if A[can_swap_idx_max] > A[idx]:
					if A[idx] > cur_max: # >
						cur_max = A[idx]
						ano_idx = idx
			# swap
			t = A[can_swap_idx_max]
			A[can_swap_idx_max] = A[ano_idx]
			A[ano_idx] = t

		return A







