package Leetcode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RelativeRanks {
    public String[] findRelativeRanks(int[] nums) {
    	for(int i = 0; i < nums.length; i++) {
    		nums[i] = nums[i] * -1;
    	}
        int[] indexes = sort(nums);
        
        String[] res = new String[nums.length];
        for(int i = 0; i < indexes.length; i++) {
        	res[indexes[i]] = (i+1) + "";
        }
        
        for(int i = 0; i < res.length; i++) {
        	if(res[i].equals("1")) {
        		res[i] = "Gold Medal";
        	}
        	if(res[i].equals("2")) {
        		res[i] = "Silver Medal";
        	}
        	if(res[i].equals("3")) {
        		res[i] = "Bronze Medal";
        	}
        }
        
        return res;
    }
    
    /**
     * Implements quicksort according to Manber's "Introduction to Algorithms".
     *
     * @param array the array of integers to be sorted
     * @param index the index into the array of integers
     * @param left the first index of the subset to be sorted
     * @param right the last index of the subset to be sorted
     */
    // @ requires 0 <= first && first <= right && right < array.length;
    // @ requires (\forall int i; 0 <= i && i < index.length; 0 <= index[i] && index[i] <
    // array.length);
    // @ requires array != index;
    // assignable index;
    public static void quickSort(/* @non_null@ */int[] array, /* @non_null@ */int[] index, int left,
        int right) {

      if (left < right) {
        int middle = partition(array, index, left, right);
        quickSort(array, index, left, middle);
        quickSort(array, index, middle + 1, right);
      }
    }

    /**
     * Implements quicksort according to Manber's "Introduction to Algorithms".
     *
     * @param array the array of doubles to be sorted
     * @param index the index into the array of doubles
     * @param left the first index of the subset to be sorted
     * @param right the last index of the subset to be sorted
     */
    // @ requires 0 <= first && first <= right && right < array.length;
    // @ requires (\forall int i; 0 <= i && i < index.length; 0 <= index[i] && index[i] <
    // array.length);
    // @ requires array != index;
    // assignable index;
    private static void quickSort(/* @non_null@ */double[] array, /* @non_null@ */int[] index,
        int left, int right) {

      if (left < right) {
        int middle = partition(array, index, left, right);
        quickSort(array, index, left, middle);
        quickSort(array, index, middle + 1, right);
      }
    }

    /**
     * Partitions the instances around a pivot. Used by quicksort and kthSmallestValue.
     *
     * @param array the array of integers to be sorted
     * @param index the index into the array of integers
     * @param l the first index of the subset
     * @param r the last index of the subset
     *
     * @return the index of the middle element
     */
    private static int partition(int[] array, int[] index, int l, int r) {

      double pivot = array[index[(l + r) / 2]];
      int help;

      while (l < r) {
        while ((array[index[l]] < pivot) && (l < r)) {
          l++;
        }
        while ((array[index[r]] > pivot) && (l < r)) {
          r--;
        }
        if (l < r) {
          help = index[l];
          index[l] = index[r];
          index[r] = help;
          l++;
          r--;
        }
      }
      if ((l == r) && (array[index[r]] > pivot)) {
        r--;
      }

      return r;
    }

    /**
     * Partitions the instances around a pivot. Used by quicksort and kthSmallestValue.
     *
     * @param array the array of doubles to be sorted
     * @param index the index into the array of doubles
     * @param l the first index of the subset
     * @param r the last index of the subset
     *
     * @return the index of the middle element
     */
    private static int partition(double[] array, int[] index, int l, int r) {

      double pivot = array[index[(l + r) / 2]];
      int help;

      while (l < r) {
        while ((array[index[l]] < pivot) && (l < r)) {
          l++;
        }
        while ((array[index[r]] > pivot) && (l < r)) {
          r--;
        }
        if (l < r) {
          help = index[l];
          index[l] = index[r];
          index[r] = help;
          l++;
          r--;
        }
      }
      if ((l == r) && (array[index[r]] > pivot)) {
        r--;
      }

      return r;
    }

    /**
     * Sorts a given array of integers in ascending order and returns an array of integers with the
     * positions of the elements of the original array in the sorted array. The sort is stable. (Equal
     * elements remain in their original order.)
     *
     * @param array this array is not changed by the method!
     * @return an array of integers with the positions in the sorted array.
     */
    public static/* @pure@ */int[] sort(int[] array) {

      int[] index = new int[array.length];
      int[] newIndex = new int[array.length];
      int[] helpIndex;
      int numEqual;

      for (int i = 0; i < index.length; i++) {
        index[i] = i;
      }
      quickSort(array, index, 0, array.length - 1);

      // Make sort stable
      int i = 0;
      while (i < index.length) {
        numEqual = 1;
        for (int j = i + 1; ((j < index.length) && (array[index[i]] == array[index[j]])); j++) {
          numEqual++;
        }
        if (numEqual > 1) {
          helpIndex = new int[numEqual];
          for (int j = 0; j < numEqual; j++) {
            helpIndex[j] = i + j;
          }
          quickSort(index, helpIndex, 0, numEqual - 1);
          for (int j = 0; j < numEqual; j++) {
            newIndex[i + j] = index[helpIndex[j]];
          }
          i += numEqual;
        } else {
          newIndex[i] = index[i];
          i++;
        }
      }
      return newIndex;
    }

    /**
     * Sorts a given array of doubles in ascending order and returns an array of integers with the
     * positions of the elements of the original array in the sorted array. NOTE THESE CHANGES: the
     * sort is no longer stable and it doesn't use safe floating-point comparisons anymore.
     * Occurrences of Double.NaN are treated as Double.MAX_VALUE
     *
     * @param array this array is not changed by the method!
     * @return an array of integers with the positions in the sorted array.
     */
    public static/* @pure@ */int[] sort(/* @non_null@ */double[] array) {

      int[] index = new int[array.length];
      array = (double[]) array.clone();
      for (int i = 0; i < index.length; i++) {
        index[i] = i + 1;
        if (Double.isNaN(array[i])) {
          array[i] = Double.MAX_VALUE;
        }
      }
      quickSort(array, index, 0, array.length - 1);
      return index;
    }

    /**
     * 原址排序，返回每个元素在整个数据集中的相对大小。不具备stable的性质。
     * 
     * @param data 待排序List
     * @param comp 比较器
     * @return 一个整型数组，表示每个元素在整个数据中的相对大小
     */
    public <E> int[] quickSort(List<E> data, Comparator<E> comp) {
      int[] indices = new int[data.size()];
      for (int i = 0; i < data.size(); i++) {
        indices[i] = i;
      }
      quicksortHelpFunc(0, data.size() - 1, data, comp, indices);

      return indices;
    }
    
    public static int random(int low, int high) {
        Random rand = new Random();
        return rand.nextInt(high - low + 1) + low;
      }

    private <E> void quicksortHelpFunc(int l, int r, List<E> data, Comparator<E> comp, int[] indices) {
      if (r <= l)
        return;
      // 引入随机因素
      int rand = random(l, r);
      E temp;
      int indexTemp;
      temp = data.get(l);
      data.set(l, data.get(rand));
      data.set(rand, temp);

      indexTemp = indices[l];
      indices[l] = indices[rand];
      indices[rand] = indexTemp;

      E pivot = data.get(l);
      int i = l + 1, j = l + 1;
      for (int k = l + 1; k <= r; k++) {
        if (comp.compare(data.get(k), pivot) > 0)
          j++;
        else {
          // =====================
          temp = data.get(i);
          data.set(i, data.get(j));
          data.set(j, temp);
          // =====================
          indexTemp = indices[i];
          indices[i] = indices[j];
          indices[j] = indexTemp;

          i++;
          j++;
        }
      }
      // 交换 注意是i-1
      temp = data.get(l);
      data.set(l, data.get(i - 1));
      data.set(i - 1, temp);

      indexTemp = indices[l];
      indices[l] = indices[i - 1];
      indices[i - 1] = indexTemp;

      quicksortHelpFunc(l, i - 2, data, comp, indices);
      quicksortHelpFunc(i, r, data, comp, indices);
    }
    
    public static void main(String[] args) {
		RelativeRanks rr = new RelativeRanks();
		int[] nums = {10,3,8,9,4};
		
		System.out.println(Arrays.toString(rr.findRelativeRanks(nums)));
	}
}
