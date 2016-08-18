package test;

// A B 是数组nums 中两个连续不相交的子数组，求max|sum(A) - sum(B)|
public class bd1 {
	public static void main(String[] args) {
		int[] nums1 = {0};
		System.out.println(help(nums1));
		
		
		int[] nums2 = {-1,1};
		System.out.println(help(nums2));
		
		
		int[] nums3 = {1,2,3,4};
		System.out.println(help(nums3));
		
		int[] nums4 = {-1,-1,-1,-1};
		System.out.println(help(nums4));
		
	}
	
	public static int help(int[] nums) {
		//边界
		if(nums == null || nums.length < 2) {
			return 0;
		}

		int max = nums[0];
		int min = nums[0];
		int[] max_b = new int[nums.length];
		int[] min_b = new int[nums.length];
		max_b[0] = nums[0];
		min_b[0] = nums[0];
		int[] maxEndHere1 = new int[nums.length];
		int[] minEndHere1 = new int[nums.length];
		maxEndHere1[0] = nums[0];
		minEndHere1[0] = nums[0];
		for(int i = 1; i < nums.length; i++) {
			max_b[i] = Math.max(nums[i], nums[i] + max_b[i-1]);
			min_b[i] = Math.min(nums[i], nums[i] + min_b[i-1]);
			max = Math.max(max, max_b[i]);
			min = Math.min(min, min_b[i]);
			
			maxEndHere1[i] = Math.max(max, max_b[i]);
			minEndHere1[i] = Math.min(min, min_b[i]);
		}
		
		int[] max_a = new int[nums.length];
		int[] min_a = new int[nums.length];
		max = nums[nums.length-1];
		min = nums[nums.length-1];
		max_a[nums.length-1] = nums[nums.length-1];
		min_a[nums.length-1] = nums[nums.length-1];
		int[] maxEndHere2 = new int[nums.length];
		int[] minEndHere2 = new int[nums.length];
		maxEndHere2[nums.length-1] = nums[nums.length-1];
		minEndHere2[nums.length-1] = nums[nums.length-1];
		for(int i = nums.length-2; i > -1; i--) {
			max_a[i] = Math.max(nums[i], nums[i] + max_a[i+1]);
			min_a[i] = Math.min(nums[i], nums[i] + min_a[i+1]);
			max = Math.max(max, max_a[i]);
			min = Math.min(min, min_a[i]);
			
			maxEndHere2[i] = max;
			minEndHere2[i] = min;
		}
		
		int res = -1;
		for(int i = 0; i < nums.length-1; i++) {
			int t1 = maxEndHere1[i];
			int t2 = minEndHere1[i];
			int t3 = maxEndHere2[i+1];
			int t4 = minEndHere2[i+1];
			
			res = Math.max(res, Math.max(Math.abs(t1-t4), Math.abs(t2-t3)));
		}
		
		return res;
	}
}
