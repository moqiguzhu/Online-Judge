package test;


public class Kth {
    public int findKthLargest(int[] nums, int k) {
        return hhelp(nums, nums.length-k+1, 0, nums.length-1);
    }
    
    public int hhelp(int[] nums, int k, int begin, int end) {
    	if(end - begin == 0) {
    		return nums[begin];
    	}
    	int ele = nums[begin];
    	int pos = partition(nums, begin, end);
    	if(pos+1 == k) {
    		return ele;
    	} else if(pos+1 < k) {
    		return hhelp(nums, k, pos+1, end);
    	} else {
    		return hhelp(nums, k, begin, pos-1);
    	}
    }
    
    // first element as pivot
    // begin inclusive end inclusive
    public int partition(int[] nums, int begin, int end) {
    	
    	assert(end - begin > 0);
    	
    	int i = begin;
    	int j = end;
    	
    	int pivot = nums[begin];
    	while(j > i) {
    		while(i <= end && nums[i] <= pivot) i++;
    		while(j > begin && nums[j] > pivot) j--;
    		if(i <= end && j > begin && i < j) {
        		int tmp = nums[i];
        		nums[i] = nums[j];
        		nums[j] = tmp;
    		}

    	}
    	// no fucntion
    	assert(i-j == 1);
    	if(j > begin) {
        	int tmp = nums[j];
        	nums[j] = nums[begin];
        	nums[begin] = tmp;
    	}
    	
    	return j;
    }
    
    // inclusive inclusive
    public void help(int[] nums, int begin, int end) {
    	if(end - begin < 1) {
    		return;
    	}
    	
    	int pos = partition(nums, begin, end);
    	help(nums, begin, pos-1);
    	help(nums, pos+1, end);
    }
    
    public static void main(String[] args) {
		int[] nums = {22,1};
		Kth kth = new Kth();
		
		System.out.println(kth.findKthLargest(nums, 2));
		
	}
}
