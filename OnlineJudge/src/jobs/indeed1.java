package jobs;

import java.util.Scanner;

public class indeed1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		
		int count = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int i = 1; i < arr.length-1; i++) {
			if(arr[i] > arr[i-1] && arr[i] > arr[i+1]) {
				count++;
			}
		}
		
		System.out.println(count);
		sc.close();
	}
}
