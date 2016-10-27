package jobs;

import java.util.Arrays;
import java.util.Scanner;

public class indeed3 {
	static int N;
	static int M;
	static int[] arr;
	static int[][] arr1;
	
	static int sum = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}
		arr1 = new int[M][2];
		for(int i = 0; i < M; i++) {
			arr1[i][0] = sc.nextInt();
			arr1[i][1] = sc.nextInt();
		}
		
		int[] order = new int[M];
		for(int i = 0; i < M; i++) {
			order[i] = i;
		}
		
		help1(order, 0);
		
		System.out.println(sum);
		sc.close();
	}
	
	
	public static void help1(int[] order, int k) {
		for(int i = k; i < order.length; i++) {
			swap(order, i, k);
			help1(order, k+1);
			swap(order, k, i);
		}
		if(k == order.length-1) {
			help3(0, arr, order);
		}
	}
	
	public static void swap(int[] order, int i, int k) {
		int tmp = order[i];
		order[i] = order[k];
		order[k] = tmp;
	}
	
	public static void help3(int index, int[] arr, int[] order) {
		int left = arr1[order[index]][0];
		int right = arr1[order[index]][1];
		int sum1 = 0, sum2 = 0;
		
		for(int j = 1; j <= N; j++) {
			sum1 += arr[j] * j;
			if(j >= left && j <= right) {
				sum2 += arr[right + left - j] * j;
			} else {
				sum2 += arr[j] * j;
			}
		}
		
		if(index == M-1) {
			sum = Math.max(sum, sum1);
			sum = Math.max(sum, sum2);
		} else {
			int[] t1 = Arrays.copyOfRange(arr, 0, arr.length);
			help3(index+1, t1, order);
			int[] t2 = Arrays.copyOfRange(arr, 0, arr.length);
			for(int j = left; j <= right; j++) {
				t2[j] = arr[right + left - j];
			}
			help3(index+1, t2, order);
		}
	}
}
